package com.example.urabanapp.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.urabanapp.R;
import com.example.urabanapp.activity.SeeCategoryActivity;
import com.example.urabanapp.activity.ServiceActivity;
import com.example.urabanapp.activity.ServiceDetailActivity;
import com.example.urabanapp.model.CategoryModel;
import com.example.urabanapp.model.ServicesModel;
import com.example.urabanapp.model.SubcateModel;
import com.example.urabanapp.utilites.ConstantData;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.categoryViewHolder>{

    ArrayList<CategoryModel> category;

    ArrayList<ServicesModel> service;
    ArrayList<SubcateModel> subcateModels;
    private List<SubcateModel> subCategoryList;

    public CategoryAdapter(ArrayList<CategoryModel> category, ArrayList<ServicesModel> service, ArrayList<SubcateModel> subcateModels) {
        this.category = category;
        this.service = service;
        this.subcateModels = subcateModels;
    }

    @NonNull
    @Override
    public CategoryAdapter.categoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.raw_category, parent, false);
        CategoryAdapter.categoryViewHolder viewHolder = new CategoryAdapter.categoryViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryAdapter.categoryViewHolder holder, int position) {
        CategoryModel model = category.get(position);


//        ServicesModel servicemodel = service.get(position);

        holder.tvcategory.setText(model.getCategory_name());
        Glide.with(holder.imgcategory.getContext()).
                load(ConstantData.SERVER_ADDRESS + model.getCategory_pic()).
                into(holder.imgcategory);

        holder.imgcategory.setOnClickListener(v->{
            ArrayList<SubcateModel> p=new ArrayList<>();
            ArrayList<ServicesModel> s=new ArrayList<>();
            for (int i=0; i<subcateModels.size(); i++){
                if (model.getCategory_name().equals(subcateModels.get(i).getCat_id())){
                    p.add(subcateModels.get(i));
                }
            }
//            for (int i = 0;i < service.size();i++) {
//                if (subcateModels.get(i).getS_name().equals(servicemodel.getSub_id())) {
//                    s.add(service.get(i));
//                }
//            }


            Intent intent = new Intent(holder.imgcategory.getContext(), SeeCategoryActivity.class);
            intent.putExtra("subcate",p);

            intent.putExtra("ser",service);

            holder.imgcategory.getContext().startActivity(intent);
        });


    }

    @Override
    public int getItemCount() {
        return category.size();
    }

    public class categoryViewHolder extends RecyclerView.ViewHolder {

        ImageView imgcategory;
        TextView tvcategory;



        public categoryViewHolder(@NonNull View itemView) {


            super(itemView);

            imgcategory = itemView.findViewById(R.id.imgcategory);
            tvcategory = itemView.findViewById(R.id.tvcategory);

        }
    }
}
