package com.example.volleyjson;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
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
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class MainActivity extends AppCompatActivity {

    private TextView txtJSON;
    private Button btnJSON;
    private RequestQueue mQueue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mQueue = Volley.newRequestQueue(this);
        txtJSON = findViewById(R.id.txtJson);
        btnJSON = findViewById(R.id.btnJson);



        btnJSON.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uraiJson();
            }
        });
    }

    private void uraiJson() {
        String url = "http://192.168.5.186/PHP-Dasar/p-aplikasi/jsondb/uas_2/showpage.php";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("data");

                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject mahasantri = jsonArray.getJSONObject(i);
                                String id = mahasantri.getString("id");
                                String nama = mahasantri.getString("nama");
                                String nama_hobby= mahasantri.getString("nama_hobby");
                                txtJSON.append("nama : "+id);
                                txtJSON.append("nama : "+nama);
                                txtJSON.append(" => hobby :"+nama_hobby+"\n\n");
                            }
                        }catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(MainActivity.this, "error", Toast.LENGTH_SHORT).show();
                        }
                    }
                },new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                Toast.makeText(MainActivity.this, ""+error, Toast.LENGTH_SHORT).show();
            }
        });
        mQueue.add(request);
    }
}