package com.example.shon.boost4.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.shon.boost4.R;
import com.example.shon.boost4.adapter.UsersRecAdapter;
import com.example.shon.boost4.entity.User;

import java.util.ArrayList;
import java.util.List;

public class Users extends Fragment {

    private List<User> mUsers = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View users = inflater.inflate(R.layout.frag_users, container, false);

        mUsers.add(new User());

        RecyclerView recView = (RecyclerView) users.findViewById(R.id.rv_users);
        recView.setLayoutManager(new LinearLayoutManager(getContext()));
        recView.setAdapter(new UsersRecAdapter(mUsers));

        return users;
    }
}
