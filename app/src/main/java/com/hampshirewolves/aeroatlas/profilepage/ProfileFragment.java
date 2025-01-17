package com.hampshirewolves.aeroatlas.profilepage;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.hampshirewolves.aeroatlas.R;

public class ProfileFragment extends Fragment {

    private FirebaseAuth firebaseAuth;

    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        firebaseAuth = FirebaseAuth.getInstance();

        checkUserStatus();

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    private void checkUserStatus() {
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();

        if (firebaseUser != null) {
            navigateToUserPage();
        } else {
            navigateToDiscoverPage();
        }
    }

    private void navigateToUserPage() {
        getParentFragmentManager()
                .beginTransaction()
                .replace(R.id.baseFragment, new UserPageFragment())
                .commit();
    }

    private void navigateToDiscoverPage() {
        getParentFragmentManager()
                .beginTransaction()
                .replace(R.id.baseFragment, new DiscoverPageFragment())
                .commit();
    }
}