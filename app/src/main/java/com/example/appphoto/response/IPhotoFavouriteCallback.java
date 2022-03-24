package com.example.appphoto.response;

import com.example.appphoto.model.Photo;

import java.util.ArrayList;

public interface IPhotoFavouriteCallback {
    void onReceiveSuccess();
    void onSaveDataIntoPrefSuccess();
    void sendListPhoto(ArrayList<Photo> photos);
}
