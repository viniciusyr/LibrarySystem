package dao;

import model.Customer;

import java.util.List;

public interface CustomerDao {
    public Customer insert(Customer customer);
    public void update(int customerId, String newEmail);
    public Customer findById(int id);
    public void deleteById(int id);
    public List<Customer> findAll();
    public void updateMsg(int rowsAffected);
}
