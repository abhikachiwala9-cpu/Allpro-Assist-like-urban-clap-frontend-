package com.example.urabanapp.adapter;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import com.example.urabanapp.R;
import com.example.urabanapp.activity.ServiceActivity;
import com.example.urabanapp.model.BookingModel;

import java.util.List;

public class BookingAdapter extends RecyclerView.Adapter<BookingAdapter.BookingViewHolder> {

    private Context context;
    private List<BookingModel> bookingList;

    // Constructor for the adapter
    public BookingAdapter(Context context, List<com.example.urabanapp.model.BookingModel> bookingList) {
        this.context = context;
        this.bookingList = bookingList;
    }

    @Override
    public BookingViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Inflate the layout for each item
        View view = LayoutInflater.from(context).inflate(R.layout.item_service, parent, false);
        return new BookingViewHolder(view);
    }

    @Override
    public void onBindViewHolder(BookingViewHolder holder, int position) {
        BookingModel booking = bookingList.get(position);

        // Set the data from the BookingModel to the views
        holder.serviceTitle.setText(booking.getPname());
        holder.serviceAddress.setText("Address: " + booking.getAddress());
        holder.serviceMob.setText("Contact no: " + booking.getC_o());
        holder.servicePrice.setText("₹ " + booking.getTot_amount());

        // You can use Picasso or Glide to load images into ImageView if needed
        Glide.with(holder.detailsButton.getContext()).load(booking.getPic1()).into(holder.serviceImage);

        // Rating can be set as well, if applicable
        holder.serviceRating.setText(String.valueOf(4.5)); // For now, using a hardcoded value

        holder.detailsButton.setOnClickListener(view -> {
            Intent intent=new Intent(context, ServiceActivity.class);
            intent.putExtra("book",booking);
            context.startActivity(intent);
        });
        // Set the "Details" button functionality
        //holder.detailsButton.setOnClickListener(v -> {
            // Handle the click to show details of the booking
       // });
    }

    @Override
    public int getItemCount() {
        return bookingList.size();
    }

    // ViewHolder class to hold references to the views in each item
    public static class BookingViewHolder extends RecyclerView.ViewHolder {

        TextView serviceTitle, serviceAddress, serviceMob, servicePrice, serviceRating;
        ImageView serviceImage;
        Button detailsButton;

        public BookingViewHolder(View itemView) {
            super(itemView);
            serviceTitle = itemView.findViewById(R.id.service_title);
            serviceAddress = itemView.findViewById(R.id.service_add);
            serviceMob = itemView.findViewById(R.id.service_mob);
            servicePrice = itemView.findViewById(R.id.service_price);
            serviceRating = itemView.findViewById(R.id.service_rating);
            serviceImage = itemView.findViewById(R.id.service_image);
            detailsButton = itemView.findViewById(R.id.service_details_button);
        }
    }
}