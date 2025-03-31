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
import com.example.urabanapp.activity.ServiceDetailActivity;
import com.example.urabanapp.model.ServicesModel;
import com.example.urabanapp.utilites.ConstantData;


import java.util.List;


import android.widget.ArrayAdapter;
import android.widget.Filter;

import java.util.ArrayList;
import java.util.List;

public class SearchAdapter extends ArrayAdapter<ServicesModel> {

    private List<ServicesModel> originalData; // Original data list
    private List<ServicesModel> filteredData; // Filtered data list
    private ItemFilter filter;

    public SearchAdapter(Context context, List<ServicesModel> data) {
        super(context, 0, data);
        this.originalData = data;
        this.filteredData = new ArrayList<>(data);
        filter = new ItemFilter();
    }

    @Override
    public int getCount() {
        return filteredData.size();
    }

    @Override
    public ServicesModel getItem(int position) {
        return filteredData.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Inflate a custom layout for each item in the AutoCompleteTextView
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.raw_search, parent, false);
        }

        ServicesModel product = getItem(position);

        // Set product name
        TextView productName = convertView.findViewById(R.id.tvName);
        productName.setText(product.getSr_name());

        // Set product image using Glide (optional)
        ImageView productImage = convertView.findViewById(R.id.img);
        Glide.with(getContext())
                .load(ConstantData.SERVER_ADDRESS+product.getSr_pic1()) // Use the product image URL
                .into(productImage);

//

        CardView searchcard= convertView.findViewById(R.id.searchcard);

        searchcard.setOnClickListener(v -> {
            Intent intent=new Intent(getContext(), ServiceDetailActivity.class);
            intent.putExtra("service",product);
            getContext().startActivity(intent);
        });

        return convertView;
    }

    @Override
    public Filter getFilter() {
        return filter;
    }

    private class ItemFilter extends Filter {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();

            // If there is no query, return the original data
            if (constraint == null || constraint.length() == 0) {
                results.values = originalData;
                results.count = originalData.size();
            } else {
                // Filter based on the product name
                List<ServicesModel> filteredList = new ArrayList<>();
                for (ServicesModel product : originalData) {
                    if (product.getSr_name().toLowerCase().contains(constraint.toString().toLowerCase())) {
                        filteredList.add(product);
                    }
                }
                results.values = filteredList;
                results.count = filteredList.size();
            }

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            if (results.values != null) {
                filteredData = (List<ServicesModel>) results.values;
                notifyDataSetChanged(); // Notify adapter about data changes
            }
        }
    }
}
