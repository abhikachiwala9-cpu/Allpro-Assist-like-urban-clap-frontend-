package com.example.urabanapp.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.urabanapp.R;
import com.example.urabanapp.utilites.ConstantData;

public class splashActivity extends AppCompatActivity {

    SharedPreferences sp;


    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_splash);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;

        });

        sp = getSharedPreferences(ConstantData.SP_NAME,MODE_APPEND);
        boolean islogin = sp.getBoolean(ConstantData.KEY_SP_ISLOGIN,false);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run()
            {
                Intent intent = new Intent(splashActivity.this, NavigationActivity.class);
                startActivity(intent);
                finish();   
//                if(islogin)
//                {
//                    Intent intent = new Intent(splashActivity.this, NavigationActivity.class);
//                    startActivity(intent);
//                    finish();
//                }
//                else {
//                    Intent intent = new Intent(splashActivity.this, LoginActivity.class);
//                    startActivity(intent);
//                    finish();
//                }
            }
        },2500);


    }
}