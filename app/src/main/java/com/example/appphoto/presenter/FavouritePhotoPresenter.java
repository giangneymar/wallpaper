package com.example.appphoto.presenter;

import static com.example.appphoto.utils.KeyConstants.PHOTO;
import static com.example.appphoto.utils.KeyConstants.PHOTO_DATA;
import static com.example.appphoto.utils.KeyConstants.PHOTO_ID;
import static com.example.appphoto.utils.KeyConstants.SHARED_KEY;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.appphoto.model.Photo;
import com.example.appphoto.response.IPhotoFavouriteCallback;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class FavouritePhotoPresenter {
    private final IPhotoFavouriteCallback callback;

    public FavouritePhotoPresenter(IPhotoFavouriteCallback callback) {
        this.callback = callback;
    }

    public void receiveDataFromFragmentPhoto(Bundle bundle, ArrayList<Photo> photoFavourite) {
        int id = bundle.getInt(PHOTO_ID);
        int image = bundle.getInt(PHOTO);
        photoFavourite.add(new Photo(id, image));
        callback.onReceiveSuccess();
    }

    public void saveDataIntoPref(Context context, ArrayList<Photo> photos) {
        SharedPreferences preferences = context.getSharedPreferences(SHARED_KEY, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(photos);
        editor.putString(PHOTO_DATA, json);
        editor.apply();
        callback.onSaveDataIntoPrefSuccess();
    }

    public void loadDataFromPref(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(SHARED_KEY, Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = preferences.getString(PHOTO_DATA, null);
        Type type = new TypeToken<ArrayList<Photo>>() {
        }.getType();
        ArrayList<Photo> photos = gson.fromJson(json, type);
        if (photos != null) {
            callback.sendListPhoto(photos);
        }
    }
}
