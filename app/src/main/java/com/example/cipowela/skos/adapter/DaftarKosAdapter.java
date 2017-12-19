package com.example.cipowela.skos.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.cipowela.skos.subactivity.DetailKosActivity;
import com.example.cipowela.skos.R;
import com.example.cipowela.skos.KamarModel;

import java.util.List;

/**
 * Created by cipowela on 14/11/17.
 */

public class DaftarKosAdapter extends RecyclerView.Adapter<DaftarKosAdapter.DKAHolder> {
    private Context context;
    private List<KamarModel> kamarModels;

    public DaftarKosAdapter(Context context, List<KamarModel> kamarModels) {
        this.context = context;
        this.kamarModels = kamarModels;
    }

    @Override
    public DKAHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_daftar_kos, parent, false);
        return new DKAHolder(v);
    }

    @Override
    public void onBindViewHolder(DKAHolder holder, int position) {
        final KamarModel test = kamarModels.get(position);
        Glide.with(context).load(test.getGambar())
                .thumbnail(0.5f)
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.imageView);
        holder.nama.setText(test.getNama());
        holder.tipe.setText(test.getTipe());
        holder.harga.setText(test.getHarga());
        holder.lihat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailKosActivity.class);
                intent.putExtra("data", test.getObject());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return kamarModels.size();
    }

    public class DKAHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView nama, tipe, harga;
        Button lihat;

        public DKAHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.list_gambar);
            nama = (TextView) itemView.findViewById(R.id.list_nama_kos);
            tipe = (TextView) itemView.findViewById(R.id.list_tipe_kos_kamar);
            harga = (TextView) itemView.findViewById(R.id.list_harga_sisa);
            lihat = (Button) itemView.findViewById(R.id.list_lihat);
        }
    }
}
