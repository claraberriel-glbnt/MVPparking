package com.claraberriel.mvpparking.mvp.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.claraberriel.mvpparking.R;
import com.claraberriel.mvpparking.activities.ReleaseFragment;
import com.claraberriel.mvpparking.activities.ReserveFragment;
import com.claraberriel.mvpparking.databinding.ActivityMainBinding;

public class NavigationView extends ParkingSizeView {
    private final ActivityMainBinding binding;
    private Context context;

    public NavigationView(Activity activity, ActivityMainBinding binding) {
        super(activity, binding);
        this.binding = binding;
        this.context = getContext();
    }

    private void showReserve() {
        showToast((getContext()).getString(R.string.nav_msg_toast_reserve));
        context.startActivity(new Intent(context, ReserveFragment.class));
    }

    private void showRelease() {
        showToast((getContext()).getString(R.string.nav_msg_toast_release));
        context.startActivity(new Intent(context, ReleaseFragment.class));
    }
}
