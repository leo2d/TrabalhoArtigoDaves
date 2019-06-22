package daniellopes.treinamento.trabalhoartigodaves.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import daniellopes.treinamento.trabalhoartigodaves.Adapter.AdapterEventos;
import daniellopes.treinamento.trabalhoartigodaves.Model.Eventos;
import daniellopes.treinamento.trabalhoartigodaves.R;
import daniellopes.treinamento.trabalhoartigodaves.Service.EventosService;

/**
 * A simple {@link Fragment} subclass.
 */
public class EventosFragment extends Fragment {

    private List<Eventos> eventos;
    private String token, resp;
    private ListView listaDeEventos;
    Gson json = new Gson();
    AdapterEventos adapterEventos;

    public EventosFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_eventos, container, false);
        bind(view);


        try {
            token = getArguments().getString("token");
            resp = new EventosService().execute(token).get();

            Type listaType = new TypeToken<List<Eventos>>() {
            }.getType();
            eventos = json.fromJson(resp, listaType);
        } catch (Exception e) {
            resp = e.getMessage();
        }

        String[] vet = new String[eventos.size()];
        int i = 0;

        for (Eventos u : eventos) {

            vet[i] = String.valueOf(u.getNome());
            i++;
        }

        adapterEventos = new AdapterEventos(eventos, getActivity());
        listaDeEventos.setAdapter(adapterEventos);

        return view;
    }

    private void bind(View view) {
        listaDeEventos = view.findViewById(R.id.listaEventos);
    }

}
