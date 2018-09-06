package com.example.dell6440.driver_side;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.os.Handler;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class Dashboard extends AppCompatActivity {
    Button logout, review, order_place, map, newOrders;
    private static final int REQUEST_LOCATION = 1;
    LocationManager locationManager;
    public static String lattitude, longitude;
    public static double latti;
    public static double longi;
    UserSessionManager session;

    //int DriverId=33;
	//int DriverId=33;

    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        logout = (Button) findViewById(R.id.logout);
        review = (Button) findViewById(R.id.review);
        map = (Button) findViewById(R.id.map);
        newOrders = (Button) findViewById(R.id.newOrders);
        //session = new UserSessionManager(getApplicationContext());
        //if(session.checkLogin())
        //  finish();

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Dashboard.this, Inbox.class);
                // i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                // Add new Flag to start new Activity
                //i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
                //   session.logoutUser();
                // finish();
            }
        });


        // Toast.makeText(this, ""+ Inbox.token, Toast.LENGTH_SHORT).show();
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            buildAlertMessageNoGps();
        }

        // mToastRunnable.run();


        review.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(Dashboard.this, review.class);
                startActivity(i);

            }

        });



        map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String geoURI = "geo:37.422,-122.084?z=23";
                Uri geo = Uri.parse(geoURI);
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, geo);
                if (mapIntent.resolveActivity(getPackageManager()) != null) {
                    startActivity(mapIntent);
                }


            }

        });

        newOrders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Dashboard.this, NewOrders.class);
                // i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                // Add new Flag to start new Activity
                //i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
                //   session.logoutUser();
                // finish();
            }
        });
    }

    private Runnable mToastRunnable = new Runnable() {

        @Override
        public void run() {
            //Toast.makeText(Inbox.this, "delayed toast" , Toast.LENGTH_SHORT).show();
            //    textView.append(" "+abc);
      /*      locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                buildAlertMessageNoGps();
*/
            // } else
            if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                getLocation();

            }

            handler.postDelayed(mToastRunnable, 5000);
        }

    };


    private void getLocation() {
        if (ActivityCompat.checkSelfPermission(Dashboard.this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission
                (Dashboard.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(Dashboard.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);

        } else {
            Location location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);

            Location location1 = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

            Location location2 = locationManager.getLastKnownLocation(LocationManager.PASSIVE_PROVIDER);

            if (location != null) {
                latti = location.getLatitude();
                longi = location.getLongitude();
                lattitude = String.valueOf(latti);
                longitude = String.valueOf(longi);

                //textView.append("Your current location is" + "\n" + "Lattitude = " + lattitude
                //      + "\n" + "Longitude = " + longitude);
                postTime();
                // getTime();
                Toast.makeText(Dashboard.this, " " + lattitude, Toast.LENGTH_SHORT).show();
            } else if (location1 != null) {
                double latti = location1.getLatitude();
                double longi = location1.getLongitude();
                lattitude = String.valueOf(latti);
                longitude = String.valueOf(longi);

                //textView.append("Your current location is" + "\n" + "Lattitude = " + lattitude
                //      + "\n" + "Longitude = " + longitude);
                postTime();
                // getTime();
                Toast.makeText(Dashboard.this, " " + longitude, Toast.LENGTH_SHORT).show();

            } else if (location2 != null) {
                double latti = location2.getLatitude();
                double longi = location2.getLongitude();
                lattitude = String.valueOf(latti);
                longitude = String.valueOf(longi);
                postTime();
                Toast.makeText(Dashboard.this, "third  " + lattitude, Toast.LENGTH_SHORT).show();

                //   textView.append("Your current location is" + "\n" + "Lattitude = " + lattitude
                //         + "\n" + "Longitude = " + longitude);
                //  getTime();
                //  Toast.makeText(Inbox.this, " "+ lattitude, Toast.LENGTH_SHORT).show();
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


    private void postTime() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constants.ApiEndPoints.TrackingUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Toast.makeText(Dashboard.this, " " + response, Toast.LENGTH_SHORT).show();
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                        Toast.makeText(Dashboard.this, " post \t " + error, Toast.LENGTH_SHORT).show();
                    }
                }) {


            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                //  String Email = email.getText().toString();
                //String Password = password.getText().toString();
                //String FirstName = lattitude.getText().toString();
                //String LastName = .getText().toString();
                lattitude = String.valueOf(latti);
                longitude = String.valueOf(longi);
                // String  id=String.valueOf(Inbox.DriverId);
                params.put("DriverId", Inbox.DriverId);
                // params.put("DriverId", "52");
                // params.put("DriverId", Inbox.DriverId);
                params.put("Latitude", lattitude);
                params.put("Longitude", longitude);
                //   params.put("FirstName", FirstName);
                // params.put("LastName", LastName);
                return params;
            }

            @Override
            public Map<String, String> getHeaders() {
                HashMap<String, String> headers = new HashMap<>();
                // headers.put("Content-Type", "application/json");
                headers.put("Authorization", "bearer " + Inbox.token);
                //    headers.put("Authorization", "bearer FqbzQGy3M01-dNXjDUbBSfp8vB70xBI1E7BG1pKnDVOvKSoYizNsmgXkS8Qh6vxWyGfxXQeb8wcVkyU2KaLj5dsBZX3yHgWpikSrqoYBHc-kM263xWxYNaYnm9Rcmc72io4bAjw9sF67My7gFO2ETSnHCn2BYL9nWlfM6g_LfnHHnQ_WHVW1WQaOFimAI0j0tN3sZcCbBxXKiJ-Q3ShkhmtXw1Bta9E2qxzwKbDyrW6vNsc65jQC3BA_FBuig7an");
                return headers;
            }
        };
        RequestQueue rq = Volley.newRequestQueue(this);
        rq.add(stringRequest);

    }


    private void getTime() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, Constants.ApiEndPoints.GetOrderId, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                String id = response;
                //  Toast.makeText(Inbox.this, " "+ response, Toast.LENGTH_SHORT).show();
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Dashboard.this, " " + error, Toast.LENGTH_SHORT).show();
                    }
                });


        // };
        RequestQueue rq = Volley.newRequestQueue(this);
        rq.add(stringRequest);
        postTime();
    }

}
