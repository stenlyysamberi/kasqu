package com.example.kasqu.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kasqu.R;
import com.example.kasqu.adapter.AdapterService;
import com.example.kasqu.internet.EndPoint;
import com.example.kasqu.internet.Retrofit;
import com.example.kasqu.model.Income;
import com.example.kasqu.model.Main;
import com.example.kasqu.model.Mitra;
import com.example.kasqu.model.SisaSaldo;
import com.example.kasqu.widget.ToRupiah;
import com.example.kasqu.session.SessionManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BerandaActivity extends AppCompatActivity {

    SessionManager sessionManager;

    EndPoint endPoint;
    RecyclerView recy_service;

    private AdapterService adapterService;
    View bottomSheetView;


//    BottomSheetDialog bottomSheetDialog;


//    TabLayout tabLayout;
//    ViewPager2 viewPager;
//    AdapterTabLayout adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beranda);

        sessionManager = new SessionManager(getApplicationContext());
        HashMap<String, String> s = sessionManager.getUserDetails();
        String ids = s.get(SessionManager.kunci_id);
        endPoint = Retrofit.getRetrofitInstance().create(EndPoint.class);
        Call<List<Main>> main = endPoint.mutasi_user(ids);
        main.enqueue(new Callback<List<Main>>() {
            @Override
            public void onResponse(Call<List<Main>> call, Response<List<Main>> response) {

                if (response.isSuccessful() && response.body()!=null){



                   List<Main> s = response.body();
                   Log.d("data", s.toString());

                   subtotal(s.get(0).getSisa_saldo());//menampilkan total pemasukan

                   service(s.get(0).getMitra());//menampilkan data mitra


                }

            }

            @Override
            public void onFailure(Call<List<Main>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "error" + t, Toast.LENGTH_SHORT).show();
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



    private void subtotal(List<SisaSaldo> total){
        ToRupiah rp = new ToRupiah();
        int sum = 0;
        List<Integer> jml = new ArrayList<Integer>();
        for (int i=0; i<total.size(); i++){
            jml.add((total.get(i).getSisa_saldo()));
        }
        Log.e("nilai", String.valueOf(jml));

        for (int num : jml){
            sum = sum+num;
        }
        TextView saldon = findViewById(R.id.saldo);
        saldon.setText(String.valueOf(rp.formatRupiah(sum)));
        Log.e("nilai", String.valueOf(sum));


    }

    private  void service(List<Mitra> mitra){
        recy_service =  findViewById(R.id.recy_mitra);
        adapterService = new AdapterService(this, mitra);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recy_service.setLayoutManager(layoutManager);
        recy_service.setAdapter(adapterService);
    }

    public void myAkun(View view) {
        Intent intent = new Intent(getApplicationContext(),ProfilActivity.class);
        startActivity(intent);
        finish();
    }



    public void income(View view) {
        Intent intent = new Intent(getApplicationContext(), TransaksiActivity.class);
        intent.putExtra("jenis_mutasi", "Pemasukan");
        startActivity(intent);
        finish();
    }

    public void spent(View view) {
        Intent intent = new Intent(getApplicationContext(), TransaksiActivity.class);
        intent.putExtra("jenis_mutasi", "Pengeluaran");
        startActivity(intent);
        finish();
    }

    public void mutasi_user(View view) {
        Intent intent = new Intent(getApplicationContext(), MutasiActivity.class);
        startActivity(intent);
        finish();
    }

    public void buys(View view) {
        Intent intent = new Intent(getApplicationContext(), BuyActivity.class);
        startActivity(intent);
        finish();
    }
}
