package com.batch.weatherapp.repository;


import com.batch.weatherapp.model.Response;
import com.batch.weatherapp.repository.remote.ServiceInstance;

import io.reactivex.Observable;

public class Repository {
    public Repository() {
    }

    private static class RepositoryInstanceHolder{
        private static final Repository INSTANCE = new Repository();
    }

    public static Repository getInstance(){
        return RepositoryInstanceHolder.INSTANCE;
    }

    public Observable<Response> getCurrentWeather(double latitude, double longitude){
        return ServiceInstance.getWeatherService().getCurrentWeather("bb6c34c5483d5d89566fad9c9bda051c",latitude,longitude);
    }
}
