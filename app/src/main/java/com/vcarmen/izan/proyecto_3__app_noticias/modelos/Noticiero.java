package com.vcarmen.izan.proyecto_3__app_noticias.modelos;

public class Noticiero {
    int imagen;
    String nombre;

    public Noticiero(int imagen, String nombre) {
        this.imagen = imagen;
        this.nombre = nombre;
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
}


