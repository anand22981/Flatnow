package com.example.flatnow;



import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;


import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class Registerpage extends AppCompatActivity implements View.OnClickListener  {
    EditText ownerName, contactNo, amount, address, description, city;

    Button submit , browse;
    RequestQueue requestQueue;
    ImageView image;
    String encodeImageString;
    Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registerpage);

        ownerName =  findViewById(R.id.OwnerName);
        contactNo =  findViewById(R.id.ContactNo);
        amount =  findViewById(R.id.Amount);
        address =  findViewById(R.id.Address);
        city =  findViewById(R.id.City);
        description =  findViewById(R.id.Description);
        image = (ImageView)findViewById(R.id.imageone);
        browse = (Button)findViewById(R.id.browse);
        submit = (Button) findViewById(R.id.Submit);
        submit.setOnClickListener(this);


           browse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dexter.withActivity(Registerpage.this)
                        .withPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                        .withListener(new PermissionListener() {
                            @Override
                            public void onPermissionGranted(PermissionGrantedResponse response)
                            {
                                Intent intent = new Intent(Intent.ACTION_PICK);
                                intent.setType("image/*");
                                startActivityForResult(Intent.createChooser(intent,"Browse Image"),1);
                            }

                            @Override
                            public void onPermissionDenied(PermissionDeniedResponse response) {

                            }

                            @Override
                            public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {
                             token.continuePermissionRequest();
                            }
                        }).check();

            }
            });
    }
    @Override
    public void onClick(View view) {
        if(view.getId()== R.id.Submit) {
            String url = "http://192.168.43.159:80/image.php";
            StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                    response -> Toast.makeText(Registerpage.this, "success", Toast.LENGTH_SHORT).show(),
                    error -> Toast.makeText(Registerpage.this, "Error", Toast.LENGTH_SHORT).show()) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("ownerName", ownerName.getText().toString());
                    params.put("contactNo", contactNo.getText().toString());
                    params.put("amount", amount.getText().toString());
                    params.put("address", address.getText().toString());
                    params.put("city", city.getText().toString());
                    params.put("description", description.getText().toString());
                    params.put("image", encodeImageString);

                    return params;

                }

            };

            requestQueue  = Volley.newRequestQueue(Registerpage.this);
            requestQueue.add(stringRequest);



        }







    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if(requestCode ==1 && resultCode== RESULT_OK)
        {
            Uri filepath = data.getData();
            try {
                InputStream inputStream =getContentResolver().openInputStream(filepath);
                bitmap = BitmapFactory.decodeStream(inputStream);
                image.setImageBitmap(bitmap);
                encodeBitmapImage(bitmap);

            }catch (Exception ex)
            {

            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void encodeBitmapImage(Bitmap bitmap)
    {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,byteArrayOutputStream);

        byte[] bytesofimage = byteArrayOutputStream.toByteArray();
        encodeImageString= android.util.Base64.encodeToString(bytesofimage, Base64.DEFAULT);
    }
}

