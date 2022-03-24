package com.example.appphoto.view.activity;

import static com.example.appphoto.utils.KeyConstants.PHOTO_INFORMATION;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.appphoto.R;
import com.example.appphoto.databinding.ActivityMainBinding;
import com.example.appphoto.databinding.ActivityPhotoDetailBinding;
import com.example.appphoto.model.Photo;
import com.example.appphoto.presenter.PhotoDetailPresenter;
import com.example.appphoto.response.IPhotoDetailCallBack;
import com.example.appphoto.utils.ShowMessage;

public class PhotoDetailActivity extends AppCompatActivity implements IPhotoDetailCallBack {
    private ActivityPhotoDetailBinding binding;
    private ActivityMainBinding mainBinding;
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
        runOnUiThread(() -> {
        });
    }

    private void initPhotoDetailPresenter() {
        photoDetailPresenter = new PhotoDetailPresenter(this);
    }

    private void getInfoPhoto() {
        photo = (Photo) getIntent().getSerializableExtra(PHOTO_INFORMATION);
        binding.img.setImageResource(photo.getImage());
    }

    private void setListener() {
        binding.btnSetWallpaper.setOnClickListener(view -> photoDetailPresenter.setWallpaper(PhotoDetailActivity.this, photo));
    }

    //handle communicate presenter and view
    @Override
    public void onSetWallpaperSuccess() {
        ShowMessage.show(binding.getRoot(), R.string.set_wallpaper_success);
    }

    @Override
    public void onSetWallpaperFailed() {
        ShowMessage.show(binding.getRoot(), R.string.set_wallpaper_failed);
    }
}