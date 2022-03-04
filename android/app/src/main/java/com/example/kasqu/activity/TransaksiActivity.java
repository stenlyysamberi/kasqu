package com.example.kasqu.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.util.Pair;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kasqu.R;
import com.example.kasqu.adapter.AdapterIncome;
import com.example.kasqu.adapter.AdapterSpent;
import com.example.kasqu.internet.EndPoint;
import com.example.kasqu.internet.Retrofit;
import com.example.kasqu.model.Income;
import com.example.kasqu.model.Main;
import com.example.kasqu.model.Spent;
import com.example.kasqu.session.SessionManager;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TransaksiActivity extends AppCompatActivity {

    private EndPoint endPoint;
    private RecyclerView recyclerView;
    private AdapterIncome adapterIncome;
    private AdapterSpent adapteSpent;
    private TextView jenis_mutasi,tgl_mutasi;
    private RelativeLayout btn_cari;
    private SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaki);


        sessionManager = new SessionManager(getApplicationContext());
        HashMap<String, String> s = sessionManager.getUserDetails();
        String ids = s.get(SessionManager.kunci_id);

        btn_cari     = findViewById(R.id.btm_cari);

        jenis_mutasi = (TextView) findViewById(R.id.tv_jenis_mutasi);
        tgl_mutasi   = (TextView) findViewById(R.id.tv_tgl_mutasi);
        Intent intent=getIntent();
        jenis_mutasi.setText(intent.getStringExtra("jenis_mutasi"));

        endPoint = Retrofit.getRetrofitInstance().create(EndPoint.class);
        Call<List<Main>> main = endPoint.mutasi_user(ids);
        main.enqueue(new Callback<List<Main>>() {
            @Override
            public void onResponse(Call<List<Main>> call, Response<List<Main>> response) {
                List<Main> s = response.body();
                btn_cari.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View view) {
                      if (tgl_mutasi.getText().equals("")){
                          Toast.makeText(getApplicationContext(), "Pilih Tanggal Kosong!", Toast.LENGTH_SHORT).show();
                      }else{
                          if (jenis_mutasi.getText().equals("Pemasukan")){
                              inCOme(s.get(0).getIncome());
                              //menampilkan data pemasukan
                          }else{
                              spent(s.get(0).getSpent());
                              //menampilkan data pengeluaran
                          }
                      }
                   }
                });
            }

            @Override
            public void onFailure(Call<List<Main>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "" + t, Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void inCOme(List<Income> incomes){
        recyclerView = findViewById(R.id.recy_mutasi);
        adapterIncome = new AdapterIncome(this, incomes);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapterIncome);
    }

    private  void spent(List<Spent> spents){
        recyclerView = findViewById(R.id.recy_mutasi);
        adapteSpent = new AdapterSpent(this, spents);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapteSpent);
    }

    public void set_tanggal_mutasi(View view) {

        MaterialDatePicker.Builder<Pair<Long, Long>> builder = MaterialDatePicker.Builder.dateRangePicker() ;
        builder.setTitleText("Pilih Rentang Waktu");
        MaterialDatePicker<Pair<Long, Long>> materialDatePicker = builder.build();

        materialDatePicker.show(getSupportFragmentManager(), "WAKTU");
        materialDatePicker.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener<Pair<Long, Long>>() {
            @Override
            public void onPositiveButtonClick(Pair<Long, Long> selection) {

                TextView tv_tgl_mutasi = findViewById(R.id.tv_tgl_mutasi);
                tv_tgl_mutasi.setText(materialDatePicker.getHeaderText());

//                if (selection.first != null && selection.second !=null){
//                    //Start
//                    Calendar start = Calendar.getInstance();
//                    int year  = start.get(Calendar.YEAR);
//                    int month = start.get(Calendar.MONTH);
//                    int day   = start.get(Calendar.DAY_OF_MONTH);
//                    int awal  = day+month+year;
//                    start.setTimeInMillis(selection.first);
//
//                    //End
//                    Calendar end = Calendar.getInstance();
//                    int years  = end.get(Calendar.YEAR);
//                    int months = end.get(Calendar.MONTH);
//                    int days   = end.get(Calendar.DAY_OF_MONTH);
//                    int akhir  = days+months+years;
//                    end.setTimeInMillis(selection.second);
//
//
//
//                    while (start.before(end)){
//                        start.add(Calendar.YEAR, 1);
//                        Log.d("onClik", String.valueOf(start.get(Calendar.YEAR)));
//                    }
//
//                }
            }
        });




//        final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(
//            MutasiActivity.this,R.style.bottomSheetDialogTheme);
//        View bottomSheetView = LayoutInflater.from(getApplicationContext())
//            .inflate(R.layout.sheet_btm_mutasi, (LinearLayout) findViewById(R.id.sheet_mutasi));
//
//        bottomSheetView.findViewById(R.id.tv_jenis_mutasi).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//
//            }
//        });
//
//
//        bottomSheetView.findViewById(R.id.daftar).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//               bottomSheetDialog.dismiss();
//            }
//        });
//
//        bottomSheetDialog.setContentView(bottomSheetView);
//        bottomSheetDialog.show();
    }
}
