package com.hampshirewolves.aeroatlas.ui.handlers;

import android.content.Context;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

public class UIHandler {

    public static void handleValidationError(ProgressBar progressBar, Context context, String message) {
        progressBar.setVisibility(View.GONE);
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }
}
