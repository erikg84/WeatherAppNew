package com.batch.weatherapp.view;


import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
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
import android.widget.ImageView;
import android.widget.TextView;

import com.batch.weatherapp.R;
import com.batch.weatherapp.adapter.WeatherAdapter;
import com.batch.weatherapp.databinding.FragmentCurrentWeatherBinding;
import com.batch.weatherapp.model.Currently;
import com.batch.weatherapp.model.DataItem;
import com.batch.weatherapp.model.Response;
import com.batch.weatherapp.viewmodel.WeatherViewModel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.zip.Inflater;

import static androidx.constraintlayout.widget.Constraints.TAG;

/**
 * A simple {@link Fragment} subclass.
 */
public class CurrentWeatherFragment extends Fragment {
    private WeatherAdapter adapter;
    private List<DataItem> dataItems;
    private double latitude, longitude = 0;
    private String featureName, countryName="";
    private WeatherViewModel viewModel;
    private FragmentCurrentWeatherBinding bind;
    private List<String> icons = new ArrayList<>(
            Arrays.asList("clear-day","clear-night","rain","snow","sleet","wind","fog",
                    "cloudy","partly-cloudy-day","partly-cloudy-night")
    );
    public CurrentWeatherFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        bind = DataBindingUtil.inflate(LayoutInflater.from(requireContext()),R.layout.fragment_current_weather, container, false);
        viewModel = new ViewModelProvider(this).get(WeatherViewModel.class);

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

}
