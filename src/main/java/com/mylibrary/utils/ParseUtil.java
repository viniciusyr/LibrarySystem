package com.mylibrary.utils;

import com.mylibrary.model.Author;
import com.mylibrary.model.Genre;

import java.util.ArrayList;
import java.util.List;

public class ParseUtil {
    private static final ParseUtil INSTANCE = new ParseUtil();

    private ParseUtil() {}

    public static ParseUtil getInstance(){
        return INSTANCE;
    }
    public List<Author> parseAuthors(String authorStr) {
        List<Author> authors = new ArrayList<>();
        if (authorStr != null && !authorStr.isEmpty()) {
            for (String entry : authorStr.split(", ")) {
                String[] parts = entry.split(":");
                if (parts.length == 2) {
                    Integer id = Integer.parseInt(parts[0].trim());
                    String name = parts[1].trim();
                    authors.add(new Author(id, name));
                }
            }
        }
        return authors;
    }

    public List<Genre> parseGenres(String genreStr) {
        List<Genre> genres = new ArrayList<>();
        if (genreStr != null && !genreStr.isEmpty()) {
            for (String entry : genreStr.split(", ")) {
                String[] parts = entry.split(":");
                if (parts.length == 2) {
                    Integer id = Integer.parseInt(parts[0].trim());
                    String name = parts[1].trim();
                    genres.add(new Genre(id, name));
                }
            }
        }
        return genres;
    }
}