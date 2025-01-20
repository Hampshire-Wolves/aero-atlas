package com.hampshirewolves.aeroatlas.ui.fragments.flightselectorpage;

import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.hampshirewolves.aeroatlas.R;
import com.hampshirewolves.aeroatlas.model.Flight;
import com.hampshirewolves.aeroatlas.ui.adapters.FlightAdapter;
import com.hampshirewolves.aeroatlas.ui.mainactivity.RecyclerViewInterface;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class FlightSelectorFragment extends Fragment implements RecyclerViewInterface {
    private FlightSelectorViewModel viewModel;
    private RecyclerView recyclerView;
    private FlightAdapter adapter;
    private List<Flight> flightsList = new ArrayList<>();
    private List<Flight> selectedFlights = new ArrayList<>();
    private DatePickerDialog datePickerDialog;
    private EditText fromEditText, toEditText;
    private Button departureBtn, returnDateBtn;
    private Button confirmBtn, searchBtn;
    private CheckBox oneWayCheckBox;

    public FlightSelectorFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(FlightSelectorViewModel.class);

        initDatePicker();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_flight_selector, container, false);

        fromEditText = view.findViewById(R.id.populateFromLocation);
        toEditText = view.findViewById(R.id.populateToLocation);
        departureBtn = view.findViewById(R.id.departurePickerDate);
        returnDateBtn = view.findViewById(R.id.returnPickerDate);
        oneWayCheckBox = view.findViewById(R.id.checkbox);
        searchBtn = view.findViewById(R.id.flight_selector_confirm_button);
        // confirmBtn = view.findViewById(R.id.flight_selector_confirm_button);
        recyclerView = view.findViewById(R.id.flight_selector_recycler_view);

        adapter = new FlightAdapter(this, getContext(), flightsList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);

        searchBtn.setOnClickListener((v) -> fetchFlights());
        // confirmBtn.setOnClickListener((v) -> navigateToSummary()); TODO: implement confirm button to summary

        oneWayCheckBox = view.findViewById(R.id.checkbox);
        oneWayCheckBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                returnDateBtn.setVisibility(View.VISIBLE);
            } else {
                returnDateBtn.setVisibility(View.INVISIBLE);
            }
        });

        departureBtn.setText(getTodaysDate());
        departureBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initDatePicker();
                datePickerDialog.show();
            }
        });

        return view;
    }

    private void fetchFlights() {
        String origin = fromEditText.getText().toString();
        String destination = toEditText.getText().toString();
        String departureDate = departureBtn.getText().toString();
        String returnDate = oneWayCheckBox.isChecked() ? returnDateBtn.getText().toString() : null;

        if (origin.isEmpty() || destination.isEmpty() || departureDate.isEmpty()) {
            Toast.makeText(getContext(), "Please fill in all required fields", Toast.LENGTH_SHORT).show();
            return;
        }

        viewModel.fetchFlights(origin, destination, departureDate, returnDate).observe(getViewLifecycleOwner(), new Observer<List<Flight>>() {
            @Override
            public void onChanged(List<Flight> flights) {
                flightsList.clear();

                if (flights != null && !flights.isEmpty()) {
                    flightsList.addAll(flights);
                } else {
                    Toast.makeText(getContext(), "No flights found", Toast.LENGTH_SHORT).show();
                }

                Log.d("FlightSelectorFragment", "Fetched flights: " + flights);

                adapter.notifyDataSetChanged();
            }
        });
    }

    private void initDatePicker() {
        DatePickerDialog.OnDateSetListener dateSetListener = (datePicker, year, month, day) -> {
            month += 1;
            String date = formatDateString(year, month, day);
            departureBtn.setText(date);
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
        month += 1;

        return formatDateString(year, month, day);
    }

    private String formatDateString(int year, int month, int day) {
        return String.format("%04d-%02d-%02d", year, month, day);
    }

//    private void navigateToSummary() {
//            if (selectedFlights.isEmpty()) {
//        Toast.makeText(getContext(), "No flights selected!", Toast.LENGTH_SHORT).show();
//        return;
//    }
//    }

    @Override
    public void onItemClick(int position) {
        Flight selectedFlight = flightsList.get(position);

        if (selectedFlights.contains(selectedFlight)) {
            selectedFlights.remove(selectedFlight);
        } else {
            selectedFlights.add(selectedFlight);
        }

        Toast.makeText(getContext(), "Selected flight with id " + selectedFlight.getId() , Toast.LENGTH_SHORT).show();
    }
}

