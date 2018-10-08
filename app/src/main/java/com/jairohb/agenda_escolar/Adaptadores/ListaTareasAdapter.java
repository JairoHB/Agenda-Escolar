package com.jairohb.agenda_escolar.Adaptadores;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import com.jairohb.agenda_escolar.R;
import com.jairohb.agenda_escolar.Entidades.Tareas;

/**
 * Created by CHENAO on 8/07/2017.
 */

public class ListaTareasAdapter extends RecyclerView.Adapter<ListaTareasAdapter.TareasViewHolder> {

    ArrayList<Tareas> listaTareas;

    public ListaTareasAdapter(ArrayList<Tareas> listaTareas) {
        this.listaTareas = listaTareas;
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
        //holder.estado.setText(listaTareas.get(position).getEstado().toString());
        holder.fecha.setText(listaTareas.get(position).getFecha().toString());
    }

    @Override
    public int getItemCount() {
        return listaTareas.size();
    }

    public class TareasViewHolder extends RecyclerView.ViewHolder {

        TextView tarea,materia,estado, fecha;

        public TareasViewHolder(View itemView) {
            super(itemView);
            tarea = (TextView) itemView.findViewById(R.id.texttarea);
            materia = (TextView) itemView.findViewById(R.id.textmateria);
            fecha = (TextView) itemView.findViewById(R.id.textfecha);
            //estado = (TextView) itemView.findViewById(R.id.textTelefono);
        }
    }
}

