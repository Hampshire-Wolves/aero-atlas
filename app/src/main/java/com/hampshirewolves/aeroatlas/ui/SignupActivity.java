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

public class SignupActivity extends AppCompatActivity {
    EditText signupPageEmailInputBox, signupPagePasswordInputBox, signupPageConfirmPasswordInputBox;
    ImageView signupPageBackButton;
    Button signupPageSignupButton, signupPageRedirectToLoginButton;
    ProgressBar progressBar;
    private FirebaseAuth mAuth;

    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_signup);

        mAuth = FirebaseAuth.getInstance();

        signupPageBackButton = findViewById(R.id.signupPageBackButton);
        signupPageEmailInputBox = findViewById(R.id.signupPageEmailInputBox);
        signupPagePasswordInputBox = findViewById(R.id.signupPagePasswordInputBox);
        signupPageConfirmPasswordInputBox = findViewById(R.id.signupPageConfirmPasswordInputBox);
        signupPageSignupButton = findViewById(R.id.signupPageSignupButton);
        signupPageRedirectToLoginButton = findViewById(R.id.signupPageRedirectToLoginButton);
        progressBar = findViewById(R.id.signupProgressBar);

        signupPageBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.putExtra("navigateTo", "DiscoverPageFragment");
                startActivity(intent);
                finish();
            }
        });

        signupPageRedirectToLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

        signupPageSignupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar.setVisibility(View.VISIBLE);

                String email = signupPageEmailInputBox.getText().toString();
                String password = String.valueOf(signupPagePasswordInputBox.getText());
                String confirmPassword = String.valueOf(signupPageConfirmPasswordInputBox.getText());

                if (TextUtils.isEmpty(email)) {
                    UIHandler.handleValidationError(progressBar, SignupActivity.this, "Email cannot be empty");
                    return;
                }

                if (TextUtils.isEmpty(password) || TextUtils.isEmpty(confirmPassword)) {
                    UIHandler.handleValidationError(progressBar, SignupActivity.this, "Password cannot be empty");
                    return;
                }

                if (!password.equals(confirmPassword)) {
                    UIHandler.handleValidationError(progressBar, SignupActivity.this, "Passwords do not match");
                    return;
                }

                mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressBar.setVisibility(View.GONE);
                        if (task.isSuccessful()) {
                            Log.d(TAG, "createUserWithEmail:success");
                            Toast.makeText(SignupActivity.this, "Account created", Toast.LENGTH_SHORT).show();

                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(intent);
                            finish();

                        } else {
                            Exception exception = task.getException();
                            if (exception != null) {
                                FirebaseErrorHandler.handleFirebaseException(exception, SignupActivity.this);
                            }
                        }
                    }
                });
            }
        });
    }
    }