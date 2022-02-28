package com.example.kasqu.internet;

import com.example.kasqu.message.Message;
import com.example.kasqu.model.Akun;
import com.example.kasqu.model.Main;
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

    @GET("beranda")
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



}
