package com.example.kasqu.internet;

import com.example.kasqu.message.Message;
import com.example.kasqu.model.Akun;
import com.example.kasqu.model.Main;
import com.example.kasqu.model.Mitra;
import com.example.kasqu.model.Mutasi;
import com.example.kasqu.model.MyResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface EndPoint {
    @FormUrlEncoded
    @POST("login")
    Call<MyResponse> login(
            @Field("phone") String hp,
            @Field("password") String password
    );

    @FormUrlEncoded
    @POST("beranda")
    Call<List<Main>> mitra();

    @FormUrlEncoded
    @POST("fect_user")
    Call<Akun> fect_user(
            @Field("user_id") String id_user
    );


    @FormUrlEncoded
    @POST("edit_user")
    Call<Message> edit_user(
            @Field("user_id") String id,
            @Field("nama") String nama,
            @Field("alamat") String alamat,
            @Field("phone") String phone
    );


    @FormUrlEncoded
    @POST("beranda")
    Call<List<Main>> mutasi_user(
            @Field("user_id") String id
    );

    @FormUrlEncoded
    @POST("bayar")
    Call<Message> bayar_user(
            @Field("user_id") String user_id,
            @Field("mitra_id") String mitra_id,
            @Field("jumlah_pemasukan") String nominal,
            @Field("tanggal_masuk") String ket
    );

    @FormUrlEncoded
    @POST("get_user_mitra")
    Call<List<Mitra>> get_user_mitra(
            @Field("user_id") String user_id
    );




}
