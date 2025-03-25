package dao;

import database.DB;
import enums.BookStatus;
import exception.DBException;
import factory.BookFactory;
import model.Book;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookDaoJDBC implements BookDao{
    @Override
    public Book insert(Book book) {
        String sql = "INSERT INTO book (title, year, author, genre, status) VALUE (?, ?, ?, ?, ?)";
        try(Connection conn = DB.getConnection();
        PreparedStatement st = conn.prepareStatement(sql,
                Statement.RETURN_GENERATED_KEYS)){
            st.setString(1, book.getTitle());
            st.setInt(2, book.getYear());
            st.setString(3, book.getAuthor());
            st.setString(4, book.getGenre());
            st.setString(5, book.getStatus().toString());

            int rowsAffected = st.executeUpdate();
            updateMsg(rowsAffected);

            if(rowsAffected > 0){
                try(ResultSet rs = st.getGeneratedKeys()) {
                    if(rs.next()){
                        Integer id = rs.getInt(1);
                        return BookFactory.createWithID(id,
                                book.getTitle(),
                                book.getYear(),
                                book.getAuthor(),
                                book.getGenre(),
                                book.getStatus());
                    }
                }
            }
        } catch (SQLException e){
            throw new DBException("Error to insert a book: " + e.getMessage());
        }
        return book;
    }

    @Override
    public void update(Book book) {
        String sql = " ";
        try(Connection conn = DB.getConnection();
        PreparedStatement st = conn.prepareStatement(sql)){
            st.setString(1, "");

            // THIS NEEDS TO BE DONE AFTER DEFINED.

            int rowsAffected = st.executeUpdate();
            updateMsg(rowsAffected);

        } catch(SQLException e){
            throw new DBException(e.getMessage());
        }
    }

    @Override
    public Book findById(int id) {
        Book book = null;
        String sql = "SELECT * FROM book WHERE ID = ?";
        try(Connection conn = DB.getConnection();
        PreparedStatement st = conn.prepareStatement(sql)){
            st.setInt(1, id);

            try(ResultSet rs = st.executeQuery()){
                while(rs.next()){
                    Integer bookId = rs.getInt(1);
                    String title = rs.getString("title");
                    int year = rs.getInt("year");
                    String author = rs.getString("author");
                    String genre = rs.getString("genre");
                    BookStatus status = BookStatus.valueOf(rs.getString("status"));

                    book = BookFactory.createWithID(bookId, title, year, author, genre, status);
                }
            }
        } catch (SQLException e){
            throw new DBException(e.getMessage());
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
        String sql = "SELECT * FROM book";
        List<Book> books = new ArrayList<>();
        try (Connection conn = DB.getConnection();
             PreparedStatement st = conn.prepareStatement(sql);
             ResultSet rs = st.executeQuery()) {

            while(rs.next()){
                int id = rs.getInt(1);
                String title = rs.getString("title");
                int year = rs.getInt("year");
                String author = rs.getString("author");
                String genre = rs.getString("genre");
                BookStatus status = BookStatus.valueOf(rs.getString("status"));

                Book book = new Book(id, title, year, author, genre, status);
                books.add(book);
            }


        } catch (SQLException e) {
            throw new DBException(e.getMessage());
        }
        return books;
    }

    @Override
    public void updateMsg(int rowsAffected) {

    }
}
