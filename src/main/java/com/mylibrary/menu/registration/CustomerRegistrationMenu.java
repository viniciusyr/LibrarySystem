package com.mylibrary.menu.registration;

import com.mylibrary.menu.Menu;
import com.mylibrary.service.CustomerService;
import com.mylibrary.service.ServiceFactory;


public class CustomerRegistrationMenu extends Menu {

    private final CustomerService cs;

    public CustomerRegistrationMenu() {
        this.cs = ServiceFactory.getCustomerService();
    }

    @Override
    public void run(){

        System.out.print("Insert the customer name: ");
        String name = scanner.nextLine();
        System.out.print("Insert the customer email: ");
        String email = scanner.nextLine();

        cs.registerCustomer(name, email);
    }

}
