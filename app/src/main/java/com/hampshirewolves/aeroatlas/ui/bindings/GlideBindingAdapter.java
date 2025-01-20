package com.hampshirewolves.aeroatlas.ui.bindings;

import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.hampshirewolves.aeroatlas.R;

public class GlideBindingAdapter {
    @BindingAdapter(value = {"imageUrl"}, requireAll = false)
    public static void getImageUrl(ImageView view, @Nullable String url) {
        if (url != null && !url.trim().isEmpty()) {
            Glide.with(view.getContext())
                    .load(url)
                    .into(view);
        } else {
            view.setImageResource(R.drawable.modern_city_bg);
        }
    }
}
