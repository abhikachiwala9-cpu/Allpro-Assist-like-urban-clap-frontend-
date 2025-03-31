package com.example.urabanapp.api;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.urabanapp.activity.NavigationActivity;
import com.example.urabanapp.model.DataOutputModel;
import com.example.urabanapp.utilites.ConstantData;
import com.google.gson.Gson;

public class DataApi {

    public void getData(Context context){

        ProgressDialog dialog = new ProgressDialog(context);
        dialog.setMessage("please wait...");
        dialog.setCancelable(false);
        dialog.show();

        RequestQueue queue= Volley.newRequestQueue(context);
        StringRequest request = new StringRequest(
                Request.Method.GET,
                ConstantData.DATA_METHOD,
                response -> {
                    dialog.dismiss();

                    DataOutputModel data = new Gson().fromJson(response,DataOutputModel.class);
                    Log.e("Response", response );
                    if(data.isStatus()){
                        ((NavigationActivity)context).getData(data);
                        Toast.makeText(context, "Data get success", Toast.LENGTH_SHORT).show();
                    }

                },
                error -> {
                    dialog.dismiss();
                    Log.e("SERVER ERROR",error.toString());
                    Toast.makeText(context, "SERVER ERROR" + error, Toast.LENGTH_SHORT).show();
                }
        );
        queue.add(request);
    }
}
