package com.example.recyclerview_ws_volley.Adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.recyclerview_ws_volley.Model.Evaluadores;
import com.example.recyclerview_ws_volley.R;
import java.util.List;

public class Adaptador extends RecyclerView.Adapter<Adaptador.ViewHolder> implements View.OnClickListener {

    Context ctx;
    List<Evaluadores> lstEvaluadores;
    private View.OnClickListener listener;

    public Adaptador (Context mCtx, List<Evaluadores> evaluadores){
        ctx = mCtx;
        this.lstEvaluadores = evaluadores;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(ctx);
        View view = inflater.inflate(R.layout.evaluadores_list_layout,parent,false);

        view.setOnClickListener(this);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Evaluadores evaluadores = lstEvaluadores.get(position);

        holder.id.setText(evaluadores.getIdEvaluador());
        holder.nombre.setText(evaluadores.getName());
        holder.area.setText(evaluadores.getArea());
        Glide.with(ctx).load(evaluadores.getUrlPhoto())
                .placeholder(R.drawable.load)
                .error(R.drawable.unknown)
                .into(holder.img);
    }

    @Override
    public int getItemCount() {
        return lstEvaluadores.size();
    }

    public void setOnclickListener(View.OnClickListener listener){
        this.listener = listener;
    }

    @Override
    public void onClick(View v) {
        if(listener != null){
            listener.onClick(v);
        }

    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView id, nombre, area;
        ImageView img;
        public ViewHolder(View itemView) {
            super(itemView);

            id = itemView.findViewById(R.id.txtvidEv);
            nombre = itemView.findViewById(R.id.txtvNombre);
            area = itemView.findViewById(R.id.txtvArea);
            img = itemView.findViewById(R.id.imgEvaluadores);
        }

    }
}
