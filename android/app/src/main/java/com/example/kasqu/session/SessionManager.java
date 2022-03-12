package com.example.kasqu.session;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.example.kasqu.activity.MainActivity;

import java.util.HashMap;

public class SessionManager {
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Context context;
    int mode = 0;

    private static final String pref_name = "crudpref";
    private static final String is_login = "islogin";
    public static final String kunci_id = "id_user";
    public static final String nama_lengkap = "nama";
    public static final String nama_mitras = "nama_mitra";
    public static final String id_mitras = "id_mitra";


    public SessionManager(Context context) {
        this.context = context;
        pref = context.getSharedPreferences(pref_name, mode);
        editor = pref.edit();
    }

    public void create_session(String id, String nama){
        editor.putBoolean(is_login, true);
        editor.putString(kunci_id,id);
        editor.putString(nama_lengkap,nama);
        editor.commit();
    }

    public void create_session_mitra(String id, String nama){
        editor.putString(id_mitras,id);
        editor.putString(nama_mitras,nama);
    }

    public boolean is_login() {
        return pref.getBoolean(is_login, false);
    }

    public void logout(){
        editor.clear();
        editor.commit();
        Intent i = new Intent(context, MainActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(i);

    }

    public HashMap<String, String> getUserDetails(){
        HashMap<String, String> user = new HashMap<String, String>();
        user.put(kunci_id, pref.getString(kunci_id, null));
        user.put(nama_lengkap,pref.getString(nama_lengkap,null));
        user.put(nama_mitras,pref.getString(nama_mitras,null));
        user.put(id_mitras,pref.getString(id_mitras,null));
        return user;
    }


}
