package com.example.appphoto.presenter;

import android.app.Activity;
import android.app.WallpaperManager;
import android.content.Context;
import android.graphics.Bitmap;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.appphoto.model.Photo;
import com.example.appphoto.response.IPhotoDetailCallBack;

import java.io.IOException;

public class PhotoDetailPresenter {
    private IPhotoDetailCallBack callBack;
    WallpaperManager wallpaperManager;

    public PhotoDetailPresenter(IPhotoDetailCallBack callBack) {
        this.callBack = callBack;
    }

    public void setWallpaper(Context context, Photo photo) {
        wallpaperManager = WallpaperManager.getInstance(context.getApplicationContext());
        Glide.with(context.getApplicationContext()).asBitmap().load(photo.getImage()).listener(new RequestListener<Bitmap>() {
            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Bitmap> target, boolean isFirstResource) {
                return false;
            }

            @Override
            public boolean onResourceReady(Bitmap resource, Object model, Target<Bitmap> target, DataSource dataSource, boolean isFirstResource) {
                try {
                    ((Activity) context).runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            callBack.onSetWallpaperSuccess();
                        }
                    });
                    wallpaperManager.setBitmap(resource);
                } catch (IOException e) {
                    e.printStackTrace();
                    ((Activity) context).runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            callBack.onSetWallpaperFailed();
                        }
                    });
                }
                return false;
            }
        }).submit();
    }
}
