package com.example.urabanapp.fragment;

import static android.content.Context.MODE_PRIVATE;

import static com.example.urabanapp.utilites.ConstantData.KEY_SP_EMAIL;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.urabanapp.R;
import com.example.urabanapp.activity.EditprofileActivity;
import com.example.urabanapp.activity.FaqActivity;
import com.example.urabanapp.activity.LoginActivity;
import com.example.urabanapp.activity.OrderHistoryActivity;
import com.example.urabanapp.activity.PrivacyActivity;
import com.example.urabanapp.activity.aboutusActivity;
import com.example.urabanapp.activity.contactus;
import com.example.urabanapp.api.Login_Register_Api;
import com.example.urabanapp.utilites.ConstantData;

import java.security.Key;

public class ProfileFragment extends Fragment {

    View view;

    CardView cardedit,cardorder,cardabout,cardcontact,cardfaq,cardprivacy;

    Button btnlogout;
    SharedPreferences sp;
    TextView tvname,tvmail;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_profile, container, false);
        return  view;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        sp=requireActivity().getSharedPreferences(ConstantData.SP_NAME, Context.MODE_PRIVATE);

        cardorder=view.findViewById(R.id.cardorder);

        cardcontact=view.findViewById(R.id.cardcontact);
        cardabout=view.findViewById(R.id.cardabout);
        cardfaq=view.findViewById(R.id.cardfaq);
        cardprivacy=view.findViewById(R.id.cardprivacy);
        tvname=view.findViewById(R.id.tvname);
        tvmail=view.findViewById(R.id.tvmail);
        cardedit=view.findViewById(R.id.cardedit);

        btnlogout=view.findViewById(R.id.btnlogout);


        sp = requireActivity().getSharedPreferences(ConstantData.SP_NAME, MODE_PRIVATE);
        String user = sp.getString(ConstantData.KEY_SP_USERNAME,"");
        String mail = sp.getString(ConstantData.KEY_SP_EMAIL,"");
        tvname.setText(user);
        tvmail.setText(mail);


        cardfaq.setOnClickListener(v->{
            Intent intent = new Intent(getActivity(), FaqActivity.class);
            startActivity(intent);
        });

        cardabout.setOnClickListener(v->{
            Intent intent = new Intent(getActivity(), aboutusActivity.class);
            startActivity(intent);
        });

        cardcontact.setOnClickListener(v->{
            Intent intent = new Intent(getActivity(), contactus.class);
            startActivity(intent);
        });

        cardprivacy.setOnClickListener(v->{
            Intent intent = new Intent(getActivity(), PrivacyActivity.class);
            startActivity(intent);
        });

//        privacy.setOnClickListener(v->{
//            Intent intent = new Intent(getActivity(), PrivacyActivity.class);
//            startActivity(intent);
//        });


        cardedit.setOnClickListener(v->{
            Intent intent = new Intent(getActivity(), EditprofileActivity.class);
            startActivity(intent);
        });


//        btnlogout.setOnClickListener(v -> {
//            SharedPreferences.Editor ed = sp.edit();
//            ed.clear();
//            ed.apply();
//
//            Intent intent = new Intent(requireActivity(),LoginActivity.class);
//            startActivity(intent);
//        });

        btnlogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showLogoutDialog();
            }
        });

        cardorder.setOnClickListener(v->{
            Intent intent = new Intent(requireActivity(), OrderHistoryActivity.class);
            startActivity(intent);
        });


    }
    private void showLogoutDialog() {
        Dialog dialog = new Dialog(getActivity());
        dialog.setContentView(R.layout.dialog_logout);
        dialog.setCancelable(true);

        Button btnYes = dialog.findViewById(R.id.btnYes);
        Button btnNo = dialog.findViewById(R.id.btnNo);

        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor ed = sp.edit();
                ed.clear();
                ed.apply();
                Toast.makeText(getActivity(), "Logged Out!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(requireActivity(),LoginActivity.class);
            startActivity(intent);
                dialog.dismiss();
                // Perform logout logic here (e.g., navigate to login screen)
            }
        });

        btnNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }


}