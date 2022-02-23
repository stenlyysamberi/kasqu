package com.example.kasqu.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kasqu.R;
import com.example.kasqu.model.Income;
import com.example.kasqu.model.Mitra;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AdapterIncome extends RecyclerView.Adapter<AdapterIncome.mitraView> {

    Context context;
    List<Income> incomes;

    public AdapterIncome(Context context, List<Income> incomes) {
        this.context = context;
        this.incomes = incomes;
    }

    @NonNull
    @Override
    public AdapterIncome.mitraView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.item_income, parent, false);
        return new mitraView(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterIncome.mitraView holder, int position) {
        holder.nama_income.setText(incomes.get(position).getNama_store());
        holder.jumlah_income.setText(incomes.get(position).getJumlah());
        Picasso.with(context).load("http://192.168.1.4:8000/storage/"+incomes.get(position).getGambar_store()).into(holder.imageView);
        //Toast.makeText(context.getApplicationContext(), "" + incomes.get(position).getGambar_store(), Toast.LENGTH_SHORT).show();

    }

    @Override
    public int getItemCount() {
        return incomes.size();
    }

    public class mitraView extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView jumlah_income,nama_income;
        public mitraView(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.img_store);
            jumlah_income = itemView.findViewById(R.id.jumlah_store);
            nama_income = itemView.findViewById(R.id.nama_store);
        }
    }
}
