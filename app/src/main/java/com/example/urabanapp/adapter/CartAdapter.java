package com.example.urabanapp.adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.urabanapp.R;
import com.example.urabanapp.api.OrderApi;
import com.example.urabanapp.model.BookingModel;
import com.example.urabanapp.utilites.ConstantData;

import java.util.ArrayList;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {

    Context context;
    ArrayList<BookingModel> cartList;

    public CartAdapter(Context context,ArrayList<BookingModel> cartList) {
        this.context = context;
        this.cartList = cartList;
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.raw_booking, parent, false);
        return new CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        BookingModel item = cartList.get(position);

        holder.tvName.setText(item.getPname());
        holder.tvPrice.setText("₹" + item.getAmount());
        Glide.with(context).load(ConstantData.SERVER_ADDRESS + item.getPic1()).into(holder.img);

        SharedPreferences sp = context.getSharedPreferences(ConstantData.SP_NAME,Context.MODE_PRIVATE);
        String uid = sp.getString(ConstantData.KEY_SP_USER_ID,"");

        holder.imgDelete.setOnClickListener(v -> {
            new OrderApi().removebooking(context, item.getId(), uid);
        });
    }

    @Override
    public int getItemCount() {
        return cartList.size();
    }

    public static class CartViewHolder extends RecyclerView.ViewHolder {
        ImageView img, imgDelete;
        TextView tvName, tvPrice;
        CardView cardView;

        public CartViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img);
            imgDelete = itemView.findViewById(R.id.imgdelete);
            tvName = itemView.findViewById(R.id.tvname);
            tvPrice = itemView.findViewById(R.id.tvprice);
            cardView = itemView.findViewById(R.id.cardView);
        }
    }

}
