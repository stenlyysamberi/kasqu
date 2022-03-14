package com.example.kasqu.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kasqu.R;
import com.example.kasqu.databinding.ActivityEditProfilBinding;
import com.example.kasqu.databinding.ActivityProfilBinding;
import com.example.kasqu.internet.EndPoint;
import com.example.kasqu.internet.Retrofit;
import com.example.kasqu.message.Message;
import com.example.kasqu.session.SessionManager;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditProfilActivity extends AppCompatActivity {
    private ActivityEditProfilBinding  editProfilBinding;
    private TextView simpan;
    private SessionManager sessionManager;
    private static final int INTENT_REQUEST_CODE = 777;
    private Bitmap bitmap;
    private ImageView imageView;
    private String old_images;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        editProfilBinding = ActivityEditProfilBinding.inflate(getLayoutInflater());
        View view = editProfilBinding.getRoot();
        setContentView(view);

        Intent intent=getIntent();
        editProfilBinding.namaLengkapProfilSaya.setText(intent.getStringExtra("nama"));
        editProfilBinding.phoneSya.setText(intent.getStringExtra("phone"));
        editProfilBinding.alamatRumah.setText(intent.getStringExtra("alamat"));
//      old_images = intent.getStringExtra("oldimage");

        Picasso.with(this)
                .load("http://192.168.1.3:8000/storage/"+intent.getStringExtra("oldimage"))
                .placeholder(R.drawable.ic_round_image_24)
                .error(R.drawable.ic_round_image_24)
                .into(imageView = editProfilBinding.fotoDp);




        sessionManager = new SessionManager(getApplicationContext());
        HashMap<String, String> s = sessionManager.getUserDetails();
        String ids = s.get(SessionManager.kunci_id);


       simpan = editProfilBinding.simpanTv;
       simpan.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               String nama = String.valueOf(editProfilBinding.namaLengkapProfilSaya.getText());
               String alamat = String.valueOf(editProfilBinding.alamatRumah.getText());
               String phone = String.valueOf(editProfilBinding.phoneSya.getText());

               edit_akun(ids,nama,alamat,phone);
           }
       });

    }

    private String imageToString() {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        byte[] imagebyte = byteArrayOutputStream.toByteArray();
        return Base64.encodeToString(imagebyte, Base64.DEFAULT);
    }

    private void edit_akun(String ids, String nama, String alamat, String phone) {
                if (nama.isEmpty() || alamat.isEmpty() || phone.isEmpty()){
                Toast.makeText(getApplicationContext(), "data is empty!", Toast.LENGTH_SHORT).show();
        }else{
            try {
//              String img = imageToString();
                EndPoint s = Retrofit.getRetrofitInstance().create(EndPoint.class);
                Call<Message> as = s.edit_user(ids,nama,alamat,phone);
                as.enqueue(new Callback<Message>() {
                    @Override
                    public void onResponse(Call<Message> call, Response<Message> response) {
                        if (response.body().getResult().equals("berhasil")){
                            Toast.makeText(getApplicationContext(), "users been updated", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(EditProfilActivity.this,ProfilActivity.class));
                            finish();
                        }
                    }

                    @Override
                    public void onFailure(Call<Message> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), "onFailure Connection" + t, Toast.LENGTH_SHORT).show();
                    }
                });

            }catch (Exception e){
                Toast.makeText(getApplicationContext(), "" + e, Toast.LENGTH_SHORT).show();
            }
        }
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == INTENT_REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            Uri path = data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), path);
                imageView = editProfilBinding.fotoDp;
                imageView.setImageBitmap(bitmap);
                imageView.setVisibility(View.VISIBLE);
                return;

            } catch (IOException e) {
                e.printStackTrace();
            }


        }
    }

    public void model_foto(View view) {
        final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(
                EditProfilActivity.this,R.style.bottomSheetDialogTheme);
        View bottomSheetView = LayoutInflater.from(getApplicationContext())
                .inflate(R.layout.sheet_opengalery, (LinearLayout) findViewById(R.id.pilih_foto));

        bottomSheetView.findViewById(R.id.pilih_foto_galeri).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent, INTENT_REQUEST_CODE);
                bottomSheetDialog.dismiss();
            }
        });

        bottomSheetDialog.setContentView(bottomSheetView);
        bottomSheetDialog.show();
    }
}