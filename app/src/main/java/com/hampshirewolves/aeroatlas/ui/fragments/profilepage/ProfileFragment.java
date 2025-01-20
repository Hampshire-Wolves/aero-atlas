package com.hampshirewolves.aeroatlas.ui.fragments.profilepage;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.hampshirewolves.aeroatlas.ui.mainactivity.MainActivity;
import com.hampshirewolves.aeroatlas.R;


public class ProfileFragment extends Fragment {

    TextView userPageEmailTextView;
    Button userPageLogoutButton;

    ImageView userPageBackButton;
    TextView signupPageEmailValue;

    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile_page, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        userPageEmailTextView = view.findViewById(R.id.userPageEmailTextView);
        userPageLogoutButton = view.findViewById(R.id.userPageLogoutButton);
        userPageBackButton = view.findViewById(R.id.userPageBackButton);
        signupPageEmailValue = view.findViewById(R.id.signupPageEmailValue);

        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        if (firebaseUser != null) {
            userPageEmailTextView.setText(firebaseUser.getEmail());
            signupPageEmailValue.setText(firebaseUser.getEmail());
        } else {
            userPageEmailTextView.setText("No email available");
        }

        userPageLogoutButton.setOnClickListener(v -> {
            FirebaseAuth.getInstance().signOut();
            Toast.makeText(requireContext(), "Successfully logged out",
                    Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getActivity(), MainActivity.class);
            startActivity(intent);
        });

        userPageBackButton.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), MainActivity.class);
            startActivity(intent);
        });

    }
}