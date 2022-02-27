package com.example.kasqu.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
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
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.tabs.TabLayout;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BerandaActivity extends AppCompatActivity implements View.OnClickListener {

    EndPoint endPoint;
    RecyclerView recy_service,recy_income,recy_spent;
    private AdapterIncome adapterIncome;
    private AdapterSpent adapteSpent;
    private AdapterService adapterService;
    //public View bottomSheetView;
    CardView card_income;
    View bottomSheetView;
    BottomSheetDialog bottomSheetDialog;


//    TabLayout tabLayout;
//    ViewPager2 viewPager;
//    AdapterTabLayout adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beranda);
        bottomSheetView = LayoutInflater.from(getApplicationContext())
                .inflate(R.layout.sheet_benefit, (LinearLayout) findViewById(R.id.bennefit));

        card_income = findViewById(R.id.card_income);
        card_income.setOnClickListener(this);


        endPoint = Retrofit.getRetrofitInstance().create(EndPoint.class);
        Call<List<Main>> main = endPoint.mitra();
        main.enqueue(new Callback<List<Main>>() {
            @Override
            public void onResponse(Call<List<Main>> call, Response<List<Main>> response) {

                if (response.isSuccessful() && response.body()!=null){

                   Income income = new Income();


                   List<Main> s = response.body();
                   Log.d("data", s.toString());
                   subtotal();
    
//                    Toast.makeText(getApplicationContext(), "" + subtotal(), Toast.LENGTH_SHORT).show();

                   inCOme(s.get(0).getIncome());
                   service(s.get(0).getMitra());
//                 spent(s.get(0).getSpent());

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

    public void inCOme(List<Income> incomes){

        recy_income = bottomSheetView.findViewById(R.id.recy_benefit);
        adapterIncome = new AdapterIncome(this, incomes);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recy_income.setLayoutManager(layoutManager);
        recy_income.setAdapter(adapterIncome);
    }

    private  void service(List<Mitra> mitra){
        recy_service =  findViewById(R.id.recy_mitra);
        adapterService = new AdapterService(this, mitra);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recy_service.setLayoutManager(layoutManager);
        recy_service.setAdapter(adapterService);
    }

    private  void spent(List<Spent> spents){
//        recy_spent = findViewById(R.id.recy_spent);
        adapteSpent = new AdapterSpent(this, spents);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recy_spent.setLayoutManager(layoutManager);
        recy_spent.setAdapter(adapteSpent);
    }

    private void subtotal(){
        ArrayList<Integer> total = new ArrayList<>();
        Income income = new Income();
        for (int i = 0; i>total.size(); i++){
            total.add(income.getJumlah());
            Log.e("total", String.valueOf(total.get(i)));
        }
    }



    @Override
    public void onClick(View view) {

        bottomSheetDialog = new BottomSheetDialog(BerandaActivity.this,R.style.bottomSheetDialogTheme);
        bottomSheetDialog.setContentView(bottomSheetView);
        bottomSheetDialog.show();

    }
}