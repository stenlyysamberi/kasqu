package com.example.kasqu.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kasqu.R;
import com.example.kasqu.widget.ToRupiah;

public class BuyActivity extends AppCompatActivity implements View.OnClickListener {

    private ToRupiah toRupiah = new ToRupiah();
    private SparseArray<String> keyValues = new SparseArray<>();
    private InputConnection inputConnection;
    private EditText jumlah;

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



        String rp = "Rp";

        jumlah = (EditText) findViewById(R.id.jumlah);
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
//        String text1 = "Sudah Makan";
//        if (text1.equals(text1)){
//            Toast.makeText(getApplicationContext(), "semangat ya my docter! <3", Toast.LENGTH_SHORT).show();
//        }else {
//            Log.e("message", "Hi, Docter ingat makan kwk");
//        }
        String nominal = jumlah.getText().toString();
        if (nominal.isEmpty()){
            Toast.makeText(getApplicationContext(), "kosong", Toast.LENGTH_SHORT).show();
        }else{
            Intent intent = new Intent(getApplicationContext(), ConfirmActivity.class);
            intent.putExtra("nominal", nominal);
            startActivity(intent);
        }
    }
}