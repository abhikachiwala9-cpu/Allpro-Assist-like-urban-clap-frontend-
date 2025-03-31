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
import com.example.urabanapp.adapter.BookingAdapter;
import com.example.urabanapp.adapter.CoupenAdapter;
import com.example.urabanapp.model.BookingModel;
import com.example.urabanapp.model.CoupenModel;

import java.util.ArrayList;


public class BookingFragment extends Fragment {


//    RecyclerView rcylbooking;
//    ArrayList<BookingModel> booking;

//    public BookingFragment(ArrayList<BookingModel> booking) {
//
//        this.booking = booking;
//    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment_booking, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        rcylbooking=view.findViewById(R.id.rcylBookingHistory);
//        getData();
    }

    public void getData() {
        //code for coupon
//        rcylbooking.setLayoutManager(new StaggeredGridLayoutManager(1, LinearLayoutManager.VERTICAL));
//        rcylbooking.setAdapter(new BookingAdapter(booking));
    }

}