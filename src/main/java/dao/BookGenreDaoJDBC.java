package dao;

import database.DB;
import exception.DBException;
import model.Genre;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookGenreDaoJDBC implements BookGenreDao{

    public static final BookGenreDaoJDBC INSTANCE = new BookGenreDaoJDBC();

    public BookGenreDaoJDBC() {}

    public static BookGenreDaoJDBC getInstance(){
        return INSTANCE;
    }

    public void addGenresToBook(Integer id, List<Genre> genres) {
        String sql = "INSERT INTO book_genre (book_id, genre_id) VALUES (?, ?)";
        try (Connection conn = DB.getConnection(); PreparedStatement st = conn.prepareStatement(sql)) {
            for (Genre genre : genres) {
                st.setInt(1, id);
                st.setInt(2, genre.getId());
                st.addBatch();
            }
            st.executeBatch();
        } catch (SQLException e) {
            throw new DBException("Error adding genres to book: " + e.getMessage());
        }
    }

    public List<Genre> findGenresByBookId(int bookId) {
        List<Genre> genres = new ArrayList<>();
        String sql = "SELECT g.id, g.name FROM genre g " +
                "JOIN book_genre bg ON g.id = bg.genre_id " +
                "WHERE bg.book_id = ?";

        try (Connection conn = DB.getConnection();
             PreparedStatement st = conn.prepareStatement(sql)) {

            st.setInt(1, bookId);

            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    Integer genreId = rs.getInt("id");
                    String name = rs.getString("name");
                    genres.add(new Genre(genreId, name));
                }
            }
        } catch (SQLException e) {
            throw new DBException("Error finding genres for book: " + e.getMessage());
        }
        return genres;
    }

}
