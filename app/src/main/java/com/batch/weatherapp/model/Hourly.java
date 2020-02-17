package com.batch.weatherapp.model;

import android.content.Context;
import android.graphics.drawable.Drawable;

import com.batch.weatherapp.R;
import com.google.gson.annotations.SerializedName;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Hourly{


	@SerializedName("summary")
	private String summary;

	@SerializedName("data")
	private List<DataItem> data;

	@SerializedName("icon")
	private String icon;



	public void setSummary(String summary){
		this.summary = summary;
	}

	public String getSummary(){
		return summary;
	}

	public void setData(List<DataItem> data){
		this.data = data;
	}

	public List<DataItem> getData(){
		return data;
	}

	public void setIcon(String icon){
		this.icon = icon;
	}

	public String getIcon(){
		return icon;
	}

	@Override
 	public String toString(){
		return 
			"Hourly{" + 
			"summary = '" + summary + '\'' + 
			",data = '" + data + '\'' + 
			",icon = '" + icon + '\'' + 
			"}";
		}
}