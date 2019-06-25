package daniellopes.treinamento.trabalhoartigodaves.Fragments;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;

import daniellopes.treinamento.trabalhoartigodaves.Model.Evento;
import daniellopes.treinamento.trabalhoartigodaves.R;
import daniellopes.treinamento.trabalhoartigodaves.Service.Evento.EventoServiceId;
import daniellopes.treinamento.trabalhoartigodaves.Util.SituacaoEvento;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetalheEventoFragment extends Fragment {

    private TextView nomeEvento, descricaoEvento;
    private TextView dataInicio, dataFim;
    private TextView dataInicioSubmissao, dataFimSubmissao;
    private TextView statusEvento;
    private Button btnArtigos;
    private String token;
    private String resp;
    private int id;

    private Evento eventos;

    String nome, descricao, dataI, dataF,
            dataIniSub, dataFimSub, status;

    public DetalheEventoFragment() {
        // Required empty public constructor
    }


    @SuppressLint("SetTextI18n")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detalhe_evento, container, false);
        bind(view);

        eventos = (Evento) getArguments().getSerializable("eventos");

        try {
            assert eventos != null;
            id = eventos.getId();
            token = getArguments().getString("token");
            resp = new EventoServiceId().execute(token).get();

        } catch (Exception e) {
            resp = e.getMessage();
        }

        preencheCamposEvento();

        gerenciarBotaoArtigos();

        return view;
    }

    private void gerenciarBotaoArtigos() {
        btnArtigos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ArtigosFragment artigosEventoFragment = new ArtigosFragment();
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                Bundle bundle = new Bundle();
                bundle.putSerializable("idEvento", eventos.getId());

                artigosEventoFragment.setArguments(bundle);
                fragmentTransaction.replace(R.id.frameContainer, artigosEventoFragment);
                fragmentTransaction.commit();

            }
        });
    }

    private void preencheCamposEvento() {
        nome = eventos.getNome();
        nomeEvento.setText(nome + "");

        descricao = eventos.getDescricao();
        descricaoEvento.setText(descricao + "");

        dataI = eventos.getDataInicioEvento();
        dataInicio.setText(dataI + "");

        dataF = eventos.getDataFimEvento();
        dataFim.setText(dataF + "");

        dataIniSub = eventos.getDataInicioSubmissao();
        dataInicioSubmissao.setText(dataIniSub + "");

        dataFimSub = eventos.getDataFimSubmissao();
        dataFimSubmissao.setText(dataFimSub + "");


        status = eventos.getStatus().toLowerCase()
                .equals(SituacaoEvento.EmSubmissao.toLowerCase())
                ? "Em Submissão" : eventos.getStatus();
        statusEvento.setText(status);
    }

    private void bind(View view) {

        this.nomeEvento = view.findViewById(R.id.nomeEvento);
        this.descricaoEvento = view.findViewById(R.id.descricaoEvento);
        this.statusEvento = view.findViewById(R.id.statusEvento);
        this.dataInicio = view.findViewById(R.id.dataInicioEvento);
        this.dataFim = view.findViewById(R.id.dataFimEvento);
        this.dataInicioSubmissao = view.findViewById(R.id.dataInicioSubmissao);
        this.dataFimSubmissao = view.findViewById(R.id.dataFimSubmissa);
        this.btnArtigos = view.findViewById(R.id.artigosbtn);
    }

}
