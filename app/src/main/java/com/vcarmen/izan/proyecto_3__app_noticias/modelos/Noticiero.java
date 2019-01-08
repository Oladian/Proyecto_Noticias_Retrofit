package com.vcarmen.izan.proyecto_3__app_noticias.modelos;

import java.io.Serializable;

public class Noticiero implements Serializable {
    int imagen;

    String nombre, fuente;

    public Noticiero(int imagen, String nombre, String fuente) {
        this.imagen = imagen;
        this.nombre = nombre;
        this.fuente = fuente;

    }

    public int getImagen() {
        return imagen;
    }

    public void setImagen(int imagen) {
        this.imagen = imagen;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFuente() {
        return fuente;
    }

    public void setFuente(String fuente) {
        this.fuente = fuente;
    }

}


