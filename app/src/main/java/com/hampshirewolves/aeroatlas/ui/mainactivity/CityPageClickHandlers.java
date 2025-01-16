package com.hampshirewolves.aeroatlas.ui.mainactivity;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.hampshirewolves.aeroatlas.citypage.CityPageFragment;


public class CityPageClickHandlers {

    private Context context;

    public void onBackBtnClicked(View view){
        Intent intent = new Intent(context, CityPageFragment.class);

        context.startActivity(intent);
    }
}
