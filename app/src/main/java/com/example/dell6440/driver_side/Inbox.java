package com.example.dell6440.driver_side;

import android.Manifest;
import android.app.DownloadManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Handler;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class Inbox extends AppCompatActivity {
    private static final int REQUEST_LOCATION = 1;
    Button login, register;
    EditText email, password;
    LocationManager locationManager;
    String lattitude, longitude;
    String abc = "asssf";
    private Handler handler = new Handler();
    String URL_Register = "http://112c0e03.ngrok.io/api/account/register";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inbox);

        login = (Button) findViewById(R.id.curr_order);
        register = (Button) findViewById(R.id.button7);
        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                login();
                mToastRunnable.run();

            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(Inbox.this, Register_Yourself.class);
                startActivity(i);
            }
        });

    }


    private void login() {

        //   String Email = email.getText().toString();
        // String Password = password.getText().toString();

        Intent i = new Intent(Inbox.this, Dashboard.class);
        startActivity(i);
    }

    private Runnable mToastRunnable = new Runnable() {

        @Override
        public void run() {
            //Toast.makeText(Inbox.this, "delayed toast" , Toast.LENGTH_SHORT).show();
            //    textView.append(" "+abc);
            locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                buildAlertMessageNoGps();

            } else if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                getLocation();

            }

            handler.postDelayed(mToastRunnable, 5000);
        }

    };

    /*
    public void yourfunction(){










       /* ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);

        textView = (TextView)findViewById(R.id.text);


        button.setOnClickListener(new View.OnClickListener() {




            @Override
            public void onClick(View view) {
                locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
                if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                    buildAlertMessageNoGps();

                } else if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                    getLocation();

                }
    }


    });
        }
    */
    private void getLocation() {
        if (ActivityCompat.checkSelfPermission(Inbox.this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission
                (Inbox.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(Inbox.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);

        } else {
            Location location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);

            Location location1 = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

            Location location2 = locationManager.getLastKnownLocation(LocationManager.PASSIVE_PROVIDER);

            if (location != null) {
                double latti = location.getLatitude();
                double longi = location.getLongitude();
                lattitude = String.valueOf(latti);
                longitude = String.valueOf(longi);

                //textView.append("Your current location is" + "\n" + "Lattitude = " + lattitude
                  //      + "\n" + "Longitude = " + longitude);
              //  postTime();
                Toast.makeText(Inbox.this, " "+ lattitude, Toast.LENGTH_SHORT).show();
            } else if (location1 != null) {
                double latti = location1.getLatitude();
                double longi = location1.getLongitude();
                lattitude = String.valueOf(latti);
                longitude = String.valueOf(longi);

                //textView.append("Your current location is" + "\n" + "Lattitude = " + lattitude
                  //      + "\n" + "Longitude = " + longitude);
                Toast.makeText(Inbox.this, " "+ lattitude, Toast.LENGTH_SHORT).show();
               // postTime();
            } else if (location2 != null) {
                double latti = location2.getLatitude();
                double longi = location2.getLongitude();
                lattitude = String.valueOf(latti);
                longitude = String.valueOf(longi);

             //   textView.append("Your current location is" + "\n" + "Lattitude = " + lattitude
               //         + "\n" + "Longitude = " + longitude);
                postTime();
                Toast.makeText(Inbox.this, " "+ lattitude, Toast.LENGTH_SHORT).show();
            } else {

                Toast.makeText(this, "Unble to Trace your location", Toast.LENGTH_SHORT).show();

            }
        }
    }

    protected void buildAlertMessageNoGps() {

        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Please Turn ON your GPS Connection")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog, final int id) {
                        startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog, final int id) {
                        dialog.cancel();
                    }
                });
        final AlertDialog alert = builder.create();
        alert.show();
    }


    private void postTime(){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_Register, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Toast.makeText(Inbox.this, " "+ response, Toast.LENGTH_SHORT).show();
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Inbox.this, " "+error, Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
              //  String Email = email.getText().toString();
                //String Password = password.getText().toString();
                //String FirstName = lattitude.getText().toString();
                //String LastName = .getText().toString();
                params.put("Email", lattitude);
                params.put("Password", longitude);
             //   params.put("FirstName", FirstName);
               // params.put("LastName", LastName);
                return params;
            }
        };
        RequestQueue rq = Volley.newRequestQueue(this);
        rq.add(stringRequest);

}}