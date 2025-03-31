package com.example.urabanapp.adapter;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.urabanapp.R;
import com.example.urabanapp.activity.ServiceDetailActivity;
import com.example.urabanapp.model.ServicesModel;
import com.example.urabanapp.utilites.ConstantData;

import java.util.ArrayList;

public class ServicesAdapter extends RecyclerView.Adapter<ServicesAdapter.serviceViewHolder>{

    ArrayList<ServicesModel> service;

    public ServicesAdapter(ArrayList<ServicesModel> service) {
        this.service = service;
    }

    @NonNull
    @Override
    public ServicesAdapter.serviceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.raw_service, parent, false);
        ServicesAdapter.serviceViewHolder viewHolder = new ServicesAdapter.serviceViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ServicesAdapter.serviceViewHolder holder, int position) {
        ServicesModel model = service.get(position);
        holder.tvservice.setText(model.getSr_name());
//        holder.tvprice.setText(model.getSr_price());

        Glide.with(holder.imgservice.getContext()).
                load(ConstantData.SERVER_ADDRESS + model.getSr_pic1()).
                into(holder.imgservice);

        holder.Card.setOnClickListener(v->{
            Intent intent =new Intent(holder.Card.getContext(), ServiceDetailActivity.class);
            intent.putExtra("service",model);
            holder.Card.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return service.size();
    }

//        try {
//            return service.size();
//        } catch (Exception e) {
//            Log.e("SERVICE", e.toString());
//            return 0;
//
//        }




    public class serviceViewHolder extends RecyclerView.ViewHolder {

        ImageView imgservice;
        TextView tvservice;
        TextView tvprice;
        LinearLayout Card;

        @SuppressLint("WrongViewCast")
        public serviceViewHolder(@NonNull View itemView) {


            super(itemView);

            imgservice = itemView.findViewById(R.id.imgcategory);
            tvservice = itemView.findViewById(R.id.tvservice);
            tvprice = itemView.findViewById(R.id.tvprice);
            Card = itemView.findViewById(R.id.Card);

        }
    }

}
