package com.example.urabanapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.urabanapp.R;
import com.example.urabanapp.activity.SeeCategoryActivity;
import com.example.urabanapp.activity.ServiceActivity;
import com.example.urabanapp.model.ServicesModel;
import com.example.urabanapp.model.SubcateModel;
import com.example.urabanapp.utilites.ConstantData;

import java.util.ArrayList;
import java.util.List;


public class SubCategoryAdapter extends RecyclerView.Adapter<SubCategoryAdapter.SubCategoryViewHolder> {

    private Context context;
    private List<SubcateModel> subCategoryList;
    ArrayList<ServicesModel> service;

    public SubCategoryAdapter(Context context, List<SubcateModel> subCategoryList, ArrayList<ServicesModel> service) {
        this.context = context;
        this.subCategoryList = subCategoryList;
        this.service = service;
    }

    @Override
    public SubCategoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Inflate the item layout (item_subcategory.xml)
        View itemView = LayoutInflater.from(context).inflate(R.layout.raw_sub, parent, false);
        return new SubCategoryViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(SubCategoryViewHolder holder, int position) {
        // Get the current subcategory model
        SubcateModel subCategory = subCategoryList.get(position);

        // Set the subcategory name
        holder.tvSubCategory.setText(subCategory.getS_name());

        // Load the image using Picasso (make sure the URL or image path is valid)
        Glide.with(context)
                .load(ConstantData.SERVER_ADDRESS+subCategory.getS_pic()) // Assuming this is the image URL
                .into(holder.imgSubCategory);

        holder.imgSubCategory.setOnClickListener(v->{
            ArrayList<ServicesModel> s=new ArrayList<>();
            for (int i = 0;i < service.size();i++) {
                if (service.get(i).getSub_id().equals(subCategory.getS_name())) {
                    s.add(service.get(i));
                }
            }

            Intent intent = new Intent(holder.imgSubCategory.getContext(), ServiceActivity.class);
            intent.putExtra("service",s);
            holder.imgSubCategory.getContext().startActivity(intent);
        });

    }

    @Override
    public int getItemCount() {
        return subCategoryList.size();
    }

    // ViewHolder class for holding references to the UI components
    public class SubCategoryViewHolder extends RecyclerView.ViewHolder {

        public TextView tvSubCategory;
        public ImageView imgSubCategory;
        public CardView cardView;

        public SubCategoryViewHolder(View itemView) {
            super(itemView);
            // Initialize UI components from the item layout
            tvSubCategory = itemView.findViewById(R.id.tvsub);
            imgSubCategory = itemView.findViewById(R.id.imgsub);
            cardView = itemView.findViewById(R.id.cardView);
        }
    }
}

//public class SubCategoryAdapter extends RecyclerView.Adapter<SubCategoryAdapter.SubCategoryViewHolder>
//{
//
//    private Context context;
//    private List<SubCategorymodel> subCategoryList;
//
//    public SubCategoryAdapter(Context context, List<SubCategorymodel> subCategoryList) {
//        this.context = context;
//        this.subCategoryList = subCategoryList;
//    }
//
//    @Override
//    public SubCategoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        // Inflate the item layout (item_subcategory.xml)
//        View itemView = LayoutInflater.from(context).inflate(R.layout.raw_sub, parent, false);
//        return new SubCategoryViewHolder(itemView);
//    }
//
//    @Override
//    public void onBindViewHolder(SubCategoryViewHolder holder, int position) {
//        // Get the current subcategory model
//        SubCategorymodel subCategory = subCategoryList.get(position);
//
//        // Set the subcategory name
//        holder.tvSubCategory.setText(subCategory.getS_name());
//
//        // Load the image using Picasso (make sure the URL or image path is valid)
//        Glide.with(context)
//                .load(ConstantData.SERVER_ADDRESS+subCategory.getS_pic()) // Assuming this is the image URL
//                .into(holder.imgSubCategory);
//    }
//
//    @Override
//    public int getItemCount() {
//        return subCategoryList.size();
//    }
//
//    // ViewHolder class for holding references to the UI components
//    public class SubCategoryViewHolder extends RecyclerView.ViewHolder {
//
//        public TextView tvSubCategory;
//        public ImageView imgSubCategory;
//        public CardView cardView;
//
//        public SubCategoryViewHolder(View itemView) {
//            super(itemView);
//            // Initialize UI components from the item layout
//            tvSubCategory = itemView.findViewById(R.id.tvsub);
//            imgSubCategory = itemView.findViewById(R.id.imgsub);
//            cardView = itemView.findViewById(R.id.cardView);
//        }
//    }
//}
