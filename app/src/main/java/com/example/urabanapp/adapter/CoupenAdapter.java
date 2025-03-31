package com.example.urabanapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.urabanapp.R;
import com.example.urabanapp.model.CoupenModel;
import com.example.urabanapp.utilites.ConstantData;

import java.util.List;

public class CoupenAdapter extends RecyclerView.Adapter<CoupenAdapter.CoupenViewHolder> {


    private List<CoupenModel> coupenList;

    public CoupenAdapter(List<CoupenModel> coupenList) {

        this.coupenList = coupenList;
    }

    @NonNull
    @Override
    public CoupenViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.raw_coupen, parent, false);
        CoupenAdapter.CoupenViewHolder viewHolder = new CoupenAdapter.CoupenViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CoupenViewHolder holder, int position) {
        CoupenModel coupen = coupenList.get(position);
        holder.couponName.setText(coupen.getC_title());
        Glide.with(holder.couponImage.getContext()).load(ConstantData.SERVER_ADDRESS+coupen.getC_pic()).into(holder.couponImage);

        // Handle "Click here to Copy" click action if required
        holder.copyText.setOnClickListener(v -> {
            // Copy coupen code to clipboard
            android.content.ClipboardManager clipboard = (android.content.ClipboardManager) holder.couponImage.getContext().getSystemService(Context.CLIPBOARD_SERVICE);
            android.content.ClipData clip = android.content.ClipData.newPlainText("Coupon Code", coupen.getC_code());
            clipboard.setPrimaryClip(clip);
            // You can show a Toast message here to notify the user
        });
    }

    @Override
    public int getItemCount() {
        return coupenList.size();
    }

    public static class CoupenViewHolder extends RecyclerView.ViewHolder {
        ImageView couponImage;
        TextView couponName, copyText;

        public CoupenViewHolder(@NonNull View itemView) {
            super(itemView);
            couponImage = itemView.findViewById(R.id.imgCoupon);
            couponName = itemView.findViewById(R.id.couponName);
            copyText = itemView.findViewById(R.id.copyText);
        }
    }
}
