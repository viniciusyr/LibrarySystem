package com.mylibrary.service;

import com.mylibrary.dao.BookDaoJDBC;
import com.mylibrary.dao.CustomerDaoJDBC;

public class ServiceFactory {
    private static CustomerService customerService;
    private static BookService bookService;

    public static CustomerService getCustomerService() {
        if (customerService == null){
            customerService = new CustomerService(new CustomerDaoJDBC());
        }
        return customerService;
    }

    public static BookService getBookService(){
        if(bookService == null){
            bookService = new BookService(new BookDaoJDBC());
        }
        return bookService;
    }
}
