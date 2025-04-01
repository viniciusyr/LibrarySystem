package dao;

import database.DB;
import enums.BookStatus;
import exception.DBException;
import factory.BookFactory;
import model.Author;
import model.Book;
import model.Genre;
import utils.ParseUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BookDaoJDBC implements BookDao{

    private static final ParseUtil parseUtil = ParseUtil.getInstance();
    private static final BookGenreDaoJDBC bookGenreDao = BookGenreDaoJDBC.getInstance();
    private static final BookAuthorDaoJDBC bookAuthorDao = BookAuthorDaoJDBC.getInstance();

    public BookDaoJDBC() {}

    @Override
    public Book insert(Book book) {
        String sql = "INSERT INTO book (title, year, status) VALUE (?, ?, ?)";
        try(Connection conn = DB.getConnection();
        PreparedStatement st = conn.prepareStatement(sql,
                Statement.RETURN_GENERATED_KEYS)){
            st.setString(1, book.getTitle());
            st.setInt(2, book.getYear());
            st.setString(3, book.getStatus().toString());

            int rowsAffected = st.executeUpdate();
            updateMsg(rowsAffected);

            if(rowsAffected > 0){
                try(ResultSet rs = st.getGeneratedKeys()) {
                    if(rs.next()){
                        Integer id = rs.getInt(1);
                        Book insertedBook = BookFactory.createWithID(id,
                                book.getTitle(),
                                book.getYear(),
                                book.getAuthor(),
                                book.getGenre(),
                                book.getStatus());

                        if (book.getAuthor() != null && !book.getAuthor().isEmpty()) {
                            bookAuthorDao.addAuthorsToBook(id, book.getAuthor());
                        }
                        if (book.getGenre() != null && !book.getGenre().isEmpty()) {
                            bookGenreDao.addGenresToBook(id, book.getGenre());
                        }

                        return insertedBook;
                    }
                }
            }
        } catch (SQLException e){
            throw new DBException("Error to insert a book: " + e.getMessage());
        }
        return null;
    }

    @Override
    public void update(int bookId, Map<String, Object> fields) {
        if(fields.isEmpty()){
            return;
        }
        StringBuilder sql = new StringBuilder("UPDATE book SET ");
        List<Object> values = new ArrayList<>();

        for(Map.Entry<String, Object> entry : fields.entrySet()){
            if(entry.getValue() != null){
                sql.append(entry.getKey()).append(" = ?, ");
                values.add(entry.getValue());
            }
        }

        sql.setLength(sql.length() - 2);
        sql.append("WHERE id = ?");
        values.add(bookId);

        try(Connection conn = DB.getConnection();
        PreparedStatement st = conn.prepareStatement(sql.toString())){

            for(int i = 0; i < values.size(); i++) {
                st.setObject(i + 1, values.get(i));
            }

            int rowsAffected = st.executeUpdate();
            updateMsg(rowsAffected);

        } catch(SQLException e){
            System.out.println("Generated SQL: " + sql);
            System.out.println("Values: " + values);
            throw new DBException("Error updating book: " + e.getMessage());
        }
    }

    @Override
    public Book findById(int id) {
        Book book = null;
        String sql = "SELECT * FROM book WHERE id = ?";

        try (Connection conn = DB.getConnection();
             PreparedStatement st = conn.prepareStatement(sql)) {

            st.setInt(1, id);

            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    Integer bookId = rs.getInt("id");
                    String title = rs.getString("title");
                    int year = rs.getInt("year");
                    BookStatus status = BookStatus.valueOf(rs.getString("status"));


                    List<Author> authors = bookAuthorDao.findAuthorsByBookId(bookId);
                    List<Genre> genres = bookGenreDao.findGenresByBookId(bookId);

                    book = BookFactory.createWithID(bookId, title, year, authors, genres, status);
                }
            }
        } catch (SQLException e) {
            throw new DBException("Error finding book by ID: " + e.getMessage());
        }
        return book;
    }

    @Override
    public void deleteById(int id) {
        String sql = "DELETE FROM book WHERE ID = ?";
        try(Connection conn = DB.getConnection();
            PreparedStatement st = conn.prepareStatement(sql)){
            st.setInt(1, id);
            st.executeQuery();

        } catch (SQLException e){
            throw new DBException("Error to delete by ID: " + e.getMessage());
        }
    }

    @Override
    public List<Book> findAll() {
        String sql = "SELECT b.id, b.title, b.year, b.status, " +
                "GROUP_CONCAT(DISTINCT CONCAT(a.id, ':', a.name) SEPARATOR ', ') AS authors, " +
                "GROUP_CONCAT(DISTINCT CONCAT(g.id, ':', g.name) SEPARATOR ', ') AS genres " +
                "FROM book b " +
                "LEFT JOIN book_author ba ON b.id = ba.book_id " +
                "LEFT JOIN author a ON ba.author_id = a.id " +
                "LEFT JOIN book_genre bg ON b.id = bg.book_id " +
                "LEFT JOIN genre g ON bg.genre_id = g.id " +
                "GROUP BY b.id";

        List<Book> books = new ArrayList<>();

        try (Connection conn = DB.getConnection();
             PreparedStatement st = conn.prepareStatement(sql);
             ResultSet rs = st.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String title = rs.getString("title");
                int year = rs.getInt("year");
                BookStatus status = BookStatus.valueOf(rs.getString("status"));
                String authorStr = rs.getString("authors");
                String genreStr = rs.getString("genres");

                List<Author> authors = parseUtil.parseAuthors(authorStr);
                List<Genre> genres = parseUtil.parseGenres(genreStr);

                Book book = new Book(id, title, year, authors, genres, status);
                books.add(book);
            }

        } catch (SQLException e) {
            throw new DBException(e.getMessage());
        }
        return books;
    }

    @Override
    public void updateMsg(int rowsAffected) {
        System.out.println("Rows affected: " + rowsAffected);
    }
}
