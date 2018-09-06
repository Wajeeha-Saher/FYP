package com.example.dell6440.driver_side;

import android.content.Intent;
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

public class review extends AppCompatActivity {
 Button  logout,submit;
    EditText message;
        UserSessionManager session;
    public static String Message;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);


        logout = (Button) findViewById(R.id.logout);
        submit = (Button) findViewById(R.id.submit);

        message = (EditText) findViewById(R.id.message);
        //   session = new UserSessionManager(getApplicationContext());
        //  if(session.checkLogin())
        //   finish();
        Message = message.getText().toString();

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(review.this, Inbox.class);
                // i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                // Add new Flag to start new Activity
                //i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);

//                session.logoutUser();
                //              finish();





            }
        });



        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (message.getText().toString().trim().length() == 0) {
                    Toast.makeText(getApplicationContext(), "Please enter feedback", Toast.LENGTH_SHORT).show();
                    return;
                }

                REVIEW();
            }
        });

    }

    private void REVIEW(){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constants.ApiEndPoints.Review, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Toast.makeText(review.this, " " +response, Toast.LENGTH_SHORT).show();
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(review.this, " "+error, Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("DriverId", Inbox.DriverId);
                params.put("Feedbacks", Message);
                return params;
            }

            @Override
            public Map<String, String> getHeaders() {
                HashMap<String, String> headers = new HashMap<>();
                // headers.put("Content-Type", "application/json");
                headers.put("Authorization", "bearer " +Inbox.token);
                //    headers.put("Authorization", "bearer FqbzQGy3M01-dNXjDUbBSfp8vB70xBI1E7BG1pKnDVOvKSoYizNsmgXkS8Qh6vxWyGfxXQeb8wcVkyU2KaLj5dsBZX3yHgWpikSrqoYBHc-kM263xWxYNaYnm9Rcmc72io4bAjw9sF67My7gFO2ETSnHCn2BYL9nWlfM6g_LfnHHnQ_WHVW1WQaOFimAI0j0tN3sZcCbBxXKiJ-Q3ShkhmtXw1Bta9E2qxzwKbDyrW6vNsc65jQC3BA_FBuig7an");
                return headers;
            }

        };
        RequestQueue rq = Volley.newRequestQueue(this);
        rq.add(stringRequest);




    }

    }

