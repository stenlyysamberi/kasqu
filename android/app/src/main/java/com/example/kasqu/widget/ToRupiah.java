package com.example.kasqu.widget;

import java.text.NumberFormat;
import java.util.Locale;

public class ToRupiah {
    public String formatRupiah(Integer number){
        Locale localeID = new Locale("in", "ID");
        NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);
        return formatRupiah.format(number);
    }
}
