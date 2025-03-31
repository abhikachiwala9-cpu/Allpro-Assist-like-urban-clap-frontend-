package com.example.urabanapp.activity;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.urabanapp.R;
import com.example.urabanapp.adapter.CoupenAdapter;
import com.example.urabanapp.model.CoupenModel;

import java.util.ArrayList;

public class CouponActivity extends AppCompatActivity {


    ArrayList<CoupenModel> coupens;

    RecyclerView rcylCoupon;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_coupon);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

//        coupens=((NavigationActivity)getApplicationContext()).getCoupen();

        coupens = (ArrayList<CoupenModel>) getIntent().getSerializableExtra("coupon");

        rcylCoupon=findViewById(R.id.rcylCoupon);

        rcylCoupon.setLayoutManager(new LinearLayoutManager(this));
        rcylCoupon.setAdapter(new CoupenAdapter(coupens));
    }
}