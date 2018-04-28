package com.example.dell6440.driver_side;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Accept extends AppCompatActivity {
    Button back,logout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accept);

        back= (Button) findViewById(R.id.button2);
        logout= (Button) findViewById(R.id.logout);


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(Accept.this, Dashboard.class);
                startActivity(i);

            }

        });


        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(Accept.this, Inbox.class);
                startActivity(i);

            }

        });


    }
}
