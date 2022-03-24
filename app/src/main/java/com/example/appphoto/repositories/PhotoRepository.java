package com.example.appphoto.repositories;

import com.example.appphoto.R;
import com.example.appphoto.model.Photo;

import java.util.ArrayList;

public class PhotoRepository {
    public void initListPhoto(ArrayList<Photo> photos) {
        photos.add(new Photo(1, R.drawable.photo_bg1));
        photos.add(new Photo(2, R.drawable.photo_bg2));
        photos.add(new Photo(3, R.drawable.photo_bg3));
        photos.add(new Photo(4, R.drawable.photo_bg4));
        photos.add(new Photo(5, R.drawable.photo_bg5));
    }
}
