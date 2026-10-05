package com.example.urabanapp.api;

import android.content.Context;
import android.content.Intent;
import android.net.wifi.hotspot2.pps.HomeSp;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.urabanapp.activity.BookActivity;
import com.example.urabanapp.activity.NavigationActivity;
import com.example.urabanapp.activity.OrderHistoryActivity;
import com.example.urabanapp.activity.ServiceDetailActivity;
import com.example.urabanapp.activity.ThanksActivity;
import com.example.urabanapp.model.BookingModel;
import com.example.urabanapp.model.BookingOutputModel;
import com.example.urabanapp.model.CouponOutputModel;
import com.example.urabanapp.utilites.ConstantData;
import com.example.urabanapp.utilites.CustomProgressDialog;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

public class OrderApi {

    public void addOrder(Context context, BookingModel model){

        RequestQueue queue = Volley.newRequestQueue(context);
        StringRequest request=new StringRequest(
                Request.Method.POST,
                ConstantData.ADD_ORDER_METHOD,
                response -> {
                    try {
                        BookingOutputModel booking = new Gson().fromJson(response,BookingOutputModel.class);
                        if(booking.isStatus()){

                            Toast.makeText(context, "Add to Cart", Toast.LENGTH_SHORT).show();

//                            Intent intent = new Intent(context, BookActivity.class);
////                            context.startActivity(intent);
////                           ((ServiceDetailActivity)context).finish();
                        }
                    }catch (Exception e){
//                        Toast.makeText(context, "Booking Sucess", Toast.LENGTH_SHORT).show();
//                        Intent intent = new Intent(context, BookActivity.class);
//                        context.startActivity(intent);
                    }

                },
                error -> {
                    Log.e("server error",error.toString());
                    Toast.makeText(context, "server error : "+ error, Toast.LENGTH_SHORT).show();
                }
        )
        {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> map= new HashMap<>();
                map.put("pid",model.getPid());
                map.put("uid",model.getUid());
                map.put("c_discount",String.valueOf(model.getC_discount()));
                map.put("date",model.getDate());
                map.put("time",model.getTime());
                map.put("c_o",model.getC_o());
                map.put("c_code",model.getC_code());
                map.put("Address",model.getAddress());
                return map;
            }
        };
        queue.add(request);
    }

//    public void  getOrder(Context context, String uid, String status) {
//
//        RequestQueue queue = Volley.newRequestQueue(context);
//        StringRequest request = new StringRequest(
//                Request.Method.POST,
//                ConstantData.GET_ORDER_METHOD,
//                response -> {

//                    BookingOutputModel booking = new Gson().fromJson(response, BookingOutputModel.class);
//                    if (booking.isStatus()) {
//
//                        ((BookActivity)context).setBooking(booking);
//                        Toast.makeText(context, "Booking Get Suucess", Toast.LENGTH_SHORT).show();
//                    }
//                },
//                error -> {
//                    Log.e("Server Error", error.toString());
//                    Toast.makeText(context, "Server Error" + error, Toast.LENGTH_SHORT).show();
//                }
//
//        ) {
//            @Nullable
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//                Map<String, String> map = new HashMap<>();
//                map.put("uid", uid);
//                map.put("status", status);
//                return map;
//            }
//        };
//        queue.add(request);
//
//    }

    public void  getOrder(BookActivity context, String uid,String status) {

        RequestQueue queue = Volley.newRequestQueue(context);
        StringRequest request = new StringRequest(
                Request.Method.POST,
                ConstantData.GET_ORDER_METHOD,
                response -> {
                    try {
                        BookingOutputModel booking = new Gson().fromJson(response, BookingOutputModel.class);
                        if (booking.isStatus()) {

                            ((BookActivity) context).setBooking(booking.getBooking());
//                            Toast.makeText(context, "Booking Get Suucess", Toast.LENGTH_SHORT).show();
                        }
                    }catch (Exception e){

                    }
                },
                error -> {
                    Log.e("Server Error", error.toString());
                    Toast.makeText(context, "Server Error" + error, Toast.LENGTH_SHORT).show();
                }

        ) {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                map.put("uid", uid);
                map.put("status", status);
                return map;
            }
        };
        queue.add(request);

    }


//    public void  removebooking(Context context, String id,String uid) {
//
//        RequestQueue queue = Volley.newRequestQueue(context);
//        StringRequest request = new StringRequest(
//                Request.Method.POST,
//                ConstantData.REMOVE_METHOD,
//                response -> {
//                    BookingOutputModel booking = new Gson().fromJson(response, BookingOutputModel.class);
//                    if (booking.isStatus()) {
//
//                        ((BookActivity)context).setBooking(booking.getBooking());
//                        Toast.makeText(context, "Remove Get Suucess", Toast.LENGTH_SHORT).show();
//                    }
//                },
//                error -> {
//                    Log.e("Server Error", error.toString());
//                    Toast.makeText(context, "Server Error" + error, Toast.LENGTH_SHORT).show();
//                }
//
//        ) {
//            @Nullable
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//                Map<String, String> map = new HashMap<>();
//                map.put("id", id);
//                map.put("uid", uid);
//                return map;
//            }
//        };
//        queue.add(request);
//
//    }


    public void  removebooking(Context context, String id,String uid) {

        RequestQueue queue = Volley.newRequestQueue(context);
        StringRequest request = new StringRequest(
                Request.Method.POST,
                ConstantData.REMOVE_METHOD,
                response -> {
                    BookingOutputModel booking = new Gson().fromJson(response, BookingOutputModel.class);
                    if (booking.isStatus()) {

                        ((BookActivity)context).setBooking(booking.getBooking());
                        Toast.makeText(context, "Remove Get Suucess", Toast.LENGTH_SHORT).show();
                    }
                },
                error -> {
                    Log.e("Server Error", error.toString());
                    Toast.makeText(context, "Server Error" + error, Toast.LENGTH_SHORT).show();
                }

        ) {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                map.put("id", id);
                map.put("uid", uid);
                return map;
            }
        };
        queue.add(request);

    }
    public void applyCoupon(Context context,String code){
        RequestQueue queue = Volley.newRequestQueue(context);
        StringRequest request = new StringRequest(
                Request.Method.POST,
                ConstantData.APPLY_COUPON_METHOD,
                response -> {
                    CouponOutputModel c=new Gson().fromJson(response,CouponOutputModel.class);
                    if(c.isStatus()){
                        ((BookActivity)context).setCoupon(c.getCoupon_data());
                        Toast.makeText(context, c.getMessage(), Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(context, c.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                },
                error -> {
                    Toast.makeText(context, "Server Error" + error, Toast.LENGTH_SHORT).show();
                    Log.e("apply coupon server error",error.toString());
                }
        ){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                map.put("code", code);
                return map;
            }
        };
        queue.add(request);
    }

//    public void confirm_order(Context context, String uid,String address,String c_o,String c_code,String c_discount,String date,String time){
//
//        CustomProgressDialog customProgressDialog=new CustomProgressDialog(context);
//        customProgressDialog.show();
//
//
//        RequestQueue requestQueue= Volley.newRequestQueue(context);
//        StringRequest stringRequest=new StringRequest(Request.Method.POST, ConstantData.confirmOrder_METHOD, new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//                customProgressDialog.dismiss();
//                BookingOutputModel p= new Gson().fromJson(response,BookingOutputModel.class);
//                if(p.isStatus()){
//                    Intent intent=new Intent(context, ThanksActivity.class);
//                    context.startActivity(intent);
//                }else{
//                    Toast.makeText(context, p.getMessage(), Toast.LENGTH_SHORT).show();
//                }
//
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError volleyError) {
//                customProgressDialog.dismiss();
//                Toast.makeText(context, "error:"+volleyError.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
//            }
//        }){
//            @Nullable
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//                HashMap<String,String> map=new HashMap<>();
//                map.put("uid",uid);
//                map.put("address",address);
//                map.put("c_o",c_o);
//                map.put("c_code",c_code);
//                map.put("c_discount",c_discount);
//                map.put("date",date);
//                map.put("time",time);
//                return map;
//            }
//        };
//        requestQueue.add(stringRequest);
//    }


    public void confirm_order(Context context, String uid,String address,String c_code,String c_o,String c_discount,String date,String time){

        CustomProgressDialog customProgressDialog=new CustomProgressDialog(context);
        customProgressDialog.show();


        RequestQueue requestQueue= Volley.newRequestQueue(context);
        StringRequest stringRequest=new StringRequest(Request.Method.POST, ConstantData.confirmOrder_METHOD, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                customProgressDialog.dismiss();
                BookingOutputModel p= new Gson().fromJson(response,BookingOutputModel.class);
                if(p.isStatus()){
                    Intent intent=new Intent(context, ThanksActivity.class);
                    context.startActivity(intent);

                }else{
                    Toast.makeText(context, p.getMessage(), Toast.LENGTH_SHORT).show();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                customProgressDialog.dismiss();
                Toast.makeText(context, "error:"+volleyError.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> map=new HashMap<>();
                map.put("uid",uid);
                map.put("address",address);
                map.put("c_o",c_o);
                map.put("c_code",c_code);
                map.put("c_discount",c_discount);
                map.put("date",date);
                map.put("time",time);
                return map;
            }
        };
        requestQueue.add(stringRequest);
    }

    public void order_history(Context context, String uid,String status){

        CustomProgressDialog customProgressDialog=new CustomProgressDialog(context);
        customProgressDialog.show();


        RequestQueue requestQueue= Volley.newRequestQueue(context);
        StringRequest stringRequest=new StringRequest(Request.Method.POST, ConstantData.GET_ORDER_HIS_METHOD, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                customProgressDialog.dismiss();
                BookingOutputModel p= new Gson().fromJson(response,BookingOutputModel.class);
                if(p.isStatus()){
//                    if(context instanceof BookActivity){
                        Toast.makeText(context, p.getMessage(), Toast.LENGTH_SHORT).show();
                        ((OrderHistoryActivity)context).set(p);

                    //}

                }else{
                    Toast.makeText(context, p.getMessage(), Toast.LENGTH_SHORT).show();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                customProgressDialog.dismiss();
                Toast.makeText(context, "error:"+volleyError.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> map=new HashMap<>();
                map.put("uid",uid);
                map.put("status",status);
                return map;
            }
        };
        requestQueue.add(stringRequest);
    }

}
