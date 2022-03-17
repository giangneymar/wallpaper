package com.example.appphoto.Presenter;

import static com.example.appphoto.Utils.PHOTO;
import static com.example.appphoto.Utils.PHOTO_DATA;
import static com.example.appphoto.Utils.PHOTO_ID;

import android.content.Context;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.example.appphoto.Interface.PhotoInterface;
import com.example.appphoto.Model.Photo;

public class PhotoPresenter implements PhotoInterface.PhotoPresenter {

    @Override
    public void sendDataToFavouritePhotoFragment(Context context, Photo photo) {
        Bundle bundle = new Bundle();
        bundle.putInt(PHOTO_ID, photo.getId());
        bundle.putInt(PHOTO, photo.getImage());
        FragmentManager manager = ((AppCompatActivity) context).getSupportFragmentManager();
        manager.setFragmentResult(PHOTO_DATA, bundle);

    }

}
