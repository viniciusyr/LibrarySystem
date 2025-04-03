package com.mylibrary.factory;

import com.mylibrary.enums.BookStatus;
import com.mylibrary.model.Author;
import com.mylibrary.model.Book;
import com.mylibrary.model.Genre;

import java.util.List;

public class BookFactory {

    public static Book create(String title, int year, List<Author> authors, List<Genre> genres, BookStatus status){
        return new Book(title, year, authors, genres, status);
    }

    public static Book createWithID(Integer id, String title, int year, List<Author> authors, List<Genre> genres, BookStatus status){
        return new Book(id, title, year, authors, genres, status);
    }

}
