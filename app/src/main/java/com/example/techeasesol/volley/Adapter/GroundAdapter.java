package com.example.techeasesol.volley.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.techeasesol.volley.Models.GroundDetailModel;
import com.example.techeasesol.volley.R;

import java.util.ArrayList;

/**
 * Created by ak603 on 3/22/2018.
 */

public class GroundAdapter extends BaseAdapter {
    ArrayList<GroundDetailModel> groundDetailsModel;
    Context context;
    private LayoutInflater layoutInflater;
    MyViewHolder viewHolder = null;

    public GroundAdapter(Activity activity, ArrayList<GroundDetailModel> groundDetailModel) {
    }

    @Override
    public int getCount() {
        if (groundDetailsModel!=null) return groundDetailsModel.size();
        return 0;
    }

    @Override
    public Object getItem(int i) {
        if(groundDetailsModel != null && groundDetailsModel.size() > i) return  groundDetailsModel.get(i);
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup parent) {
        final GroundDetailModel model=groundDetailsModel.get(i);
        viewHolder=new MyViewHolder() ;
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ground_detail_layout,parent,false);
        viewHolder.name = view.findViewById(R.id.name);
        viewHolder.location = view.findViewById(R.id.location);
        viewHolder.information = view.findViewById(R.id.information);
        viewHolder.name.setText(model.getName());
        viewHolder.location.setText(model.getLocation());
        viewHolder.information.setText(model.getInformation());

        return null;
    }

    private class MyViewHolder  {
        TextView name,location,information;

    }
}
