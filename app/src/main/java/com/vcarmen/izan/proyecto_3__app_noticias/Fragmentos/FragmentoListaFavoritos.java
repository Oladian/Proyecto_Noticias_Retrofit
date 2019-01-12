package com.vcarmen.izan.proyecto_3__app_noticias.Fragmentos;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.vcarmen.izan.proyecto_3__app_noticias.R;
import com.vcarmen.izan.proyecto_3__app_noticias.adaptadores.NoticiasAdapter;
import com.vcarmen.izan.proyecto_3__app_noticias.modelos.Noticia;
import com.vcarmen.izan.proyecto_3__app_noticias.modelos.OnComunicarFragmentos;
import com.vcarmen.izan.proyecto_3__app_noticias.modelos.Retrofit.NoticasFavoritosRetrofit;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FragmentoListaFavoritos extends Fragment {
    @BindView(R.id.listViewNoticias)
    ListView listView;

    List<Noticia> listaNoticias;
    NoticiasAdapter adapter;

    private OnComunicarFragmentos callback;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnComunicarFragmentos)
            this.callback = (OnComunicarFragmentos) context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragmento_lista_noticias, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this,view);

        NoticasFavoritosRetrofit.getInstance().getNoticias(new NoticasFavoritosRetrofit.CallbackNoticias() {
            @Override
            public void onNoticias(List<Noticia> noticias) {
                listaNoticias = noticias;
                adapter = new NoticiasAdapter(getContext(), noticias);
                listView.setAdapter(adapter);
            }

            @Override
            public void onNoticiasError(String mensajeError) {

            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {

                NoticasFavoritosRetrofit.getInstance().deleteNoticia(listaNoticias.get(i), new NoticasFavoritosRetrofit.CallbackDeleteNoticia() {
                    @Override
                    public void onDeleteNoticia() {

                    }

                    @Override
                    public void onDeleteNoticiaError() {

                    }
                });

                listaNoticias.remove(i);
                adapter.notifyDataSetChanged();
                return false;

            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                callback.pasarNoticia(listaNoticias.get(position));
            }
        });
    }
}
