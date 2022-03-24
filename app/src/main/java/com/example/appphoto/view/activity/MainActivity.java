package com.example.appphoto.view.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.appphoto.R;
import com.example.appphoto.adapter.ViewPagerAdapter;
import com.example.appphoto.databinding.ActivityMainBinding;
import com.example.appphoto.utils.ShowMessage;
import com.example.appphoto.view.fragment.FavouritePhotoFragment;
import com.example.appphoto.view.fragment.PhotoFragment;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private long backPressTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initViewPager();
    }

    private void initViewPager() {
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPagerAdapter.addFragments(new PhotoFragment(), getString(R.string.photo));
        viewPagerAdapter.addFragments(new FavouritePhotoFragment(), getString(R.string.favourite));
        binding.viewPager.setAdapter(viewPagerAdapter);
        binding.tabLayout.setupWithViewPager(binding.viewPager);
    }

    @Override
    public void onBackPressed() {
        if (backPressTime + 2000 > System.currentTimeMillis()) {
            super.onBackPressed();
            return;
        } else {
            ShowMessage.show(getCurrentFocus(), R.string.favourite);
        }
        backPressTime = System.currentTimeMillis();
    }
}