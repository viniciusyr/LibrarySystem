package service;

import dao.CustomerDao;
import dao.CustomerDaoJDBC;
import factory.CustomerFactory;
import model.Customer;

import java.util.Scanner;

public class CustomerService {

    private static final Scanner scanner = new Scanner(System.in);
    private final CustomerDaoJDBC customerDao;

    public CustomerService(CustomerDaoJDBC customerDao){
        this.customerDao = customerDao;
    }

    public void registerCustomer(String name, String email){
        Customer customer = CustomerFactory.create(name, email);
        customerDao.insert(customer);
    }

}
