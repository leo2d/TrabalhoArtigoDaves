package daniellopes.treinamento.trabalhoartigodaves.Adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import daniellopes.treinamento.trabalhoartigodaves.Model.Artigo;
import daniellopes.treinamento.trabalhoartigodaves.R;

public class AdapterArtigos extends BaseAdapter {

    private List<Artigo> Artigos;
    private Context context;
    private boolean eventoFEchado;

    public AdapterArtigos(List<Artigo> Artigos, boolean eventoFechado, Context context) {
        this.Artigos = Artigos;
        this.context = context;
        this.eventoFEchado = eventoFechado;
    }

    @Override
    public int getCount() {
        return Artigos.size();
    }

    @Override
    public Object getItem(int position) {
        return Artigos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return Artigos.get(position).getId();
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {

        View viewArtigos = LayoutInflater
                .from(context)
                .inflate(R.layout.linha_artigo, viewGroup, false);

        Artigo ArtigoSelecionado = Artigos.get(position);

        TextView titulo = viewArtigos.findViewById(R.id.linha_artigo_titulo);
        titulo.setText(ArtigoSelecionado.getNome());

        TextView autor = viewArtigos.findViewById(R.id.linha_artigo_autor);
        autor.setText(ArtigoSelecionado.getAutor().getNome());

        TextView media = viewArtigos.findViewById(R.id.linha_artigo_media);
        media.setText( eventoFEchado ? ArtigoSelecionado.getMediaAvaliacaoes() + "" : "--");

        return viewArtigos;
    }
}
