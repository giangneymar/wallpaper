package com.example.appphoto.View.Fragment;

import static com.example.appphoto.Utils.PHOTO_DATA;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentResultListener;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appphoto.Adapter.FavouritePhotoAdapter;
import com.example.appphoto.Interface.FavouritePhoInterface;
import com.example.appphoto.Model.Photo;
import com.example.appphoto.Presenter.FavouritePhotoPresenter;
import com.example.appphoto.databinding.FragmentFavouritePhotoBinding;

import java.util.ArrayList;

public class FavouritePhotoFragment extends Fragment implements FavouritePhoInterface.FavouritePhotoView, FavouritePhoInterface.SendArray {

    private FragmentFavouritePhotoBinding binding;
    private View view;
    private FavouritePhotoAdapter favouritePhotoAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<Photo> photoFavourite;
    private FavouritePhotoPresenter favouritePhotoPresenter;

    public FavouritePhotoFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentFavouritePhotoBinding.inflate(inflater, container, false);
        view = binding.getRoot();

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        initPhotoFavourite();
        initFavouritePhotoPresenter();

        favouritePhotoPresenter.loadData(getActivity(),photoFavourite);
        sendArray(photoFavourite);

        setRecyclerView();
        receiveDataFromFragmentPhoto();
    }

    private void initFavouritePhotoPresenter() {
        favouritePhotoPresenter = new FavouritePhotoPresenter(this);
        favouritePhotoPresenter.setFavouritePhotoView(this);
    }

    private ArrayList<Photo> initPhotoFavourite() {
        if (photoFavourite == null) {
            photoFavourite = new ArrayList<>();
        }
        return photoFavourite;
    }

    private void setRecyclerView() {
        favouritePhotoAdapter = new FavouritePhotoAdapter(getActivity(), photoFavourite);
        layoutManager = new GridLayoutManager(getActivity(), 3);
        binding.recyclerView.setLayoutManager(layoutManager);
        binding.recyclerView.setAdapter(favouritePhotoAdapter);
    }

    private void receiveDataFromFragmentPhoto() {
        FragmentManager fragmentManager = getParentFragmentManager();
        fragmentManager.setFragmentResultListener(PHOTO_DATA, getViewLifecycleOwner(), new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                favouritePhotoPresenter.getData(result, photoFavourite);
                checkPhotoExist();
                favouritePhotoPresenter.saveData(getContext(), photoFavourite);
                setRecyclerView();
            }
        });
    }

    private void checkPhotoExist(){
        boolean isPhotoExist = false;
        for (int i = 0; i < photoFavourite.size(); i++) {
            for (int j = 0; j < photoFavourite.size(); j++) {
                if (photoFavourite.get(i).getImage() == photoFavourite.get(j).getImage() && i != j) {
                    photoFavourite.remove(j);
                    showMessage(getActivity(), "Ảnh Đã Tồn Tại Trong Mục Yêu Thích");
                    favouritePhotoAdapter.notifyDataSetChanged();
                    isPhotoExist = true;
                    break;
                }
            }
        }

        if (isPhotoExist == false) {
            showMessage(getActivity(), "Đã Thích Ảnh");
        }
    }

    @Override
    public void showMessage(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void sendArray(ArrayList<Photo> photoArrayList) {
        photoFavourite = photoArrayList;
    }
}