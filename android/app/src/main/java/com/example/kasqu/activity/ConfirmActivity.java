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
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.kasqu.R;
import com.example.kasqu.databinding.ActivityConfirmBinding;
import com.example.kasqu.databinding.ActivityMutasiBinding;
import com.example.kasqu.widget.ToRupiah;

public class ConfirmActivity extends AppCompatActivity {

    private ActivityConfirmBinding activityConfirmBinding;
    private ToRupiah toRupiah = new ToRupiah();
    private TextView nominal,confir_bayar,total_bayar;
    private CardView tombol;
    private String channelnotif = "channelku" ;
    private String channelid = "default";
    private String notif;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityConfirmBinding= ActivityConfirmBinding.inflate(getLayoutInflater());
        View view = activityConfirmBinding.getRoot();
        setContentView(view);
        nominal = activityConfirmBinding.nominal;
        confir_bayar = activityConfirmBinding.jumlahBayar;
        total_bayar = activityConfirmBinding.totalBayar;
        Intent intent=getIntent();
        nominal.setText(toRupiah.formatRupiah(Integer.valueOf(intent.getStringExtra("nominal"))));

        notif = intent.getStringExtra("nominal");

        confir_bayar.setText(toRupiah.formatRupiah(Integer.valueOf(intent.getStringExtra("nominal"))));

        total_bayar.setText(toRupiah.formatRupiah(Integer.valueOf(intent.getStringExtra("nominal"))));

        tombol = activityConfirmBinding.btnConfir;
        tombol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                notif();
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