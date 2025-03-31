package com.example.urabanapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
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

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class OtpActivity extends AppCompatActivity {

    EditText editTextDigit1,editTextDigit2,editTextDigit3,editTextDigit4;

    Button btnVerify;
    String username, password, email, mobileno, verificationId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_otp);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
//        ui_ids();
//        etswitcher();


        username = getIntent().getStringExtra("username");
        password = getIntent().getStringExtra("password");
        email = getIntent().getStringExtra("email");
        mobileno = getIntent().getStringExtra("mobileno");
        verificationId = getIntent().getStringExtra("verificationId");

        editTextDigit1 = findViewById(R.id.editTextDigit1);
        editTextDigit2 = findViewById(R.id.editTextDigit2);
        editTextDigit3 = findViewById(R.id.editTextDigit3);
        editTextDigit4 = findViewById(R.id.editTextDigit4);
        btnVerify = findViewById(R.id.btnVerify);
        setOtpBoxListeners();


        btnVerify.setOnClickListener(v->{
            if (isOtpValid()){
                String code =getOtp();
                verifyOTP(code);
            }else {
                Toast.makeText(this, "Please Enter Valid OTP", Toast.LENGTH_SHORT).show();
            }
            // Itent intent = new Intent(this, HomeScreenActivity.class);
//            startActivity(intent);
//            finish();
        });

    }
    private boolean isOtpValid() {
        return !(editTextDigit1.getText().toString().trim().isEmpty() ||
                editTextDigit2.getText().toString().trim().isEmpty() ||
                editTextDigit3.getText().toString().trim().isEmpty() ||
                editTextDigit4.getText().toString().trim().isEmpty());
    }

    private String getOtp() {
        return editTextDigit1.getText().toString() +
                editTextDigit2.getText().toString() +
                editTextDigit3.getText().toString() +
                editTextDigit4.getText().toString();
    }
    private void setOtpBoxListeners() {
        editTextDigit1.addTextChangedListener(new OtpTextWatcher(editTextDigit1, editTextDigit2));
        editTextDigit2.addTextChangedListener(new OtpTextWatcher(editTextDigit2, editTextDigit3));
        editTextDigit3.addTextChangedListener(new OtpTextWatcher(editTextDigit3, editTextDigit4));
        editTextDigit4.addTextChangedListener(new OtpTextWatcher(editTextDigit4, null));
    }

    private static class OtpTextWatcher implements TextWatcher {
        private final EditText currentEditText;
        private final EditText nextEditText;

        OtpTextWatcher(EditText currentEditText, EditText nextEditText) {
            this.currentEditText = currentEditText;
            this.nextEditText = nextEditText;
        }

        @Override
        public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
        }

        @Override
        public void afterTextChanged(Editable editable) {
            if (editable.length() == 1 && nextEditText != null) {
                nextEditText.requestFocus();
            }
        }
    }
    public void verifyOTP(String code) {
        if (verificationId == null || verificationId.isEmpty()) {
            Toast.makeText(this, "Verification ID missing!", Toast.LENGTH_SHORT).show();
            return;
        }

        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(() -> {
            OkHttpClient client = new OkHttpClient.Builder().build();
            MediaType mediaType = MediaType.parse("text/plain");
            RequestBody body = RequestBody.create(mediaType, "");

            Request request = new Request.Builder()
                    .url("https://cpaas.messagecentral.com/verification/v3/validateOtp?countryCode=91&mobileNumber=" +
                            mobileno + "&verificationId=" + verificationId + "&customerId="+ ConstantData.CUSTOMER_ID +"&code=" + code)
                    .method("GET", null)  // GET requests shouldn't have a body
                    .addHeader("authToken", ConstantData.AUTH_TOKEN )
                    .build();

            try {
                Response response = client.newCall(request).execute();
                if (response.isSuccessful()) {
                    runOnUiThread(() -> {
                        Toast.makeText(OtpActivity.this, "Verification successful", Toast.LENGTH_SHORT).show();
                        //pending registeer api

                        new Login_Register_Api().registerUser(this,new PersonModel(username,email,password,mobileno,"",""));
//
//                        Intent intent = new Intent(OtpActivity.this, NavigationActivity.class);
//                        startActivity(intent);
//                        finish();
                    });
                } else {
                    runOnUiThread(() -> Toast.makeText(OtpActivity.this, "Invalid OTP. Try again!", Toast.LENGTH_SHORT).show());
                }
            } catch (IOException e) {
                Log.e("ERROR", e.getLocalizedMessage());
                runOnUiThread(() -> Toast.makeText(OtpActivity.this, "Network error! Try again.", Toast.LENGTH_SHORT).show());
            }
        });
    }





//        public void etswitcher () {
//            editTextDigit1.addTextChangedListener(new TextWatcher() {
//                @Override
//                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//                }
//
//                @Override
//                public void onTextChanged(CharSequence s, int start, int before, int count) {
//
//                }
//
//                @Override
//                public void afterTextChanged(Editable s) {
//                    if (s != null) {
//                        if (s.length() == 1) {
//                            etotp2.requestFocus();
//                        }
//                    }
//                }
//            });
//            etotp2.addTextChangedListener(new TextWatcher() {
//                @Override
//                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//                }
//
//                @Override
//                public void onTextChanged(CharSequence s, int start, int before, int count) {
//
//                }
//
//                @Override
//                public void afterTextChanged(Editable s) {
//                    if (s != null) {
//                        if (s.length() == 1) {
//                            etotp3.requestFocus();
//                        }
//                    }
//                }
//            });
//            etotp3.addTextChangedListener(new TextWatcher() {
//                @Override
//                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//                }
//
//                @Override
//                public void onTextChanged(CharSequence s, int start, int before, int count) {
//
//                }
//
//                @Override
//                public void afterTextChanged(Editable s) {
//                    if (s != null) {
//                        if (s.length() == 1) {
//                            etotp4.requestFocus();
//                        }
//                    }
//                }
//            });
//
//        }
//
//
//        private boolean isOtpValid () {
//            return !(editTextDigit1.getText().toString().trim().isEmpty() ||
//                    editTextDigit2.getText().toString().trim().isEmpty() ||
//                    editTextDigit3.getText().toString().trim().isEmpty() ||
//                    editTextDigit4.getText().toString().trim().isEmpty());
//        }
//
//        private String getOtp () {
//            return etotp1.getText().toString() +
//                    etotp2.getText().toString() +
//                    etotp3.getText().toString() +
//                    etotp4.getText().toString();
//        }
//
//
//        public void verifyOTP (String code){
//            if (verificationId == null || verificationId.isEmpty()) {
//                Toast.makeText(this, "Verification ID missing!", Toast.LENGTH_SHORT).show();
//                return;
//            }
//
//            ExecutorService executor = Executors.newSingleThreadExecutor();
//            executor.execute(() -> {
//                OkHttpClient client = new OkHttpClient.Builder().build();
//                MediaType mediaType = MediaType.parse("text/plain");
//                RequestBody body = RequestBody.create(mediaType, "");
//
//                Request request = new Request.Builder()
//                        .url("https://cpaas.messagecentral.com/verification/v3/validateOtp?countryCode=91&mobileNumber=" +
//                                mobileno + "&verificationId=" + verificationId + "&customerId=" + ConstantData.CUSTOMER_ID + "&code=" + code)
//                        .method("GET", null)  // GET requests shouldn't have a body
//                        .addHeader("authToken", ConstantData.AUTH_TOKEN)
//                        .build();
//
//                try {
//                    Response response = client.newCall(request).execute();
//                    if (response.isSuccessful()) {
//                        runOnUiThread(() -> {
//                            Toast.makeText(OtpActivity.this, "Verification successful", Toast.LENGTH_SHORT).show();
//                            //pending registeer api
//
//                            Intent intent = new Intent(OtpActivity.this, HomeFragment.class);
//                            startActivity(intent);
//                            finish();
//                        });
//                    } else {
//                        runOnUiThread(() -> Toast.makeText(OtpActivity.this, "Invalid OTP. Try again!", Toast.LENGTH_SHORT).show());
//                    }
//                } catch (IOException e) {
//                    Log.e("ERROR", e.getLocalizedMessage());
//                    runOnUiThread(() -> Toast.makeText(OtpActivity.this, "Network error! Try again.", Toast.LENGTH_SHORT).show());
//                }
//            });
//
//        }

}