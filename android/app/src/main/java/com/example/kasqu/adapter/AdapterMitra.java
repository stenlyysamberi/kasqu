package com.example.kasqu.adapter;

import android.content.Context;
import android.util.Log;
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
import com.example.kasqu.session.SessionManager;
import com.example.kasqu.widget.ToRupiah;

import java.util.ArrayList;
import java.util.List;

public class AdapterMitra extends RecyclerView.Adapter<AdapterMitra.mitraView> {

    SessionManager sessionManager;
    List<Mitra> incomes;
    Context context;
    private int checkedPosition = 0;

    public AdapterMitra(List<Mitra> incomes, Context context) {
        this.incomes = incomes;
        this.context = context;
    }

    public void SetMitra(List<Mitra> mitras){
        this.incomes = new ArrayList<>();
        this.incomes = mitras;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public AdapterMitra.mitraView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.item_mitra, parent, false);
        return new mitraView(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterMitra.mitraView holder, int position) {
        //holder.nama_mitra.setText(incomes.get(position).getNama_usaha());
        holder.bin(incomes.get(position));
    }

    @Override
    public int getItemCount() {
        return incomes.size();
    }

    public class mitraView extends RecyclerView.ViewHolder {
        TextView nama_mitra;
        ImageView imageView;
        public mitraView(@NonNull View itemView) {
            super(itemView);
            nama_mitra = itemView.findViewById(R.id.mitra_name);
            imageView  = itemView.findViewById(R.id.imgSelect);

        }

        void bin(final Mitra mitra){
            if (checkedPosition == -1){
                imageView.setVisibility(View.GONE);
            }else{
                if (checkedPosition == getAdapterPosition()){
                    imageView.setVisibility(View.VISIBLE);
//                    nama_mitra.setBackgroundResource(R.drawable.bg_receive);
                }else{
                    imageView.setVisibility(View.GONE);
                }
            }

            nama_mitra.setText(mitra.getNama_usaha());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    imageView.setVisibility(View.VISIBLE);
//                    nama_mitra.setBackgroundResource(R.drawable.bg_receive);
                    if (checkedPosition !=getAdapterPosition()){
                        notifyItemChanged(checkedPosition);
                        checkedPosition = getAdapterPosition();
                    }
                }
            });

        }

    }


        public Mitra getSelected(){
                if (checkedPosition != -1){
                    return incomes.get(checkedPosition);
                }
                return null;
        }



}


