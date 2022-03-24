package com.example.appphoto.presenter;

import static com.example.appphoto.utils.KeyConstants.PHOTO;
import static com.example.appphoto.utils.KeyConstants.PHOTO_ID;

import android.content.Context;
import android.os.Bundle;

import com.example.appphoto.model.Photo;
import com.example.appphoto.repositories.PhotoRepository;
import com.example.appphoto.response.IPhotoCallback;

import java.util.ArrayList;

public class PhotoPresenter {
    private final IPhotoCallback callback;
    private final PhotoRepository photoRepository;

    public PhotoPresenter(IPhotoCallback callback) {
        photoRepository = new PhotoRepository();
        this.callback = callback;
    }

    public void likePhoto(Context context, Photo photo) {
        Bundle bundle = new Bundle();
        bundle.putInt(PHOTO_ID, photo.getId());
        bundle.putInt(PHOTO, photo.getImage());
        callback.onLikeSuccess(context, bundle);
    }

    public void initListPhoto(ArrayList<Photo> photos) {
        photoRepository.initListPhoto(photos);
    }
}
