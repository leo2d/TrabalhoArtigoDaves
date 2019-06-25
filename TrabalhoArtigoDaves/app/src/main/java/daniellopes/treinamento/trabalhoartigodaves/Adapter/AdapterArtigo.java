package daniellopes.treinamento.trabalhoartigodaves.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import daniellopes.treinamento.trabalhoartigodaves.Model.Artigo;
import daniellopes.treinamento.trabalhoartigodaves.Model.Evento;
import daniellopes.treinamento.trabalhoartigodaves.R;

public class AdapterArtigo extends BaseAdapter {

    private List<Artigo> artigos;
    private Context context;

    public AdapterArtigo(List<Artigo> artigos, Context context) {
        this.artigos = artigos;
        this.context = context;
    }

    @Override
    public int getCount() {
        return artigos.size();
    }

    @Override
    public Object getItem(int position) {
        return artigos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return artigos.get(position).getId();
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        View viewArtigos = LayoutInflater
                .from(context)
                .inflate(R.layout.linha_artigos, viewGroup, false);

        Artigo artigoSelecionado = artigos.get(position);

        TextView nomeArtigo = viewArtigos.findViewById(R.id.nomeArtigo);
        nomeArtigo.setText(artigoSelecionado.getNome());

        TextView resenhaArtigo = viewArtigos.findViewById(R.id.resenhaArtigo);
        resenhaArtigo.setText(artigoSelecionado.getResenha());

        TextView textoArtigo = viewArtigos.findViewById(R.id.textoArtigo);
        textoArtigo.setText(artigoSelecionado.getResenha());

        return viewArtigos;
    }
}
