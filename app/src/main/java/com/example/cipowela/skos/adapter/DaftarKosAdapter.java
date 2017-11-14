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
import android.widget.Toast;

import com.example.cipowela.skos.DetailKosActivity;
import com.example.cipowela.skos.R;
import com.example.cipowela.skos.TestModel;

import java.util.List;

/**
 * Created by cipowela on 14/11/17.
 */

public class DaftarKosAdapter extends RecyclerView.Adapter<DaftarKosAdapter.DKAHolder> {
    private Context context;
    private List<TestModel> testModels;

    public DaftarKosAdapter(Context context, List<TestModel> testModels) {
        this.context = context;
        this.testModels = testModels;
    }

    @Override
    public DKAHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_daftar_kos, parent, false);
        return new DKAHolder(v);
    }

    @Override
    public void onBindViewHolder(DKAHolder holder, int position) {
        TestModel test = testModels.get(position);

        holder.imageView.setImageResource(test.getGambar());
        holder.nama.setText(test.getNama());
        holder.tipe.setText(test.getTipe());
        holder.harga.setText(test.getHarga());
        holder.lihat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, DetailKosActivity.class));
            }
        });
    }

    @Override
    public int getItemCount() {
        return testModels.size();
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
