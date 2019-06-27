package daniellopes.treinamento.trabalhoartigodaves.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import daniellopes.treinamento.trabalhoartigodaves.Contracts.DrawerLocker;
import daniellopes.treinamento.trabalhoartigodaves.Model.Artigo;
import daniellopes.treinamento.trabalhoartigodaves.Model.Usuario;
import daniellopes.treinamento.trabalhoartigodaves.Model.UsuarioLogin;
import daniellopes.treinamento.trabalhoartigodaves.R;
import daniellopes.treinamento.trabalhoartigodaves.Service.Usuario.LoginUsuarioService;
import daniellopes.treinamento.trabalhoartigodaves.Util.TokenUtil;
import daniellopes.treinamento.trabalhoartigodaves.Util.UsuarioUtils;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment {

    private EditText campologin, campoSenha;
    private Button cadastrarse, logar;
    private String token, resp = "";

    private DrawerLocker drawerLocker;


    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        drawerLocker = (DrawerLocker) getActivity();

        drawerLocker.setDrawerLocked(true);

        View view = inflater.inflate(R.layout.fragment_login, container, false);

        bind(view);

        logar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                try {

                    resp = new LoginUsuarioService().execute(campologin.getText().toString(),
                            campoSenha.getText().toString()).get();
                    token = resp.substring(resp.indexOf("token") + 8, resp.indexOf("}") - 1);

                    Type type = new TypeToken<UsuarioLogin>() {
                    }.getType();

                    Gson gson = new Gson();

                    UsuarioLogin result = gson.fromJson(resp, type);


                    TokenUtil tokenUtil = new TokenUtil(token);
                    UsuarioUtils usuarioUtils = new UsuarioUtils(result);

                } catch (Exception e) {
                    resp = e.getMessage();
                }

//                token = "123";
//                Usuario c = new Usuario();
//                c.setId(1);
//                c.setEmail("");
//                c.setNome("jao");
//                c.setLogin("jao");
//                c.setMatricula("123");
//                c.setSenha("123");


                if (token != null) {
                    Usuario c = new Gson().fromJson(resp.toString(), Usuario.class);
                    System.out.println(c);

                    AreaDoUsuarioFragment areaDoUsuarioFragment = new AreaDoUsuarioFragment();

                    FragmentManager fragmentManager = getFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                    Bundle bundle = new Bundle();
                    bundle.putSerializable("token", token);
                    bundle.putSerializable("usuario", campologin.getText().toString());
                    bundle.putSerializable("usuarioId", c.getId());

                    drawerLocker.setDrawerLocked(false);

                    areaDoUsuarioFragment.setArguments(bundle);
                    fragmentTransaction.replace(R.id.frameContainer, areaDoUsuarioFragment);
                    fragmentTransaction.commit();

                    Toast.makeText(getActivity(), "Login efetuado com Sucesso", Toast.LENGTH_LONG).show();

                } else {
                    Toast.makeText(getActivity(), "Login ou senha invalidas", Toast.LENGTH_LONG).show();
                }

            }
        });

        cadastrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                CadastroFragment cadastroFragment = new CadastroFragment();
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frameContainer, cadastroFragment);
                fragmentTransaction.commit();
            }
        });


        return view;
    }

    private void bind(View view) {

        campologin = view.findViewById(R.id.campoLogin);
        campoSenha = view.findViewById(R.id.campoSenha);
        logar = view.findViewById(R.id.btnLogar);
        cadastrarse = view.findViewById(R.id.btnCadastre_se);
    }

}
