package daniellopes.treinamento.trabalhoartigodaves.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import daniellopes.treinamento.trabalhoartigodaves.Model.Artigo;
import daniellopes.treinamento.trabalhoartigodaves.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetalheArtigoFragment extends Fragment {


    private TextView nomeArtigo, resenhaArtigo;
    private TextView textoArtigo, numeroAvaliacoesArtigo;
    private TextView mediaAvaliacaoesArtigo, statusArtigo;
    private TextView autorArtigo;
    private Button avaliarArtigo;
    private String token;
    private String resp;
    private int id;

    private Artigo artigo;

    public DetalheArtigoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_detalhe_artigo, container, false);
        bind(view);

        artigo = (Artigo) getArguments().getSerializable("artigo");

        preencherCamposArtigo();
        gerenciarBotaoAvaliar();

        return view;
    }

    private void preencherCamposArtigo() {
        nomeArtigo.setText(artigo.getNome());
        resenhaArtigo.setText(artigo.getResenha());

        String nomeAutor = null != artigo.getAutor() ? artigo.getAutor().getNome() : "";
        autorArtigo.setText(nomeAutor);
        statusArtigo.setText(artigo.getStatus());

        textoArtigo.setText(artigo.getTexto());
        numeroAvaliacoesArtigo.setText(artigo.getNumeroAvaliacaoes() +"");
        mediaAvaliacaoesArtigo.setText(artigo.getMediaAvaliacaoes() + "");

    }

    private void bind(View view) {

        this.nomeArtigo = view.findViewById(R.id.nomeArtigo);
        this.resenhaArtigo = view.findViewById(R.id.resenhaArtigo);
        this.textoArtigo = view.findViewById(R.id.textoArtigo);
        this.numeroAvaliacoesArtigo = view.findViewById(R.id.numeroAvaliacao);
        this.mediaAvaliacaoesArtigo = view.findViewById(R.id.mediaAvaliacao);
        this.statusArtigo = view.findViewById(R.id.statusArtigo);
        this.autorArtigo = view.findViewById(R.id.nomeAutor);
        this.avaliarArtigo = view.findViewById(R.id.btnAvaliacao);
    }

    private void gerenciarBotaoAvaliar() {
        avaliarArtigo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AvaliacaoFragment avaliacaoFragment = new AvaliacaoFragment();
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                Bundle bundle = new Bundle();
                bundle.putSerializable("artigo", artigo);

                avaliacaoFragment.setArguments(bundle);
                fragmentTransaction.replace(R.id.frameContainer, avaliacaoFragment);
                fragmentTransaction.commit();

            }
        });
    }

}
