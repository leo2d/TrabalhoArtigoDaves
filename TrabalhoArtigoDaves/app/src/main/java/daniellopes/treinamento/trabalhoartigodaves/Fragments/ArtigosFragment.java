package daniellopes.treinamento.trabalhoartigodaves.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
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

import daniellopes.treinamento.trabalhoartigodaves.Adapter.AdapterArtigos;
import daniellopes.treinamento.trabalhoartigodaves.Adapter.AdapterEventos;
import daniellopes.treinamento.trabalhoartigodaves.Model.Artigo;
import daniellopes.treinamento.trabalhoartigodaves.Model.Evento;
import daniellopes.treinamento.trabalhoartigodaves.R;
import daniellopes.treinamento.trabalhoartigodaves.Service.Artigo.ArtigosPorEventoService;
import daniellopes.treinamento.trabalhoartigodaves.Service.Evento.EventoService;
import daniellopes.treinamento.trabalhoartigodaves.Util.SituacaoEvento;
import daniellopes.treinamento.trabalhoartigodaves.Util.TokenUtil;
import daniellopes.treinamento.trabalhoartigodaves.Util.UsuarioUtils;

/**
 * A simple {@link Fragment} subclass.
 */
public class ArtigosFragment extends Fragment {

    List<Artigo> artigos;
    private String token, resp;
    private ListView listaDeArtigos;
    Gson json = new Gson();
    AdapterArtigos adapterArtigos;
    Evento evento;
    boolean usuarioTemArtigoNoEvento;

    public ArtigosFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_artigos, container, false);

        bind(view);

        evento = (Evento) getArguments().getSerializable("evento");
        token = TokenUtil.getToken();

        artigos = buscarArtigos(evento.getId());
        preencherAdaper(artigos, SituacaoEvento.eventoFechado(evento.getStatus()));

        usuarioTemArtigoNoEvento = verificarUsuarioTemArtigoNoEvento(artigos);

        gerenciarArtigoSelecioando();

        return view;
    }

    private boolean verificarUsuarioTemArtigoNoEvento(List<Artigo> artigos) {

        for (Artigo art : artigos) {
            if (art.artigoPertenceAusuarioLogado(UsuarioUtils.getUsuarioLogado().getId())) {
                return true;
            }
        }

        return false;
    }

    private void gerenciarArtigoSelecioando() {

        listaDeArtigos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                DetalheArtigoFragment detalheFragment = new DetalheArtigoFragment();
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                Bundle bundle = new Bundle();
                bundle.putSerializable("artigo", artigos.get(position));
                bundle.putSerializable("statusEvento", evento.getStatus());
                bundle.putSerializable("usuarioTemArtigoNoEvento", usuarioTemArtigoNoEvento);

                detalheFragment.setArguments(bundle);
                fragmentTransaction.replace(R.id.frameContainer, detalheFragment);
                fragmentTransaction.commit();
            }
        });

    }

    private void preencherAdaper(List<Artigo> artigos, boolean eventoFechado) {
        if (null != artigos) {

            String[] vet = new String[artigos.size()];
            int i = 0;

            for (Artigo u : artigos) {

                vet[i] = String.valueOf(u.getNome());
                i++;
            }

            try {
                adapterArtigos = new AdapterArtigos(artigos, eventoFechado, getActivity());
                listaDeArtigos.setAdapter(adapterArtigos);
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    private List<Artigo> buscarArtigos(int idEvento) {
        try {

            String id = idEvento + "";

            List<Artigo> result = new ArtigosPorEventoService()
                    .execute(token, id)
                    .get();

            List<Artigo> artigosAprovados = new ArrayList<>();

            for (Artigo art : result) {
                if (null != art.getStatus() && !art.getStatus().isEmpty()) {
                    if (art.getStatus().toLowerCase().equals("aprovado")) {
                        artigosAprovados.add(art);
                    }
                }
            }

            return artigosAprovados;


        } catch (Exception e) {
            resp = e.getMessage();
            return null;
        }
    }

    private void bind(View view) {
        listaDeArtigos = view.findViewById(R.id.listaDeArtigos);
    }

}
