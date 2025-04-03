package com.mylibrary.dao;

import com.mylibrary.model.Customer;

import java.util.List;
import java.util.Map;

public interface CustomerDao {
    public Customer insert(Customer customer);
    public void update(int customerId, Map<String, Object> fields);
    public Customer findById(int id);
    public void deleteById(int id);
    public List<Customer> findAll();
    public void updateMsg(int rowsAffected);
}
