package com.mylibrary.dao;

import com.mylibrary.database.DB;
import com.mylibrary.exception.DBException;
import com.mylibrary.model.Genre;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GenreDaoJDBC implements GenreDao{

    public GenreDaoJDBC() {}

    @Override
    public Genre insert(Genre genre) {
        String sql = "INSERT INTO genre (name) VALUES (?) ";
        try (Connection conn = DB.getConnection();
             PreparedStatement st = conn.prepareStatement(sql,
                     Statement.RETURN_GENERATED_KEYS)) {
            st.setString(1, genre.getName());

            int rowsAffected = st.executeUpdate();
            updateMsg(rowsAffected);

            if (rowsAffected > 0) {
                try (ResultSet rs = st.getGeneratedKeys()) {
                    Integer id = rs.getInt(1);
                    genre.setId(id);
                }
            }

        } catch (SQLException e) {
            throw new DBException("Error to insert a customer: " + e.getMessage());
        }
        return genre;
    }

    @Override
    public void update(int genreId, Map<String, Object> fields) {
        StringBuilder sql = new StringBuilder("UPDATE customer SET ");
        List<Object> values = new ArrayList<>();

        for(Map.Entry<String, Object> entry : fields.entrySet()){
            if(entry.getValue() != null){
                sql.append(entry.getKey()).append(" = ?, ");
                values.add(entry.getValue());
            }
        }

        sql.setLength(sql.length() - 2);
        sql.append("WHERE id = ?");
        values.add(genreId);

        try(Connection conn = DB.getConnection();
            PreparedStatement st = conn.prepareStatement(String.valueOf(sql))){

            for (int i = 0; i < values.size(); i++) {
                st.setObject(i + 1, values.get(i));
            }

            int rowsAffected = st.executeUpdate();
            updateMsg(rowsAffected);

        } catch (SQLException e){
            System.out.println("Generated SQL: " + sql);
            System.out.println("Values: " + values);
            throw new DBException("Update Customer error: " + e.getMessage());
        }
    }

    @Override
    public Genre findById(int id) {
        Genre genre = null;
        String sql = "SELECT * FROM genre WHERE ID = ?";
        try(Connection conn = DB.getConnection();
            PreparedStatement st = conn.prepareStatement(sql)) {

            st.setInt(1, id);

            try(ResultSet rs = st.executeQuery()) {
                while(rs.next()) {
                    int genreId = rs.getInt("id");
                    String name = rs.getString("name");

                    genre = new Genre(genreId, name);

                }
            }
        } catch (SQLException e){
            throw new DBException("Error to find by ID: "+ e.getMessage());
        }
        return genre;
    }

    @Override
    public void deleteById(int id) {
        String sql = "DELETE FROM genre WHERE ID = ?";
        try(Connection conn = DB.getConnection();
            PreparedStatement st = conn.prepareStatement(sql)){
            st.setInt(1, id);
            st.executeUpdate();

        } catch (SQLException e){
            throw new DBException("Error to delete by ID: " + e.getMessage());
        }
    }

    @Override
    public List<Genre> findAll() {
        String sql = "SELECT * FROM genre";
        List<Genre> genres = new ArrayList<>();
        try(Connection conn = DB.getConnection();
            PreparedStatement st = conn.prepareStatement(sql);
            ResultSet rs = st.executeQuery()) {

            while(rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("name");
                Genre newGenre = new Genre(id, name);
                genres.add(newGenre);
            }

        } catch (SQLException e){
            throw new DBException("Error to find all genres: " + e.getMessage());
        }
        return genres;
    }

    @Override
    public void updateMsg(int rowsAffected) {
        System.out.println("Rows affected: " + rowsAffected);
    }
}

