package com.example.techeasesol.volley.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.example.techeasesol.volley.Adapter.TimeAdapter;
import com.example.techeasesol.volley.Models.GroundDetailModel;
import com.example.techeasesol.volley.Models.GroundTimesModel;
import com.example.techeasesol.volley.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class GroundDetailsFragment extends Fragment {
    String ground_id;
    TextView stadium_name,stadium_information,tv_time1,tv_time2,tv_time3;
    ImageView stadium_images;
    ListView listView;
    ArrayList<GroundTimesModel> groundTimesModels;
    TimeAdapter timeAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_ground_details, container, false);
        stadium_name = view.findViewById(R.id.stadium_name);
        stadium_information = view.findViewById(R.id.information);
        stadium_images = view.findViewById(R.id.stadium_image);
        listView = view.findViewById(R.id.time_listview);

        if (getArguments() != null) {
            ground_id  = getArguments().getString("zmaid").toString();
            Log.d("abdul", ground_id);
        }
        groundTimesModels = new ArrayList<>();
        apiCall();
        timeAdapter = new TimeAdapter(getActivity(), groundTimesModels);
        listView.setAdapter(timeAdapter);

        return view;
    }


    public void apiCall(){
        String url = "http://openspot.qa/openspot/groundDetail";
        final StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("zma response", response);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONObject jsonObject1 = jsonObject.getJSONObject("ground");
                    String name = jsonObject1.getString("location");
                    stadium_name.setText(name);
                    String information = jsonObject1.getString("information");
                    stadium_information.setText(information);
                    JSONArray jsonArray = jsonObject.getJSONArray("images");
                    for(int i=0;i<jsonArray.length();i++){
                        JSONObject jsonObject2 = jsonArray.getJSONObject(i);
                        String image = jsonObject2.getString("image");
                        Glide.with(getActivity()).load(image).into(stadium_images);
                    }
                    JSONArray jsonArray1 = jsonObject.getJSONArray("times");
                    for(int j=0;j<jsonArray1.length();j++){
                    JSONObject times =  jsonArray1.getJSONObject(j);
                    Log.d("times",times.getString("time_from"));
                    GroundTimesModel groundTimes = new GroundTimesModel();
                    groundTimes.setTimes1(times.getString("time_from"));
                    groundTimes.setPrice1(times.getString("price"));
                 //   groundTimes.setTimes2(times.getString(""));
                 //   groundTimes.setTimes3(times.getString(""));
                        groundTimesModels.add(groundTimes);

                    }
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
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params=new HashMap<String, String>();
                params.put("ground_id", ground_id);
                return params;
            }
        };
        RequestQueue mRequestQueue = Volley.newRequestQueue(getActivity());
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(200000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        mRequestQueue.add(stringRequest);
    }

}
