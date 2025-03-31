package com.example.urabanapp.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.urabanapp.R;
import com.example.urabanapp.adapter.CoupenAdapter;
import com.example.urabanapp.model.CoupenModel;

import java.util.ArrayList;


public class CoupounFragment extends Fragment {

    RecyclerView rcylCoupen;

    ArrayList<CoupenModel> coupon;

    public CoupounFragment( ArrayList<CoupenModel> coupon) {

        this.coupon = coupon;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rcylCoupen=view.findViewById(R.id.rcylCoupen);
        getData();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment_coupoun, container, false);
        return view;


    }

    public void getData() {
        //code for coupon
        rcylCoupen.setLayoutManager(new StaggeredGridLayoutManager(1, LinearLayoutManager.VERTICAL));
        rcylCoupen.setAdapter(new CoupenAdapter(coupon));
    }
}