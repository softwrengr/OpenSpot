package com.example.techeasesol.volley.Adapter;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.techeasesol.volley.Fragments.GroundDetailsFragment;
import com.example.techeasesol.volley.Models.GroundDetailModel;
import com.example.techeasesol.volley.R;
import java.util.ArrayList;
/**
 * Created by ak603 on 3/22/2018.
 */
public class GroundAdapter extends BaseAdapter {
    ArrayList<GroundDetailModel> groundDetailsModel;
    Context context;
    MyViewHolder viewHolder = null;

    public GroundAdapter(Context context, ArrayList<GroundDetailModel> groundDetailModel) {
        this.context = context;
        this.groundDetailsModel = groundDetailModel;
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
         viewHolder.linearLayout = view.findViewById(R.id.mainView);
        viewHolder.imageView = view.findViewById(R.id.imageview);
        viewHolder.name = view.findViewById(R.id.name);
        viewHolder.location = view.findViewById(R.id.location);
        viewHolder.information = view.findViewById(R.id.information);
        Glide.with(context).load(model.getImage()).into(viewHolder.imageView);
        String id = model.getId();
        Bundle bundle = new Bundle();
        bundle.putString("id",id);
        Fragment myid = new GroundDetailsFragment();
        myid.setArguments(bundle);
        viewHolder.name.setText(model.getName());
        viewHolder.location.setText(model.getLocation());
        viewHolder.information.setText(model.getInformation());

        viewHolder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = new GroundDetailsFragment();
                ((AppCompatActivity)context).getFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).addToBackStack("").commit();
            }
        });
        return view;
    }

    private class MyViewHolder  {
        TextView name,location,information;
        ImageView imageView;
        LinearLayout linearLayout;

    }
}
