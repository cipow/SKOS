package com.example.cipowela.skos.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.cipowela.skos.KamarModel;
import com.example.cipowela.skos.R;
import com.example.cipowela.skos.subactivity.KamarDetailActivity;

import java.util.List;

/**
 * Created by cipowela on 03/01/18.
 */

public class KamarAdapter extends RecyclerView.Adapter<KamarAdapter.Holder> {
    private Context context;
    private List<KamarModel> kamarModels;

    public KamarAdapter(Context context, List<KamarModel> kamarModels) {
        this.context = context;
        this.kamarModels = kamarModels;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.list_daftar_kamar, parent, false
        );

        return new Holder(v);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        final KamarModel kamar = kamarModels.get(position);
        Glide.with(context).load(kamar.getGambar())
                .thumbnail(0.5f)
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.cover);
        String[] text = kamar.getTipe().split("-");
        holder.jenis.setText(text[0]);
        holder.tipe.setText(text[1]);
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, KamarDetailActivity.class);
                intent.putExtra("data", kamar.getObject());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return kamarModels.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        public FrameLayout layout;
        public ImageView cover;
        public TextView jenis, tipe;

        public Holder(View itemView) {
            super(itemView);
            layout = itemView.findViewById(R.id.list_kamar);
            cover = itemView.findViewById(R.id.cover_kamar);
            jenis = itemView.findViewById(R.id.jenis_kamar);
            tipe = itemView.findViewById(R.id.tipe_kamar);
        }
    }
}
