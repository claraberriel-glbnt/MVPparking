package com.claraberriel.mvpparking.mvp.view;

import android.app.Activity;
import android.content.Context;
import android.app.FragmentManager;

import androidx.annotation.Nullable;

import java.lang.ref.WeakReference;

public class ParkingActivityView {
    private WeakReference<Activity> activityRef;

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

    @Nullable
    public FragmentManager getFragmentManager() {
        Activity activity = getActivity();
        return (activity != null) ? activity.getFragmentManager() : null;
    }
}
