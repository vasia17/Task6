package com.example.shon.boost4;

import android.support.v7.widget.RecyclerView;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.FirebaseError;

/**
 * Created by Shon on 15.02.2016.
 */
public class MyChildEventListener implements ChildEventListener {

    private MyView view;
    private RecyclerView mRecView;
    private RecyclerViewAdapter adapter;

    public MyChildEventListener(RecyclerView rv, MyView view) {
        this.view = view;
        this.mRecView = rv;
        this.adapter = (RecyclerViewAdapter) rv.getAdapter();
    }

    @Override
    public void onChildAdded(DataSnapshot dataSnapshot, String s) {
        adapter.add(dataSnapshot.getValue(Measurement.class));
        mRecView.scrollToPosition(MainActivity.POS);
        view.onViewDataChange();
    }

    @Override
    public void onChildChanged(DataSnapshot dataSnapshot, String s) {

    }

    @Override
    public void onChildRemoved(DataSnapshot dataSnapshot) {

    }

    @Override
    public void onChildMoved(DataSnapshot dataSnapshot, String s) {

    }

    @Override
    public void onCancelled(FirebaseError firebaseError) {

    }
}

