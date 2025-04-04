package com.mylibrary.validation;

import com.mylibrary.utils.RegexUtil;

public class BookValidation {

    private static final RegexUtil regex = RegexUtil.getInstance();

    public static final BookValidation getInstance = new BookValidation();

    public BookValidation() {}

    public boolean validateTitle(String title){
        return title != null && regex.isTitleValid(title);
    }

    public boolean validateAuthor(String author){
        return author != null && regex.isNameValid(author);
    }

    public boolean validateGenre(String genre){
        return genre != null && regex.isGenreValid(genre);
    }

}
