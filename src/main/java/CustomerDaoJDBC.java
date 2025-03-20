import exception.DBException;

import javax.xml.transform.Result;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerDaoJDBC implements CustomerDao {

    PreparedStatement st = null;

    @Override
    public void insert(Customer customer){
        try{
            DB.getConnection();

            st = DB.conn.prepareStatement("INSERT INTO customer (name, email) VALUES (?, ?) ",
                    Statement.RETURN_GENERATED_KEYS);
            st.setString(1, customer.getName());
            st.setString(2, customer.getEmail());

            int rowsAffected = st.executeUpdate();
            if(rowsAffected > 0){
                ResultSet rs = st.getGeneratedKeys();
                while(rs.next()){
                    int id = rs.getInt(1);
                    customer = new Customer(id, customer.getName(), customer.getEmail());
                }
                DB.closeResultSet(rs);
            }

        } catch (SQLException e) {
            throw new DBException("Error to insert a customer: " + e.getMessage());
        } finally {
            DB.closeStatement(st);
            DB.closeConnection(DB.conn);
        }
    }

    @Override
    public void update(Customer customer, String newEmail) {
        try{
            DB.getConnection();
            st = DB.conn.prepareStatement("UPDATE customer SET email = ? WHERE id = ?");
            st.setString(1, newEmail);

            int rowsAffected = st.executeUpdate();
            updateMsg(rowsAffected);

        } catch (SQLException e){
            throw new DBException("Update Customer error: " + e.getMessage());
        } finally {
            DB.closeStatement(st);
            DB.closeConnection(DB.conn);
        }
    }

    @Override
    public Customer findById(int id) {
        Customer customer = null;
        try {
            DB.getConnection();
            st = DB.conn.prepareStatement("SELECT * FROM customer WHERE ID = ?");
            st.setInt(1, id);

            ResultSet rs = st.executeQuery();
            if(rs.next()){
                int customerId = rs.getInt(1);
                String name = rs.getString("name");
                String email = rs.getString("email");

                customer = new Customer(customerId, name, email);
                DB.closeResultSet(rs);
            }
        } catch (SQLException e){
            throw new DBException("Error to find by ID: "+ e.getMessage());
        } finally {
            DB.closeStatement(st);
            DB.closeConnection(DB.conn);
        }
        return customer;
    }

    @Override
    public void deleteById(int id) {
        try{
            DB.getConnection();
            st = DB.conn.prepareStatement("DELETE * FROM customer WHERE ID = ?");
            st.setInt(1, id);
            st.executeQuery();

        } catch (SQLException e){
            throw new DBException("Error to delete by ID: " + e.getMessage());
        } finally {
            DB.closeStatement(st);
            DB.closeConnection(DB.conn);
            System.out.println("The user was deleted");
        }
    }

    @Override
    public List<Customer> findAll() {
        List<Customer> customers = new ArrayList<>();
        try {
            DB.getConnection();
            st = DB.conn.prepareStatement("SELECT * FROM customer");
            ResultSet rs = st.executeQuery();

            if(rs.next()){
                int id = rs.getInt(1);
                String name = rs.getString(2);
                String email = rs.getString(3);
                Customer newCustomer = new Customer(id, name, email);
                customers.add(newCustomer);
            }

        } catch (SQLException e){
            throw new DBException("Error to find all customers: " + e.getMessage());
        } finally {
            DB.closeStatement(st);
            DB.closeConnection(DB.conn);
        }
        return customers;
    }

    @Override
    public void updateMsg(int rowsAffected){
        System.out.println("Rows affected: " + rowsAffected);
    }

}
