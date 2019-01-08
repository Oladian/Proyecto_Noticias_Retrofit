package com.vcarmen.izan.proyecto_3__app_noticias.adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.vcarmen.izan.proyecto_3__app_noticias.R;
import com.vcarmen.izan.proyecto_3__app_noticias.modelos.ApiConfig;
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
    private List<Noticia> noticias;

    public NoticiasAdapter(Context context, List<Noticia> noticias) {
        this.context = context;
        this.noticias = noticias;
    }

    @Override
    public int getCount() {
        return noticias.size();
    }

    @Override
    public Object getItem(int i) {
        return noticias.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;

        Noticia noticia = noticias.get(i);

        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.noticias_row_item, viewGroup, false);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

        viewHolder.titulo.setText(noticia.getTitle());
        viewHolder.autor.setText(noticia.getAuthor());
        viewHolder.fecha.setText(noticia.getFormatPublishedAt());

        Glide.with(view).load(noticia.getUrlToImage()).apply(RequestOptions.placeholderOf(R.color.colorPrimary)).into(viewHolder.imagen);

        //viewHolder.imagen.setImageUrl(noticia.getUrlToImage(), VolleySingleton.getInstance(context).getImageLoader());

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
