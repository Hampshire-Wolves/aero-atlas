package com.hampshirewolves.aeroatlas.ui.fragments.flightselectorpage;

import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;

import com.hampshirewolves.aeroatlas.R;

import java.util.Calendar;


public class FlightSelectorFragment extends Fragment {

    private DatePickerDialog datePickerDialog;
    private Button departureButton;

    private CheckBox checkBox;

    public FlightSelectorFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initDatePicker();
        departureButton = departureButton.findViewById(R.id.departurePickerDate);
        departureButton.setText(getTodaysDate());

        checkBox.findViewById(R.id.checkbox);
        checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked){
                departureButton.setVisibility(View.VISIBLE);
            } else {
                departureButton.setVisibility(View.INVISIBLE);
            }
        });
    }

    private String getTodaysDate() {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        month = month + 1;
        int day = cal.get(Calendar.DAY_OF_MONTH);

        return makeDateString(day, month, year);
    };

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
                departureButton.setText(date);
            }

        };
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        datePickerDialog = new DatePickerDialog(this.getContext(),dateSetListener,year,month,day);
        datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());
    }

    private String makeDateString(int day, int month, int year) {
        return getMonthFormat(month) + " " + day + " " + year;
    }

    private String getMonthFormat(int month) {
        if (month==1){
            return "JAN";
        }
        if (month==2){
            return "FEB";
        }
        if (month==3){
            return "MAR";
        }
        if (month==4){
            return "APR";
        }
        if (month==5){
            return "MAY";
        }
        if (month==6){
            return "JUN";
        }
        if (month==7){
            return "JUL";
        }
        if (month==8){
            return "AUG";
        }
        if (month==9){
            return "SEP";
        }
        if (month==10){
            return "OCT";
        }
        if (month==11){
            return "NOV";
        }
        if (month==12){
            return "DEC";
        }

        return "invalid entry please check";
    }


    public void departureDatePicker(View view) {
        datePickerDialog.show();
    }
}