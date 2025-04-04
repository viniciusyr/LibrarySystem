package com.mylibrary.validation;

import com.mylibrary.utils.RegexUtil;

public class CustomerValidation {

    private static final RegexUtil regex = RegexUtil.getInstance();

    public static final CustomerValidation getInstance = new CustomerValidation();

    public CustomerValidation() {}

    public boolean validateName(String name){
        return name != null && regex.isNameValid(name);
    }

    public boolean validateEmail(String email){
        return email != null && regex.isEmailValid(email);
    }

    public boolean validateGenre(String genre){
        return genre != null && regex.isGenreValid(genre);
    }

    public boolean validateCpf(String cpf){ return cpf != null && regex.isCpfValid(cpf);
    }

}
