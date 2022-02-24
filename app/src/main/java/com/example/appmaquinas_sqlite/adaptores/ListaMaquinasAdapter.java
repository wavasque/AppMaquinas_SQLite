package com.example.appmaquinas_sqlite.adaptores;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appmaquinas_sqlite.R;
import com.example.appmaquinas_sqlite.entidades.EMaquinas;
import com.example.appmaquinas_sqlite.ViewMaquinaActivity;

import java.util.ArrayList;

public class ListaMaquinasAdapter extends RecyclerView.Adapter<ListaMaquinasAdapter.MaquinaViewHolder> {

    ArrayList<EMaquinas> listaMaquinas ;
    private View view;

    public ListaMaquinasAdapter(ArrayList<EMaquinas> listaMaquinas){
        this.listaMaquinas = listaMaquinas;
    }

    @NonNull
    @Override
    public MaquinaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_item_maquinas,null,false);
        return new MaquinaViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull MaquinaViewHolder holder, int position) {

         holder.Viewmaquina.setText("Maq: "+listaMaquinas.get(position).getMaquina());
         holder.Viewubicacion.setText("Ubi: "+listaMaquinas.get(position).getUbicacion());
         holder.Viewdepartamento.setText("Depar: "+listaMaquinas.get(position).getDepartamento());
         holder.Viewfec_ult.setText("Ult_R: "+listaMaquinas.get(position).getUtl_fecha_revisada().toString());
         holder.Viewfec_pro.setText("Prox_R: "+listaMaquinas.get(position).getPro_fecha_revisar().toString());
         holder.Viewstatus.setText("Status: "+listaMaquinas.get(position).getStatus());
         holder.Viewcleinte.setText("Cliente: "+listaMaquinas.get(position).getCliente());

    }

    @Override
    public int getItemCount() {
        return listaMaquinas.size();
    }



    public class MaquinaViewHolder extends RecyclerView.ViewHolder {
        TextView Viewmaquina ,  Viewubicacion , Viewdepartamento , Viewfec_pro, Viewfec_ult,Viewstatus , Viewcleinte ;
        TextView Viewmaquina2 , Viewubicacion2 , Viewdepartamento2 ;
        public MaquinaViewHolder(@NonNull View itemView) {
            super(itemView);

            Viewmaquina = itemView.findViewById(R.id.view_maquina);
            Viewubicacion = itemView.findViewById(R.id.view_ubicacion);
            Viewdepartamento = itemView.findViewById(R.id.view_Departamento);
            Viewfec_ult = itemView.findViewById(R.id.view_fec_ult);
            Viewfec_pro = itemView.findViewById(R.id.view_fec_prox);
            Viewstatus = itemView.findViewById(R.id.view_status);
            Viewcleinte = itemView.findViewById(R.id.view_cliete);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Context context = view.getContext();
                    Intent intent = new Intent(context, ViewMaquinaActivity.class);
                    intent.putExtra("ID", listaMaquinas.get(getAdapterPosition()).getId());
                    context.startActivity(intent);
                }
            });
        }

    }
}


