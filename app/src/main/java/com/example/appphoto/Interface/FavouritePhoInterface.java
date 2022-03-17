package com.example.appphoto.Interface;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;

import com.example.appphoto.Model.Photo;

import java.util.ArrayList;

public interface FavouritePhoInterface {

    interface FavouritePhotoPresenter{
        void saveData(Context context, ArrayList<Photo> photos);
        ArrayList<Photo> loadData(Context context, ArrayList<Photo> photos);
        void getData(@NonNull Bundle result, ArrayList<Photo> photoFavourite);
    }

    interface FavouritePhotoView{
        void showMessage(Context context, String message);
    }

    interface SendArray{
        void sendArray(ArrayList<Photo> photoArrayList);
    }
}
