package com.hampshirewolves.aeroatlas.flightselectorpage;

import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;

import com.hampshirewolves.aeroatlas.R;


public class FlightSelectorFragment extends Fragment {

    private DatePickerDialog datePickerDialog;
    private Button departureButton;

    public FlightSelectorFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_flight_selector, container, false);
    }

    void initDatePicker(){
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int day, int month, int year) {
                month = month +1 ;
                String date = makeDateString(day,month,year);
            }
        };
    }

    private String makeDateString(int day, int month, int year) {
        return day + " " + month + " " + year;
    }

    ;

    public void departureDatePicker(View view) {
    }
}