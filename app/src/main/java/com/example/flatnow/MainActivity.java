package com.example.flatnow;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.flatnow.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    private ImageButton submit;
    private ImageButton find;


    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        replaceFragment(new HomeFragment());

        binding.bottomNavigationView.setOnItemSelectedListener(item -> {
             switch(item.getItemId()){
                 case R.id.home:
                     replaceFragment( new HomeFragment());
                     break;
                 case R.id.profile:
                     replaceFragment(new profileFragment());
                     break;
             }

            return  true;

        });



//        submit = findViewById(R.id.done);
//           find = findViewById(R.id.find);






//        find.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                openlistpage();
//            }
//        });

//        submit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                openregisterpage();
//
//            }
//        });
    }

//    public void openlistpage(){
//        Intent intent = new Intent(this,listpage.class);
//        startActivity(intent);
//    }
//    public void openregisterpage(){
//        Intent intent = new Intent(this,Registerpage.class);
//        startActivity(intent);
//    }

    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout,fragment);
        fragmentTransaction.commit();
    }



}