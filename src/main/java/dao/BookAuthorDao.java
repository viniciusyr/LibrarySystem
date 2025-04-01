package dao;

import model.Author;

import java.util.List;

public interface BookAuthorDao {
    void addAuthorsToBook(Integer id, List<Author> authors);
    List<Author> findAuthorsByBookId(int bookId);
}
