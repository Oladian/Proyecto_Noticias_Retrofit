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
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.github.ivbaranov.mfb.MaterialFavoriteButton;
import com.vcarmen.izan.proyecto_3__app_noticias.R;
import com.vcarmen.izan.proyecto_3__app_noticias.modelos.Noticia;
import com.vcarmen.izan.proyecto_3__app_noticias.modelos.Retrofit.NoticasFavoritosRetrofit;


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
    @BindView(R.id.favoriteButton)
    MaterialFavoriteButton favoriteButton;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.noticias_detalle, container, false);
        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final Noticia noticia = (Noticia) getArguments().getSerializable("NOTICIA");
        setNoticia(noticia, view);

        if (noticia.isFavorito()==true) favoriteButton.setFavorite(true);

        favoriteButton.setOnFavoriteChangeListener(new MaterialFavoriteButton.OnFavoriteChangeListener() {
            @Override
            public void onFavoriteChanged(MaterialFavoriteButton buttonView, boolean favorite) {
                if (favorite==true) {
                    NoticasFavoritosRetrofit.getInstance().postNoticias(noticia, new NoticasFavoritosRetrofit.CallbackNoticia() {
                        @Override
                        public void onPostNoticia() {
                            Toast.makeText(view.getContext(), "Ahora es favorito", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onNoticiaError(String mensajeError) {
                            Toast.makeText(view.getContext(), "ERROR AL GUARDAR", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
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
