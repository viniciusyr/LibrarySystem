package com.mylibrary.service;

import com.mylibrary.dao.AuthorDaoJDBC;
import com.mylibrary.model.Author;

import java.util.HashMap;
import java.util.Map;

public class AuthorService {

    private final AuthorDaoJDBC authorDao;

    public AuthorService(AuthorDaoJDBC authorDao){
        this.authorDao = authorDao;
    }

    public void registerAuthor(String name){
        Author author = new Author(name);
        authorDao.insert(author);
    }

    public void editAuthor(int id, String newName){
        Author author = authorDao.findById(id);
        if(author == null){
            throw new IllegalArgumentException("Author not found!");
        }

        Map<String, Object> updatedFields = new HashMap<>();

        if(newName != null){
            updatedFields.put("name", newName);
        }

        if(updatedFields.isEmpty()){
            System.out.println("No updates were made!");
        }

        authorDao.update(id, updatedFields);
    }
}
