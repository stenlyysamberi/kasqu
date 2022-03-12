package com.example.kasqu.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kasqu.R;
import com.example.kasqu.model.Income;
import com.example.kasqu.model.Mutasi;
import com.example.kasqu.widget.ToRupiah;

import java.util.List;

public class AdapterMutasi extends RecyclerView.Adapter<AdapterMutasi.mitraView> {

    Context context;
    List<Mutasi> incomes;
    ToRupiah rp = new ToRupiah();

    public AdapterMutasi(Context context, List<Mutasi> incomes) {
        this.context = context;
        this.incomes = incomes;
    }

    @NonNull
    @Override
    public AdapterMutasi.mitraView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.item_mutasi, parent, false);
        return new mitraView(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterMutasi.mitraView holder, int position) {
        holder.judul.setText(String.valueOf(rp.formatRupiah(incomes.get(position).getJumlah())));
        holder.subjudul.setText("kasqu - " + incomes.get(position).getNama_usaha());
        holder.tglmutasi.setText(incomes.get(position).getTgl_mutasi());
//      holder.statusmutasi.setText(incomes.get(position).getStatus());
    }

    @Override
    public int getItemCount() {
        return incomes.size();
    }

    public class mitraView extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView judul,subjudul,tglmutasi,statusmutasi;
        public mitraView(@NonNull View itemView) {
            super(itemView);
            judul = itemView.findViewById(R.id.judul);
            subjudul = itemView.findViewById(R.id.sub_judul);
            tglmutasi = itemView.findViewById(R.id.tgl_mutasi);
            statusmutasi = itemView.findViewById(R.id.status_mutasi);
        }
    }
}
