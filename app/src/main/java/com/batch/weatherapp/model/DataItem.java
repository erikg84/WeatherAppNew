package com.batch.weatherapp.model;


import com.google.gson.annotations.SerializedName;


public class DataItem{

	@SerializedName("precipProbability")
	private double precipProbability;

	@SerializedName("precipIntensity")
	private double precipIntensity;

	@SerializedName("time")
	private int time;

	public void setPrecipProbability(int precipProbability){
		this.precipProbability = precipProbability;
	}

	public double getPrecipProbability(){
		return precipProbability;
	}

	public void setPrecipIntensity(int precipIntensity){
		this.precipIntensity = precipIntensity;
	}

	public double getPrecipIntensity(){
		return precipIntensity;
	}

	public void setTime(int time){
		this.time = time;
	}

	public int getTime(){
		return time;
	}

	@Override
 	public String toString(){
		return 
			"DataItem{" + 
			"precipProbability = '" + precipProbability + '\'' + 
			",precipIntensity = '" + precipIntensity + '\'' + 
			",time = '" + time + '\'' + 
			"}";
		}
}