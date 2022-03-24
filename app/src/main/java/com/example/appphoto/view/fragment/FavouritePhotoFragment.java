package com.example.appphoto.view.fragment;

import static com.example.appphoto.utils.KeyConstants.PHOTO_DATA;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appphoto.R;
import com.example.appphoto.adapter.FavouritePhotoAdapter;
import com.example.appphoto.databinding.FragmentFavouritePhotoBinding;
import com.example.appphoto.model.Photo;
import com.example.appphoto.presenter.FavouritePhotoPresenter;
import com.example.appphoto.response.IPhotoFavouriteCallback;
import com.example.appphoto.utils.ShowMessage;

import java.util.ArrayList;

public class FavouritePhotoFragment extends Fragment implements IPhotoFavouriteCallback {

    private FragmentFavouritePhotoBinding binding;
    private FavouritePhotoAdapter favouritePhotoAdapter;
    private ArrayList<Photo> photoFavourite;
    private FavouritePhotoPresenter favouritePhotoPresenter;

    public FavouritePhotoFragment() {
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentFavouritePhotoBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        initPhotoFavourite();
        initFavouritePhotoPresenter();
        loadDataFromPref();
        sendListPhoto(photoFavourite);
        setRecyclerView();
        receiveDataFromFragmentPhoto();
    }

    private void initPhotoFavourite() {
        if (photoFavourite == null) {
            photoFavourite = new ArrayList<>();
        }
    }

    private void initFavouritePhotoPresenter() {
        favouritePhotoPresenter = new FavouritePhotoPresenter(this);
    }

    private void loadDataFromPref() {
        favouritePhotoPresenter.loadDataFromPref(requireActivity());
    }

    private void setRecyclerView() {
        if (favouritePhotoAdapter == null) {
            favouritePhotoAdapter = new FavouritePhotoAdapter(getActivity(), photoFavourite);
        }
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getActivity(), 3);
        binding.recyclerView.setLayoutManager(layoutManager);
        binding.recyclerView.setAdapter(favouritePhotoAdapter);
    }

    private void receiveDataFromFragmentPhoto() {
        FragmentManager fragmentManager = getParentFragmentManager();
        fragmentManager.setFragmentResultListener(PHOTO_DATA, getViewLifecycleOwner(), (requestKey, result) -> {
            favouritePhotoPresenter.receiveDataFromFragmentPhoto(result, photoFavourite);
            checkPhotoExist();
            favouritePhotoPresenter.saveDataIntoPref(requireContext(), photoFavourite);
            setRecyclerView();
        });
    }

    @SuppressLint("NotifyDataSetChanged")
    private void checkPhotoExist() {
        boolean isPhotoExist = false;
        for (int i = 0; i < photoFavourite.size(); i++) {
            for (int j = 0; j < photoFavourite.size(); j++) {
                if (photoFavourite.get(i).getImage() == photoFavourite.get(j).getImage() && i != j) {
                    photoFavourite.remove(j);
                    ShowMessage.show(getView(), R.string.photo_exist);
                    favouritePhotoAdapter.notifyDataSetChanged();
                    isPhotoExist = true;
                    break;
                }
            }
        }
        if (!isPhotoExist) {
            ShowMessage.show(getView(), R.string.liked);
        }
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
        photoFavourite = photos;
    }
}