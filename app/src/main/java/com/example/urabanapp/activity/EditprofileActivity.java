package com.example.urabanapp.activity;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
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

public class EditprofileActivity extends AppCompatActivity {

    EditText etname,etuser,etmail,etmobno;
    Button btnupdate;
    ImageView arrow;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_editprofile);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        etmail=findViewById(R.id.etmail);
        etuser=findViewById(R.id.etuser);
        etmobno=findViewById(R.id.etmobno);
        btnupdate=findViewById(R.id.btnupdate);

        arrow=findViewById(R.id.arrow);

        arrow.setOnClickListener(v->{
            finish();
        });

        SharedPreferences sp=getSharedPreferences(ConstantData.SP_NAME,MODE_PRIVATE);
        String uid=sp.getString(ConstantData.KEY_SP_USER_ID,"0");

        etmail.setText(sp.getString(ConstantData.KEY_SP_EMAIL,""));
        etmobno.setText(sp.getString(ConstantData.KEY_SP_MOB_NO,""));
        etuser.setText(sp.getString(ConstantData.KEY_SP_USERNAME,""));

        btnupdate.setOnClickListener(v->{
//            String username=etuser.getText().toString();
//            String email=etmail.getText().toString();
//            String mobileno=etmobno.getText().toString();

//            if(username.isEmpty() && email.isEmpty() && mobileno.isEmpty()){
//                Toast.makeText(this, "enter all data", Toast.LENGTH_SHORT).show();
//            }else{
//                new Login_Register_Api().editprofile(
//                        this,
//                        new PersonModel(username,email,"",mobileno,"",uid)
//                );
//            }



            String username = etuser.getText().toString().trim();
            String email = etmail.getText().toString().trim();
            String mobileno = etmobno.getText().toString().trim();

            if (username.isEmpty()) {
                etuser.setError("Enter valid username");
                etuser.requestFocus();
                return;
            }

            if (email.isEmpty()) {
                etmail.setError("Enter valid email");
                etmail.requestFocus();
                return;
            }

            if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                etmail.setError("Invalid email format");
                etmail.requestFocus();
                return;
            }

            if (mobileno.isEmpty()) {
                etmobno.setError("Enter valid mobile number");
                etmobno.requestFocus();
                return;
            }

            if (mobileno.length() != 10) {
                etmobno.setError("Mobile number must be 10 digits");
                etmobno.requestFocus();
                return;
            }

            new Login_Register_Api().editprofile(
                        this,
                        new PersonModel(username,email,"",mobileno,"",uid)
                );
        });
    }
}