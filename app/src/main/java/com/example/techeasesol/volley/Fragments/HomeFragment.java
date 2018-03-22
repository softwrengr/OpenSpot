package com.example.techeasesol.volley.Fragments;

import android.annotation.SuppressLint;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.design.internal.BottomNavigationItemView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.example.techeasesol.volley.Adapter.RecyclerViewAdapter;
import com.example.techeasesol.volley.R;

import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

public class HomeFragment extends Fragment {
    EditText etSearchbar;
    boolean check = false;
    RecyclerView recyclerView;
    RecyclerViewAdapter recyclerViewAdapter;
    LinearLayout bottomView,mainView;
    BottomNavigationItemView bottomNavigationItemView;
    Button btnFindSpot;
    @SuppressLint("WrongConstant")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);
        etSearchbar = view.findViewById(R.id.searchbar2);
        recyclerView = (RecyclerView) view.findViewById(R.id.dayslistview);
        bottomView = view.findViewById(R.id.bottomView);
        mainView = view.findViewById(R.id.mainView);
        btnFindSpot = view.findViewById(R.id.findSpot);

        btnFindSpot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = new BookingFragment();
                getFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).addToBackStack("").commit();
            }
        });
        etSearchbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(check == false){
                   bottomView.setVisibility(view.VISIBLE);
                   mainView.setVisibility(View.GONE);
                     check = true;
                }
                else if(check == true){
                   bottomView.setVisibility(View.GONE);
                   mainView.setVisibility(View.VISIBLE);
                    check = false;
                }
            }
        });

        SimpleDateFormat curFormater = new SimpleDateFormat("EEE dd");
        GregorianCalendar date = new GregorianCalendar();
        String[] dateStringArray = new String[7];
        date.set(GregorianCalendar.DATE, date.get(GregorianCalendar.DATE)-date.get(GregorianCalendar.DAY_OF_WEEK));
        for (int day = 0; day < 7; day++) {
            dateStringArray[day] = curFormater.format(date.getTime());
            date.roll(Calendar.DAY_OF_MONTH, true);
        }
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerViewAdapter = new RecyclerViewAdapter(dateStringArray);
        recyclerView.setAdapter(recyclerViewAdapter);
        return view;
    }
}
