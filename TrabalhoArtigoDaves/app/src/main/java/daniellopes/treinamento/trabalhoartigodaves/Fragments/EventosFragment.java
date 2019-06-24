package daniellopes.treinamento.trabalhoartigodaves.Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import daniellopes.treinamento.trabalhoartigodaves.Adapter.AdapterEventos;
import daniellopes.treinamento.trabalhoartigodaves.Model.Evento;
import daniellopes.treinamento.trabalhoartigodaves.R;
import daniellopes.treinamento.trabalhoartigodaves.Service.Evento.EventoService;

/**
 * A simple {@link Fragment} subclass.
 */
public class EventosFragment extends Fragment {

    private List<Evento> eventos;
    private String token, resp;
    private ListView listaDeEventos;
    Gson json = new Gson();
    AdapterEventos adapterEventos;
    private EventoService eventoService;

    public EventosFragment() {
        // Required empty public constructor
        //eventoService = new EventoService();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_eventos, container, false);
        bind(view);

        if (null == eventoService)
            eventoService = new EventoService();
/*
        try {
            token = getArguments().getString("token");
            resp = new EventoService().execute(token).get();

            Type listaType = new TypeToken<List<Evento>>() {
            }.getType();
            eventos = json.fromJson(resp, listaType);

       //    eventos = eventoService.BuscarTodosEventos();

        } catch (Exception e) {
            resp = e.getMessage();
        }

*/
        eventos = new ArrayList<>();
        eventos.add(new Evento(1, "Feira de quarta", "Feira de legumes", "20/10/2019",
                "20/11/2019", "25/10/2019", "10/11/2019",
                15, 12, 3, 0, "Aberto"));
        eventos.add(new Evento(2, "Feira de quarta", "Feira de ciencia", "20/10/2019",
                "20/11/2019", "25/10/2019", "10/11/2019",
                15, 12, 3, 0, "Aberto"));


        String[] vet = new String[eventos.size()];
        int i = 0;

        for (Evento u : eventos) {

            vet[i] = String.valueOf(u.getNome());
            i++;
        }

        adapterEventos = new AdapterEventos(eventos, getActivity());
        listaDeEventos.setAdapter(adapterEventos);

/*
        listaDeEventos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent detalharVenda = new Intent(getApplicationContext(), DetalharVendaActivity.class);
                detalharVenda.putExtra(CHAVE_VENDA, eventos.get(position));
                startActivityForResult(detalharVenda, TELA_DETALHE_VENDA);
            }
        });
*/

        return view;
    }

    private void bind(View view) {
        listaDeEventos = view.findViewById(R.id.listaEventos);
    }

}
