package com.example.appphoto.adapter;

import static com.example.appphoto.utils.KeyConstants.PHOTO_DATA;
import static com.example.appphoto.utils.KeyConstants.PHOTO_INFORMATION;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appphoto.databinding.RowImagePhotoBinding;
import com.example.appphoto.model.Photo;
import com.example.appphoto.presenter.PhotoPresenter;
import com.example.appphoto.response.IPhotoCallback;
import com.example.appphoto.view.activity.PhotoDetailActivity;

import java.util.ArrayList;

public class PhotoAdapter extends RecyclerView.Adapter<PhotoAdapter.ItemHolder> implements IPhotoCallback {
    private final Context context;
    private final ArrayList<Photo> photos;
    private final PhotoPresenter photoPresenter;

    public PhotoAdapter(Context context, ArrayList<Photo> photos) {
        this.context = context;
        this.photos = photos;
        photoPresenter = new PhotoPresenter(this);
    }

    public class ItemHolder extends RecyclerView.ViewHolder {
        private final RowImagePhotoBinding binding;

        public ItemHolder(RowImagePhotoBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            binding.getRoot().setOnClickListener(view -> {
                Intent intent = new Intent(context, PhotoDetailActivity.class);
                intent.putExtra(PHOTO_INFORMATION, photos.get(getLayoutPosition()));
                context.startActivity(intent);
            });
            binding.imgBtnLike.setOnClickListener(view -> photoPresenter.likePhoto(context, photos.get(getLayoutPosition()))
            );
        }
    }

    //handle recyclerview
    @NonNull
    @Override
    public ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        RowImagePhotoBinding binding = RowImagePhotoBinding.inflate(layoutInflater, parent, false);
        return new ItemHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemHolder holder, int position) {
        Photo photo = photos.get(position);
        holder.binding.img.setImageResource(photo.getImage());
    }

    @Override
    public int getItemCount() {
        return photos.size();
    }

    //handle communicate presenter and view
    @Override
    public void onLikeSuccess(Context context, Bundle bundle) {
        FragmentManager manager = ((AppCompatActivity) context).getSupportFragmentManager();
        manager.setFragmentResult(PHOTO_DATA, bundle);
    }
}
