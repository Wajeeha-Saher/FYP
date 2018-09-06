package com.example.dell6440.driver_side;

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

public class Register_Yourself extends AppCompatActivity {
    Button register;
    String URL_Register="https://e366ab61.ngrok.io//api/account/register";
    EditText Name, Car_no, ContactNo,email,password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register__yourself);

        register = (Button) findViewById(R.id.register);
        Name = (EditText) findViewById(R.id.fname);
        Car_no= (EditText) findViewById(R.id.car_no);
        ContactNo = (EditText) findViewById(R.id.contact_no);
        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (email.getText().toString().trim().length() == 0) {
                    Toast.makeText(getApplicationContext(), "Please enter email", Toast.LENGTH_SHORT).show();
                    return;
                } else if (password.getText().toString().trim().length() == 0) {
                    Toast.makeText(getApplicationContext(), "Please enter password", Toast.LENGTH_SHORT).show();
                    return;
                }
                else if (Name.getText().toString().trim().length() == 0) {
                    Toast.makeText(getApplicationContext(), "Please enter password", Toast.LENGTH_SHORT).show();
                    return;
                }
                else if (Car_no.getText().toString().trim().length() == 0) {
                    Toast.makeText(getApplicationContext(), "Please enter password", Toast.LENGTH_SHORT).show();
                    return;
                }
                else if (ContactNo.getText().toString().trim().length() == 0) {
                    Toast.makeText(getApplicationContext(), "Please enter password", Toast.LENGTH_SHORT).show();
                    return;
                }
                else if (password.getText().toString().trim().length() == 0) {
                    Toast.makeText(getApplicationContext(), "Please enter password", Toast.LENGTH_SHORT).show();
                    return;
                }
                signup();
            }
        });

    }
    private void signup(){


        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_Register, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Toast.makeText(Register_Yourself.this, " " +response, Toast.LENGTH_SHORT).show();
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Register_Yourself.this, " "+error, Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                String name = Name.getText().toString();
                String Vehicle = Car_no.getText().toString();
                String Contact = ContactNo.getText().toString();
                String Email = email.getText().toString();
                String Password = password.getText().toString();

                params.put("Email", Email);
                params.put("Password", Password);
                params.put("FirstName", name);
                params.put("LastName", "two");
                params.put("RoleId", "3");
                params.put("Contact", Contact);
                params.put("Vehicle", Vehicle);
                return params;
            }


        };
        RequestQueue rq = Volley.newRequestQueue(this);
        rq.add(stringRequest);




    }
}
