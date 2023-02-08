package com.cv.cgpcinema;

public class Movie {

    private String title;
    private String description;
    private String cast;

    public Movie(String title, String description, String cast) {
        this.title = title;
        this.description = description;
        this.cast = cast;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getCast() {
        return cast;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCast(String cast) {
        this.cast = cast;
    }
}
