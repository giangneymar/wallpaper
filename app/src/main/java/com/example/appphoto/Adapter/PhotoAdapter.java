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
import com.example.appphoto.Presenter.PhotoPresenter;
import com.example.appphoto.R;
import com.example.appphoto.View.Activity.PhotoDetailActivity;
import com.example.appphoto.databinding.RowImagePhotoBinding;

import java.util.ArrayList;

public class PhotoAdapter extends RecyclerView.Adapter<PhotoAdapter.ItemHolder> {

    private Context context;
    private ArrayList<Photo> photos;
    private PhotoPresenter photoPresenter;


    public PhotoAdapter(Context context, ArrayList<Photo> photos) {
        this.context = context;
        this.photos = photos;
        photoPresenter = new PhotoPresenter();
    }

    @NonNull
    @Override
    public ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_image_photo, null);
        ItemHolder itemHolder = new ItemHolder(view);
        return itemHolder;
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

    public class ItemHolder extends RecyclerView.ViewHolder {
        private RowImagePhotoBinding binding;

        public ItemHolder(@NonNull View itemView) {
            super(itemView);
            binding = RowImagePhotoBinding.bind(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, PhotoDetailActivity.class);
                    intent.putExtra(PHOTO_INFORMATION, photos.get(getLayoutPosition()));
                    context.startActivity(intent);
                }
            });
            binding.imgBtnLike.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    photoPresenter.sendDataToFavouritePhotoFragment(context, photos.get(getLayoutPosition()));
                }
            });
        }

    }


}
