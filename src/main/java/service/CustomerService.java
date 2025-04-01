package service;

import dao.CustomerDaoJDBC;
import factory.CustomerFactory;
import model.Customer;

import java.util.HashMap;
import java.util.Map;

public class CustomerService {

    private final CustomerDaoJDBC customerDao;

    public CustomerService(CustomerDaoJDBC customerDao) {
        this.customerDao = customerDao;
    }

    public void registerCustomer(String name, String email){
        Customer customer = CustomerFactory.create(name, email);
        customerDao.insert(customer);
    }

    public void editCustomer(int customerId, String newName, String newEmail){
        Customer customer = customerDao.findById(customerId);
        if(customer == null){
            throw new IllegalArgumentException("Customer not found!");
        }

        Map<String, Object> updatedFields = new HashMap<>();

        if(newName != null){
            updatedFields.put("name", newName);
        }

        if(newEmail != null){
            updatedFields.put("email", newEmail);
        }

        if(updatedFields.isEmpty()){
            System.out.println("No updates were made!");
        }

       customerDao.update(customerId, updatedFields);
    }

}


