package com.example.techeasesol.volley.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.techeasesol.volley.Adapter.SwipeStackAdapter;
import com.example.techeasesol.volley.R;

import java.util.ArrayList;
import java.util.List;

import link.fls.swipestack.SwipeStack;


public class FindSpotFragment extends Fragment {
    SwipeStack swipeStack;
    List<String > mData;
    Button booknow;
    LinearLayout linearLayout;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_find_spot, container, false);
        linearLayout = view.findViewById(R.id.social_login);
        booknow = view.findViewById(R.id.booknow);
        swipeStack = view.findViewById(R.id.swipeStack);
        mData=new ArrayList<>();
        mData.add("card 1");
        swipeStack.setAdapter(new SwipeStackAdapter(mData));

        booknow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                linearLayout.setVisibility(View.VISIBLE);
                booknow.setVisibility(View.GONE);
            }
        });

        return view;
    }
}
