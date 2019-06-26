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

import daniellopes.treinamento.trabalhoartigodaves.Contracts.DrawerLocker;
import daniellopes.treinamento.trabalhoartigodaves.R;
import daniellopes.treinamento.trabalhoartigodaves.Util.TokenUtil;

/**
 * A simple {@link Fragment} subclass.
 */
public class AreaDoUsuarioFragment extends Fragment {

    private TextView usuarioLogado;
    private String token, login;
    private int id;
    private Button btnEventos, btnSair, btnMeusArtigos;

    public AreaDoUsuarioFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

 /*       DrawerLocker drawerLocker = (DrawerLocker) getActivity();

        drawerLocker.setDrawerLocked(false);*/

        View view = inflater.inflate(R.layout.fragment_area_do_usuario, container, false);
        bind(view);

/*        token = getArguments().getString("token");
        login = getArguments().getString("usuario");
        id = getArguments().getInt("usuarioId");

        usuarioLogado.setText(login);*/

        token = TokenUtil.getToken();



        btnEventos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EventosFragment eventosFragment = new EventosFragment();
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                Bundle bundle = new Bundle();
                bundle.putSerializable("token", token);

                eventosFragment.setArguments(bundle);
                fragmentTransaction.replace(R.id.frameContainer, eventosFragment);
                fragmentTransaction.commit();

            }
        });

        btnSair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                LoginFragment loginFragment = new LoginFragment();
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                TokenUtil tokenUtil = new TokenUtil("");

                fragmentTransaction.replace(R.id.frameContainer, loginFragment);
                fragmentTransaction.commit();

            }
        });

        return view;
    }

    private void bind(View view) {

        usuarioLogado = view.findViewById(R.id.usuarioLogado);
        btnEventos = view.findViewById(R.id.btnEventos);
        btnSair = view.findViewById(R.id.btnSair);
    }

}
