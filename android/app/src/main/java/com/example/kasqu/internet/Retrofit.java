package com.example.kasqu.internet;

import retrofit2.converter.gson.GsonConverterFactory;

public class Retrofit {
    private static retrofit2.Retrofit retrofit;

    //private static final String BASE_URL ="http://safe-temple-10558.herokuapp.com/api/";

    private static final String BASE_URL ="http://192.168.1.3:8000/api/";

    public static retrofit2.Retrofit getRetrofitInstance() {
        if (retrofit == null) {
            retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
