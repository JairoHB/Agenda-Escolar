package com.jairohb.agenda_escolar.Adaptadores;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;
import com.jairohb.agenda_escolar.R;
import com.jairohb.agenda_escolar.Entidades.Tareas;
import com.jairohb.agenda_escolar.edit_materia;


public class ListaTareasAdapter extends RecyclerView.Adapter<ListaTareasAdapter.TareasViewHolder> {

    ArrayList<Tareas> listaTareas;
    private Context mContext;


    public ListaTareasAdapter(Context context,ArrayList<Tareas> listaTareas) {
        this.listaTareas = listaTareas;
        mContext = context;
    }

    @Override
    public TareasViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_tarea,null,false);
        return new TareasViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TareasViewHolder holder, int position) {
        holder.tarea.setText(listaTareas.get(position).getTarea().toString());
        holder.materia.setText(listaTareas.get(position).getMateria().toString());
        holder.fecha.setText(listaTareas.get(position).getFecha().toString());
        final String id = listaTareas.get(position).getid().toString();

        if(listaTareas.get(position).getEstado().toString().equals("3")){
            holder.estado.setImageResource(R.drawable.ic_action_sem_verde);
            holder.estado.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, edit_materia.class);
                    intent.putExtra("id", id);
                    mContext.startActivity(intent);
                }
            });
        }
        else if(listaTareas.get(position).getEstado().toString().equals("2")){
            holder.estado.setImageResource(R.drawable.ic_action_sem_amarillo);
            holder.estado.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, edit_materia.class);
                    intent.putExtra("id", id);
                    mContext.startActivity(intent);
                }
            });
        }
        else if(listaTareas.get(position).getEstado().toString().equals("1")){
            holder.estado.setImageResource(R.drawable.ic_action_sem_red);
            holder.estado.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, edit_materia.class);
                    intent.putExtra("id", id);
                    mContext.startActivity(intent);
                }
            });
        }
        else{
            holder.estado.setImageResource(R.drawable.ic_action_sem_red);
            holder.estado.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, edit_materia.class);
                    intent.putExtra("id", id);
                    mContext.startActivity(intent);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return listaTareas.size();
    }

    public class TareasViewHolder extends RecyclerView.ViewHolder {

        TextView tarea,materia,fecha;
        ImageButton estado;

        public TareasViewHolder(View itemView) {
            super(itemView);
            tarea = (TextView) itemView.findViewById(R.id.texttarea);
            materia = (TextView) itemView.findViewById(R.id.textmateria);
            fecha = (TextView) itemView.findViewById(R.id.textfecha);
            estado = (ImageButton) itemView.findViewById(R.id.imageFlecha);
        }
    }
}

