package com.hampshirewolves.aeroatlas.ui.fragments.auth;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.hampshirewolves.aeroatlas.R;
import com.hampshirewolves.aeroatlas.ui.fragments.discoverpage.DiscoverPageFragment;
import com.hampshirewolves.aeroatlas.ui.fragments.profilepage.ProfileFragment;

public class UserAuthFragment extends Fragment {
    private FirebaseAuth firebaseAuth;

    public UserAuthFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        firebaseAuth = FirebaseAuth.getInstance();

        checkUserStatus();

        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    private void checkUserStatus() {
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();

        if (firebaseUser != null) {
            navigateToProfilePage();
        } else {
            navigateToDiscoverPage();
        }
    }

    private void navigateToProfilePage() {
        getParentFragmentManager()
                .beginTransaction()
                .replace(R.id.baseFragment, new ProfileFragment())
                .commit();
    }

    private void navigateToDiscoverPage() {
        getParentFragmentManager()
                .beginTransaction()
                .replace(R.id.baseFragment, new DiscoverPageFragment())
                .commit();
    }
}