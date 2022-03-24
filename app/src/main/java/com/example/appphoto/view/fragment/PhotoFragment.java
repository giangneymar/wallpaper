package com.example.appphoto.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appphoto.adapter.PhotoAdapter;
import com.example.appphoto.databinding.FragmentPhotoBinding;
import com.example.appphoto.model.Photo;
import com.example.appphoto.presenter.PhotoPresenter;
import com.example.appphoto.response.IPhotoCallback;

import java.util.ArrayList;

public class PhotoFragment extends Fragment implements IPhotoCallback {
    private View view;
    private FragmentPhotoBinding binding;
    private PhotoPresenter photoPresenter;

    public PhotoFragment() {}

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
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
        photoPresenter = new PhotoPresenter(this);
    }

    public void setRecyclerView() {
        ArrayList<Photo> photos = new ArrayList<>();
        photoPresenter.initListPhoto(photos);
        PhotoAdapter photoAdapter = new PhotoAdapter(getActivity(), photos);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(view.getContext(), 3);
        binding.recyclerView.setLayoutManager(layoutManager);
        binding.recyclerView.setAdapter(photoAdapter);
    }

    //handle communicate presenter and view
    @Override
    public void onLikeSuccess(Context context, Bundle bundle) {

    }
}