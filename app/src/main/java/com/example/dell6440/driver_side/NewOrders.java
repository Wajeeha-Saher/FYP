package com.example.dell6440.driver_side;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class NewOrders extends ListActivity {
    OrdersAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        LoadData();
    }

    private void RefreshData(OrderDTO[] orders) {

        mAdapter = new OrdersAdapter(this, orders);
        setListAdapter(mAdapter);

    }

    private void LoadData() {
        String a =Inbox.DriverId;
        JsonObjectRequest jsonObject = new JsonObjectRequest(Request.Method.GET, Constants.ApiEndPoints.OrderNew, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                   Toast.makeText(NewOrders.this, " abc" , Toast.LENGTH_SHORT).show();
                try {
                    JSONArray ordersResponse = response.getJSONArray("response");

                    int ordersCount = ordersResponse.length();
                    OrderDTO[] orders = new OrderDTO[ordersCount];

                    for (int i = 0; i < ordersCount; i++) {
                        JSONObject item = (JSONObject) ordersResponse.get(i);
                        JSONObject customer = (JSONObject) item.getJSONObject("customer");
                        JSONObject order = (JSONObject) item.getJSONObject("order");

                        OrderDTO order1 = new OrderDTO();
                        order1.PickupLocation = order.getString("PickupLocation");
                        order1.DropOffLocation = order.getString("DropoffLocation");
                        order1.LoadType = order.getString("LoadType");
                        order1.Weight = order.getString("Weight");

                        orders[i] = order1;
                    }
                    RefreshData(orders);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }


        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                          Toast.makeText(NewOrders.this, " " + error, Toast.LENGTH_SHORT).show();
                    }
                }) {
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
        rq.add(jsonObject);


    }
}
