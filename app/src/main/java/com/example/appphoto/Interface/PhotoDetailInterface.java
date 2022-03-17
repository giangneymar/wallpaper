package com.example.appphoto.Interface;

import android.content.Context;

import com.example.appphoto.Model.Photo;

public interface PhotoDetailInterface {
    interface PhotoDetailPresenter{
        void setWallpaper(Context context, Photo photo);
    }
}
