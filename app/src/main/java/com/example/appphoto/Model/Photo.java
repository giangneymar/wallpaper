package com.example.appphoto.Model;

import java.io.Serializable;

public class Photo implements Serializable {
    private int id;
    private int image;

    public Photo(int id, int image) {
        this.id = id;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

}
