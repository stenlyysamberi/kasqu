package com.example.kasqu.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kasqu.R;
import com.example.kasqu.internet.EndPoint;
import com.example.kasqu.internet.Retrofit;
import com.example.kasqu.model.MyResponse;
import com.example.kasqu.session.SessionManager;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private TextView back;
    CardView cardlogin;
    EditText hp,password;
    private GoogleSignInClient mGoogleSignInClient;
    private int RC_SIGN_IN;

    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        sessionManager = new SessionManager(getApplicationContext());

        cardlogin = findViewById(R.id.card_login);

        hp = findViewById(R.id.phone);
        password = findViewById(R.id.password);

        back = findViewById(R.id.back);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });


        cardlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String hpp = hp.getText().toString();
                String psw  = password.getText().toString();



                //loadingDialog.loadingAlert();
                if (hpp.isEmpty() || psw.isEmpty()){
                    Toast.makeText(getApplicationContext(), "data belum lengkap", Toast.LENGTH_SHORT).show();
                    hp.setBackgroundResource(R.drawable.outline_red);
                    password.setBackgroundResource(R.drawable.outline_red);
                    //loadingDialog.dismissDialog();
                }else{
                    loginEmail(hpp,psw);
                }
            }
        });
    }

    private void loginEmail(String mobile_phone, String password){


        try {
            EndPoint endpoint = Retrofit.getRetrofitInstance().create(EndPoint.class);
            Call<MyResponse> respon = endpoint.login(mobile_phone,password);
            respon.enqueue(new Callback<MyResponse>() {
                @Override
                public void onResponse(Call<MyResponse> call, Response<MyResponse> response) {

                    if (response.isSuccessful() && response.body()!=null){
                        if (response.body().getResult().equals("berhasil")){
                            int id = response.body().getId();
                            String nama = response.body().getNama();
                            sessionManager.create_session(id,nama);

                            Intent intent = new Intent(getApplicationContext(),BerandaActivity.class);
                            intent.putExtra("mobile", mobile_phone);
                            startActivity(intent);

                        }else{
                            gagal_login();
                        }
                    }
                }

                @Override
                public void onFailure(Call<MyResponse> call, Throwable t) {
                    Log.e("responBody", String.valueOf(t));
                }
            });

        }catch (Exception e){
            Log.e("errorLogin", String.valueOf(e));
        }

    }

    public void gagal_login(){
        final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(
                LoginActivity.this,R.style.bottomSheetDialogTheme);
        View bottomSheetView = LayoutInflater.from(getApplicationContext())
                .inflate(R.layout.sheet_salah_akun, (LinearLayout) findViewById(R.id.error_password));
        bottomSheetView.findViewById(R.id.daftar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this,MainActivity.class));
                finish();
            }
        });

        bottomSheetDialog.setContentView(bottomSheetView);
        bottomSheetDialog.show();
    }
}