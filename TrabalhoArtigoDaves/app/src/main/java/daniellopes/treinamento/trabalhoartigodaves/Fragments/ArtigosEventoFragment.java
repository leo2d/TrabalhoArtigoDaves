package daniellopes.treinamento.trabalhoartigodaves.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import daniellopes.treinamento.trabalhoartigodaves.Adapter.AdapterArtigo;
import daniellopes.treinamento.trabalhoartigodaves.Adapter.AdapterEventos;
import daniellopes.treinamento.trabalhoartigodaves.Model.Artigo;
import daniellopes.treinamento.trabalhoartigodaves.Model.Evento;
import daniellopes.treinamento.trabalhoartigodaves.R;
import daniellopes.treinamento.trabalhoartigodaves.Service.Artigo.ArtigoEventoIdServer;

/**
 * A simple {@link Fragment} subclass.
 */
public class ArtigosEventoFragment extends Fragment {

    private TextView nomeArtigo, resenhaArtigo;
    private ListView listaArtigoEventos;
    private List<Artigo> artigos;
    private String token, resp;
    private Evento evento;
    private int id;
    Gson json = new Gson();
    AdapterArtigo adapterArtigo;


    public ArtigosEventoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_artigos_evento, container, false);
        bind(view);


        evento = (Evento) getArguments().getSerializable("eventos");

        try {

            id = evento.getId();
            token = getArguments().getString("token");
            resp = new ArtigoEventoIdServer().execute(token).get();

            Type listType = new TypeToken<List<Artigo>>() {
            }.getType();
            artigos = json.fromJson(resp, listType);

        } catch (Exception e) {
            resp = e.getMessage();
        }

        String[] vet = new String[artigos.size()];
        int i = 0;

        for (Artigo u : artigos) {

            vet[i] = String.valueOf(u.getNome());
            i++;
        }

        adapterArtigo = new AdapterArtigo(artigos, getActivity());
        listaArtigoEventos.setAdapter(adapterArtigo);

        return view;
    }

    private void bind(View view) {

        listaArtigoEventos = view.findViewById(R.id.listArtigoEvento);

    }


}
