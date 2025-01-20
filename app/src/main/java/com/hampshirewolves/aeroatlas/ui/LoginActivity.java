package com.hampshirewolves.aeroatlas.ui;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.hampshirewolves.aeroatlas.R;
import com.hampshirewolves.aeroatlas.ui.handlers.FirebaseErrorHandler;
import com.hampshirewolves.aeroatlas.ui.handlers.UIHandler;
import com.hampshirewolves.aeroatlas.ui.mainactivity.MainActivity;

public class LoginActivity extends AppCompatActivity {
    EditText loginPageEmailInputBox, loginPagePasswordInputBox;

    ImageView loginPageBackButton;
    Button loginPageLoginButton, loginPageRedirectToRegisterButton;
    private FirebaseAuth mAuth;
    ProgressBar progressBar;

    @Override
    public void onStart() {
        super.onStart();
        // Checks if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            Intent intent =new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
        loginPageBackButton = findViewById(R.id.loginPageBackButton);
        loginPageEmailInputBox = findViewById(R.id.loginPageEmailInputBox);
        loginPagePasswordInputBox = findViewById(R.id.loginPagePasswordInputBox);
        loginPageLoginButton = findViewById(R.id.loginPageLoginButton);
        loginPageRedirectToRegisterButton = findViewById(R.id.loginPageRedirectToRegisterButton);
        progressBar = findViewById(R.id.loginProgressBar);

        loginPageBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.putExtra("navigateTo", "DiscoverPageFragment");
                startActivity(intent);
                finish();
            }
        });

        //opens login activity when the redirect to register button is clicked
        loginPageRedirectToRegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SignupActivity.class);
                startActivity(intent);
                finish();
            }});


        loginPageLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar.setVisibility(View.VISIBLE);
                String email = loginPageEmailInputBox.getText().toString();
                String password = loginPagePasswordInputBox.getText().toString();

                if (TextUtils.isEmpty(email)){
                    UIHandler.handleValidationError(progressBar, LoginActivity.this, "Email cannot be empty");
                    return;
                }

                if (TextUtils.isEmpty(password)){
                    UIHandler.handleValidationError(progressBar, LoginActivity.this, "Password cannot be empty");
                  return;
                }

                mAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                progressBar.setVisibility(View.GONE);
                                if (task.isSuccessful()) {
                                    Log.d(TAG, "signInWithEmail:success");
                                    Toast.makeText(getApplicationContext(), "Successfully logged in",
                                            Toast.LENGTH_SHORT).show();
                                    Intent intent =new Intent(getApplicationContext(), MainActivity.class);
                                    startActivity(intent);
                                    finish();

                                } else {
                                    Log.w(TAG, "signInWithEmail:failure", task.getException());
                                    Exception exception = task.getException();
                                    if (exception != null) {
                                        FirebaseErrorHandler.handleFirebaseException(exception, LoginActivity.this);
                                    }
                                }
                            }
                        });
            }
        });

    }
}