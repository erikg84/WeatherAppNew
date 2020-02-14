package com.batch.weatherapp.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.batch.weatherapp.R;
import com.batch.weatherapp.model.Response;
import com.batch.weatherapp.repository.Repository;
import com.batch.weatherapp.viewmodel.WeatherViewModel;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = findViewById(R.id.get_weather_button);
        final TextView textView = findViewById(R.id.current_weather);
        final WeatherViewModel viewModel = new WeatherViewModel(getApplication());
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                viewModel.getResponseObservable();
            }
        });
        viewModel.getResponse().observe(this, new Observer<Response>() {
            @Override
            public void onChanged(Response response) {
                textView.setText(response.getCurrently().toString());
            }
        });

    }

}