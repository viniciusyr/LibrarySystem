package dao;

import model.Genre;

import java.util.List;

public interface BookGenreDao {
    void addGenresToBook(Integer id, List<Genre> genres);
    List<Genre> findGenresByBookId(int bookId);
}
