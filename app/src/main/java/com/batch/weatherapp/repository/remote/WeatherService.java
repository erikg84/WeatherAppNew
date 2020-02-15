package com.batch.weatherapp.repository.remote;

import com.batch.weatherapp.model.Response;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface WeatherService {


    @GET("/forecast/{key}/{latitude},{longitude}")
    Observable<Response> getCurrentWeather(
            @Path("key") String key,
            @Path("latitude") double latitude,
            @Path("longitude") double longitude
    );

}
