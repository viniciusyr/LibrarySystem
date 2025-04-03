package com.mylibrary.factory;

import com.mylibrary.model.Customer;

public class CustomerFactory {

    public static Customer create(String name, String email){
        return new Customer(name, email);
    }

    public static Customer createWithID(Integer id, String name, String email){
        return new Customer(id, name, email);
    }
}
