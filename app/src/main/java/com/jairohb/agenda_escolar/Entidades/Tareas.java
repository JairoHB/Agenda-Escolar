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
    private String id;

    public Tareas(String id, String estado, String tarea, String materia, String fecha) {
        this.estado = estado;
        this.tarea = tarea;
        this.materia = materia;
        this.fecha = fecha;
        this.id = id;
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
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getid() {
        return id;
    }

    public void setid(String id) {
        this.id = id;
    }

}

