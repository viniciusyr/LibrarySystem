package service;

import dao.BookDaoJDBC;
import enums.BookStatus;
import factory.BookFactory;
import model.Book;

import java.util.HashMap;
import java.util.Map;

public class BookService {

    private final BookDaoJDBC bookDao;

    public BookService(BookDaoJDBC bookDao){
        this.bookDao = bookDao;
    }

    public void registerBook(String title, int year, String author, String genre, BookStatus status){
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
    };



}
