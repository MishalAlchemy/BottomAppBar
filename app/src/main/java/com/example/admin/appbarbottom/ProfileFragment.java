package com.example.admin.appbarbottom;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;

import adapter.ItemAdapter;
import interfaces.CustomItemClick;
import model.Profile;

public class ProfileFragment extends Fragment implements CustomItemClick {
    MainActivity mainActivity;
    public static final String TAG = ProfileFragment.class.getSimpleName();


    public static ProfileFragment newInstance() {
        return new ProfileFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_profile, container, false);
        mainActivity = (MainActivity) getActivity();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));

        recyclerView.setAdapter(new ItemAdapter(Utils.generateAnimalItems(getContext()), this));

        recyclerView.addOnScrollListener(new HideShowScrollListener() {
            @Override
            public void onHide() {
                hideViews();
            }

            @Override
            public void onShow() {
                showViews();
            }
        });
    }

    public void hideViews() {
        // TODO (-mToolbar)  plus means  2 view above ho jaye or not visible to user
        mainActivity.bottomAppBar.animate().translationY(-mainActivity.bottomAppBar.getHeight()).setInterpolator(new AccelerateInterpolator(2));

        // TODO uncomment this Hide Footer in android when Scrolling
        // TODO (+mToolbar)  plus means  2 view forward ho jaye or not visible to user
        mainActivity.bottomAppBar.animate().translationY(+mainActivity.bottomAppBar.getHeight()).setInterpolator(new AccelerateInterpolator(2));
    }

    public void showViews() {
        mainActivity.bottomAppBar.animate().translationY(0).setInterpolator(new DecelerateInterpolator(2));

        // TODO uncomment this Hide Footer in android when Scrolling
        mainActivity.bottomAppBar.animate().translationY(0).setInterpolator(new DecelerateInterpolator(2));

    }

    @Override
    public void onClick(int position, Profile profile, ImageView view) {
        Fragment fragment = DetailProfileFragment.newInstance(profile, ViewCompat.getTransitionName(view));
        mainActivity.addFragment(fragment, true, fragment.getClass().getName(), false, false, null, false, true, view);
        mainActivity.setToolbar("Animal Detail", true);
        mainActivity.toggleFabButton();
        showViews();
    }


    public abstract class HideShowScrollListener extends RecyclerView.OnScrollListener {
        private static final int HIDE_THRESHOLD = 20;
        private int scrolledDistance = 0;
        private boolean controlsVisible = true;

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);

            if (scrolledDistance > HIDE_THRESHOLD && controlsVisible) {
                onHide();
                controlsVisible = false;
                scrolledDistance = 0;
            } else if (scrolledDistance < -HIDE_THRESHOLD && !controlsVisible) {
                onShow();
                controlsVisible = true;
                scrolledDistance = 0;
            }

            if ((controlsVisible && dy > 0) || (!controlsVisible && dy < 0)) {
                scrolledDistance += dy;
            }
        }

        public abstract void onHide();

        public abstract void onShow();

    }


}
