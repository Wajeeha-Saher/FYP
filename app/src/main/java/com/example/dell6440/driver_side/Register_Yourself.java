package com.example.dell6440.driver_side;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Register_Yourself extends AppCompatActivity {
    Button register;
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
                signup();
            }
        });

    }
    private void signup(){
        String name = Name.getText().toString();
        String Location = Car_no.getText().toString();
        String Contact = ContactNo.getText().toString();
        String Email = email.getText().toString();
        String Password = password.getText().toString();


    }
}
