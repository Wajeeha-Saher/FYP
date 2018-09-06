package com.example.dell6440.driver_side;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Accept extends Activity {
    Button back,logout;
    UserSessionManager session;
    String fetch_URL;
    TextView Display;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accept);

       // back= (Button) findViewById(R.id.back);
        //logout= (Button) findViewById(R.id.Logout);
     //   session = new UserSessionManager(getApplicationContext());
        Display = (TextView) findViewById(R.id.display);
       // if(session.checkLogin())
         //   finish();


        getorder();

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                session.logoutUser();
  //              finish();

            }});

                back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(Accept.this, Dashboard.class);
                startActivity(i);

            }

        });




    }
public void getorder(){
    JsonObjectRequest jsonObject = new JsonObjectRequest(Request.Method.GET, fetch_URL,null, new Response.Listener<JSONObject>() {
        @Override
        public void onResponse(JSONObject response) {
            //   Toast.makeText(Inbox.this, " " + response, Toast.LENGTH_SHORT).show();
            try {
                JSONArray jsonArray = response.getJSONArray(" ");

                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject employee = jsonArray.getJSONObject(i);

                    String pick = employee.getString("Picklocation");
                    String drop = employee.getString("DropLocation");


                    Display.append(pick + ", " +  ", " + drop + "\n\n");
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }





    },
            new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                    //  Toast.makeText(Inbox.this, " " + error, Toast.LENGTH_SHORT).show();
                }
            }) {
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
    rq.add(jsonObject);




}
}
