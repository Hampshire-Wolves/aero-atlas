package com.hampshirewolves.aeroatlas.Handlers;

import android.content.Context;
import android.widget.Toast;

import com.google.firebase.FirebaseNetworkException;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;


public class FirebaseErrorHandler {

    public static void handleFirebaseException(Exception exception, Context context) {
        if (exception instanceof FirebaseAuthWeakPasswordException) {
            Toast.makeText(context, "Password should be at least 6 characters.", Toast.LENGTH_SHORT).show();
        } else if (exception instanceof FirebaseAuthInvalidCredentialsException) {
            String errorCode = ((FirebaseAuthInvalidCredentialsException) exception).getErrorCode();
            if ("ERROR_INVALID_EMAIL".equals(errorCode)) {
                Toast.makeText(context, "Invalid email format.", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(context, "Invalid credentials. Please check your email or password.", Toast.LENGTH_SHORT).show();
            }
        } else if (exception instanceof FirebaseAuthUserCollisionException) {
            Toast.makeText(context, "This email is already registered.", Toast.LENGTH_SHORT).show();
        } else if (exception instanceof FirebaseNetworkException) {
            Toast.makeText(context, "A network error has occurred.", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Authentication failed. Please try again.", Toast.LENGTH_SHORT).show();
        }
    }
}
