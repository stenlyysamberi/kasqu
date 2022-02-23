package com.example.kasqu.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.kasqu.R;
import com.example.kasqu.adapter.SliderAdapter;

import me.relex.circleindicator.CircleIndicator;

public class MainActivity extends AppCompatActivity {

    Activity activity;

    ViewPager viewPager2;
    int[] images = {R.drawable.img4,R.drawable.img4,R.drawable.img4};
    String [] header = {"Selamat Datang di Jaki!","Bayar ini itu, dengan sekali Klik","Lebih Mudah Tampa Dompet"};
    String [] dec    = {
            "Aplikasi layanan Tiket yang siap bikin transaksi kamu jadi lebih mudah, aman dan menyenangkan",
            "Jajan makanan hingga bayar Tiket? Semua beres dan makin mudah pake Jaki!",
            "Kini bisa transaksi dan isi saldo langsung pakai kartu debit kamu"

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
        startActivity(new Intent(MainActivity.this,LoginActivity.class));
        finish();
    }

    public void btn_daftar(View view) {
//        startActivity(new Intent(SliderActivity.this,RegisterActivity.class));
//        finish();
    }
}