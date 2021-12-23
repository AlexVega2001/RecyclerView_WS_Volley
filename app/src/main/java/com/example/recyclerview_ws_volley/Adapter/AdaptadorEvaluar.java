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
import com.example.recyclerview_ws_volley.Model.Evaluados;
import com.example.recyclerview_ws_volley.R;

import java.util.List;

public class AdaptadorEvaluar extends RecyclerView.Adapter<AdaptadorEvaluar.ViewHolder> {

    Context ctx;
    List<Evaluados> lstEvaluados;

    public AdaptadorEvaluar (Context mCtx, List<Evaluados> evaluados){
        ctx = mCtx;
        this.lstEvaluados = evaluados;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(ctx);
        View view = inflater.inflate(R.layout.evaluados_list_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Evaluados evaluados = lstEvaluados.get(position);

        holder.cargo.setText(evaluados.getCargo());
        holder.nombre.setText(evaluados.getNombres());
        holder.fechaEvaIn.setText(evaluados.getFechaEvaInicio());
        holder.relaDep.setText(evaluados.getRelacionDep());
        Glide.with(ctx).load(evaluados.getUrl())
                       .placeholder(R.drawable.load)
                       .error(R.drawable.unknown)
                       .into(holder.urlImg);

    }

    @Override
    public int getItemCount() {
        return lstEvaluados.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView nombre, fechaEvaIn, cargo, relaDep;
        ImageView urlImg;
        public ViewHolder(View itemView) {
            super(itemView);

            nombre = (TextView) itemView.findViewById(R.id.txtvNombreEvaluado);
            fechaEvaIn = (TextView) itemView.findViewById(R.id.txtvFechaEvaInicio);
            cargo = (TextView) itemView.findViewById(R.id.txtvCargoEval);
            relaDep = (TextView) itemView.findViewById(R.id.txtvRelDep);
            urlImg = (ImageView) itemView.findViewById(R.id.imgEvaluados);
        }

    }
}
