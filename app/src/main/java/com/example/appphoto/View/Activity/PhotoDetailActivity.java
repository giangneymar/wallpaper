package com.example.appphoto.View.Activity;

import static com.example.appphoto.Utils.PHOTO_INFORMATION;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.appphoto.Model.Photo;
import com.example.appphoto.Presenter.PhotoDetailPresenter;
import com.example.appphoto.databinding.ActivityPhotoDetailBinding;

public class PhotoDetailActivity extends AppCompatActivity {

    private ActivityPhotoDetailBinding binding;
    private Photo photo;
    private PhotoDetailPresenter photoDetailPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPhotoDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initPhotoDetailPresenter();
        getInfoPhoto();
        setListener();

        runOnUiThread(new Runnable() {
            @Override
            public void run() {

            }
        });
    }

    private void initPhotoDetailPresenter() {
        photoDetailPresenter = new PhotoDetailPresenter();
    }

    private void setListener() {
        binding.btnSetWallpaper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                photoDetailPresenter.setWallpaper(PhotoDetailActivity.this, photo);
            }
        });
    }

    private void getInfoPhoto() {
        photo = (Photo) getIntent().getSerializableExtra(PHOTO_INFORMATION);
        binding.img.setImageResource(photo.getImage());
    }

}