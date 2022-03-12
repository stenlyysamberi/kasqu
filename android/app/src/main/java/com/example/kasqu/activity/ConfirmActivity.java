package com.example.kasqu.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.NotificationCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kasqu.R;
import com.example.kasqu.databinding.ActivityConfirmBinding;
import com.example.kasqu.databinding.ActivityMutasiBinding;
import com.example.kasqu.internet.EndPoint;
import com.example.kasqu.internet.Retrofit;
import com.example.kasqu.message.Message;
import com.example.kasqu.session.SessionManager;
import com.example.kasqu.widget.ToRupiah;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ConfirmActivity extends AppCompatActivity {

    private ActivityConfirmBinding activityConfirmBinding;
    private ToRupiah toRupiah = new ToRupiah();
    private TextView nominal,confir_bayar,total_bayar,nama_mitra;
    private CardView tombol;
    private String channelnotif = "channelku" ;
    private String channelid = "default";
    private String notif,id_mitra;
    private SessionManager sessionManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityConfirmBinding= ActivityConfirmBinding.inflate(getLayoutInflater());
        View view = activityConfirmBinding.getRoot();
        setContentView(view);

        sessionManager = new SessionManager(getApplicationContext());
        HashMap<String, String> s = sessionManager.getUserDetails();
        String ids = s.get(SessionManager.kunci_id);
        String nama = s.get(SessionManager.nama_lengkap);



        nominal = activityConfirmBinding.nominal;
        confir_bayar = activityConfirmBinding.jumlahBayar;
        total_bayar = activityConfirmBinding.totalBayar;
        nama_mitra = activityConfirmBinding.namaMitra;

        Intent intent=getIntent();
        nominal.setText(toRupiah.formatRupiah(Integer.valueOf(intent.getStringExtra("nominal"))));

        notif = intent.getStringExtra("nominal");
        id_mitra = intent.getStringExtra("id_mitra");


        confir_bayar.setText(toRupiah.formatRupiah(Integer.valueOf(intent.getStringExtra("nominal"))));

        total_bayar.setText(toRupiah.formatRupiah(Integer.valueOf(intent.getStringExtra("nominal"))));

        nama_mitra.setText(intent.getStringExtra("nama_mitra"));

        tombol = activityConfirmBinding.btnConfir;
        tombol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    EndPoint endPoint = Retrofit.getRetrofitInstance().create(EndPoint.class);
                    Call<Message> me = endPoint.bayar_user(ids,id_mitra, notif,"Dana Masuk from");
                    me.enqueue(new Callback<Message>() {
                        @Override
                        public void onResponse(Call<Message> call, Response<Message> response) {
                            if (response.body().getResult().equals("berhasil")){
                                notif();
                                Intent intent = new Intent(getApplicationContext(), BerandaActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        }

                        @Override
                        public void onFailure(Call<Message> call, Throwable t) {
                            Log.e("bayarFailur", String.valueOf(t));
                        }
                    });
                }catch (Exception e){
                    Log.e("CatchError", String.valueOf(e));
                }
            }
        });


    }

    private void notif() {
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(ConfirmActivity. this, channelid )
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle( "Kasqu" )
                .setContentText( "Transaksi sebesar" + " " +  toRupiah.formatRupiah(Integer.valueOf(notif)) + " " + "BERHASIL." + " " + "Silakan Hubungi admi kampus prihal konfirmasi" );
        NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context. NOTIFICATION_SERVICE ) ;
        if (android.os.Build.VERSION. SDK_INT >= android.os.Build.VERSION_CODES. O ) {
            int importance = NotificationManager. IMPORTANCE_HIGH ;
            NotificationChannel notificationChannel = new
                    NotificationChannel( channelnotif , "contoh channel" , importance) ;
            notificationChannel.enableLights( true ) ;
            notificationChannel.setLightColor(Color.RED) ;
            mBuilder.setChannelId( channelnotif ) ;
            assert mNotificationManager != null;
            mNotificationManager.createNotificationChannel(notificationChannel) ;
        }
        assert mNotificationManager != null;
        mNotificationManager.notify(( int ) System. currentTimeMillis (), mBuilder.build()) ;
    }


}