package com.mylibrary.dao;

import com.mylibrary.model.Genre;

import java.util.List;

public interface BookGenreDao {
    void addGenresToBook(Integer id, List<Genre> genres);
    List<Genre> findGenresByBookId(int bookId);
}
