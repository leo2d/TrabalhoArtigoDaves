package daniellopes.treinamento.trabalhoartigodaves.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import daniellopes.treinamento.trabalhoartigodaves.Model.Eventos;
import daniellopes.treinamento.trabalhoartigodaves.R;

public class AdapterEventos extends BaseAdapter {

    private List<Eventos> eventos;
    private Context context;

    public AdapterEventos(List<Eventos> eventos, Context context){
        this.eventos = eventos;
        this.context = context;
    }

    @Override
    public int getCount() {
        return eventos.size();
    }

    @Override
    public Object getItem(int position) {
        return eventos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return eventos.get(position).getId();
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {

        View viewEventos = LayoutInflater
                .from(context)
                .inflate(R.layout.linha_eventos, viewGroup, false);

        Eventos eventoSelecionado = eventos.get(position);

        TextView nome = viewEventos.findViewById(R.id.txtNomeEvento);
        nome.setText(eventoSelecionado.getNome());

        TextView dataInicio = viewEventos.findViewById(R.id.txtDataEvento);
        dataInicio.setText(eventoSelecionado.getDataInicioEvento());

        TextView status = viewEventos.findViewById(R.id.txtStatusEvento);
        status.setText(eventoSelecionado.getStatus());

        return viewEventos;
    }
}
