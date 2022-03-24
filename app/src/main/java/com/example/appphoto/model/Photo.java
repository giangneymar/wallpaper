package com.example.appphoto.model;

import java.io.Serializable;

public class Photo implements Serializable {
    private final int id;
    private final int image;

    public Photo(int id, int image) {
        this.id = id;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public int getImage() {
        return image;
    }
}
