package com.example.kasqu.widget;

import com.google.android.material.datepicker.MaterialDatePicker;

public class Waktu {

    public void service_waktu(MaterialDatePicker materialDatePicker){
        MaterialDatePicker.Builder builder = MaterialDatePicker.Builder.datePicker();
        builder.setTitleText("Select a date");
        materialDatePicker = builder.build();
        //materialDatePicker.show(materialDatePicker.getFragmentManager(), "WAKTU");
    }

}
