package service;

import dao.CustomerDaoJDBC;
import factory.CustomerFactory;
import model.Customer;

public class CustomerService {

    private final CustomerDaoJDBC customerDao;

    public CustomerService(CustomerDaoJDBC customerDao) {
        this.customerDao = customerDao;
    }

    public void registerCustomer(String name, String email){
        Customer customer = CustomerFactory.create(name, email);
        customerDao.insert(customer);
    }

    public void editCustomer(int customerId, String newEmail){
       customerDao.update(customerId, newEmail);
    }

}


