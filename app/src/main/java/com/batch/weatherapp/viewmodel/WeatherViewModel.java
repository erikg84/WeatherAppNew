package com.batch.weatherapp.viewmodel;

import android.app.Application;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.batch.weatherapp.model.Response;
import com.batch.weatherapp.repository.Repository;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import static android.content.ContentValues.TAG;

public class WeatherViewModel extends AndroidViewModel {

    private MutableLiveData<Response> myResponse = new MutableLiveData<>();
    private MutableLiveData<Boolean> showProgressBar = new MutableLiveData<>();

    private Repository repo = Repository.getInstance();
    private CompositeDisposable disposable = new CompositeDisposable();

    public WeatherViewModel(@NonNull Application application) {
        super(application);
    }

    public void getResponseObservable() {
        Log.d(TAG, "getDefinitionsObservable: ");
        repo.getCurrentWeather()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d(TAG, "onSubscribe: ");
                        showProgressBar.setValue(true);
                    }
                    
                    @Override
                    public void onNext(Response response) {
                        Log.d(TAG, "onNext: ");
                        myResponse.setValue(response);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onError: ");
                        Log.d(TAG, e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "onComplete: ");
                        showProgressBar.setValue(false);
                    }
                });
    }

    public LiveData<Response> getResponse() {
        return myResponse;
    }

    public LiveData<Boolean> getShowProgressBar() {
        return showProgressBar;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        disposable.dispose();
    }
}
