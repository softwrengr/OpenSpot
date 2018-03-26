package com.example.techeasesol.volley.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.techeasesol.volley.R;

public class GroundDetailsFragment extends Fragment {
    String myid;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_ground_details, container, false);
        if (getArguments() != null) {
            myid  = getArguments().getString("id");
            Log.d("abdul", myid);
        }
        return view;
    }
}
