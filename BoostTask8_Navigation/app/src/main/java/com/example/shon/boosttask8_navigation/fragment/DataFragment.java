package com.example.shon.boosttask8_navigation.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.shon.boosttask8_navigation.MainActivity;
import com.example.shon.boosttask8_navigation.R;
import com.example.shon.boosttask8_navigation.adapter.DataRecAdapter;
import com.example.shon.boosttask8_navigation.entity.Sample;
import com.example.shon.boosttask8_navigation.view.MyPlotView;
import com.firebase.client.Firebase;

import java.util.ArrayList;
import java.util.List;

public class DataFragment extends Fragment {

    private List<Sample> mSamples = new ArrayList<>();

    private RecyclerView mRecView;
    private MyPlotView mPlotView;
    private Firebase mSamplesRef;


    private void initSamplesRef(Bundle extras) {
        if (extras != null) {
            String firebaseUrl = (String) extras.get("samplesRef");
            Log.d(MainActivity.MAIN_TAG, firebaseUrl);
            if (firebaseUrl != null && !firebaseUrl.equals("null")) {
                mSamplesRef = new Firebase(firebaseUrl);
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d(MainActivity.MAIN_TAG, "DataFragment: onCreateView - "
                + getArguments().getString(""));

        View data = inflater.inflate(R.layout.frag_data, container, false);

        mRecView = (RecyclerView) data.findViewById(R.id.rv_samples);
        mRecView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecView.setAdapter(new DataRecAdapter(mSamples));

        mPlotView = (MyPlotView) data.findViewById(R.id.my_plot_View);
        mPlotView.setSamples(mSamples);

//        if(MainActivity.sSamplesRef != null){
//            MainActivity.sChildEventListener = MainActivity.sSamplesRef
//                    .addChildEventListener(new MyChildEventListener(mRecView, mPlotView));
//        } else {
//            data.findViewById(R.id.tv_instead_rv).setVisibility(View.VISIBLE);
//            data.findViewById(R.id.rv_samples).setVisibility(View.GONE);
//        }

        return data;
    }
}