package com.vcarmen.izan.proyecto_3__app_noticias.Fragmentos;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.vcarmen.izan.proyecto_3__app_noticias.R;
import com.vcarmen.izan.proyecto_3__app_noticias.modelos.Noticia;


import butterknife.BindView;
import butterknife.ButterKnife;

public class FragmentoDetalleNoticia extends Fragment {
    public static final String EXTRA_NOTICIA = "EXTRA_NOTICIA";

    @BindView(R.id.detalletitulo)
    TextView titulo;
    @BindView(R.id.detalleAutor)
    TextView autor;
    @BindView(R.id.detalleDescripcion)
    TextView descripcion;
    @BindView(R.id.detalleFecha)
    TextView fecha;
    @BindView(R.id.detalleImagen)
    ImageView imagen;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.noticias_detalle, container, false);
        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Noticia noticia = (Noticia) getArguments().getSerializable("NOTICIA");
        setNoticia(noticia, view);
    }

    private void setNoticia(Noticia noticia, View view) {
        if (noticia != null) {
            titulo.setText(noticia.getTitle());
            autor.setText(noticia.getAuthor());
            descripcion.setText(noticia.getDescription());
            fecha.setText(noticia.getFormatPublishedAt());

            Glide.with(view)
                    .load(noticia.getUrlToImage())
                    .apply(RequestOptions.placeholderOf(R.color.colorPrimary))
                    .into(imagen);
        }
    }

}
