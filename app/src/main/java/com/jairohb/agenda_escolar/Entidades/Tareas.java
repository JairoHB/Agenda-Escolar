package com.jairohb.agenda_escolar.Entidades;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by CHENAO on 7/05/2017.
 */

public class Tareas implements  Serializable{

    private String estado;
    private String tarea;
    private String materia;
    private String fecha;

    public Tareas(String estado, String tarea, String materia, String fecha) {
        this.estado = estado;
        this.tarea = tarea;
        this.materia = materia;
        this.fecha = fecha;
    }

    public Tareas(){

    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getTarea() {
        return tarea;
    }

    public void setTarea(String tarea) {
        this.tarea = tarea;
    }

    public String getMateria() {
        return materia;
    }

    public void setMateria(String materia) {
        this.materia = materia;
    }

    public String getFecha() {
        return estado;
    }

    public void setFecha(String fecha) {
        this.estado = fecha;
    }

}

