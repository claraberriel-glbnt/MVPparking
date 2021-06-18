package com.claraberriel.mvpparking.mvp.view;

import android.app.Activity;
import android.content.Context;

import androidx.annotation.Nullable;

import java.lang.ref.WeakReference;

public class ParkingActivityView {
    private final WeakReference<Activity> activityRef;

    public ParkingActivityView(Activity activity) {
        activityRef = new WeakReference<>(activity);
    }

    @Nullable
    public Activity getActivity() {
        return activityRef.get();
    }

    @Nullable
    public Context getContext() {
        return getActivity();
    }

}
