package com.example.techeasesol.volley.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.example.techeasesol.volley.R;
import java.util.List;
/**
 * Created by ak603 on 3/23/2018.
 */
public class SwipeStackAdapter extends BaseAdapter {
    private List<String> mData;
    Context context;
    public SwipeStackAdapter(List<String> data) {
        this.mData = data;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public String getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_layout, parent, false);
        TextView textViewCard = (TextView) convertView.findViewById(R.id.textview);
      //  textViewCard.setText(mData.get(position));
        return convertView;
    }
}
