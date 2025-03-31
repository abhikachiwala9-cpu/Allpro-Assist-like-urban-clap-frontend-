package com.example.urabanapp.activity;

import static android.app.PendingIntent.getActivity;

import static java.security.AccessController.getContext;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.urabanapp.R;
import com.example.urabanapp.adapter.CartAdapter;
import com.example.urabanapp.api.OrderApi;
import com.example.urabanapp.fragment.CoupounFragment;
import com.example.urabanapp.model.BookingModel;
import com.example.urabanapp.model.CoupenModel;
import com.example.urabanapp.utilites.ConstantData;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class BookActivity extends AppCompatActivity implements PaymentResultListener {



    RecyclerView rcylCart;
    EditText etCoupon;
    Button btndate, btntime,apply,btnexplore;
    TextView  tvgst, tvCoupon,tvamount,tvtotal, address,tvadd,tvall;
    TextInputEditText t1,t2,t3,t4,t5,t6;
    RadioButton rbtnCOD, rbtnOnline;
    RadioGroup rdbPayment;


    boolean isApply=false;
    Button conform,btnaddress;
    String Date="select date",time="select time";
    String addressstr="";

    double dis=0;
    private BookingModel model;
    String c_code="none";
    String c_o="cash";
    double c_discount = 0,max_amt=0;
    private String uid="0";

    LinearLayout non,empty;

    @SuppressLint({"MissingInflatedId", "WrongViewCast"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_cart);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        non = findViewById(R.id.non);
        empty = findViewById(R.id.empty);
        rcylCart = findViewById(R.id.rcylCart);
        btndate=findViewById(R.id.btndate);
        btntime=findViewById(R.id.btntime);
        apply=findViewById(R.id.apply);
        etCoupon=findViewById(R.id.etcoupon);
        address=findViewById(R.id.address);
        tvadd=findViewById(R.id.tvadd);
        btnexplore=findViewById(R.id.btnexplore);
        tvall=findViewById(R.id.tvall);

        btnexplore.setOnClickListener(v->{
            Intent intent = new Intent(this,NavigationActivity.class);
            startActivity(intent);
        });

        tvall.setOnClickListener(v->{
            ArrayList<CoupenModel> coupenModels = (ArrayList<CoupenModel>) getIntent().getSerializableExtra("coupon");
            Intent intent = new Intent(this,CouponActivity.class);
            intent.putExtra("coupon",coupenModels);
            startActivity(intent);
        });




//        tvall=findViewById(R.id.tvall);


        tvamount = findViewById(R.id.tvamount);
        tvCoupon=findViewById(R.id.tvCoupon);
        tvtotal=findViewById(R.id.tvtotal);
        tvgst=findViewById(R.id.tvgst);
        rdbPayment=findViewById(R.id.rdbPayment);

//        rbtnCOD=findViewById(R.id.rbtnCOD);
//        rbtnOnline=findViewById(R.id.rbtnOnline);
        conform=findViewById(R.id.conform);

//        tvname.setText(model.getPname());
        //tvamount.setText(model.getAmount());
//        discount2.setText(model.getC_discount());
//        totalamount2.setText(model.getTot_amount());
//        address.setText(model.getAddress());


        etCoupon.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                removeCoupon();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        address.setOnClickListener(v->{
            final BottomSheetDialog dialog=new BottomSheetDialog(BookActivity.this);
            View layout= LayoutInflater.from(BookActivity.this).inflate(R.layout.adress,null);
            dialog.setContentView(layout);
            t1=layout.findViewById(R.id.t1);
            t2=layout.findViewById(R.id.t2);
            t3=layout.findViewById(R.id.t3);
            t4=layout.findViewById(R.id.t4);
            t5=layout.findViewById(R.id.t5);
            t6=layout.findViewById(R.id.t6);
            Button btnSaveAddress=layout.findViewById(R.id.btnSaveAddress);
            btnSaveAddress.setOnClickListener(v1 -> {
                if (validateInputs()) {
                    addressstr=t1.getText().toString()+","
                            +t2.getText().toString()+","
                            +t3.getText().toString()+","
                            +t4.getText().toString()+","
                            +t5.getText().toString()+","
                            +t6.getText().toString()+",";


                    tvadd.setText(addressstr);
                    dialog.dismiss();
                }
//                addressstr=t1.getText().toString()+","
//                        +t2.getText().toString()+","
//                        +t3.getText().toString()+","
//                        +t4.getText().toString()+","
//                        +t5.getText().toString()+","
//                        +t6.getText().toString()+",";
//
//
//                tvadd.setText(addressstr);
//                dialog.dismiss();


            });
            dialog.show();
        });



        SharedPreferences sp = getSharedPreferences(ConstantData.SP_NAME, MODE_PRIVATE);
        String uid = sp.getString(ConstantData.KEY_SP_USER_ID, "0");


        if (uid.equals("0")) {
            Intent intent = new Intent(BookActivity.this, LoginActivity.class);
            startActivity(intent);
//            Toast.makeText(this, "Login to contuniue...", Toast.LENGTH_SHORT).show();
        } else {

            new OrderApi().getOrder(this, uid, "0");

        }

        apply.setOnClickListener(v->{
            String code = etCoupon.getText().toString().trim();
            if(code.isEmpty()){
                Toast.makeText(this, "enter coupon code", Toast.LENGTH_SHORT).show();
            }else{
                if(isApply){
                    Toast.makeText(this, "coupon already applied", Toast.LENGTH_SHORT).show();
                }else{
                    new OrderApi().applyCoupon(this,code);
                }

            }
        });

        conform.setOnClickListener(v->{
            if(rdbPayment.getCheckedRadioButtonId()==R.id.rbtnCOD){
                c_o="cash";

            }else{
                c_o="online";
            }
            if(Date.equals("Select Date")) {

                Toast.makeText(this, "Please Select Date", Toast.LENGTH_SHORT).show();

            } else if(time.equals("Select Time")) {

                Toast.makeText(this, "Please Select Time", Toast.LENGTH_SHORT).show();

            } else {

                if (addressstr.trim().length() == 0) {
                    Toast.makeText(this, "Please enter Adreess", Toast.LENGTH_SHORT).show();
                } else {
                    if (c_o.equals("cash")) {
                        new OrderApi().confirm_order(BookActivity.this, uid, addressstr, c_code, "CASH", c_discount + "",Date,time);
                    } else {
                        openRazorPay();
                    }
                }
            }

        });

        btndate.setText(Date);
        btntime.setText(time);

        btndate.setOnClickListener(v -> {
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.DAY_OF_YEAR, 1); // Add 1 day to get tomorrow

            // Set minimum and maximum dates
            long minDate = calendar.getTimeInMillis(); // Tomorrow
            calendar.add(Calendar.DAY_OF_YEAR, 3); // Add 3 days to tomorrow
            long maxDate = calendar.getTimeInMillis(); // 3 days from tomorrow

            // Create the DatePickerDialog
            DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                    (DatePicker view, int year, int month, int dayOfMonth) -> {
                        // Store the selected date in the string variable
                        Date = dayOfMonth + "/" + (month + 1) + "/" + year;

                        btndate.setText(Date);
                    },
                    Calendar.getInstance().get(Calendar.YEAR),
                    Calendar.getInstance().get(Calendar.MONTH),
                    Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
            );

            // Apply the date range restrictions
            datePickerDialog.getDatePicker().setMinDate(minDate);
            datePickerDialog.getDatePicker().setMaxDate(maxDate);

            datePickerDialog.show();
        });

        btntime.setOnClickListener(v -> {
            TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                    (TimePicker view, int hourOfDay, int minute) -> {
                        // Check if the selected time is between 10:00 AM and 6:00 PM
                        if (hourOfDay < 10 || hourOfDay > 18 || (hourOfDay == 18 && minute > 0)) {
                            // Show a toast for invalid time
                            Toast.makeText(BookActivity.this, "Invalid time selected. Please select a time between 10:00 AM and 6:00 PM.", Toast.LENGTH_SHORT).show();
                        } else {
                            // Format and store the selected time
                            time = String.format(Locale.getDefault(), "%02d:%02d %s",
                                    (hourOfDay > 12 ? hourOfDay - 12 : hourOfDay == 0 ? 12 : hourOfDay),
                                    minute,
                                    (hourOfDay >= 12 ? "PM" : "AM"));

                            btntime.setText(time);
                            // Optional: Display the selected time
                            //Toast.makeText(MainActivity.this, "Selected Time: " + selectedTime, Toast.LENGTH_SHORT).show();
                        }
                    },
                    10, // Default hour (10:00 AM)
                    0,  // Default minute
                    false // Use 12-hour format
            );

            timePickerDialog.show();
        });
    }



    double total=0,amt=0,gst=0;
    public void setBooking(ArrayList<BookingModel> booking){


        if(booking.size()==0)
        {
            empty.setVisibility(View.VISIBLE);
            non.setVisibility(View.GONE);

        }else{
            empty.setVisibility(View.GONE);
            non.setVisibility(View.VISIBLE);
        }
        total=0;
        amt=0;
        for (int i=0;i<booking.size();i++){
            amt+=Double.parseDouble(booking.get(i).getAmount()+"");

        }


        gst=amt * 0.05;

        total=gst + amt;

//        tvgst.setText((int) gst);
        tvamount.setText(amt+"");
        tvCoupon.setText("0");
        tvtotal.setText(Math.floor(total)+"");


        CartAdapter bookingAdapter=new CartAdapter(this,booking);
        rcylCart.setLayoutManager(new LinearLayoutManager(this));
        rcylCart.setAdapter(bookingAdapter);


    }


    public void setCoupon(CoupenModel model){
        c_discount = Double.parseDouble(model.getC_discount());
        max_amt = Double.parseDouble(model.getC_maxamt());
        tvCoupon.setText(c_discount+"");
        dis=c_discount;
        total = total-c_discount;
        tvtotal.setText(total+"");
        isApply=true;
    }

    public void removeCoupon() {
        if (isApply) {
            total = total + dis;
            dis=0;
            tvtotal.setText(Math.round(total)+"");
            tvCoupon.setText("0");
            isApply = false;
        }
    }

    public void openRazorPay(){
        Checkout checkout = new Checkout();

        // set your id as below
        checkout.setKeyID("rzp_test_Qi8mCCOIlysMtE");

        // set image
        checkout.setImage(R.mipmap.ic_launcher);

        // initialize json object
        JSONObject object = new JSONObject();
        try {
            // to put name
            object.put("name", "SOLEGLIDE");

            // put description
            object.put("description", "Test payment");

            // to set theme color
            object.put("theme.color", "#7954CA");

            // put the currency
            object.put("currency", "INR");

            // put amount
            object.put("amount", Math.round(total*100));

            // put mobile number
            object.put("prefill.contact", "9664597411");

            // put email
            object.put("prefill.email", "triveditisha18@gmail.com");

            // open razorpay to checkout activity
            checkout.open(BookActivity.this, object);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onPaymentSuccess(String s){
        SharedPreferences sp = getSharedPreferences(ConstantData.SP_NAME,MODE_PRIVATE);
        new OrderApi().confirm_order(BookActivity.this,
                sp.getString(ConstantData.KEY_SP_USER_ID,"0"),
                addressstr,c_code,"ONLINE",c_discount+"",Date,time);
    }

    @Override
    public void onPaymentError(int i, String s) {
        Toast.makeText(this, "Payment error", Toast.LENGTH_SHORT).show();
    }

    private boolean validateInputs() {
        String houseNo = t1.getText().toString().trim();
        String address1 = t2.getText().toString().trim();
        String city = t4.getText().toString().trim();
        String state = t5.getText().toString().trim();
        String postalCode = t6.getText().toString().trim();

        if (TextUtils.isEmpty(houseNo)) {
            t1.setError("House No is required");
            t1.requestFocus();
            return false;
        }
        if (TextUtils.isEmpty(address1)) {
            t2.setError("Address Line 1 is required");
            t2.requestFocus();
            return false;
        }
        if (TextUtils.isEmpty(city)) {
            t4.setError("City is required");
            t4.requestFocus();
            return false;
        }
        if (TextUtils.isEmpty(state)) {
            t5.setError("State is required");
            t5.requestFocus();
            return false;
        }
        if (TextUtils.isEmpty(postalCode)) {
            t6.setError("Postal Code is required");
            t6.requestFocus();
            return false;
        }
        if (!postalCode.matches("\\d{6}")) { // Validate 6-digit postal code
            t6.setError("Invalid Postal Code (must be 6 digits)");
            t6.requestFocus();
            return false;
        }

        return true; // All inputs are valid
    }

}