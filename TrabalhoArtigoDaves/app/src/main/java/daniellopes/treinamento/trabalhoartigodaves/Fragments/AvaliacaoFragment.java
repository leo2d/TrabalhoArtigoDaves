package daniellopes.treinamento.trabalhoartigodaves.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import daniellopes.treinamento.trabalhoartigodaves.Model.Artigo;
import daniellopes.treinamento.trabalhoartigodaves.Model.Avaliacao;
import daniellopes.treinamento.trabalhoartigodaves.R;
import daniellopes.treinamento.trabalhoartigodaves.Service.Avaliacao.AvaliacaoService;
import daniellopes.treinamento.trabalhoartigodaves.Service.Usuario.LoginUsuarioService;
import daniellopes.treinamento.trabalhoartigodaves.Util.TokenUtil;

/**
 * A simple {@link Fragment} subclass.
 */
public class AvaliacaoFragment extends Fragment {

    private TextView nomeArtigoAvaliacao;
    private EditText comentarioAvaliacao;
    private Button btnEviarAvaliacao;
    Spinner spinnerNota;

    private Artigo artigo;
    private int nota;
    private String statusEvento;

    public AvaliacaoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_avaliacao, container, false);

        artigo = (Artigo) getArguments().getSerializable("artigo");
        statusEvento = (String) getArguments().getSerializable("statusEvento");


        bind(view);

        preencherCamposTela();

        preencherSpinnerNota();
        gerenciarBotaoEviar();

        tratarEventos();
        return view;
    }

    private void tratarEventos() {
        spinnerNota.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                nota = (int) spinnerNota.getSelectedItem();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }
        });
    }

    private void gerenciarBotaoEviar() {

        btnEviarAvaliacao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                enviarAvaliacao(nota, comentarioAvaliacao.getText().toString());

            }
        });

    }

    private void enviarAvaliacao(int nota, String comentario) {
        try {

            Avaliacao ava = new Avaliacao();
            ava.setComentario(comentario);
            ava.setNota(nota);

            Gson gson = new Gson();
            String content = gson.toJson(ava);

            String idArtigo = artigo.getId() + "";

            String resp = new AvaliacaoService()
                    .execute(TokenUtil.getToken(), idArtigo, content)
                    .get();

            voltarPraDetalheArtigo();

        } catch (Exception e) {
            System.out.println("deu ruim" + e.getMessage());
        }
    }

    private void voltarPraDetalheArtigo() {
        try {
            DetalheArtigoFragment detalheFragment = new DetalheArtigoFragment();
            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

            Bundle bundle = new Bundle();
            bundle.putSerializable("artigo", artigo);
            bundle.putSerializable("statusEvento", statusEvento);

            detalheFragment.setArguments(bundle);
            fragmentTransaction.replace(R.id.frameContainer, detalheFragment);
            fragmentTransaction.commit();
        } catch (Exception ex) {
            System.out.println("deu ruim" + ex.getMessage());
        }
    }

    private void preencherCamposTela() {
        nomeArtigoAvaliacao.setText(artigo.getNome());
    }

    private void bind(View view) {
        this.nomeArtigoAvaliacao = view.findViewById(R.id.nomeArtigoAvaliacao);
        this.spinnerNota = view.findViewById(R.id.spinnerNota);
        this.btnEviarAvaliacao = view.findViewById(R.id.btnConfirmarAvaliacao);
        this.comentarioAvaliacao = view.findViewById(R.id.comentarioAvaliacao);
    }

    private void preencherSpinnerNota() {

        final Integer[] quantidades = {1, 2, 3, 4, 5};//, 6, 7, 8, 9, 10};
        ArrayAdapter<Integer> adapter = new ArrayAdapter<Integer>(getActivity(), R.layout.spinner_item, quantidades);
        spinnerNota.setAdapter(adapter);
    }

}
