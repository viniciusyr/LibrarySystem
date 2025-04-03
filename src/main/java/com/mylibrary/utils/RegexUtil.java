package com.mylibrary.utils;

import java.util.regex.Pattern;

public class RegexUtil {

    private static final RegexUtil INSTANCE = new RegexUtil();

    private final Pattern TITLE_REGEX = Pattern.compile("^[A-Za-zÀ-ÖØ-öø-ÿ' -]{2,100}$");
    private final Pattern NAME_REGEX = Pattern.compile("^[A-Za-zÀ-ÖØ-öø-ÿ' -]{2,100}$");
    private final Pattern EMAIL_REGEX = Pattern.compile("^[\\w.-]+@[a-zA-Z\\d.-]+\\.[a-zA-Z]{2,}$");
    private final Pattern PHONE_REGEX = Pattern.compile("^\\+?\\d{1,3}[-.\\s]?\\(?\\d{1,4}\\)?[-.\\s]?\\d{4,5}[-.\\s]?\\d{4}$");
    private final Pattern ZIP_REGEX = Pattern.compile("^\\d{5}[-.\\s]?\\d{3}$|^\\d{5}(-\\d{4})?$");
    private final Pattern GENRE_REGEX = Pattern.compile("^[A-Za-zÀ-ÖØ-öø-ÿ]+(?: [A-Za-zÀ-ÖØ-öø-ÿ]+)*$");
    private final Pattern CPF_REGEX = Pattern.compile("^\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}$|^\\d{11}$");

    private RegexUtil() {}

    public static RegexUtil getInstance(){
        return INSTANCE;
    }

    public boolean isTitleValid(String title){
        return TITLE_REGEX.matcher(title).matches();
    }

    public boolean isNameValid(String name){
        return NAME_REGEX.matcher(name).matches();
    }

    public boolean isEmailValid(String email){
        return EMAIL_REGEX.matcher(email).matches();
    }

    public boolean isPhoneValid(String phoneNumber){
        return PHONE_REGEX.matcher(phoneNumber).matches();
    }

    public boolean isZipValid(String zipCode){
        return ZIP_REGEX.matcher(zipCode).matches();
    }

    public boolean isGenreValid(String genre){
        return GENRE_REGEX.matcher(genre).matches();
    }

    public boolean isCpfValid(String cpf){
        return CPF_REGEX.matcher(cpf).matches();
    }

}
