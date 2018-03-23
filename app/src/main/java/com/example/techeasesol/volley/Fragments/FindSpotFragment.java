package com.example.techeasesol.volley.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.techeasesol.volley.Adapter.SwipeStackAdapter;
import com.example.techeasesol.volley.R;

import java.util.ArrayList;
import java.util.List;

import link.fls.swipestack.SwipeStack;


public class FindSpotFragment extends Fragment {
    SwipeStack swipeStack;
    List<String > mData;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_find_spot, container, false);
        swipeStack = view.findViewById(R.id.swipeStack);
        mData=new ArrayList<>();
        mData.add("card 1");
        swipeStack.setAdapter(new SwipeStackAdapter(mData));

        return view;
    }
}
