package com.example.urabanapp.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.urabanapp.R;
import com.example.urabanapp.adapter.CategoryAdapter;
import com.example.urabanapp.adapter.ServicesAdapter;
import com.example.urabanapp.adapter.SubCategoryAdapter;
import com.example.urabanapp.model.CategoryModel;
import com.example.urabanapp.model.ServicesModel;
import com.example.urabanapp.model.SubcateModel;

import java.util.ArrayList;

public class SeeCategoryActivity extends AppCompatActivity {

    ImageView imgCatArrow;
    RecyclerView rcylCate;


    ArrayList<SubcateModel> subcat;
    ArrayList<ServicesModel> service;
    ArrayList<CategoryModel> category;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_see_category);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        rcylCate=findViewById(R.id.rcylCate);
        imgCatArrow=findViewById(R.id.imgCatArrow);

        imgCatArrow.setOnClickListener(v->{
            finish();
        });

        try{

            subcat=(ArrayList<SubcateModel>) getIntent().getSerializableExtra("subcate");
            service=(ArrayList<ServicesModel>) getIntent().getSerializableExtra("ser");

            SubCategoryAdapter subCategoryAdapter=new SubCategoryAdapter(this,subcat,service);

            rcylCate.setLayoutManager(new GridLayoutManager(SeeCategoryActivity.this,2));
            rcylCate.setAdapter(subCategoryAdapter);

        }catch(Exception e){

        }

    }
}