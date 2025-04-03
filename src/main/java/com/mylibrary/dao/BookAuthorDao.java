package com.mylibrary.dao;

import com.mylibrary.model.Author;

import java.util.List;

public interface BookAuthorDao {
    void addAuthorsToBook(Integer id, List<Author> authors);
    List<Author> findAuthorsByBookId(int bookId);
}
