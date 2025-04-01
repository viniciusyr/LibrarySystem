package dao;

import database.DB;
import exception.DBException;
import factory.CustomerFactory;
import model.Author;
import model.Customer;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AuthorDaoJDBC implements AuthorDao {

    public AuthorDaoJDBC() {}

    @Override
    public void insert(Author author) {
        String sql = "INSERT INTO author (name, birthdate) VALUES (?, ?) ";
        try(Connection conn = DB.getConnection();
            PreparedStatement st = conn.prepareStatement(sql,
                    Statement.RETURN_GENERATED_KEYS)){
            st.setString(1, author.getName());

            int rowsAffected = st.executeUpdate();
            updateMsg(rowsAffected);

            if (rowsAffected > 0) {
                try(ResultSet rs = st.getGeneratedKeys()){
                    Integer id = rs.getInt(1);
                    author.setId(id);
                }
            }

        } catch (SQLException e){
            throw new DBException("Error to insert a customer: " + e.getMessage());
        }
    }

    @Override
    public void update(int authorId, Map<String, Object> fields) {
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
        values.add(authorId);

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
            throw new DBException("Update model.Customer error: " + e.getMessage());
        }
    }

    @Override
    public Author findById(int id) {
        Author author = null;
        String sql = "SELECT * FROM author WHERE ID = ?";
        try(Connection conn = DB.getConnection();
            PreparedStatement st = conn.prepareStatement(sql)) {

            st.setInt(1, id);

            try(ResultSet rs = st.executeQuery()) {
                while(rs.next()) {
                    int authorId = rs.getInt("id");
                    String name = rs.getString("name");
                    LocalDate birthdate = rs.getDate("birthdate").toLocalDate();

                    author = new Author(authorId, name);

                }
            }
        } catch (SQLException e){
            throw new DBException("Error to find by ID: "+ e.getMessage());
        }
        return author;
    }

    @Override
    public void deleteById(int id) {
        String sql = "DELETE FROM author WHERE ID = ?";
        try(Connection conn = DB.getConnection();
            PreparedStatement st = conn.prepareStatement(sql)){
            st.setInt(1, id);
            st.executeUpdate();

        } catch (SQLException e){
            throw new DBException("Error to delete by ID: " + e.getMessage());
        }
    }

    @Override
    public List<Author> findAll() {
        String sql = "SELECT * FROM author";
        List<Author> authors = new ArrayList<>();
        try(Connection conn = DB.getConnection();
            PreparedStatement st = conn.prepareStatement(sql);
            ResultSet rs = st.executeQuery()) {

            while(rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("name");
                Author newAuthor = new Author(id, name);
                authors.add(newAuthor);
            }

        } catch (SQLException e){
            throw new DBException("Error to find all authors: " + e.getMessage());
        }
        return authors;
    }

    @Override
    public void updateMsg(int rowsAffected) {
        System.out.println("Rows affected: " + rowsAffected);
    }
}
