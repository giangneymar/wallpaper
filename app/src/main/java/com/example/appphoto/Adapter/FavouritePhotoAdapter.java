package com.example.appphoto.Adapter;

import static com.example.appphoto.Utils.PHOTO_INFORMATION;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appphoto.Model.Photo;
import com.example.appphoto.Presenter.FavouritePhotoPresenter;
import com.example.appphoto.R;
import com.example.appphoto.View.Activity.PhotoDetailActivity;
import com.example.appphoto.databinding.RowImageFavouritePhotoBinding;

import java.util.ArrayList;

public class FavouritePhotoAdapter extends RecyclerView.Adapter<FavouritePhotoAdapter.ItemHolder> {

    private Context context;
    private ArrayList<Photo> photosFavorite;
    private FavouritePhotoPresenter favouritePhotoPresenter;


    public FavouritePhotoAdapter(Context context, ArrayList<Photo> photosFavorite) {
        this.context = context;
        this.photosFavorite = photosFavorite;
        favouritePhotoPresenter = new FavouritePhotoPresenter();
    }

    @NonNull
    @Override
    public ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_image_favourite_photo, null);
        ItemHolder itemHolder = new ItemHolder(view);

        return itemHolder;
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

    public class ItemHolder extends RecyclerView.ViewHolder {
        private RowImageFavouritePhotoBinding binding;

        public ItemHolder(@NonNull View itemView) {
            super(itemView);
            binding = RowImageFavouritePhotoBinding.bind(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, PhotoDetailActivity.class);
                    intent.putExtra(PHOTO_INFORMATION, photosFavorite.get(getLayoutPosition()));
                    context.startActivity(intent);
                }
            });
            binding.imgBtnDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    photosFavorite.remove(photosFavorite.get(getLayoutPosition()));
                    notifyDataSetChanged();
                    favouritePhotoPresenter.saveData(context, photosFavorite);
                }
            });
        }
    }
}
