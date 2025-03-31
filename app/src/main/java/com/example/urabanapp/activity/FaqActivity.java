package com.example.urabanapp.activity;

import android.os.Bundle;
import android.widget.ExpandableListView;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.urabanapp.R;
import com.example.urabanapp.adapter.FAQExpandableListAdapter;
import com.example.urabanapp.model.FAQModel;

import java.util.ArrayList;
import java.util.List;

public class FaqActivity extends AppCompatActivity {

    ExpandableListView expandableListView;
    FAQExpandableListAdapter adapter;
    List<FAQModel> faqs;

    ImageView arrow;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_faq);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        arrow=findViewById(R.id.arrow);

        arrow.setOnClickListener(v->{
            finish();
        });
        expandableListView = findViewById(R.id.expandableListView);
        faqs = new ArrayList<>();
        addFaqs();
        adapter = new FAQExpandableListAdapter(this, faqs);
        expandableListView.setAdapter(adapter);
    }

    public void addFaqs() {
        //add faq in this this function like this
        //ok???????
        faqs.add(new FAQModel("What is Allpro Assist?", "Allpro Assist is an online platform that connects customers with professionals for various home and personal services, such as beauty, home cleaning, appliance repair, and more."));
        faqs.add(new FAQModel("How does Allpro Assit work?", "You can book services through the Allpro Assist mobile app. Select the required service, choose a professional, schedule an appointment, and make the payment online or in cash (if applicable)."));
        faqs.add(new FAQModel("Where is Allpro assit available?", "Allpro Assit operates in multiple cities across India and several international locations. Availability depends on the specific service and location."));
        faqs.add(new FAQModel("Are Allpro Assist professionals verified?", "Yes, all professionals undergo background verification, training, and skill assessment before being listed on the platform."));
        faqs.add(new FAQModel("What if I am not satisfied with the service?", "Allpro Assist has a satisfaction guarantee policy. You can raise a complaint through the app, and they will arrange for a resolution, which may include a free re-service or refund."));
        faqs.add(new FAQModel("How long does it take to get a refund?", "Refund processing usually takes 5-7 business days, depending on the payment method used.Would you like a customized FAQ section based on a specific service or business model?"));
    }
}