package com.example.flatnow;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class listpage extends AppCompatActivity {

    private static final String URL_FLAT = "http://192.168.43.159:80/NEWList.php";
    List<Flat> flatList;
    RecyclerView recyclerView;
    flatadapter  adapter;
    




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listpage);
        flatList = new ArrayList<>();
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));



        loadflats();
    }


        private void loadflats () {

            StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_FLAT,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                            GsonBuilder gsonBuilder= new GsonBuilder();
                            Gson gson = gsonBuilder.create();
                           Flat []data= gson.fromJson(response,Flat[].class);
                            recyclerView.setAdapter(new flatadapter(getApplicationContext(),data));


                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(listpage.this, error.getMessage(), Toast.LENGTH_SHORT).show();

                }

            });

            Volley.newRequestQueue(this).add(stringRequest);




        }



//   private  void requestImage(){
//       RequestQueue requestQueue = Volley.newRequestQueue(this);
//       ImageRequest imageRequest =new ImageRequest("", new Response.Listener<Bitmap>() {
//           @Override
//           public void onResponse(Bitmap response) {
//               imageview.setImageBitmap(response);
//               
//
//           }
//       }, 0, 0, ImageView.ScaleType.CENTER_CROP, null, new Response.ErrorListener() {
//           @Override
//           public void onErrorResponse(VolleyError error) {
//               
//           }
//       });
//   }






}

