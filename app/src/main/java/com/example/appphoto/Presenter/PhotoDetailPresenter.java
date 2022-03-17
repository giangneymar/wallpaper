package com.example.appphoto.Presenter;

import android.app.Activity;
import android.app.WallpaperManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.appphoto.Interface.PhotoDetailInterface;
import com.example.appphoto.Model.Photo;

import java.io.IOException;

public class PhotoDetailPresenter implements PhotoDetailInterface.PhotoDetailPresenter {

    WallpaperManager wallpaperManager;

    @Override
    public void setWallpaper(Context context, Photo photo) {
        wallpaperManager = WallpaperManager.getInstance(context.getApplicationContext());
        Glide.with(context.getApplicationContext()).asBitmap().load(photo.getImage()).listener(new RequestListener<Bitmap>() {
            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Bitmap> target, boolean isFirstResource) {
                Toast.makeText(context.getApplicationContext(), "Tải Ảnh Thất Bại", Toast.LENGTH_SHORT).show();
                return false;
            }

            @Override
            public boolean onResourceReady(Bitmap resource, Object model, Target<Bitmap> target, DataSource dataSource, boolean isFirstResource) {
                try {
                    ((Activity) context).runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(context.getApplicationContext(), "Đặt Hình Nền Thành Công", Toast.LENGTH_SHORT).show();
                        }
                    });
                    wallpaperManager.setBitmap(resource);
                } catch (IOException e) {
                    e.printStackTrace();
                    ((Activity) context).runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(context.getApplicationContext(), "Đặt Hình Nền Thất Bại", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
                return false;
            }
        }).submit();
    }
}
