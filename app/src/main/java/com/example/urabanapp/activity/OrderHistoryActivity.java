package com.example.urabanapp.activity;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.urabanapp.R;
import com.example.urabanapp.adapter.OrderHistoryAdapter;
import com.example.urabanapp.api.OrderApi;
import com.example.urabanapp.model.BookingModel;
import com.example.urabanapp.model.BookingOutputModel;
import com.example.urabanapp.utilites.ConstantData;

import java.util.ArrayList;

public class OrderHistoryActivity extends AppCompatActivity {

    RecyclerView rcyloh;

    ArrayList<BookingModel> bookings;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_orderhistory);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        rcyloh=findViewById(R.id.rcyloh);

        SharedPreferences sp=getSharedPreferences(ConstantData.SP_NAME,MODE_PRIVATE);
        String uid=sp.getString(ConstantData.KEY_SP_USER_ID,"");

        new OrderApi().order_history(
                this,
                uid,
                "1"
        );
    }
    public void set(BookingOutputModel p){
        bookings=p.getBooking();

        OrderHistoryAdapter adapter = new OrderHistoryAdapter(
                bookings,
                new OrderHistoryAdapter.OnClickListener() {
                    @Override
                    public void onClickPlus(BookingModel orderModel) {

                    }

                    @Override
                    public void onClickMinus(BookingModel orderModel) {

                    }

                    @Override
                    public void removeOrder(BookingModel orderModel) {

                    }
                },
                this

        );
        rcyloh.setLayoutManager(new LinearLayoutManager(this));
        rcyloh.setAdapter(adapter);
    }
}