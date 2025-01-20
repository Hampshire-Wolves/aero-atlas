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
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initDatePicker();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_flight_selector, container, false);


        // TODO: add functionality to date pickers & make return date only appear when checkbox is checked
        Button returnDateBtn = view.findViewById(R.id.returnPickerDate);

        checkBox = view.findViewById(R.id.checkbox);
        checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                returnDateBtn.setVisibility(View.VISIBLE);
            } else {
                returnDateBtn.setVisibility(View.INVISIBLE);
            }
        });

        departureButton = view.findViewById(R.id.oneWayFlightButton);
        departureButton.setText(getTodaysDate());

        departureButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initDatePicker();
                departureDatePicker(v);
            }
        });

        return view;
    }

    private void initDatePicker() {
        DatePickerDialog.OnDateSetListener dateSetListener = (datePicker, day, month, year) -> {
            month += 1;
            String date = makeDateString(day, month, year);
            departureButton.setText(date);
        };

        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        datePickerDialog = new DatePickerDialog(this.getContext(), dateSetListener, year, month, day);
        datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());
    }

    private String getTodaysDate() {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        return makeDateString(day, month + 1, year);
    }

    private String makeDateString(int day, int month, int year) {
        return getMonthFormat(month) + " " + day + " " + year;
    }

    private String getMonthFormat(int month) {
        switch (month) {
            case 1: return "JAN";
            case 2: return "FEB";
            case 3: return "MAR";
            case 4: return "APR";
            case 5: return "MAY";
            case 6: return "JUN";
            case 7: return "JUL";
            case 8: return "AUG";
            case 9: return "SEP";
            case 10: return "OCT";
            case 11: return "NOV";
            case 12: return "DEC";
            default: return "INVALID";
        }
    }

    public void departureDatePicker(View view) {
        datePickerDialog.show();
    }
}

