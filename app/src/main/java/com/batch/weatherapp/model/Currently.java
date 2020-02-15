package com.batch.weatherapp.model;


import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;

import com.batch.weatherapp.R;
import com.google.gson.annotations.SerializedName;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Currently{

	private Context context;

	public void setContext(Context context){
		this.context = context;
		Log.d("MY TAG: ", "setContext: ");
	}

	private Map<String, Integer> iconMap = new HashMap<String, Integer>() {{
		put("clear-day", R.mipmap.ic_summer_foreground);
		put("clear-night", R.mipmap.ic_clear_night_foreground);
		put("rain", R.mipmap.ic_rain_foreground);
		put("snow", R.mipmap.ic_snow_foreground);
		put("sleet", R.mipmap.ic_sleet_foreground);
		put("wind", R.mipmap.ic_wind_foreground);
		put("fog", R.mipmap.ic_haze_foreground);
		put("cloudy", R.mipmap.ic_clouds_foreground);
		put("partly-cloudy-day", R.mipmap.ic_partly_cloudy_day_foreground);
		put("partly-cloudy-night", R.mipmap.ic_night_foreground);

	}};

	@SerializedName("summary")
	private String summary;

	@SerializedName("precipProbability")
	private double precipProbability;

	@SerializedName("visibility")
	private double visibility;

	@SerializedName("windGust")
	private double windGust;

	@SerializedName("precipIntensity")
	private double precipIntensity;

	@SerializedName("icon")
	private String icon;

	@SerializedName("cloudCover")
	private double cloudCover;

	@SerializedName("windBearing")
	private int windBearing;

	@SerializedName("apparentTemperature")
	private double apparentTemperature;

	@SerializedName("pressure")
	private double pressure;

	@SerializedName("dewPoint")
	private double dewPoint;

	@SerializedName("ozone")
	private double ozone;

	@SerializedName("nearestStormBearing")
	private int nearestStormBearing;

	@SerializedName("nearestStormDistance")
	private int nearestStormDistance;

	@SerializedName("temperatureMax")
	private double temperatureMax;

	@SerializedName("temperature")
	private double temperature;

	@SerializedName("temperatureMin")
	private double temperatureMin;

	@SerializedName("humidity")
	private double humidity;

	@SerializedName("time")
	private int time;

	@SerializedName("windSpeed")
	private double windSpeed;

	@SerializedName("uvIndex")
	private int uvIndex;

	@SerializedName("imageRes")
	private Drawable imageRes;

	public Drawable getImageRes() {
		return context.getResources().getDrawable(iconMap.get(getIcon()),null);
	}

	public void setSummary(String summary){
		this.summary = summary;
	}

	public String getSummary(){
		Log.d("SUMMRAY TAG", "getSummary: ");
		return summary;
	}

	public void setPrecipProbability(int precipProbability){
		this.precipProbability = precipProbability;
	}

	public double getPrecipProbability(){
		return precipProbability;
	}

	public void setVisibility(double visibility){
		this.visibility = visibility;
	}

	public double getVisibility(){
		return visibility;
	}

	public void setWindGust(double windGust){
		this.windGust = windGust;
	}

	public double getWindGust(){
		return windGust;
	}

	public void setPrecipIntensity(int precipIntensity){
		this.precipIntensity = precipIntensity;
	}

	public double getPrecipIntensity(){
		return precipIntensity;
	}

	public void setIcon(String icon){
		this.icon = icon;
	}

	public String getIcon(){
		return icon;
	}

	public void setCloudCover(int cloudCover){
		this.cloudCover = cloudCover;
	}

	public double getCloudCover(){
		return cloudCover;
	}

	public void setWindBearing(int windBearing){
		this.windBearing = windBearing;
	}

	public int getWindBearing(){
		return windBearing;
	}

	public void setApparentTemperature(double apparentTemperature){
		this.apparentTemperature = apparentTemperature;
	}

	public double getApparentTemperature(){
		return apparentTemperature;
	}

	public void setPressure(double pressure){
		this.pressure = pressure;
	}

	public double getPressure(){
		return pressure;
	}

	public void setDewPoint(double dewPoint){
		this.dewPoint = dewPoint;
	}

	public double getDewPoint(){
		return dewPoint;
	}

	public void setOzone(int ozone){
		this.ozone = ozone;
	}

	public double getOzone(){
		return ozone;
	}

	public void setNearestStormBearing(int nearestStormBearing){
		this.nearestStormBearing = nearestStormBearing;
	}

	public int getNearestStormBearing(){
		return nearestStormBearing;
	}

	public void setNearestStormDistance(int nearestStormDistance){
		this.nearestStormDistance = nearestStormDistance;
	}

	public int getNearestStormDistance(){
		return nearestStormDistance;
	}

	public void setTemperature(double temperature){
		this.temperature = temperature;
	}

	public String getTemperature(){
		return (int)temperature+"°";
	}

	public void setTemperatureMin(double temperatureMin){
		this.temperatureMin = temperatureMin;
	}

	public String getTemperatureMin(){
		return (int)temperatureMin+"°";
	}

	public void setTemperatureMax(double temperatureMax){
		this.temperatureMax = temperatureMax;
	}

	public String getTemperatureMax(){
		return (int)temperatureMax+"°";
	}



	public void setHumidity(double humidity){
		this.humidity = humidity;
	}

	public double getHumidity(){
		return humidity;
	}

	public void setTime(int time){
		this.time = time;
	}

	public String getTime(){
		Date date = new java.util.Date(time*1000L);
		SimpleDateFormat sdf = new java.text.SimpleDateFormat("E,h:ssa");
		return sdf.format(date);
	}

	public void setWindSpeed(double windSpeed){
		this.windSpeed = windSpeed;
	}

	public double getWindSpeed(){
		return windSpeed;
	}

	public void setUvIndex(int uvIndex){
		this.uvIndex = uvIndex;
	}

	public int getUvIndex(){
		return uvIndex;
	}

	@Override
 	public String toString(){
		return 
			"Currently{" + 
			"\nsummary = '" + summary + '\'' +
			"\nprecipProbability = '" + precipProbability + '\'' +
			"\nvisibility = '" + visibility + '\'' +
			"\nwindGust = '" + windGust + '\'' +
			"\nprecipIntensity = '" + precipIntensity + '\'' +
			"\nicon = '" + icon + '\'' +
			"\ncloudCover = '" + cloudCover + '\'' +
			"\nwindBearing = '" + windBearing + '\'' +
			"\napparentTemperature = '" + apparentTemperature + '\'' +
			"\npressure = '" + pressure + '\'' +
			"\ndewPoint = '" + dewPoint + '\'' +
			"\nozone = '" + ozone + '\'' +
			"\nnearestStormBearing = '" + nearestStormBearing + '\'' +
			"\nnearestStormDistance = '" + nearestStormDistance + '\'' +
			"\ntemperature = '" + temperature + '\'' +
			"\nhumidity = '" + humidity + '\'' +
			"\ntime = '" + time + '\'' +
			"\nwindSpeed = '" + windSpeed + '\'' +
			"\nuvIndex = '" + uvIndex + '\'' +
			"}";
		}
}