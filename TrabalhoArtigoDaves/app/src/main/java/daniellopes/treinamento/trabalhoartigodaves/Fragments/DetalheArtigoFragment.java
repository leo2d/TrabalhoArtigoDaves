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

import java.util.List;

import daniellopes.treinamento.trabalhoartigodaves.Model.Artigo;
import daniellopes.treinamento.trabalhoartigodaves.Model.Avaliacao;
import daniellopes.treinamento.trabalhoartigodaves.R;
import daniellopes.treinamento.trabalhoartigodaves.Service.Avaliacao.BuscarAvaliacoesArtigoService;
import daniellopes.treinamento.trabalhoartigodaves.Util.SituacaoEvento;
import daniellopes.treinamento.trabalhoartigodaves.Util.TokenUtil;
import daniellopes.treinamento.trabalhoartigodaves.Util.UsuarioUtils;

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
    private String statusEvento;
    private boolean usuarioTemArtigoNoEvento;

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
        statusEvento = (String) getArguments().getSerializable("statusEvento");
        usuarioTemArtigoNoEvento = (boolean) getArguments().getSerializable("usuarioTemArtigoNoEvento");

        preencherCamposArtigo();
        gerenciarExibicaoBotatAvaliar(artigo, statusEvento);
        gerenciarBotaoAvaliar();


        return view;
    }

    private void gerenciarExibicaoBotatAvaliar(Artigo artigo, String statusEvento) {

        boolean exibirBotao = SituacaoEvento.eventoAberto(statusEvento)
                && artigo.getStatus().toLowerCase().equals("aprovado")
                && !artigo.artigoPertenceAusuarioLogado(UsuarioUtils.getUsuarioLogado().getId())
                && !verificarUsuarioAvaliouArtigo()
                & !usuarioTemArtigoNoEvento;


        avaliarArtigo.setEnabled(exibirBotao);
        avaliarArtigo.setVisibility(exibirBotao ? View.VISIBLE : View.INVISIBLE);
    }


    private boolean verificarUsuarioAvaliouArtigo() {

        List<Avaliacao> avaliacoesArtigo = buscarAvaliacoes(artigo.getId());

        if (null != avaliacoesArtigo && (avaliacoesArtigo.size() > 0)) {
            for (Avaliacao ava : avaliacoesArtigo) {
                if (ava.getUser().getId() == UsuarioUtils.getUsuarioLogado().getId()) {
                    return true;
                }
            }
        }

        return false;
    }

    private List<Avaliacao> buscarAvaliacoes(int id) {
        try {
            String idArtigo = artigo.getId() + "";
            List<Avaliacao> avaliacoesArtigo =
                    new BuscarAvaliacoesArtigoService()
                            .execute(TokenUtil.getToken(), idArtigo)
                            .get();

            return avaliacoesArtigo;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }


    private void preencherCamposArtigo() {
        nomeArtigo.setText(artigo.getNome());
        resenhaArtigo.setText(artigo.getResenha());

        String nomeAutor = null != artigo.getAutor() ? artigo.getAutor().getNome() : "";
        autorArtigo.setText(nomeAutor);
        statusArtigo.setText(artigo.getStatus());

        textoArtigo.setText(artigo.getTexto());
        numeroAvaliacoesArtigo.setText(SituacaoEvento.eventoFechado(statusEvento)
                ? artigo.getNumeroAvaliacaoes() + "" : "--");
        mediaAvaliacaoesArtigo.setText(SituacaoEvento.eventoFechado(statusEvento)
                ? artigo.getMediaAvaliacaoes() + "" : "--");

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
                bundle.putSerializable("statusEvento", statusEvento);

                avaliacaoFragment.setArguments(bundle);
                fragmentTransaction.replace(R.id.frameContainer, avaliacaoFragment);
                fragmentTransaction.commit();

            }
        });
    }

}
