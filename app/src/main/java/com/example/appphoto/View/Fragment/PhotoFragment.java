package com.example.appphoto.View.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appphoto.Adapter.PhotoAdapter;
import com.example.appphoto.Model.Photo;
import com.example.appphoto.Presenter.PhotoPresenter;
import com.example.appphoto.R;
import com.example.appphoto.databinding.FragmentPhotoBinding;

import java.util.ArrayList;


public class PhotoFragment extends Fragment {


    private View view;
    private FragmentPhotoBinding binding;
    private ArrayList<Photo> photos;
    private PhotoAdapter photoAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private PhotoPresenter photoPresenter;

    public PhotoFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentPhotoBinding.inflate(inflater, container, false);
        view = binding.getRoot();

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        initPhotoPresenter();
        setRecyclerView();
    }

    private void initPhotoPresenter(){
        photoPresenter = new PhotoPresenter();
    }

    public void setRecyclerView() {
        photos = new ArrayList<>();
        initListPhoto();
        photoAdapter = new PhotoAdapter(getActivity(), photos);
        layoutManager = new GridLayoutManager(view.getContext(), 3);
        binding.recyclerView.setLayoutManager(layoutManager);
        binding.recyclerView.setAdapter(photoAdapter);
    }

    private void initListPhoto() {
        photos.add(new Photo(1, R.drawable.photo_bg1));
        photos.add(new Photo(2, R.drawable.photo_bg2));
        photos.add(new Photo(3, R.drawable.photo_bg3));
        photos.add(new Photo(4, R.drawable.photo_bg4));
        photos.add(new Photo(5, R.drawable.photo_bg5));
    }

}