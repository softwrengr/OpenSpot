package com.example.techeasesol.volley.Fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.app.Fragment;
import android.support.design.internal.BottomNavigationItemView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.techeasesol.volley.Adapter.GroundAdapter;
import com.example.techeasesol.volley.Adapter.RecyclerViewAdapter;
import com.example.techeasesol.volley.Models.GroundDetailModel;
import com.example.techeasesol.volley.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class HomeFragment extends Fragment {
    EditText etSearchbar;
    boolean check = false;
    RecyclerView recyclerView;
    RecyclerViewAdapter recyclerViewAdapter;
    LinearLayout bottomView, mainView;
    ListView listView;
    ArrayList<GroundDetailModel> groundDetailModel;
    GroundAdapter groundAdapter;

    @SuppressLint("WrongConstant")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);
        etSearchbar = view.findViewById(R.id.searchbar2);
        recyclerView = (RecyclerView) view.findViewById(R.id.dayslistview);
        listView = view.findViewById(R.id.listview);
        bottomView = view.findViewById(R.id.bottomView);
        mainView = view.findViewById(R.id.mainView);

        groundDetailModel = new ArrayList<>();
        serverResponse();
        groundAdapter = new GroundAdapter(getActivity(), groundDetailModel);
        listView.setAdapter(groundAdapter);

        etSearchbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (check == false) {
                    bottomView.setVisibility(view.VISIBLE);
                    check = true;
                } else if (check == true) {
                    bottomView.setVisibility(View.GONE);
                    check = false;
                }
            }
        });

        SimpleDateFormat curFormater = new SimpleDateFormat("EEE dd");
        GregorianCalendar date = new GregorianCalendar();
        String[] dateStringArray = new String[7];
        date.set(GregorianCalendar.DATE, date.get(GregorianCalendar.DATE) - date.get(GregorianCalendar.DAY_OF_WEEK));
        for (int day = 0; day < 7; day++) {
            dateStringArray[day] = curFormater.format(date.getTime());
            date.roll(Calendar.DAY_OF_MONTH, true);
        }
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerViewAdapter = new RecyclerViewAdapter(dateStringArray);
        recyclerView.setAdapter(recyclerViewAdapter);

        return view;
    }

    public void serverResponse() {
        String url = "http://openspot.qa/openspot/grounds";
        final RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        final StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("zma response", response);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                        JSONObject object = jsonObject.getJSONObject("ground");
                        GroundDetailModel groundDetail = new GroundDetailModel();
                        groundDetail.setName(object.getString("name"));
                        groundDetail.setInformation(object.getString("information"));
                        groundDetailModel.add(groundDetail);
                        groundAdapter.notifyDataSetChanged();

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {
            @Override
            public String getBodyContentType() {
                return "application/x-www-form-urlencoded;charset=UTF-8";
            }

        };
        RequestQueue mRequestQueue = Volley.newRequestQueue(getActivity());
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(200000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        mRequestQueue.add(stringRequest);
    }
}