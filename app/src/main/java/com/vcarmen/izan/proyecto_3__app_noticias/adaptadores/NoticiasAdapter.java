package com.vcarmen.izan.proyecto_3__app_noticias.adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;
import com.vcarmen.izan.proyecto_3__app_noticias.R;
import com.vcarmen.izan.proyecto_3__app_noticias.modelos.Noticia;
import com.vcarmen.izan.proyecto_3__app_noticias.modelos.api.VolleySingleton;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by luis on 9/12/17.
 */

public class NoticiasAdapter extends BaseAdapter {

    private Context context;
    private List<Noticia> noticia;

    public NoticiasAdapter(Context context, List<Noticia> noticia) {
        this.context = context;
        this.noticia = noticia;
    }

    @Override
    public int getCount() {
        return noticia.size();
    }

    @Override
    public Object getItem(int i) {
        return noticia.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;

        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.noticias_row_item, viewGroup, false);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

        Noticia noticia = (Noticia) getItem(i);


        viewHolder.titulo.setText(noticia.getTitle());
        viewHolder.autor.setText(noticia.getAuthor());
        viewHolder.fecha.setText(noticia.getFormatPublishedAt());
        viewHolder.imagen.setImageUrl(noticia.getUrlToImage(), VolleySingleton.getInstance(context).getImageLoader());

        return view;
    }

    public static class ViewHolder {
        @BindView(R.id.titulo)
        TextView titulo;
        @BindView(R.id.autor)
        TextView autor;
        @BindView(R.id.fecha)
        TextView fecha;
        @BindView(R.id.imagen)
        NetworkImageView imagen;

        public ViewHolder(View v) {
            ButterKnife.bind(this, v);
        }
    }
}
