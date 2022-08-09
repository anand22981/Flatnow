package com.example.flatnow;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.toolbox.NetworkImageView;
import com.bumptech.glide.Glide;

import java.util.List;

public class flatadapter  extends RecyclerView.Adapter<flatadapter.flatviewholder>
{

    public flatadapter(Context context, Flat[] data) {
        this.context = context;
        this.data = data;
    }

    Context context;
    Flat[]data;



    @NonNull
    @Override
    public flatviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.flatlist,parent,false);
        return new flatviewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull flatviewholder holder, int position) {
        Flat flat = data[position];



        holder.textDescription.setText(flat.getDescription());
        holder.textcity.setText(flat.getCity());
        holder.textcontactno.setText(String.valueOf(flat.getContactno()));
        holder.textPrice.setText(String.valueOf(flat.getAmount()));
        Glide.with(holder.img.getContext()).load("http://192.168.43.159:80/img/"+flat.getImage()).into(holder.img);
//





    }




    @Override
    public int getItemCount() {return data.length;}
       class flatviewholder extends  RecyclerView.ViewHolder{



           TextView textDescription, textcity, textcontactno,textPrice;
           ImageView img;

           public flatviewholder(@NonNull View itemView) {
               super(itemView);
               textDescription = itemView.findViewById(R.id.textViewflatdescription);
               textcity =itemView.findViewById(R.id.textViewflatcity);
               textcontactno =itemView.findViewById(R.id.textViewflatcontactno);
               textPrice=itemView.findViewById(R.id.textViewflatprice);
                img = itemView.findViewById(R.id.networkImageView);




           }
       }

}
