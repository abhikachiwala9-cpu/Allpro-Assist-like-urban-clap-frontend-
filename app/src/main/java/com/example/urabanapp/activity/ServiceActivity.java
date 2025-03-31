package com.example.urabanapp.activity;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.urabanapp.R;
import com.example.urabanapp.adapter.ServicesAdapter;
import com.example.urabanapp.model.ServicesModel;

import java.util.ArrayList;

public class ServiceActivity extends AppCompatActivity {

    ImageView imgCatArrow;
    RecyclerView rcylservice;
    ArrayList<ServicesModel> servicesModels;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_service);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        rcylservice=findViewById(R.id.rcylservice);
        imgCatArrow=findViewById(R.id.imgCatArrow);

        imgCatArrow.setOnClickListener(v->{
            finish();
        });

            servicesModels=(ArrayList<ServicesModel>) getIntent().getSerializableExtra("service");
            ServicesAdapter servicesAdapter=new ServicesAdapter(servicesModels);
            rcylservice.setLayoutManager(new GridLayoutManager(ServiceActivity.this,2));
            rcylservice.setAdapter(servicesAdapter);

    }
}