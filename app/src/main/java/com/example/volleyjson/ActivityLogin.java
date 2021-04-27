package com.example.volleyjson;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class ActivityLogin extends AppCompatActivity {

    EditText username, password;
    com.ornach.nobobutton.NoboButton btn_action;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btn_action = findViewById(R.id.btn_action);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);

        btn_action.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
    }

    public void login() {
        StringRequest request = new StringRequest(Request.Method.POST, "http://192.168.5.186/PHP-Dasar/p-aplikasi/jsondb/uas_2/api_perusahaan.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if(response.contains("null")) {
                            Toast.makeText(ActivityLogin.this, "Silahkan Isi Data Terlebih Dahulu", Toast.LENGTH_SHORT).show();
                        }else if(response.contains("ok")){
                            Toast.makeText(ActivityLogin.this, "Data Cocok Silahkan Masuk", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        }else {
                            Toast.makeText(ActivityLogin.this, "Input Yang Anda Masukan Tidak Cocok Dengan Data Kami, Silahkan Coba Lagi", Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> params = new HashMap<>();
                params.put("username", username.getText().toString());
                params.put("password", password.getText().toString());
                return params;
            }
        };

        Volley.newRequestQueue(this).add(request);
    }


}