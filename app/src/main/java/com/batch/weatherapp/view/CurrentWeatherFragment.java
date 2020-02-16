package com.batch.weatherapp.view;


import android.content.Context;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.batch.weatherapp.R;
import com.batch.weatherapp.adapter.WeatherAdapter;
import com.batch.weatherapp.databinding.FragmentCurrentWeatherBinding;
import com.batch.weatherapp.model.Currently;
import com.batch.weatherapp.model.DataItem;
import com.batch.weatherapp.placesadapter.PlacesAutoCompleteAdapter;
import com.batch.weatherapp.viewmodel.WeatherViewModel;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.net.PlacesClient;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class CurrentWeatherFragment extends Fragment implements PlacesAutoCompleteAdapter.ClickListener{

    private WeatherAdapter adapter;
    private List<DataItem> dataItems;
    private double latitude, longitude = 0;
    private String featureName, countryName="";
    private WeatherViewModel viewModel;
    private FragmentCurrentWeatherBinding bind;
    private PlacesAutoCompleteAdapter mAutoCompleteAdapter;
    private RecyclerView recyclerView;
    private Context context;

    public CurrentWeatherFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        bind = DataBindingUtil.inflate(LayoutInflater.from(requireContext()),R.layout.fragment_current_weather, container, false);
        viewModel = new ViewModelProvider(this).get(WeatherViewModel.class);
        context = container.getContext();
        setupObservers();
        //Default location to New York, USA
        setLocaleDetails("new york");
        bind.searchBar.setText(featureName+", "+countryName);
        viewModel.getResponseObservable(latitude,longitude);

        dataItems = new ArrayList<>();
        adapter = new WeatherAdapter(dataItems);
        bind.recyclerview.setLayoutManager(new LinearLayoutManager(requireActivity()));
        bind.recyclerview.setAdapter(adapter);
        setupListeners();
        setupPlacesAdapter();
        return bind.getRoot();
    }
    private void setupListeners(){
        bind.searchButton.setOnClickListener(v -> {
                    setLocaleDetails(bind.searchBar.getText().toString());
                    bind.searchBar.setText(featureName+", "+countryName);
                    viewModel.getResponseObservable(latitude, longitude);
                });
    }
    private void setupObservers(){
            viewModel.getResponse().observe(getViewLifecycleOwner(), response -> {
                Currently currently = response.getCurrently();
                currently.setContext(requireContext());
                bind.setCurrently(currently);//binding current weather data - min/max
                bind.setData(response.getDaily().getData().get(0));//binding min/max
                adapter.addDataItems(response.getHourly().getData());
                adapter.notifyDataSetChanged();
            });
    }
    private void setLocaleDetails(String placeName){
        Geocoder geocoder = new Geocoder(requireContext());
        try {

            Address location= geocoder.getFromLocationName(placeName,1).get(0);
            latitude = location.getLatitude();
            longitude = location.getLongitude();
            featureName = location.getFeatureName();
            countryName = location.getCountryName();

        } catch (IOException e) {
            e.printStackTrace();
        }


    }


    private void setupPlacesAdapter(){

        Places.initialize(requireContext(), "AIzaSyDPjOpwm4thAtskqkNKm61FJCTlV8GABDQ");
        bind.searchBar.addTextChangedListener(filterTextWatcher);

        mAutoCompleteAdapter = new PlacesAutoCompleteAdapter(context);
        bind.placesRecyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        mAutoCompleteAdapter.setClickListener(this);
        bind.placesRecyclerView.setAdapter(mAutoCompleteAdapter);
        mAutoCompleteAdapter.notifyDataSetChanged();
    }
    private TextWatcher filterTextWatcher = new TextWatcher() {
        public void afterTextChanged(Editable s) {

            if (!s.toString().equals("")) {topSettings(true);
                mAutoCompleteAdapter.getFilter().filter(s.toString());
                if (bind.placesRecyclerView.getVisibility() == View.GONE) {
                    bind.placesRecyclerView.setVisibility(View.VISIBLE);

                }
            } else {
                if (bind.placesRecyclerView.getVisibility() == View.VISIBLE) {
                    bind.placesRecyclerView.setVisibility(View.GONE);
                    topSettings(false);
                }
            }
        }
        public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
        public void onTextChanged(CharSequence s, int start, int before, int count) { }
    };
    @Override
    public void click(Place place) {
        //Toast.makeText(requireActivity(), place.getAddress()+", "+place.getLatLng().latitude+place.getLatLng().longitude, Toast.LENGTH_SHORT).show();
        bind.searchBar.setText(featureName+", "+countryName);
        bind.placesRecyclerView.setVisibility(View.GONE);
        topSettings(false);

    }
    private void topSettings(Boolean isEnabled){
        if(isEnabled){
            bind.searchBar.setBackgroundColor(Color.WHITE);
            bind.searchBar.setTextColor(Color.BLACK);
            bind.searchButton.setBackgroundColor(Color.WHITE);
        }
        else{
            bind.searchBar.setBackgroundColor(Color.TRANSPARENT);
            bind.searchBar.setTextColor(Color.WHITE);
            bind.searchButton.setBackgroundColor(Color.TRANSPARENT);
        }
    }

}
