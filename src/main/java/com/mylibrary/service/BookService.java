package com.mylibrary.service;

import com.mylibrary.dao.BookDaoJDBC;
import com.mylibrary.enums.BookStatus;
import com.mylibrary.factory.BookFactory;
import com.mylibrary.model.Author;
import com.mylibrary.model.Book;
import com.mylibrary.model.Genre;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BookService {

    private final BookDaoJDBC bookDao;

    public BookService(BookDaoJDBC bookDao){
        this.bookDao = bookDao;
    }

    public void registerBook(String title, int year, List<Author> author, List<Genre> genre, BookStatus status){
        Book book = BookFactory.create(title, year, author, genre, status);
        bookDao.insert(book);
    }

    public void editBook(int bookId, String newTitle, Integer newYear, String newAuthor, String newGenre){
        Book book = bookDao.findById(bookId);
        if(book == null){
            throw new IllegalArgumentException("Book not found!");
        }

        Map<String, Object> updatedFields = new HashMap<>();

        if(newTitle != null){
           updatedFields.put("title", newTitle);
        }

        if(newYear != null){
            updatedFields.put("year", newYear);
        }

        if(newAuthor != null){
            updatedFields.put("author", newAuthor);
        }

        if(newGenre != null){
            updatedFields.put("genre", newGenre);
        }

        if (updatedFields.isEmpty()){
            System.out.println("No updates were made!");
        }

        bookDao.update(bookId, updatedFields);
    }



}
