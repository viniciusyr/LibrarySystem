package service;

import dao.CustomerDaoJDBC;

public class ServiceFactory {
    private static CustomerService customerService;

    public static CustomerService getCustomerService() {
        if (customerService == null){
            customerService = new CustomerService(new CustomerDaoJDBC());
        }
        return customerService;
    }
}
