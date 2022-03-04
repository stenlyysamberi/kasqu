package com.example.kasqu.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import com.example.kasqu.R;
import com.example.kasqu.adapter.AdapterMutasi;
import com.example.kasqu.adapter.AdapterService;
import com.example.kasqu.databinding.ActivityMutasiBinding;
import com.example.kasqu.internet.EndPoint;
import com.example.kasqu.internet.Retrofit;
import com.example.kasqu.model.Akun;
import com.example.kasqu.model.Main;
import com.example.kasqu.model.Mutasi;
import com.example.kasqu.session.SessionManager;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MutasiActivity extends AppCompatActivity {

    private ActivityMutasiBinding activityMutasiBinding;
    private SessionManager sessionManager;
    private AdapterMutasi adapterMutasi;
    private RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMutasiBinding = ActivityMutasiBinding.inflate(getLayoutInflater());
        View view = activityMutasiBinding.getRoot();
        setContentView(view);

        try {
            sessionManager = new SessionManager(getApplicationContext());
            HashMap<String, String> s = sessionManager.getUserDetails();
            String ids = s.get(SessionManager.kunci_id);

            EndPoint endPoint = Retrofit.getRetrofitInstance().create(EndPoint.class);
            Call<List<Main>> mutasi = endPoint.mutasi_user(ids);
            mutasi.enqueue(new Callback<List<Main>>() {
                @Override
                public void onResponse(Call<List<Main>> call, Response<List<Main>> response) {
                    List<Main> s = response.body();
                    user_mutasi(s.get(0).getMutasi_user());
                }

                @Override
                public void onFailure(Call<List<Main>> call, Throwable t) {

                }
            });


        }catch (Exception e){
            Log.e("mutasi", String.valueOf(e));
        }




    }

    private void user_mutasi(List<Mutasi> mutasis){

        recyclerView = findViewById(R.id.recy_mutasi);
        adapterMutasi = new AdapterMutasi(this, mutasis);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapterMutasi);

    }
}