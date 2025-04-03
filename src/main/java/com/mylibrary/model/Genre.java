package com.mylibrary.model;

public class Genre {
    private Integer id;
    private String name;

    public Genre(Integer id, String name){
        this.id = id;
        this.name = name;
    }

    public Genre(String name){
        this.id = null;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Genre{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
