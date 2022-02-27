package com.example.kasqu.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.kasqu.R;
import com.example.kasqu.adapter.SliderAdapter;
import com.example.kasqu.model.Main;
import com.example.kasqu.session.SessionManager;

import me.relex.circleindicator.CircleIndicator;

public class MainActivity extends AppCompatActivity {

    Activity activity;
    SessionManager sessionManager;

    ViewPager viewPager2;
    int[] images = {R.drawable.img4,R.drawable.money1,R.drawable.money2};
    String [] header = {"Welcome to Kasqu!","What is gross total income?","Difference between Gross Total Income"};
    String [] dec    = {
            "Take the instance of ‘gross total income’ and ‘total income’",
            "The ‘gross total income’ (GTI) is the total income you earn by adding all heads of income!",
            "To understand their difference in simple terms, look at the following formulae"

    };
    SliderAdapter adepter;
    CircleIndicator indicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager2 = findViewById(R.id.viewpager2);
        indicator = findViewById(R.id.indicator);
        adepter = new SliderAdapter(this, images,header,dec);
        viewPager2.setAdapter(adepter);
        indicator.setViewPager(viewPager2);

    }

    public void sing_up(View view) {

        sessionManager = new SessionManager(getApplicationContext());
        if (!sessionManager.is_login()){
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        }else{
            startActivity(new Intent(MainActivity.this,BerandaActivity.class));
            finish();
        }



    }

    public void btn_daftar(View view) {
//        startActivity(new Intent(SliderActivity.this,RegisterActivity.class));
//        finish();
    }
}