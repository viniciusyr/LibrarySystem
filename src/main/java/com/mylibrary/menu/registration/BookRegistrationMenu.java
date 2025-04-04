package com.mylibrary.menu.registration;

import com.mylibrary.enums.BookStatus;
import com.mylibrary.menu.Menu;
import com.mylibrary.model.Author;
import com.mylibrary.model.Genre;
import com.mylibrary.service.BookService;
import com.mylibrary.service.ServiceFactory;

import java.util.ArrayList;
import java.util.List;

public class BookRegistrationMenu extends Menu {

    private final BookService bs;

    public BookRegistrationMenu(){
        this.bs = ServiceFactory.getBookService();
    }

    private static List<Author> authors = new ArrayList<>();
    private static List<Genre> genres = new ArrayList<>();
    @Override
    public void run() {
        System.out.print("Insert the book title: ");
        String title = scanner.nextLine();
        System.out.print("Insert the year of publication of the book: ");
        int year = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Insert the book author: ");
        String author = scanner.nextLine();
        authors.add(new Author(author));
        System.out.print("Insert the book genre: ");
        String genre = scanner.nextLine();
        genres.add(new Genre(null, genre));
        BookStatus status = BookStatus.AVAILABLE;

        bs.registerBook(title, year, authors, genres, status);

    }
}
