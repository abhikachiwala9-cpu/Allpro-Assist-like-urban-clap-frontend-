package com.example.urabanapp.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.urabanapp.api.DataApi;
import com.example.urabanapp.R;
import com.example.urabanapp.fragment.BookingFragment;
import com.example.urabanapp.fragment.CoupounFragment;
import com.example.urabanapp.fragment.HomeFragment;
import com.example.urabanapp.fragment.ProfileFragment;
import com.example.urabanapp.model.BannerModel;
import com.example.urabanapp.model.BookingModel;
import com.example.urabanapp.model.CategoryModel;
import com.example.urabanapp.model.CoupenModel;
import com.example.urabanapp.model.DataOutputModel;
import com.example.urabanapp.model.PincodeModel;
import com.example.urabanapp.model.ServicesModel;

import com.example.urabanapp.model.SubcateModel;
import com.nafis.bottomnavigation.NafisBottomNavigation;

import java.util.ArrayList;

public class NavigationActivity extends AppCompatActivity {


    NafisBottomNavigation bnv;
    public ArrayList<BannerModel> banner;
    public ArrayList<CategoryModel> category;
    public ArrayList<CoupenModel> coupon;
//    public ArrayList<BookingModel> booking;

    public ArrayList<PincodeModel> pincode;
    public ArrayList<ServicesModel> service;
    public ArrayList<SubcateModel> Subcate;



    public static final int ID_HOME = 1,ID_COUP0N = 2,ID_BOOKING = 3, ID_PROFILE = 4;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_navigation);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        bnv= findViewById(R.id.bnv);
        new DataApi().getData(this);



        bnv.add(new NafisBottomNavigation.Model(ID_HOME,R.mipmap.home));
        bnv.add(new NafisBottomNavigation.Model(ID_COUP0N,R.mipmap.discount));
        bnv.add(new NafisBottomNavigation.Model(ID_BOOKING,R.mipmap.booking));
        bnv.add(new NafisBottomNavigation.Model(ID_PROFILE,R.mipmap.profile));


        bnv.show(1,true);
        bnv.setOnClickMenuListener(model -> {
            if (model.getId() == ID_HOME) {
                addFragment(new HomeFragment(banner,category,service,Subcate));
            } else if (model.getId() == ID_COUP0N) {
                addFragment(new CoupounFragment(coupon));
            }else if (model.getId() == ID_BOOKING) {
                //addFragment(new BookingFragment());
                startActivity(new Intent(this,OrderHistoryActivity.class));
            } else {
                addFragment(new ProfileFragment());
            }
            return null;
        });

    }


    public void addFragment(Fragment fragment) {
        FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();

        // Replace the fragment inside the container
        transaction.replace(R.id.frame, fragment);

        // Optional: Add this transaction to the back stack to allow navigation back
        transaction.addToBackStack(null);  // You can provide a name for back stack if needed

        // Commit the transaction
        transaction.commit();
    }

    public void getData(DataOutputModel data){
        Log.e("SERVICES", ""+data.getService_data());
        banner=data.getBanner_data();
        coupon=data.getCoupon_data();
        category=data.getCategory_data();
        service=data.getService_data();
        Subcate=data.getSubcat_data();

        addFragment(new HomeFragment(banner,category,service,Subcate));
    }

    public ArrayList<CoupenModel> getCoupen(){
        return coupon;
    }

}