package com.example.urabanapp.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.urabanapp.R;
import com.example.urabanapp.api.OrderApi;
import com.example.urabanapp.model.BookingModel;
import com.example.urabanapp.model.ServicesModel;
import com.example.urabanapp.utilites.ConstantData;

import java.util.ArrayList;

public class ServiceDetailActivity extends AppCompatActivity {


    TextView tvSName,tvSName2,price,desc;
    ImageView arrow;
    Button btnbook;
    VideoView video;
    SharedPreferences sp;
    ImageSlider imgservice;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_service_detail);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        tvSName=findViewById(R.id.tvSName);
        tvSName2=findViewById(R.id.tvSName2);
        price=findViewById(R.id.price);
        arrow=findViewById(R.id.arrow);
        desc=findViewById(R.id.desc);
        btnbook=findViewById(R.id.btnbook);
        video=findViewById(R.id.video);
        imgservice=findViewById(R.id.imgservice);

        ArrayList<SlideModel>  imglist = new ArrayList<>();

        Intent intent=getIntent();
        ServicesModel model= (ServicesModel) intent.getSerializableExtra("service");
        sp = getSharedPreferences(ConstantData.SP_NAME,MODE_PRIVATE);
        String uid= sp.getString(ConstantData.KEY_SP_USER_ID,"");

        imglist.add(new SlideModel(ConstantData.SERVER_ADDRESS+model.getSr_pic1(),ScaleTypes.FIT));
        imglist.add(new SlideModel(ConstantData.SERVER_ADDRESS+model.getSr_pic2(),ScaleTypes.FIT));
        imglist.add(new SlideModel(ConstantData.SERVER_ADDRESS+model.getSr_pic3(),ScaleTypes.FIT));


        imgservice.setImageList(imglist);


        tvSName.setText(model.getSr_name());
        tvSName2.setText(model.getSr_name());
        desc.setText(model.getDescription());
        price.setText(model.getSr_price());

        Uri uri = Uri.parse(ConstantData.SERVER_ADDRESS+model.getSr_video());

        video.setVideoURI(uri);
//        Toast.makeText(this, "URL: " + uri, Toast.LENGTH_SHORT).show();
//        Log.e("VID URL", uri.toString());
        video.setOnCompletionListener(v -> {
            video.start();
        });
        video.start();

        // creating object of
        // media controller class
//        MediaController mediaController = new MediaController(this);

        // sets the anchor view
        // anchor view for the videoView
//        mediaController.setAnchorView(video);

        // sets the media player to the videoView
//        mediaController.setMediaPlayer(video);

        // sets the media controller to the videoView
//        video.setMediaController(mediaController);

        arrow.setOnClickListener(v->{
            finish();
        });

        btnbook.setOnClickListener(v->{

            Toast.makeText(this, "getting cart", Toast.LENGTH_SHORT).show();

            new OrderApi().addOrder(this,

                    new BookingModel(
                            uid,
                            model.getId(),
                            "",
                            "",
                            0,
                            0,
                            0,
                            "Time",
                            "Date",
                            0,
                            "CASH",
                            "No",
                            "Bytes",
                            "",
                            "",
                            "",
                            "",
                            ""
                    )
            );
        });
    }
}