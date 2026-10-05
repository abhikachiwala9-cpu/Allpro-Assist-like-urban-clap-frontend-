package com.example.urabanapp.fragment;

import static android.content.Context.MODE_PRIVATE;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.TextView;

import com.denzcoskun.imageslider.ImageSlider;
import com.example.urabanapp.R;
import com.example.urabanapp.activity.BookActivity;
import com.example.urabanapp.activity.SeeCategoryActivity;
import com.example.urabanapp.activity.ServiceActivity;
import com.example.urabanapp.adapter.BannerAdapter;
import com.example.urabanapp.adapter.CategoryAdapter;
import com.example.urabanapp.adapter.SearchAdapter;
import com.example.urabanapp.adapter.ServicesAdapter;
import com.example.urabanapp.adapter.SubCategoryAdapter;
import com.example.urabanapp.model.BannerModel;
import com.example.urabanapp.model.CategoryModel;
import com.example.urabanapp.model.CoupenModel;
import com.example.urabanapp.model.ServicesModel;
import com.example.urabanapp.model.SubcateModel;
import com.example.urabanapp.utilites.ConstantData;
import com.example.urabanapp.utilites.CustomPageTransformer;

import java.util.ArrayList;


public class HomeFragment extends Fragment {


    CardView cardView;
    ImageView imgclose,imgcart;
    TextView seall;
    public ArrayList<BannerModel> banner;
    public ArrayList<CategoryModel> category;
    public ArrayList<ServicesModel> services;
    View view;
    TextView tvemail,tvseecat;
    SharedPreferences sp;
    ImageSlider imageSlider;
    RecyclerView rcylCat,rcylProd,rcylSub;
    ViewPager2 viewPager;
    AutoCompleteTextView etsearch;
    ArrayList<String> imageUrls;
    Handler handler = new Handler();
    Runnable autoSlideRunnable;
    int currentPage = 1;
    ArrayList<CoupenModel> coupon;

    ArrayList<SubcateModel> Sub_data;

    public HomeFragment(ArrayList<BannerModel> banner, ArrayList<CategoryModel> category, ArrayList<ServicesModel> services, ArrayList<SubcateModel> subCategorymodels,ArrayList<CoupenModel> coupon) {
        this.banner = banner;
        this.category = category;
        this.services = services;
        this.Sub_data=subCategorymodels;
        this.coupon = coupon;
    }

//    public HomeFragment(ArrayList<BannerModel> banner, ArrayList<CategoryModel> category, ArrayList<ServicesModel> service, ArrayList<SubcateModel> subcate) {
//    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        return view;

    }

//    private void showBottomSheetDialog() {
//        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(requireContext());
//        View view = getLayoutInflater().inflate(R.layout.bottomsheetac, null);
//
//        ImageView imgclose = view.findViewById(R.id.imgclose);
//        imgclose.setOnClickListener(v -> bottomSheetDialog.dismiss());
//
//        bottomSheetDialog.setContentView(view);
//        bottomSheetDialog.show();
//    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


//        imageSlider=view.findViewById(R.id.imgslider);



        tvemail=view.findViewById(R.id.tvuser);
        rcylCat=view.findViewById(R.id.rcylCat);
        rcylSub=view.findViewById(R.id.rcylSub);
        rcylProd=view.findViewById(R.id.rcylProd);
        viewPager = view.findViewById(R.id.viewPager);
        seall = view.findViewById(R.id.seall);
        tvseecat = view.findViewById(R.id.tvSeeCat);
        imgcart = view.findViewById(R.id.imgcart);
        etsearch = view.findViewById(R.id.etsearch);


        seall.setOnClickListener(v->{
            Intent intent=new Intent(getActivity(),ServiceActivity.class);
            intent.putExtra("service",services);
            getContext().startActivity(intent);
        });
        imgcart.setOnClickListener(v->{
            Intent intent=new Intent(getActivity(), BookActivity.class);
            intent.putExtra("coupon",coupon);
            getContext().startActivity(intent);
        });

        SearchAdapter adapter = new SearchAdapter(getActivity(),services);
        etsearch.setThreshold(1);
        etsearch.setAdapter(adapter);



//        tvseecat.setOnClickListener(v->{
//            Intent intent=new Intent(getActivity(),SeeCategoryActivity.class);
//            intent.putExtra("category",category);
//            getContext().startActivity(intent);
//        });
        tvseecat.setOnClickListener(v->{
            Intent intent=new Intent(getActivity(), SeeCategoryActivity.class);
            intent.putExtra("subcate", Sub_data);
            intent.putExtra("ser", services);
            getContext().startActivity(intent);
        });


        sp = requireActivity().getSharedPreferences(ConstantData.SP_NAME, MODE_PRIVATE);
        String user = sp.getString(ConstantData.KEY_SP_USERNAME,"");
        tvemail.setText("Hello, " + user);

        getData();
    }



    public void getData(){


        getBanner();

//        ArrayList<SlideModel> imageList = new ArrayList<>();
//
//        for(int i=0;i< banner.size();i++){
//            imageList.add(new SlideModel(ConstantData.SERVER_ADDRESS+banner.get(i).getBanner_pic(),null, ScaleTypes.FIT));
//        }
//
//        imageSlider.setImageList(imageList);

        rcylCat.setLayoutManager(new LinearLayoutManager(
                getActivity(),RecyclerView.HORIZONTAL,false
        ));
        rcylCat.setAdapter(new CategoryAdapter(category,services,Sub_data));


        rcylProd.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.HORIZONTAL,false));
        rcylProd.setAdapter(new ServicesAdapter(services));

        SubCategoryAdapter adp=new SubCategoryAdapter(getActivity(),Sub_data,services);
//        rcylSub.setLayoutManager(new GridLayoutManager(getActivity(),2));

        rcylSub.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.HORIZONTAL));
        rcylSub.setAdapter(adp);

    }

    public void getBanner() {
        imageUrls = new ArrayList<>();

        for (int i = 0; i < banner.size(); i++) {
            imageUrls.add(ConstantData.SERVER_ADDRESS+banner.get(i).banner_pic);
        }

        // Create a loop effect by adding first and last images at opposite ends
        if (!imageUrls.isEmpty()) {
            imageUrls.add(0, imageUrls.get(imageUrls.size() - 1)); // Add last image at start
            imageUrls.add(imageUrls.get(1)); // Add first image at end
        }


        BannerAdapter adapter = new BannerAdapter(getActivity(), imageUrls);
        viewPager.setAdapter(adapter);

        viewPager.setOffscreenPageLimit(3);

        // Apply composite transformer
        CompositePageTransformer transformer = new CompositePageTransformer();
        transformer.addTransformer(new MarginPageTransformer(10)); // Space between images
        transformer.addTransformer(new CustomPageTransformer());

        viewPager.setPageTransformer(transformer);

        // Start at position 1 (because 0 is fake last image)
        viewPager.setCurrentItem(1, false);

        // Ensure auto-scrolling starts immediately
        startAutoSliding();
    }



    void startAutoSliding() {
        autoSlideRunnable = new Runnable() {
            @Override
            public void run() {
                if (viewPager != null) {
                    if (currentPage >= imageUrls.size() - 1) {
                        viewPager.setCurrentItem(1, false); // Jump to the first real image
                    } else {
                        viewPager.setCurrentItem(currentPage + 1, true);
                    }
                    handler.postDelayed(this, 3000);
                }
            }
        };

        // *Start auto-scrolling immediately*
        handler.postDelayed(autoSlideRunnable, 3000);

        // Detect manual swiping and restart auto-scroll
        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                currentPage = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                if (state == ViewPager2.SCROLL_STATE_IDLE) {
                    if (currentPage == 0) {
                        viewPager.setCurrentItem(imageUrls.size() - 2, false);
                    } else if (currentPage == imageUrls.size() - 1) {
                        viewPager.setCurrentItem(1, false);
                    }
                }

                // *Restart auto-scroll after manual swipe*
                handler.removeCallbacks(autoSlideRunnable);
                handler.postDelayed(autoSlideRunnable, 3000);
            }
        });
    }
}