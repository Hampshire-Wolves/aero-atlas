package com.hampshirewolves.aeroatlas.profilepage;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.hampshirewolves.aeroatlas.LoginActivity;
import com.hampshirewolves.aeroatlas.MainActivity;
import com.hampshirewolves.aeroatlas.R;


public class UserPageFragment extends Fragment {


    public UserPageFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user_page, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button userPageLogoutButton = view.findViewById(R.id.userPageLogoutButton);

        userPageLogoutButton.setOnClickListener(v -> {
            FirebaseAuth.getInstance().signOut();

            Intent intent = new Intent(getActivity(), MainActivity.class);
            startActivity(intent);
        });

    }
}