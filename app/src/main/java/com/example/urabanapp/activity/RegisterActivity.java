package com.example.urabanapp.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
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
import com.example.urabanapp.model.PersonModel;
import com.example.urabanapp.utilites.ConstantData;
import com.example.urabanapp.utilites.Validation;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class RegisterActivity extends AppCompatActivity {

    EditText etname,etmail,etmob,etpass,etconpass;
    Button btnsend;
    SharedPreferences sp;
    String fullname,mobno,pass,email,conpass;
    TextView tvlog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        etname=findViewById(R.id.etname);
        etmail=findViewById(R.id.etmail);
        etmob=findViewById(R.id.etmob);
        etpass=findViewById(R.id.etpass);
        etconpass=findViewById(R.id.etconpass);
        btnsend=findViewById(R.id.btnsend);
        tvlog=findViewById(R.id.tvlog);

        tvlog.setOnClickListener(v->{
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        });

        btnsend.setOnClickListener(v -> {
             fullname=etname.getText().toString().trim();
            email=etmail.getText().toString().trim();
            mobno=etmob.getText().toString().trim();
             pass=etpass.getText().toString().trim();
            conpass=etconpass.getText().toString().trim();

            if(fullname.isEmpty()){
                Toast.makeText(this, "Enter name", Toast.LENGTH_SHORT).show();
            } else if (email.isEmpty()) {
                Toast.makeText(this, "Enter mail", Toast.LENGTH_SHORT).show();
            } else if (!Validation.isValidEmail(email)) {
                Toast.makeText(this, "Invalid Email", Toast.LENGTH_SHORT).show();
            } else if (mobno.isEmpty()) {
                Toast.makeText(this, "Enter Number", Toast.LENGTH_SHORT).show();
            } else if (!Validation.isValidContactNumber(mobno)) {
                Toast.makeText(this, "Invalid number", Toast.LENGTH_SHORT).show();
            } else if (pass.isEmpty()) {
                Toast.makeText(this, "Enter Password", Toast.LENGTH_SHORT).show();
            } else if (!Validation.isValidPass(pass)) {
                Toast.makeText(this, "Invalid password", Toast.LENGTH_SHORT).show();
            } else if (conpass.isEmpty()) {
                Toast.makeText(this, "Enter conform Password", Toast.LENGTH_SHORT).show();
            } else if (!Validation.isValidPass(pass)) {
                Toast.makeText(this, "Invalid password", Toast.LENGTH_SHORT).show();
            } else {
                generateOTP(mobno);
                //new Login_Register_Api().registerUser(this, new PersonModel(fullname, email, pass, mobno, "", ""));
            }
        });
    }

    public void generateOTP(String mobileno) {
        ExecutorService executor = Executors.newSingleThreadExecutor(); // Run in background thread
        executor.execute(() -> {
            OkHttpClient client = new OkHttpClient.Builder().build();
            MediaType mediaType = MediaType.parse("text/plain");
            RequestBody body = RequestBody.create(mediaType, "");

            Request request = new Request.Builder()
                    .url("https://cpaas.messagecentral.com/verification/v3/send?countryCode=91&customerId="+ConstantData.CUSTOMER_ID+"&flowType=SMS&mobileNumber=" + mobileno)
                    .method("POST", body)
                    .addHeader("authToken", ConstantData.AUTH_TOKEN)
                    .build();

            try {
                Response response = client.newCall(request).execute();
                if (response.isSuccessful() && response.body() != null) {
                    String responseBody = response.body().string();

                    JSONObject jsonResponse = new JSONObject(responseBody);

                    // Extract verificationId from the response
                    JSONObject data = jsonResponse.getJSONObject("data");
                    String verificationId = data.getString("verificationId");

                    // Send data to OTPActivity
                    runOnUiThread(() -> {
                        Intent intent = new Intent(RegisterActivity.this, OtpActivity.class);
                        intent.putExtra("mobileno", mobileno);
                        intent.putExtra("username", fullname);
                        intent.putExtra("password", pass);
                        intent.putExtra("email", email);
                        intent.putExtra("verificationId", verificationId); // Pass verificationId
                        startActivity(intent);
                    });
                } else {
                    runOnUiThread(() -> Toast.makeText(RegisterActivity.this, "OTP request failed", Toast.LENGTH_SHORT).show());
                }
            } catch (IOException | JSONException e) {
                Log.e("ERROR", e.getLocalizedMessage());
                runOnUiThread(() -> Toast.makeText(RegisterActivity.this, "Network error! Try again.", Toast.LENGTH_SHORT).show());
            }
        });
    }
}