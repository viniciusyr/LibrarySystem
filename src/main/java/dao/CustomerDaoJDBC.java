package dao;

import exception.DBException;
import factory.CustomerFactory;
import model.Customer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import database.DB;

public class CustomerDaoJDBC implements CustomerDao {

    public CustomerDaoJDBC() {
    }

    @Override
    public Customer insert(Customer customer){
        String sql = "INSERT INTO customer (name, email) VALUES (?, ?) ";
        try(Connection conn = DB.getConnection();
            PreparedStatement st = conn.prepareStatement(sql,
                    Statement.RETURN_GENERATED_KEYS)){
            st.setString(1, customer.getName());
            st.setString(2, customer.getEmail());

            int rowsAffected = st.executeUpdate();
            updateMsg(rowsAffected);

            if(rowsAffected > 0) {
                try (ResultSet rs = st.getGeneratedKeys()) {
                    if (rs.next()) {
                        Integer id = rs.getInt(1);
                        return CustomerFactory.createWithID(id, customer.getName(), customer.getEmail());
                    }
                }
            }
        } catch (SQLException e) {
            throw new DBException("Error to insert a customer: " + e.getMessage());
        }
        return customer;
    }

    @Override
    public void update(Customer customer, String newEmail) {
        String sql = "UPDATE customer SET email = ? WHERE id = ?";
        try(Connection conn = DB.getConnection();
            PreparedStatement st = conn.prepareStatement(sql)){
            st.setString(1, newEmail);

            int rowsAffected = st.executeUpdate();
            updateMsg(rowsAffected);

        } catch (SQLException e){
            throw new DBException("Update model.Customer error: " + e.getMessage());
        }
    }

    @Override
    public Customer findById(int id) {
        Customer customer = null;
        String sql = "SELECT * FROM customer WHERE ID = ?";
        try(Connection conn = DB.getConnection();
            PreparedStatement st = conn.prepareStatement(sql)) {

            st.setInt(1, id);

            try(ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    int customerId = rs.getInt(1);
                    String name = rs.getString("name");
                    String email = rs.getString("email");

                    customer = CustomerFactory.createWithID(customerId, name, email);

                }
            }
        } catch (SQLException e){
            throw new DBException("Error to find by ID: "+ e.getMessage());
        }
        return customer;
    }

    @Override
    public void deleteById(int id) {
        String sql = "DELETE FROM customer WHERE ID = ?";
        try(Connection conn = DB.getConnection();
            PreparedStatement st = conn.prepareStatement(sql)){
            st.setInt(1, id);
            st.executeQuery();

        } catch (SQLException e){
            throw new DBException("Error to delete by ID: " + e.getMessage());
        }
    }

    @Override
    public List<Customer> findAll() {
        String sql = "SELECT * FROM customer";
        List<Customer> customers = new ArrayList<>();
        try(Connection conn = DB.getConnection();
            PreparedStatement st = conn.prepareStatement(sql);
            ResultSet rs = st.executeQuery()) {

            while(rs.next()){
                int id = rs.getInt(1);
                String name = rs.getString(2);
                String email = rs.getString(3);
                Customer newCustomer = new Customer(id, name, email);
                customers.add(newCustomer);
            }

        } catch (SQLException e){
            throw new DBException("Error to find all customers: " + e.getMessage());
        }
        return customers;
    }

    @Override
    public void updateMsg(int rowsAffected){
        System.out.println("Rows affected: " + rowsAffected);
    }

}
