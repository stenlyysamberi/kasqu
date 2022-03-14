package com.example.kasqu.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kasqu.R;
import com.example.kasqu.databinding.ActivityProfilBinding;
import com.example.kasqu.internet.EndPoint;
import com.example.kasqu.internet.Retrofit;
import com.example.kasqu.message.Message;
import com.example.kasqu.model.Akun;
import com.example.kasqu.model.Main;
import com.example.kasqu.model.MyResponse;
import com.example.kasqu.session.SessionManager;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfilActivity extends AppCompatActivity {
    Context context;
    TextView nama,address,phone;
    SessionManager sessionManager;
    ImageView imageView;
    private ActivityProfilBinding profil;
    private String old_mages;
//    private String ids;
    RelativeLayout simpan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        profil = ActivityProfilBinding.inflate(getLayoutInflater());
        View view = profil.getRoot();
        setContentView(view);


        sessionManager = new SessionManager(getApplicationContext());
        HashMap<String, String> s = sessionManager.getUserDetails();
        String ids = s.get(SessionManager.kunci_id);
        fetch_akun(ids);


    }

    private void fetch_akun(final String id){
        try {
            EndPoint endPoint = Retrofit.getRetrofitInstance().create(EndPoint.class);
            Call<Akun> akun = endPoint.fect_user(id);
            akun.enqueue(new Callback<Akun>() {
                @Override
                public void onResponse(Call<Akun> call, Response<Akun> response) {

                    if (response.isSuccessful() && response.body()!=null){
                        imageView = profil.imgProfil;
                        Picasso.with(context).load("http://192.168.1.3:8000/storage/"+response.body().getGambar()).into(imageView);
                        nama = profil.namaSaya;
                        nama.setText(response.body().getNama());
                        address = profil.alamatSaya;
                        address.setText(response.body().getAlamat());
                        phone = profil.hpSaya;
                        phone.setText(response.body().getPhone());

                        old_mages = response.body().getGambar();

                    }else{
                        Toast.makeText(getApplicationContext(), "connection failed" + response.errorBody(), Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<Akun> call, Throwable t) {
                    Toast.makeText(getApplicationContext(), "connection failed" + t, Toast.LENGTH_SHORT).show();
                }
            });
        }catch (Exception e){
            Log.e("akunError", String.valueOf(e));
        }
    }

    public void logout(View view) {
        sessionManager.logout();
        finish();
    }

    public void back(View view) {
        Intent intent = new Intent(ProfilActivity.this, BerandaActivity.class);
        startActivity(intent);
        finish();
    }

    public void edit_profil(View view) {
        //Toast.makeText(getApplicationContext(), "" + String.valueOf(old_mages), Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(ProfilActivity.this, EditProfilActivity.class);
        intent.putExtra("nama", String.valueOf(nama.getText()));
        intent.putExtra("phone", String.valueOf(phone.getText()));
        intent.putExtra("alamat", String.valueOf(address.getText()));
        intent.putExtra("oldimage", String.valueOf(old_mages));
        startActivity(intent);
        finish();
    }
}