package com.vcarmen.izan.proyecto_3__app_noticias;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.vcarmen.izan.proyecto_3__app_noticias.adaptadores.NoticieroAdapter;
import com.vcarmen.izan.proyecto_3__app_noticias.modelos.Noticiero;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FragmentoListaNoticieros extends Fragment {

    @BindView(R.id.lista_noticieros)
    ListView listView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragmento_noticieros,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        ButterKnife.bind(this,view);
        super.onViewCreated(view, savedInstanceState);

        List<Noticiero> lista = cargarNoticieros();

        NoticieroAdapter adapter = new NoticieroAdapter(getContext(), lista);
        listView.setAdapter(adapter);

    }

    public List<Noticiero> cargarNoticieros(){

        List<Noticiero> noticieros = new ArrayList<>();

        noticieros.add(new Noticiero(R.drawable.cnnspanish, "CNN ESPAÑA"));
        noticieros.add(new Noticiero(R.drawable.marca, "MARCA"));
        noticieros.add(new Noticiero(R.drawable.elmundo, "EL MUNDO"));

        return noticieros;
    }
}
