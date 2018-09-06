package com.example.dell6440.driver_side;


public class Constants {

    public static class ApiEndPoints {

        public static final String ApiUrl = "https://0a2581b2.ngrok.io/"; // This should end with /

        public static final String LoginURL = ApiUrl + "login";
        public static final String RegisterURL = ApiUrl + "account/register";
        public static final String GetDriverDetails = ApiUrl + "api/driver";
        public static final String TrackingUrl = ApiUrl + "api/driver/location/current";
        public static final String GetOrderId = ApiUrl + "api/Orders/getorderid/"+Inbox.DriverId;
        public static final String Review = ApiUrl + "api/Customer/feedback/all";

        public static final String OrderNew = ApiUrl + "api/Orders/new/";

    }
}
