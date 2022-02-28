package com.example.kasqu.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
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

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfilActivity extends AppCompatActivity {

    EditText nama,address,phone;
    SessionManager sessionManager;
    private ActivityProfilBinding profil;
//    private String ids;
    CardView simpan;

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


        simpan = findViewById(R.id.simpan_profil);
        simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String nama = String.valueOf(profil.namaLengkapProfilSaya.getText());
                String alamat = String.valueOf(profil.alamatRumah.getText());
                String phone = String.valueOf(profil.phoneSya);

                edit_akun(ids,nama,alamat,phone);
            }
        });
    }

    private void fetch_akun(final String id){
        try {
            EndPoint endPoint = Retrofit.getRetrofitInstance().create(EndPoint.class);
            Call<Akun> akun = endPoint.fect_user(id);
            akun.enqueue(new Callback<Akun>() {
                @Override
                public void onResponse(Call<Akun> call, Response<Akun> response) {

                    if (response.isSuccessful() && response.body()!=null){
                        nama = profil.namaLengkapProfilSaya;
                        nama.setText(response.body().getNama());
                        address = profil.alamatRumah;
                        address.setText(response.body().getAlamat());
                        phone = profil.phoneSya;
                        phone.setText(response.body().getPhone());

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

    private void edit_akun (String id,String nama, String alamat, String phone){
        if (nama.isEmpty() || alamat.isEmpty() || phone.isEmpty()){
            Toast.makeText(getApplicationContext(), "data is empty!", Toast.LENGTH_SHORT).show();
        }else{
            try {

                EndPoint endPoint = Retrofit.getRetrofitInstance().create(EndPoint.class);
                Call<Message> as = endPoint.edit_user(id,nama,alamat,phone);
                as.enqueue(new Callback<Message>() {
                   @Override
                   public void onResponse(Call<Message> call, Response<Message> response) {
                       if (response.body().getResult().equals("berhasil")){
                           Toast.makeText(getApplicationContext(), "has been connected", Toast.LENGTH_SHORT).show();
                       }
                   }

                   @Override
                   public void onFailure(Call<Message> call, Throwable t) {
                       Toast.makeText(getApplicationContext(), "" + t, Toast.LENGTH_SHORT).show();
                   }
               });

            }catch (Exception e){
                Toast.makeText(getApplicationContext(), "" + e, Toast.LENGTH_SHORT).show();
            }
        }
    }
}