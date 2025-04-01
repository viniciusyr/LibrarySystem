package factory;

import enums.BookStatus;
import model.Author;
import model.Book;
import model.Genre;

import java.util.List;

public class BookFactory {

    public static Book create(String title, int year, List<Author> authors, List<Genre> genres, BookStatus status){
        return new Book(title, year, authors, genres, status);
    }

    public static Book createWithID(Integer id, String title, int year, List<Author> authors, List<Genre> genres, BookStatus status){
        return new Book(id, title, year, authors, genres, status);
    }

}
