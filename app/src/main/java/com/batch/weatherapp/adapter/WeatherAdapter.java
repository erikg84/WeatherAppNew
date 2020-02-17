package com.batch.weatherapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;
import com.batch.weatherapp.R;
import com.batch.weatherapp.databinding.RowBinding;
import com.batch.weatherapp.model.DataItem;

import java.util.List;

public class WeatherAdapter extends RecyclerView.Adapter<WeatherAdapter.WeatherHolder> {

    private List<DataItem> dataList;
    private Context context;


    public WeatherAdapter(List<DataItem> dataList) {
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public WeatherAdapter.WeatherHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        RowBinding view = DataBindingUtil.inflate(LayoutInflater.from(context),R.layout.row,parent,false);
        return new WeatherHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WeatherAdapter.WeatherHolder holder, int position) {
        DataItem data = dataList.get(position);
        data.setContext(context);
        holder.bind.setData(data);

    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class WeatherHolder extends RecyclerView.ViewHolder {

        RowBinding bind;

        public WeatherHolder(@NonNull RowBinding itemView) {
            super(itemView.getRoot());
            bind = itemView;
        }
    }
    public void addDataItems(List<DataItem> dataList, Context newcontxt){
        this.dataList.clear();
        this.dataList = dataList;
        context = newcontxt;
    }
}
