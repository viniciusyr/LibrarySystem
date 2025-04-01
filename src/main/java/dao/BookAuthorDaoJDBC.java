package dao;

import database.DB;
import exception.DBException;
import model.Author;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BookAuthorDaoJDBC implements BookAuthorDao{

    private static final BookAuthorDaoJDBC INSTANCE = new BookAuthorDaoJDBC();

    public BookAuthorDaoJDBC() {}

    public static BookAuthorDaoJDBC getInstance(){
        return INSTANCE;
    }

    @Override
    public void addAuthorsToBook(Integer id, List<Author> authors) {
        String sql = "INSERT INTO book_author (book_id, author_id) VALUES (?, ?)";

        try (Connection conn = DB.getConnection();
             PreparedStatement st = conn.prepareStatement(sql)) {

            for (Author author : authors) {
                st.setInt(1, id);
                st.setInt(2, author.getId());

                st.addBatch();
            }

            int[] rowsAffected = st.executeBatch();  // Executando o batch de inserção
            System.out.println("Rows affected (authors): " + Arrays.toString(rowsAffected));
        } catch (SQLException e) {
            throw new DBException("Error adding authors to book: " + e.getMessage());
        }
    }

    public List<Author> findAuthorsByBookId(int bookId) {
        List<Author> authors = new ArrayList<>();
        String sql = "SELECT a.id, a.name FROM author a " +
                "JOIN book_author ba ON a.id = ba.author_id " +
                "WHERE ba.book_id = ?";

        try (Connection conn = DB.getConnection();
             PreparedStatement st = conn.prepareStatement(sql)) {

            st.setInt(1, bookId);

            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    Integer authorId = rs.getInt("id");
                    String name = rs.getString("name");
                    authors.add(new Author(authorId, name));
                }
            }
        } catch (SQLException e) {
            throw new DBException("Error finding authors for book: " + e.getMessage());
        }
        return authors;
    }

}
