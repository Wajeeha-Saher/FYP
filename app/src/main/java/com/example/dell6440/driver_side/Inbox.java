package com.example.dell6440.driver_side;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Inbox extends AppCompatActivity {
  //  private static final int REQUEST_LOCATION = 1;
    Button login, register;
    EditText email, password;
    UserSessionManager session;
    //LocationManager locationManager;
    //String lattitude, longitude;
    //String response1;
    public static String token;
    //int DriverId;

    public static String DriverId;
  //  private Handler handler = new Handler();
    public static String Email ;
    String Password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inbox);
//        session = new UserSessionManager(getApplicationContext());
        login = (Button) findViewById(R.id.curr_order);
        register = (Button) findViewById(R.id.button7);
        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);



        Email = email.getText().toString();
       // Password = password.getText().toString();


            login.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (email.getText().toString().trim().length() == 0) {
                        Toast.makeText(getApplicationContext(), "Please enter email", Toast.LENGTH_SHORT).show();
                        return;
                    } else if (password.getText().toString().trim().length() == 0) {
                        Toast.makeText(getApplicationContext(), "Please enter password", Toast.LENGTH_SHORT).show();
                        return;
                    }

                 //   login();
              //      locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
                //    if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                  //      buildAlertMessageNoGps();
                  //  }
                  //  mToastRunnable.run();

                    Intent i = new Intent(Inbox.this, Dashboard.class);
                    startActivity(i);
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


        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constants.ApiEndPoints.LoginURL
, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(Inbox.this, " " + response, Toast.LENGTH_SHORT).show();


                JSONObject jsonObject = null;
                try {
                    jsonObject = new JSONObject(response);
                    token = jsonObject.optString("access_token");
                    Toast.makeText(Inbox.this, " " +token, Toast.LENGTH_SHORT).show();

                } catch (JSONException e) {
                    e.printStackTrace();
                }


                //   session.createUserLoginSession("User Session ", Email);


               bearer();

//                Intent i = new Intent(Inbox.this, Dashboard.class);
                // i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                // Add new Flag to start new Activity
                //i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
             //   startActivity(i);
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Toast.makeText(Inbox.this, " " + error, Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();

               // String email_1 = Email;

               Email = email.getText().toString();
                Password = password.getText().toString();
               // String grant = "password";
                params.put("grant_type", "password");
                params.put("username", Email);
                params.put("password", Password);

                return params;

            }
        };


        RequestQueue rq = Volley.newRequestQueue(this);
        rq.add(stringRequest);

    }


    public void bearer(){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constants.ApiEndPoints.GetDriverDetails,  new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                JSONObject jsonObject = null;
                try {
                    jsonObject = new JSONObject(response);
                    DriverId = jsonObject.optString("UserId");
                    Toast.makeText(Inbox.this, " " +DriverId, Toast.LENGTH_SHORT).show();
                    session.createUserLoginSession("User Session ", DriverId);
                } catch (JSONException e) {
                    e.printStackTrace();
                }


                /*try {

                    JSONObject person = new JSONObject();
                    String DriverId = person.getString("UserId");
                    Toast.makeText(Inbox.this, " " + DriverId, Toast.LENGTH_SHORT).show();
                } catch (JSONException e) {
                    e.printStackTrace();
*/

                Intent i = new Intent(Inbox.this, Dashboard.class);
                 i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                // Add new Flag to start new Activity
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
                }




                //    }
             //   }
            },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Inbox.this, " " + error, Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Username", Email);
                return params;

            }
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String,String> params = new HashMap<String, String>();
                params.put("Authorization", "bearer " +token);
                return params;
            }
        };

        RequestQueue rq = Volley.newRequestQueue(this);
        rq.add(stringRequest);


    }

    }