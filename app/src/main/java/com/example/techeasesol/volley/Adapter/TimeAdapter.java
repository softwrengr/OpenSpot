package com.example.techeasesol.volley.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.techeasesol.volley.Models.GroundDetailModel;
import com.example.techeasesol.volley.Models.GroundTimesModel;
import com.example.techeasesol.volley.R;

import java.util.ArrayList;

public class TimeAdapter extends BaseAdapter{
    ArrayList<GroundTimesModel> groundTimesModels;
    Context context;
    TimeAdapter.ViewHolder viewHolder = null;

    public TimeAdapter(Activity activity, ArrayList<GroundTimesModel> groundTimesModels) {
        this.context = activity;
        this.groundTimesModels = groundTimesModels;
    }

    @Override
    public int getCount() {
        return groundTimesModels.size();
    }

    @Override
    public Object getItem(int i) {
        return groundTimesModels.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup parent) {
        final GroundTimesModel model=groundTimesModels.get(i);
        viewHolder=new ViewHolder() ;
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.times_layout,parent,false);
        viewHolder.tv_times1 = view.findViewById(R.id.time1);
        viewHolder.tv_price1 = view.findViewById(R.id.price1);
        viewHolder.tv_times1.setText(model.getTimes1());
        viewHolder.tv_price1.setText(model.getPrice1());
        return view;
    }

    public class ViewHolder{
        TextView tv_times1,tv_times2,tv_times3;
        TextView tv_price1;
    }
}
