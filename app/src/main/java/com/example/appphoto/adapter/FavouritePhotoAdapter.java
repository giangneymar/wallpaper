package com.example.appphoto.adapter;

import static com.example.appphoto.utils.KeyConstants.PHOTO_INFORMATION;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appphoto.R;
import com.example.appphoto.databinding.RowImageFavouritePhotoBinding;
import com.example.appphoto.model.Photo;
import com.example.appphoto.presenter.FavouritePhotoPresenter;
import com.example.appphoto.response.IPhotoFavouriteCallback;
import com.example.appphoto.utils.ShowMessage;
import com.example.appphoto.view.activity.PhotoDetailActivity;

import java.util.ArrayList;

public class FavouritePhotoAdapter extends RecyclerView.Adapter<FavouritePhotoAdapter.ItemHolder> implements IPhotoFavouriteCallback {
    private final Context context;
    private final ArrayList<Photo> photosFavorite;
    private final FavouritePhotoPresenter favouritePhotoPresenter;

    public FavouritePhotoAdapter(Context context, ArrayList<Photo> photosFavorite) {
        this.context = context;
        this.photosFavorite = photosFavorite;
        favouritePhotoPresenter = new FavouritePhotoPresenter(this);
    }

    public class ItemHolder extends RecyclerView.ViewHolder {
        private final RowImageFavouritePhotoBinding binding;

        @SuppressLint("NotifyDataSetChanged")
        public ItemHolder(RowImageFavouritePhotoBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            binding.getRoot().setOnClickListener(view -> {
                Intent intent = new Intent(context, PhotoDetailActivity.class);
                intent.putExtra(PHOTO_INFORMATION, photosFavorite.get(getLayoutPosition()));
                context.startActivity(intent);
            });
            binding.imgBtnDelete.setOnClickListener(view -> {
                photosFavorite.remove(photosFavorite.get(getLayoutPosition()));
                ShowMessage.show(view, R.string.deleted_photo);
                notifyDataSetChanged();
                favouritePhotoPresenter.saveDataIntoPref(context, photosFavorite);
            });
        }
    }

    //handle recyclerview
    @NonNull
    @Override
    public ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        RowImageFavouritePhotoBinding binding = RowImageFavouritePhotoBinding.inflate(layoutInflater, parent, false);
        return new ItemHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemHolder holder, int position) {
        Photo photo = photosFavorite.get(position);
        holder.binding.img.setImageResource(photo.getImage());
    }

    @Override
    public int getItemCount() {
        return photosFavorite.size();
    }

    //handle communicate presenter and view
    @Override
    public void onReceiveSuccess() {

    }

    @Override
    public void onSaveDataIntoPrefSuccess() {

    }

    @Override
    public void sendListPhoto(ArrayList<Photo> photos) {

    }
}
