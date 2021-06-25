package com.claraberriel.mvpparking.activities;

import android.app.Activity;
import android.os.Bundle;

import com.claraberriel.mvpparking.databinding.ActivityNavigationBinding;

public class NavActivity extends Activity {
    private ActivityNavigationBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityNavigationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}
