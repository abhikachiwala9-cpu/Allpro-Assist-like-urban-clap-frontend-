package com.example.urabanapp.api;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.urabanapp.activity.LoginActivity;
import com.example.urabanapp.activity.NavigationActivity;
import com.example.urabanapp.activity.OtpActivity;
import com.example.urabanapp.model.PersonModel;
import com.example.urabanapp.model.PersonOutputModel;
import com.example.urabanapp.utilites.ConstantData;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

public class Login_Register_Api {

    public void registerUser(Context context, PersonModel personModel) {
        String URL = ConstantData.REGISTER_METHOD;
        RequestQueue queue = Volley.newRequestQueue(context);
        StringRequest request = new StringRequest(
                Request.Method.POST,
                URL,
                response -> {
                    try {
                        Gson gson = new Gson();
                        PersonOutputModel p = gson.fromJson(response, PersonOutputModel.class);
                        if (p.isStatus()) {
                            Intent intent = new Intent(context, LoginActivity.class);
                            context.startActivity(intent);
                            Toast.makeText(context, "User Registered Success", Toast.LENGTH_SHORT).show();
                        }
                    } catch (Exception e) {
                        Toast.makeText(context, "ERROR: " + e.toString(), Toast.LENGTH_SHORT).show();
                    }
                },
                error -> {
                    Toast.makeText(context, "SERVER ERROR:" + error.toString(), Toast.LENGTH_SHORT).show();
                }
        ){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String,String> map = new HashMap<>();
                map.put("username",personModel.getUsername());
                map.put("email",personModel.getEmail());
                map.put("password",personModel.getPassword());
                map.put("mobileno",personModel.getMobileno());

                return map;
            }
        };
        queue.add(request);
    }


//    public void loginUser(Context context, String email,String password) {
//        String URL = ConstantData.LOGIN_METHOD;
//        RequestQueue queue = Volley.newRequestQueue(context);
//        StringRequest request = new StringRequest(
//                Request.Method.POST,
//                URL,
//                response -> {
//                    try {
//                        Gson gson = new Gson();
//                        PersonOutputModel p = gson.fromJson(response, PersonOutputModel.class);
//                        if (p.isStatus()) {
//                            SharedPreferences sp=context.getSharedPreferences(ConstantData.SP_NAME,Context.MODE_PRIVATE);
//                            SharedPreferences.Editor editor=sp.edit();
//                            editor.putBoolean(ConstantData.KEY_SP_ISLOGIN,true);
//                            editor.putString(ConstantData.KEY_SP_EMAIL,p.getPerson().getEmail());
//                            editor.putString(ConstantData.KEY_SP_PASSWORD,p.getPerson().getPassword());
//                            editor.putString(ConstantData.KEY_SP_MOB_NO,p.getPerson().getMobileno());
//                            editor.putString(ConstantData.KEY_SP_USERNAME,p.getPerson().getUsername());
//                            editor.putString(ConstantData.KEY_SP_USER_ID,p.getPerson().getId());
//
//                            editor.commit();
//                            Intent intent = new Intent(context, NavigationActivity.class);
//                            context.startActivity(intent);
//                            Toast.makeText(context, "User Login Success", Toast.LENGTH_SHORT).show();
//                        } else {
//                            Toast.makeText(context, "User Not Found", Toast.LENGTH_SHORT).show();
//                        }
//                    } catch (Exception e) {
//                        Log.e("ERROR", e.toString() );
//                        Toast.makeText(context, "ERROR: " + e.toString(), Toast.LENGTH_SHORT).show();
//                    }
//                },
//                error -> {
//                    Toast.makeText(context, "SERVER ERROR:" + error.toString(), Toast.LENGTH_SHORT).show();
//                }
//        ){
//            @Nullable
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//
//                Map<String,String> map = new HashMap<>();
//                map.put("email",email);
//                map.put("password",password);
//                return map;
//            }
//        };
//        queue.add(request);
//    }

    public void loginUser(Context context, String email,String password) {
        String URL = ConstantData.LOGIN_METHOD ;
        RequestQueue queue = Volley.newRequestQueue(context);
        StringRequest request = new StringRequest(
                Request.Method.POST,
                URL,
                response -> {
                    try {
                        Gson gson = new Gson();
                        PersonOutputModel p = gson.fromJson(response,PersonOutputModel.class);
                        if(p.isStatus()){
                            SharedPreferences sp = context.getSharedPreferences(ConstantData.SP_NAME,Context.MODE_PRIVATE);
                            SharedPreferences.Editor ed = sp.edit();
                            ed.putBoolean(ConstantData.KEY_SP_ISLOGIN,true);
                            ed.putString(ConstantData.KEY_SP_USER_ID,p.getPeople().getId());
                            ed.putString(ConstantData.KEY_SP_EMAIL,p.getPeople().getEmail());
                            ed.putString(ConstantData.KEY_SP_MOB_NO,p.getPeople().getMobileno());
                            ed.putString(ConstantData.KEY_SP_USERNAME,p.getPeople().getUsername());
                            ed.commit();
                            Intent intent = new Intent(context, NavigationActivity.class);
                            context.startActivity(intent);
                            Toast.makeText(context, "User Login successfully..", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Toast.makeText(context, "User Not Found", Toast.LENGTH_SHORT).show();
                        }
                    }catch (Exception e){
                        Log.e("LOGIN ERROR",e.toString());
                        Toast.makeText(context, "ERROR: " + e.toString(), Toast.LENGTH_SHORT).show();
                    }
                },
                error -> {
                    Toast.makeText(context, "SERVER ERROR:" + error.toString(), Toast.LENGTH_SHORT).show();
                }
        ){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String,String> map = new HashMap<>();
                map.put("email",email);
                map.put("password",password);
                return map;
            }
        };
        queue.add(request);
    }

    public void editprofile(Context context,PersonModel model){
        RequestQueue queue=Volley.newRequestQueue(context);
        StringRequest request=new StringRequest(
                Request.Method.POST,
                ConstantData.EDIT_PROFILE_METHOD,
                response -> {
                    PersonOutputModel p=new Gson().fromJson(response,PersonOutputModel.class);
                    if(p.isStatus()){
                        Toast.makeText(context, "User updated", Toast.LENGTH_SHORT).show();

                        SharedPreferences sp = context.getSharedPreferences(ConstantData.SP_NAME,Context.MODE_PRIVATE);
                        SharedPreferences.Editor ed = sp.edit();

                        ed.putString(ConstantData.KEY_SP_EMAIL,model.getEmail());
                        ed.putString(ConstantData.KEY_SP_MOB_NO,model.getMobileno());
                        ed.putString(ConstantData.KEY_SP_USERNAME,model.getUsername());
                        ed.commit();

                        context.startActivity(new Intent(context,NavigationActivity.class));
                    }
                },
                error -> {
                    Log.e("server error",error.toString());
                    Toast.makeText(context, "server error"+error, Toast.LENGTH_SHORT).show();
                }
        ){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> map = new HashMap<>();
                map.put("username",model.getUsername());
                map.put("email", model.getEmail());
                map.put("mobileno", model.getMobileno());
                map.put("uid", model.getId());
                return map;
            }
        };
        queue.add(request);
    }
}
