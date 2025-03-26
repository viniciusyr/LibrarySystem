package service;

import dao.BookDaoJDBC;
import enums.BookStatus;
import factory.BookFactory;
import model.Book;

public class BookService {

    private final BookDaoJDBC bookDao;

    public BookService(BookDaoJDBC bookDao){
        this.bookDao = bookDao;
    }

    public void registerBook(String title, int year, String author, String genre, BookStatus status){
        Book book = BookFactory.create(title, year, author, genre, status);
        bookDao.insert(book);
    }

    public void editBook(){};

}
