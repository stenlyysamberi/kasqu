package com.example.kasqu.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.kasqu.R;
import com.example.kasqu.adapter.AdapterIncome;
import com.example.kasqu.adapter.AdapterService;
import com.example.kasqu.adapter.AdapterSpent;
import com.example.kasqu.adapter.AdapterTabLayout;
import com.example.kasqu.internet.EndPoint;
import com.example.kasqu.internet.Retrofit;
import com.example.kasqu.model.Income;
import com.example.kasqu.model.Main;
import com.example.kasqu.model.Mitra;
import com.example.kasqu.model.Spent;
import com.google.android.material.tabs.TabLayout;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BerandaActivity extends AppCompatActivity {

    EndPoint endPoint;
    RecyclerView recy_service,recy_income,recy_spent;
    private AdapterIncome adapterIncome;
    private AdapterSpent adapteSpent;
    private AdapterService adapterService;

//    TabLayout tabLayout;
//    ViewPager2 viewPager;
//    AdapterTabLayout adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beranda);

        endPoint = Retrofit.getRetrofitInstance().create(EndPoint.class);
        Call<List<Main>> main = endPoint.mitra();
        main.enqueue(new Callback<List<Main>>() {
            @Override
            public void onResponse(Call<List<Main>> call, Response<List<Main>> response) {

                if (response.isSuccessful() && response.body()!=null){

                    Toast.makeText(getApplicationContext(), "berhasil", Toast.LENGTH_SHORT).show();
                   List<Main> s = response.body();
                   Log.d("data", s.toString());

                   inCOme(s.get(0).getIncome());
                   service(s.get(0).getMitra());
                   spent(s.get(0).getSpent());

                }

            }

            @Override
            public void onFailure(Call<List<Main>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "" + t, Toast.LENGTH_SHORT).show();
            }
        });


//        tabLayout =findViewById(R.id.tabLayout);
//        viewPager =findViewById(R.id.viewpagerOrder);

//        FragmentManager fm = getSupportFragmentManager();
//        adapter = new AdapterTabLayout(fm,getLifecycle());
//        viewPager.setAdapter(adapter);
//
//        tabLayout.addTab(tabLayout.newTab().setText("Mitra"));
//        tabLayout.addTab(tabLayout.newTab().setText("Income"));
//        tabLayout.addTab(tabLayout.newTab().setText("Spent"));
//
//        tabLayout.getTabAt(0).setIcon(R.drawable.icon_akun);
//        tabLayout.getTabAt(1).setIcon(R.drawable.ic_round_attach_money_24);
//        tabLayout.getTabAt(2).setIcon(R.drawable.icon_cari);
//
//
//        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
//            @Override
//            public void onTabSelected(TabLayout.Tab tab) {
//                viewPager.setCurrentItem(tab.getPosition());
//            }
//
//            @Override
//            public void onTabUnselected(TabLayout.Tab tab) {
//
//            }
//
//            @Override
//            public void onTabReselected(TabLayout.Tab tab) {
//
//            }
//        });
//
//        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
//            @Override
//            public void onPageSelected(int position) {
//                tabLayout.selectTab(tabLayout.getTabAt(position));
//            }
//        });
    }

    private  void inCOme(List<Income> incomes){
        recy_income = findViewById(R.id.recy_income);
        adapterIncome = new AdapterIncome(this, incomes);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recy_income.setLayoutManager(layoutManager);
        recy_income.setAdapter(adapterIncome);
    }

    private  void service(List<Mitra> mitra){
        recy_service = findViewById(R.id.recy_mitra);
        adapterService = new AdapterService(this, mitra);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recy_service.setLayoutManager(layoutManager);
        recy_service.setAdapter(adapterService);
    }

    private  void spent(List<Spent> spents){
        recy_spent = findViewById(R.id.recy_spent);
        adapteSpent = new AdapterSpent(this, spents);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recy_spent.setLayoutManager(layoutManager);
        recy_spent.setAdapter(adapteSpent);
    }
}