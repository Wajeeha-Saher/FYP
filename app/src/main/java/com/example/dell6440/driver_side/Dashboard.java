package com.example.dell6440.driver_side;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Dashboard extends AppCompatActivity {
    Button logout,review,order_place,map;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        logout = (Button) findViewById(R.id.logout);
        review = (Button) findViewById(R.id.review);
        order_place = (Button) findViewById(R.id.curr_order);
        map= (Button) findViewById(R.id.map);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(Dashboard.this, Inbox.class);
                startActivity(i);


            }
        });

        review.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(Dashboard.this, review.class);
                startActivity(i);

            }

        });

        order_place.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(Dashboard.this, Accept.class);
                startActivity(i);

            }

        });



        map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String geoURI= "geo:37.422,-122.084?z=23";
                Uri geo = Uri.parse(geoURI);
                Intent mapIntent= new Intent(Intent.ACTION_VIEW,geo);
                if(mapIntent.resolveActivity(getPackageManager())!=null){
                    startActivity(mapIntent);}


            }

        });


    }
}
