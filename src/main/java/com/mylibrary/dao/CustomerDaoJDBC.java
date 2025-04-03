package com.mylibrary.dao;

import com.mylibrary.exception.DBException;
import com.mylibrary.factory.CustomerFactory;
import com.mylibrary.model.Customer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.mylibrary.database.DB;

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
                        return CustomerFactory.createWithID(
                                id,
                                customer.getName(),
                                customer.getEmail());
                    }
                }
            }
        } catch (SQLException e) {
            throw new DBException("Error to insert a customer: " + e.getMessage());
        }
        return customer;
    }

    @Override
    public void update(int customerId, Map<String, Object> fields) {
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
        values.add(customerId);

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
    public Customer findById(int id) {
        Customer customer = null;
        String sql = "SELECT * FROM customer WHERE ID = ?";
        try(Connection conn = DB.getConnection();
            PreparedStatement st = conn.prepareStatement(sql)) {

            st.setInt(1, id);

            try(ResultSet rs = st.executeQuery()) {
                while(rs.next()) {
                    int customerId = rs.getInt("id");
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
            st.executeUpdate();

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
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String email = rs.getString("email");
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
