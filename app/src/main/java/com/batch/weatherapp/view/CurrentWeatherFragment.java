package com.batch.weatherapp.view;


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
import com.batch.weatherapp.model.DataItem;
import com.batch.weatherapp.model.Response;
import com.batch.weatherapp.viewmodel.WeatherViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

import static androidx.constraintlayout.widget.Constraints.TAG;

/**
 * A simple {@link Fragment} subclass.
 */
public class CurrentWeatherFragment extends Fragment {
    private WeatherAdapter adapter;
    private List<DataItem> dataItems;

    public CurrentWeatherFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentCurrentWeatherBinding bind = DataBindingUtil.inflate(LayoutInflater.from(requireContext()),R.layout.fragment_current_weather, container, false);

        dataItems = new ArrayList<>();

        adapter = new WeatherAdapter(dataItems);
        bind.recyclerview.setLayoutManager(new LinearLayoutManager(requireActivity()));
        bind.recyclerview.setAdapter(adapter);

        WeatherViewModel viewModel = new ViewModelProvider(this).get(WeatherViewModel.class);

        bind.searchButton.setOnClickListener(v -> viewModel.getResponseObservable());

        viewModel.getResponse().observe(getViewLifecycleOwner(), new Observer<Response>() {
                    @Override
                    public void onChanged(Response response) {
                        //textView.setText(response.getCurrently().toString());
                        bind.setCurrently(response.getCurrently());
                        bind.setData(response.getHourly().getData().get(0));
                        adapter.addDataItems(response.getHourly().getData());
                        for(DataItem item: response.getHourly().getData()){
                            Log.d(TAG, "TEMP_MAX>>: "+item.getTemperatureMax());
                            Log.d(TAG, "TEMP>>: "+item.getTemperature());
                        }
                        //Log.d(TAG, "Max Temperature: "+response.getHourly().getData().get(0).getTemperatureMax());
                        //Log.d(TAG, "Min Temperature: "+response.getHourly().getData().get(0).getTemperatureMin());
                        adapter.notifyDataSetChanged();
                    }
                }

        );

        return bind.getRoot();
    }

}
