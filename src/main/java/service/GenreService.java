package service;

import dao.GenreDaoJDBC;
import model.Genre;

import java.util.HashMap;
import java.util.Map;

public class GenreService {

    private final GenreDaoJDBC genderDao;

    public GenreService(GenreDaoJDBC genderDao){
        this.genderDao = genderDao;
    }

    public void registerGenre(String name){
        Genre genre = new Genre(name);
        genderDao.insert(genre);
    }

    public void editGenre(int id, String newName){
        Genre gender = genderDao.findById(id);
        if(gender == null){
            throw new IllegalArgumentException("Genre not found!");
        }

        Map<String, Object> updatedFields = new HashMap<>();

        if(newName != null){
            updatedFields.put("name", newName);
        }

        if(updatedFields.isEmpty()){
            System.out.println("No updates were made!");
        }

        genderDao.update(id, updatedFields);
    }
}
