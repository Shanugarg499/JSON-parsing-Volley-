package com.example.jsonparsing_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
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

public class MainActivity extends AppCompatActivity {

    private TextView mTextViewResult;
    private RequestQueue mQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextViewResult = findViewById(R.id.data_text);
        Button button_parse = findViewById(R.id.button);
        mQueue = Volley.newRequestQueue(this);

        mTextViewResult.setText("");

        button_parse.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                jsonParse();
            }
        });

    }

    private void jsonParse() {

        String url = "http://www.mocky.io/v2/5e419e082f0000cb5458368e";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try{
                        JSONArray jsonArray = response.getJSONArray("products");

                        for(int i = 0 ; i<jsonArray.length(); i++){
                            JSONObject product = jsonArray.getJSONObject(i);
                            int id , priceid, gst, state, sellerid, livesellerid, weight, mrp, isFreedelivery, stock, minQuantity;
                            String name, price;
                            id = product.getInt("id");
                            priceid = product.getInt("priceId");
                            gst = product.getInt("gst");
                            state = product.getInt("state");
                            sellerid = product.getInt("sellerId");
                            livesellerid = product.getInt("liveSellerId");
                            weight = product.getInt("weight");
                            price = product.getString("price");
                            mrp = product.getInt("mrp");
                            isFreedelivery = product.getInt("isFreeDelivery");
                            stock = product.getInt("stock");
                            minQuantity = product.getInt("minQuantity");
                            name = product.getString("name");


                            mTextViewResult.append(String.valueOf(id) + ", " + String.valueOf(priceid) + ", " + String.valueOf(gst)+", "+ name +", " + String.valueOf(state) + "\n" + String.valueOf(sellerid) + ", " + String.valueOf(livesellerid)+ ", "+ String.valueOf(weight)+ ", "+ price + ", "+ String.valueOf(mrp) +", " + String.valueOf(isFreedelivery)+ ", " + String.valueOf(stock) + ","+String.valueOf(minQuantity) + " \n\n");
                        }

                }catch(JSONException e){
                        e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        mQueue.add(request);

    }
}
