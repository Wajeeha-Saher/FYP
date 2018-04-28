package com.example.dell6440.driver_side;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class review extends AppCompatActivity {
 Button back, logout,submit;
    EditText name,message;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);

        back = (Button) findViewById(R.id.back);
        logout = (Button) findViewById(R.id.logout);
        submit = (Button) findViewById(R.id.submit);
        name = (EditText) findViewById(R.id.name);
        message = (EditText) findViewById(R.id.message);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(review.this, Inbox.class);
                startActivity(i);


            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(review.this, Dashboard.class);
                startActivity(i);


            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                REVIEW();
            }
        });

    }

    private void REVIEW(){

        String Name = name.getText().toString().trim();
        String Message = message.getText().toString().trim();
    }
}
