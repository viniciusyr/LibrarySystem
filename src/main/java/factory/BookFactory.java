package factory;

import enums.BookStatus;
import model.Book;

public class BookFactory {

    public static Book create(String title, int year, String author, String genre, BookStatus status){
        return new Book(title, year, author, genre, status);
    }

    public static Book createWithID(Integer id, String title, int year, String author, String genre, BookStatus status){
        return new Book(id, title, year, author, genre, status);
    }

}
