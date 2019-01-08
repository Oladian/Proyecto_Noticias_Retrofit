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
import android.widget.Toast;

import com.vcarmen.izan.proyecto_3__app_noticias.R;
import com.vcarmen.izan.proyecto_3__app_noticias.adaptadores.NoticieroAdapter;
import com.vcarmen.izan.proyecto_3__app_noticias.modelos.Noticiero;
import com.vcarmen.izan.proyecto_3__app_noticias.modelos.OnComunicarFragmentos;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FragmentoListaNoticieros extends Fragment {

    @BindView(R.id.lista_noticieros)
    ListView listView;

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

        return inflater.inflate(R.layout.fragmento_noticieros,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        ButterKnife.bind(this,view);
        super.onViewCreated(view, savedInstanceState);

        final List<Noticiero> lista = cargarNoticieros();

        NoticieroAdapter adapter = new NoticieroAdapter(getContext(), lista);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //Toast.makeText(getContext(), lista.get(i).getFuente()+"", Toast.LENGTH_SHORT).show();

                callback.pasarFuente(lista.get(i));
            }
        });

    }

    public List<Noticiero> cargarNoticieros(){

        List<Noticiero> noticieros = new ArrayList<>();

        noticieros.add(new Noticiero(R.drawable.cnnspanish, "CNN ESPAÑA", "cnn-es"));
        noticieros.add(new Noticiero(R.drawable.marca, "MARCA", "marca"));
        noticieros.add(new Noticiero(R.drawable.elmundo, "EL MUNDO","el-mundo"));

        return noticieros;
    }
}
