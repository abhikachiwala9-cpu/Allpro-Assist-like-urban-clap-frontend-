package com.example.urabanapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.urabanapp.R;
import com.example.urabanapp.model.BookingModel;
import com.example.urabanapp.model.BookingModel;
import com.example.urabanapp.model.BookingModel;
import com.example.urabanapp.utilites.ConstantData;
import com.transferwise.sequencelayout.SequenceLayout;
import com.transferwise.sequencelayout.SequenceStep;
//import com.shuhart.stepview.StepView;

import java.util.ArrayList;
import java.util.List;



public class OrderHistoryAdapter extends RecyclerView.Adapter<OrderHistoryAdapter.CartViewHolder> {
    List<BookingModel> orderModels;
    OrderHistoryAdapter.OnClickListener clickListener;
    ArrayList<String> steps;
    Context context;

    public OrderHistoryAdapter(List<BookingModel> orderModels, OrderHistoryAdapter.OnClickListener clickListener, Context context) {
        this.orderModels = orderModels;
        this.clickListener = clickListener;
        this.context = context;
        steps=new ArrayList<>();
        steps.add("Order Placed");
        steps.add("Dispatched");
        steps.add("Shipping");
        steps.add("Delivered");

    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(com.example.urabanapp.R.layout.raw_orderhistory, parent, false);
        CartViewHolder cartViewHolder = new CartViewHolder(view);
        return cartViewHolder;
    }

    @NonNull


    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        BookingModel orderModel = orderModels.get(position);
        Glide.with(context).load(ConstantData.SERVER_ADDRESS+orderModel.getPic1()).into(holder.orderShoesImage);
        holder.orderShoesName.setText(orderModel.getPname());
        holder.orderShoesPrice.setText(orderModel.getAmount() + "" );



        holder.orderCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.tracker.setVisibility(View.VISIBLE);
            }
        });

        holder.tracker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.tracker.setVisibility(View.GONE);

            }
        });
        holder.step1.setSubtitle("Your Order has Received,Order Will be Dispatch within few Hours");
        holder.step2.setSubtitle("Order dispatched from SOLEGLIDE's Warehouse");
        holder.step3.setSubtitle("Order is being Shipped");
        holder.step4.setSubtitle("Order is Out for delivery");
        holder.step5.setSubtitle("Delieverd order");





        if(orderModel.getStatus()==1){
            holder.step1.setActive(true);
        }else if(orderModel.getStatus()==2){
            holder.step2.setActive(true);

        }else if(orderModel.getStatus()==3){
            holder.step3.setActive(true);

        }else if(orderModel.getStatus()==4){
            holder.step4.setActive(true);

        }else{
            holder.step5.setActive(true);

        }



    }

    @Override
    public int getItemCount() {
        return orderModels.size();
    }

    public class CartViewHolder extends RecyclerView.ViewHolder {
        ImageView orderShoesImage;
        SequenceLayout tracker;
        CardView orderCard;
        SequenceStep step1,step2,step3,step4,step5;
        TextView orderShoesName, orderShoesPrice, orderShoesColor, orderShoesSize;


        public CartViewHolder(@NonNull View itemView) {
            super(itemView);
            orderShoesImage = itemView.findViewById(R.id.orderShoesImage);
//
            orderShoesName = itemView.findViewById(R.id.orderShoesName);
            orderShoesPrice = itemView.findViewById(R.id.orderShoesPrice);


            step1=itemView.findViewById(R.id.step1);
            step2=itemView.findViewById(R.id.step2);
            step3=itemView.findViewById(R.id.step3);
            step4=itemView.findViewById(R.id.step4);
            step5=itemView.findViewById(R.id.step5);
            tracker=itemView.findViewById(R.id.tracker);
            orderCard=itemView.findViewById(R.id.orderCard);



        }
    }

    public interface OnClickListener {
        public void onClickPlus(BookingModel orderModel);

        public void onClickMinus(BookingModel orderModel);

        public void removeOrder(BookingModel orderModel);

    }

}
