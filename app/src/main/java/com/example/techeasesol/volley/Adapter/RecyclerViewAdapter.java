package com.example.techeasesol.volley.Adapter;
import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.example.techeasesol.volley.R;
import java.util.ArrayList;

/**
 * Created by ak603 on 3/14/2018.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.viewHolder> {
     //ArrayList<String > arrayList = new ArrayList<>();
    String[] names;

    public RecyclerViewAdapter(String[] names) {
        this.names = names;
    }

    @Override
    public viewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.dayslayout,parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(viewHolder holder, int position) {
        holder.days.setText(names[position]);
    }

    @Override
    public int getItemCount() {
        return names.length;
    }

    public class viewHolder extends RecyclerView.ViewHolder{
     Button days;
       public viewHolder(View itemView) {
           super(itemView);
           days = itemView.findViewById(R.id.todayDate);
       }
   }
}
