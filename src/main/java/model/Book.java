package model;

import enums.BookStatus;

import java.util.Objects;

public class Book {

    private final Integer id;
    private final String title;
    private final int year;
    private final String author;
    private final String genre;
    private BookStatus status;

    public Book(int id, String title, int year, String author, String genre, BookStatus status){
        this.id = id;
        this.title = title;
        this.year = year;
        this.author = author;
        this.genre = genre;
        this.status = status;
    }

    public Book(String title, int year, String author, String genre, BookStatus status) {
        this.id = null;
        this.title = title;
        this.year = year;
        this.author = author;
        this.genre = genre;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public int getYear() {
        return year;
    }

    public String getAuthor() {
        return author;
    }

    public String getGenre() {
        return genre;
    }

    public BookStatus getStatus() {
        return status;
    }

    public void setStatus(BookStatus status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Book book)) return false;
        return Objects.equals(id, book.id) && year == book.year && Objects.equals(title, book.title) && Objects.equals(author, book.author) && Objects.equals(genre, book.genre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, year, author, genre);
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", year=" + year +
                ", author='" + author + '\'' +
                ", genre='" + genre + '\'' +
                ", status=" + status +
                '}';
    }
}
