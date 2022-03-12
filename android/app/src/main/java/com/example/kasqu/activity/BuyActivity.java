package com.example.kasqu.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kasqu.R;
import com.example.kasqu.adapter.AdapterMitra;
import com.example.kasqu.internet.EndPoint;
import com.example.kasqu.internet.Retrofit;
import com.example.kasqu.model.Mitra;
import com.example.kasqu.session.SessionManager;
import com.example.kasqu.widget.ToRupiah;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BuyActivity extends AppCompatActivity implements View.OnClickListener {

    private ToRupiah toRupiah = new ToRupiah();
    private SparseArray<String> keyValues = new SparseArray<>();
    private InputConnection inputConnection;
    private EditText jumlah;
    private TextView nama_mitra;
    private AdapterMitra adapterMitra;
    private String id_mitra;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy);

        TextView satu;
        TextView dua;
        TextView tiga;
        TextView empat;
        TextView lima;
        TextView enam;
        TextView tujuh;
        TextView delapan;
        TextView sembilan;
        TextView kosong;
        TextView clear;
        TextView hapus;
        final TextView[] setNominal = new TextView[1];


        jumlah = (EditText) findViewById(R.id.jumlah);
        nama_mitra = findViewById(R.id.nama_mitra);
        //jumlah.setRawInputType(InputType.TYPE_CLASS_TEXT);
        //jumlah.setTextIsSelectable(true);

        InputConnection ic = jumlah.onCreateInputConnection(new EditorInfo());
        setInputConnection(ic);

        satu = (TextView) findViewById(R.id.satu);
        satu.setOnClickListener(this);

        dua = (TextView) findViewById(R.id.dua);
        dua.setOnClickListener(this);

        tiga = (TextView)findViewById(R.id.tiga);
        tiga.setOnClickListener(this);

        empat = (TextView) findViewById(R.id.empat);
        empat.setOnClickListener(this);

        lima = (TextView)findViewById(R.id.lima);
        lima.setOnClickListener(this);

        enam = (TextView)findViewById(R.id.enam);
        enam.setOnClickListener(this);

        tujuh = (TextView)findViewById(R.id.tujuh);
        tujuh.setOnClickListener(this);

        delapan = (TextView)findViewById(R.id.delapan);
        delapan.setOnClickListener(this);

        sembilan = (TextView) findViewById(R.id.sembilan);
        sembilan.setOnClickListener(this);

        kosong = (TextView)findViewById(R.id.kosong);
        kosong.setOnClickListener(this);

        clear = (TextView)findViewById(R.id.clear);
        clear.setOnClickListener(this);

        hapus= (TextView)findViewById(R.id.hapus);
        hapus.setOnClickListener(this);

        keyValues.put(R.id.satu, "1");
        keyValues.put(R.id.dua, "2");
        keyValues.put(R.id.tiga, "3");
        keyValues.put(R.id.empat, "4");
        keyValues.put(R.id.lima, "5");
        keyValues.put(R.id.enam, "6");
        keyValues.put(R.id.tujuh, "7");
        keyValues.put(R.id.delapan, "8");
        keyValues.put(R.id.sembilan, "9");
        keyValues.put(R.id.kosong, "0");
        keyValues.put(R.id.clear,"000");


    }



    @Override
    public void onClick(View view) {
        if (inputConnection == null)
            return;

        if (view.getId() == R.id.hapus){
            CharSequence select_txt = inputConnection.getSelectedText(0);
            if (TextUtils.isEmpty(select_txt)){
                inputConnection.deleteSurroundingText(1,0);
            }else{
               inputConnection.commitText("", 1);
            }
        } else{
            String value = keyValues.get(view.getId());
            inputConnection.commitText(value, 1);
        }
    }

    public void setInputConnection(InputConnection ic){
        inputConnection = ic;
    }

    public void konfirm(View view) {

        String nominal = jumlah.getText().toString();
        String mitra   = nama_mitra.getText().toString();
        if (nominal.isEmpty() || mitra.isEmpty()){
            Toast.makeText(getApplicationContext(), "Pastikan nominal bayar & mitra terinput!", Toast.LENGTH_SHORT).show();
        }else{
            Intent intent = new Intent(getApplicationContext(), ConfirmActivity.class);
            intent.putExtra("nominal", nominal);
            intent.putExtra("nama_mitra", mitra);
            intent.putExtra("id_mitra", id_mitra);
            startActivity(intent);
        }
    }


    public void select_mitra(View view) {

        SessionManager sessionManager;
        sessionManager = new SessionManager(getApplicationContext());
        HashMap<String, String> s = sessionManager.getUserDetails();
        String ids = s.get(SessionManager.kunci_id);


        final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(
                BuyActivity.this,R.style.bottomSheetDialogTheme);
        View bottomSheetView = LayoutInflater.from(getApplicationContext())
                .inflate(R.layout.sheet_btm_mitra, (LinearLayout) findViewById(R.id.sheet_mitra));

        RecyclerView recyclerView;
        StaggeredGridLayoutManager layoutManager;

        recyclerView = bottomSheetView.findViewById(R.id.recy_mitra);
        layoutManager = new StaggeredGridLayoutManager(1, LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        EndPoint endPoint = Retrofit.getRetrofitInstance().create(EndPoint.class);
        Call<List<Mitra>> mitra = endPoint.get_user_mitra(ids);
        mitra.enqueue(new Callback<List<Mitra>>() {
            @Override
            public void onResponse(Call<List<Mitra>> call, Response<List<Mitra>> response) {
                List<Mitra> mit = response.body();
                adapterMitra = new AdapterMitra(mit, BuyActivity.this);
                recyclerView.setAdapter(adapterMitra);
                adapterMitra.notifyDataSetChanged();
            }
            @Override
            public void onFailure(Call<List<Mitra>> call, Throwable t) {
                Log.e("mitra", String.valueOf(t));
            }
        });

        bottomSheetView.findViewById(R.id.btn_select_item).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (adapterMitra.getSelected() != null){
                    show(adapterMitra.getSelected().getNama_usaha(), adapterMitra.getSelected().getMitra_id());
                }else{
                    show("tidak adalah pilihan","");
                }

                bottomSheetDialog.dismiss();
            }
        });

        bottomSheetDialog.setContentView(bottomSheetView);
        bottomSheetDialog.show();

    }

    private void CreateList() {

        ArrayList data = new ArrayList();

        for (int i = 0; i < 20; i++){
            Mitra mitra = new Mitra();
            mitra.setNama_usaha(String.valueOf(i+1));
            data.add(mitra);

        }
        adapterMitra.SetMitra(data);
    }

    private void show(String pesan, String id) {
          nama_mitra.setText(pesan);
          id_mitra = id;
          Log.e("pesan", id_mitra);


    }

    public void back(View view) {
        Intent intent = new Intent(BuyActivity.this, BerandaActivity.class);
        startActivity(intent);
        finish();
    }
}