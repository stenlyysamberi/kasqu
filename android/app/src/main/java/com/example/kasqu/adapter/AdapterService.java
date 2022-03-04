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
import com.example.kasqu.model.Mitra;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AdapterService extends RecyclerView.Adapter<AdapterService.mitraView> {

    Context context;
    List<Mitra> incomes;

    public AdapterService(Context context, List<Mitra> incomes) {
        this.context = context;
        this.incomes = incomes;
    }

    @NonNull
    @Override
    public AdapterService.mitraView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.item_service, parent, false);
        return new mitraView(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterService.mitraView holder, int position) {
        holder.nama_owner.setText(incomes.get(position).getNama_owner());
        holder.nama_store.setText(incomes.get(position).getNama_usaha());
        Picasso.with(context).load("http://safe-temple-10558.herokuapp.com//storage/"+incomes.get(position).getGambar()).into(holder.imageView);
        //Toast.makeText(context.getApplicationContext(), "" + incomes.get(position).getGambar_store(), Toast.LENGTH_SHORT).show();

    }

    @Override
    public int getItemCount() {
        return incomes.size();
    }

    public class mitraView extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView nama_owner,nama_store;
        public mitraView(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.img_ct0);
            nama_store = itemView.findViewById(R.id.ct1);
            nama_owner = itemView.findViewById(R.id.ct2);
        }
    }
}
