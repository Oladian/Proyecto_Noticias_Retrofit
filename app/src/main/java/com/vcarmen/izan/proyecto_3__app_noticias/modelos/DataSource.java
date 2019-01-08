package com.vcarmen.izan.proyecto_3__app_noticias.modelos;

import com.vcarmen.izan.proyecto_3__app_noticias.modelos.Noticia;

import java.util.List;

public interface DataSource {

    public void getNoticias(NoticiasCallback noticiasCallback);
    public void getPelicula(int id, NoticiaCallback noticiaCallback);

    interface NoticiaCallback {
        public void onNoticiaCargada(Noticia noticia);
        public void onNoticiaError();
    }

    interface NoticiasCallback {
        public void onNoticiasCargadas(List<Noticia> noticias);
        public void onNoticiasError();
    }
}