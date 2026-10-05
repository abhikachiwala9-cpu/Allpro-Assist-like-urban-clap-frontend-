package com.example.urabanapp.utilites;

public class ConstantData {

    //API
    public static final String SERVER_ADDRESS = "http://192.168.1.4:8000";
    public static final String REGISTER_METHOD = SERVER_ADDRESS+"/api_register";
    public static final String LOGIN_METHOD =SERVER_ADDRESS+ "/api_login";
    public static final String DATA_METHOD =SERVER_ADDRESS+ "/api_data";
    public static final String ADD_ORDER_METHOD =SERVER_ADDRESS+ "/api_addbooking";
    public static final String GET_ORDER_METHOD =SERVER_ADDRESS+ "/api_getbooking";
    public static final String REMOVE_METHOD =SERVER_ADDRESS+ "/api_removebooking";
    public static final String APPLY_COUPON_METHOD =SERVER_ADDRESS+  "/api_applycoupon";
    public static final String confirmOrder_METHOD =SERVER_ADDRESS+ "/api_confirmorder";
    public static final String GET_ORDER_HIS_METHOD =SERVER_ADDRESS+ "/api_getOrderhistory";
    public static final String EDIT_PROFILE_METHOD =SERVER_ADDRESS+ "/api_editprofile";
    public static final String FIND_USER_METHOD =SERVER_ADDRESS+ "/api_finduser";
    public static final String FORGOT_PASS_METHOD =SERVER_ADDRESS+ "/api_changepassword";


    //SHAREDPREFRENCES
    public static final String SP_NAME="sp";
    public static final String KEY_SP_EMAIL="email";
    public static final String KEY_SP_USER_ID="uid";

    public static final String KEY_SP_MOB_NO="mobileno";
    public static final String KEY_SP_USERNAME="username";
    public static final String KEY_SP_ISLOGIN="is_login";




    //OTP
    public static final String CUSTOMER_ID="C-CB67A8E4BC6B4EC";
    public static final String AUTH_TOKEN="eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJDLUNCNjdBOEU0QkM2QjRFQyIsImlhdCI6MTczODU3Mjk0OSwiZXhwIjoxODk2MjUyOTQ5fQ.P4ZOatonig8IVNRLDBHqtWmqqZqQdz2nB7ZhaGHvzjkjewmLWrNuBAmGthhG8fjnboodYymo50zhFmjbaJdDNA";
}
