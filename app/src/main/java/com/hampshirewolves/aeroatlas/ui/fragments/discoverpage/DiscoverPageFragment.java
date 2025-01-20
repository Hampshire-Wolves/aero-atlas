package com.hampshirewolves.aeroatlas.ui.fragments.discoverpage;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.hampshirewolves.aeroatlas.ui.LoginActivity;
import com.hampshirewolves.aeroatlas.ui.mainactivity.MainActivity;
import com.hampshirewolves.aeroatlas.R;
import com.hampshirewolves.aeroatlas.ui.SignupActivity;
import com.hampshirewolves.aeroatlas.ui.mainactivity.RecyclerViewInterface;

public class DiscoverPageFragment extends Fragment implements RecyclerViewInterface {

    MenuItem profileButton;

    public DiscoverPageFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_discover_page, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button profilePageLoginButton = view.findViewById(R.id.profilePageLoginButton);
        Button profilePageSignupButton = view.findViewById(R.id.profilePageSignupButton);
        ImageView discoverPageBackButton = view.findViewById(R.id.discoverPageBackButton);

        profilePageLoginButton.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), LoginActivity.class);
            startActivity(intent);
        });

        profilePageSignupButton.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), SignupActivity.class);
            startActivity(intent);
        });

        discoverPageBackButton.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), MainActivity.class);
            startActivity(intent);
        });
    }

    @Override
    public void onItemClick(int position) {

    }
}