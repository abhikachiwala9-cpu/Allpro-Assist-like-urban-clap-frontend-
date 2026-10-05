package com.example.urabanapp.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.urabanapp.R;
import com.example.urabanapp.api.Login_Register_Api;

public class forget_passActivity extends AppCompatActivity {

    EditText email,pass,conpass,mobileNo;
    Button confirm,submit;
    LinearLayout ll;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_forget_pass);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        email = findViewById(R.id.email);
        pass = findViewById(R.id.pass);
        conpass = findViewById(R.id.conpass);
        confirm = findViewById(R.id.confirm);
        submit = findViewById(R.id.submit);
        mobileNo = findViewById(R.id.mobileNo);
        ll = findViewById(R.id.ll);

        submit.setOnClickListener(v -> {
            String e = email.getText().toString().trim();
            String mobile = mobileNo.getText().toString().trim();
            if (e.isEmpty()) {
                Toast.makeText(this, "Email Is Required", Toast.LENGTH_SHORT).show();
            } else if (mobile.isEmpty()) {
                Toast.makeText(this, "Mobile Is Required", Toast.LENGTH_SHORT).show();
            } else {
                new Login_Register_Api().findUser(this, e,mobile);
            }
        });

        confirm.setOnClickListener(v -> {
            String e = email.getText().toString().trim();
            String mobile = mobileNo.getText().toString().trim();
            String password = pass.getText().toString().trim();
            String cpass = conpass.getText().toString().trim();

            if (password.isEmpty()) {
                Toast.makeText(this, "Password Is Required", Toast.LENGTH_SHORT).show();
            } else if (!cpass.equals(password)) {
                Toast.makeText(this, "Confirm Password Missed Matched", Toast.LENGTH_SHORT).show();
            } else {
                new Login_Register_Api().forgotPassword(this,e,mobile,password);
            }
        });

    }

    public void set() {
        ll.setVisibility(View.VISIBLE);
        submit.setVisibility(View.GONE);
        email.setEnabled(false);
        mobileNo.setEnabled(false);
    }
}