package com.example.urabanapp.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.urabanapp.R;
import com.example.urabanapp.api.Login_Register_Api;
import com.example.urabanapp.utilites.ConstantData;
import com.example.urabanapp.utilites.Validation;

public class LoginActivity extends AppCompatActivity {

    EditText etmail,etpass;
    Button btnlogin;
    SharedPreferences sp;
    TextView tvreg,tvForgotPassword;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        etmail=findViewById(R.id.etmail);
        etpass=findViewById(R.id.etpass);
        btnlogin=findViewById(R.id.btnlogin);
        tvreg=findViewById(R.id.tvreg);
        tvForgotPassword=findViewById(R.id.tvForgotPassword);


        tvForgotPassword.setOnClickListener(v->{
            Intent intent = new Intent(this, ForgetPasswordActivity.class);
                startActivity(intent);
                finish();
        });

        btnlogin.setOnClickListener(v->{
            String email=etmail.getText().toString().trim();
            String password=etpass.getText().toString().trim();
            if(email.isEmpty()){
                Toast.makeText(this,"enter email",Toast.LENGTH_SHORT).show();
            }else if (!Validation.isValidEmail(email)) {
                Toast.makeText(this, "Invalid Email", Toast.LENGTH_SHORT).show();
            }else if(password.isEmpty()){
                Toast.makeText(this, "Enter Passowrd", Toast.LENGTH_SHORT).show();
            } else if (!Validation.isValidPass(password)) {
                Toast.makeText(this, "Invalid Password", Toast.LENGTH_SHORT).show();
            }else {
                new Login_Register_Api().loginUser(this,email,password);
                sp = getSharedPreferences(ConstantData.SP_NAME, MODE_PRIVATE);
                SharedPreferences.Editor ed = sp.edit();
                ed.putBoolean(ConstantData.KEY_SP_ISLOGIN, true);
                ed.putString(ConstantData.KEY_SP_EMAIL, etmail.getText().toString().trim());
                ed.commit();
//
//                Intent intent = new Intent(this, NavigationActivity.class);
//                startActivity(intent);
//                finish();
            }
        });
        tvreg.setOnClickListener(v->{
            Intent intent = new Intent(this,RegisterActivity.class);
            startActivity(intent);
        });

    }
}