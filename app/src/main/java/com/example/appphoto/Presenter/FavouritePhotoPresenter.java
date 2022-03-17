package com.example.appphoto.Presenter;

import static com.example.appphoto.Utils.PHOTO;
import static com.example.appphoto.Utils.PHOTO_DATA;
import static com.example.appphoto.Utils.PHOTO_ID;
import static com.example.appphoto.Utils.SHARED_KEY;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.appphoto.Interface.FavouritePhoInterface;
import com.example.appphoto.Model.Photo;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class FavouritePhotoPresenter implements FavouritePhoInterface.FavouritePhotoPresenter {

    private FavouritePhoInterface.FavouritePhotoView favouritePhotoView;
    private FavouritePhoInterface.SendArray sendArray;

    public void setFavouritePhotoView(FavouritePhoInterface.FavouritePhotoView favouritePhotoView) {
        this.favouritePhotoView = favouritePhotoView;
    }

    public FavouritePhotoPresenter(FavouritePhoInterface.SendArray sendArray) {
        this.sendArray = sendArray;
    }

    public FavouritePhotoPresenter() {
    }

    @Override
    public void getData(Bundle result, ArrayList<Photo> photoFavourite) {
        int id = result.getInt(PHOTO_ID);
        int image = result.getInt(PHOTO);

        photoFavourite.add(new Photo(id, image));
    }

    @Override
    public void saveData(Context context, ArrayList<Photo> photos) {
        SharedPreferences preferences = context.getSharedPreferences(SHARED_KEY, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(photos);
        editor.putString(PHOTO_DATA, json);
        editor.apply();

    }

    @Override
    public ArrayList<Photo> loadData(Context context, ArrayList<Photo> photos) {
        SharedPreferences preferences = context.getSharedPreferences(SHARED_KEY, Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = preferences.getString(PHOTO_DATA, null);
        Type type = new TypeToken<ArrayList<Photo>>() {
        }.getType();
        photos = gson.fromJson(json, type);
        if (photos != null) {
            sendArray.sendArray(photos);
        }
        favouritePhotoView.showMessage(context, "Tải Ảnh");
        return photos;
    }


}
