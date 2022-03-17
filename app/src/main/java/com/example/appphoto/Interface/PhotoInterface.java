package com.example.appphoto.Interface;

import android.content.Context;

import com.example.appphoto.Model.Photo;

public interface PhotoInterface {

    interface PhotoPresenter{
        void sendDataToFavouritePhotoFragment(Context context, Photo photo);
    }
}
