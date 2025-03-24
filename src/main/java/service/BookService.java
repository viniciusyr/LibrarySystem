package service;

import dao.BookDaoJDBC;
import factory.BookFactory;
import model.Book;

public class BookService {

    private final BookDaoJDBC bookDao;

    public BookService(BookDaoJDBC bookDao){
        this.bookDao = bookDao;
    }

    public void registerBook(String title, int year, String author){
        Book book = BookFactory.create(title, year, author);
        bookDao.insert(book);
    }

}
