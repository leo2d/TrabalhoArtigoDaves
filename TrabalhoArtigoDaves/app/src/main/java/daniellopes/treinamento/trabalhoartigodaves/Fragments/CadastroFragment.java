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

import daniellopes.treinamento.trabalhoartigodaves.R;
import daniellopes.treinamento.trabalhoartigodaves.Service.CadastroUsuarioService;

/**
 * A simple {@link Fragment} subclass.
 */
public class CadastroFragment extends Fragment {

    private EditText caEmail, caLogin, caMatricula, caNome, caSenha;
    private Button cadastrarUsuario;
    private String resp = "";

    public CadastroFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_cadastro, container, false);
        bind(view);


        cadastrarUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = caEmail.getText().toString();
                String login = caLogin.getText().toString();
                String matricula = caMatricula.getText().toString();
                String nome = caNome.getText().toString();
                String senha = caSenha.getText().toString();

                try {

                    if (!email.equals("") && !login.equals("") && !matricula.equals("") &&
                            !nome.equals("") && !senha.equals("")) {

                        resp = new CadastroUsuarioService().execute(email, login, matricula, nome, senha).get();
                        LoginFragment loginFragment = new LoginFragment();
                        FragmentManager fragmentManager = getFragmentManager();
                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.replace(R.id.frameContainer, loginFragment);
                        fragmentTransaction.commit();

                        Toast.makeText(getActivity(), "Cadastrado com Sucesso", Toast.LENGTH_SHORT).show();

                    } else {
                        Toast.makeText(getActivity(), "Preencha todos os campos", Toast.LENGTH_SHORT).show();
                    }

                } catch (Exception e) {
                    resp = e.getMessage();
                    Toast.makeText(getActivity(), "Erro ao cadastrar", Toast.LENGTH_SHORT).show();
                }


            }
        });

        return view;

    }

    private void bind(View view) {
        caLogin = view.findViewById(R.id.campoLoginCadastro);
        caSenha = view.findViewById(R.id.campoSenhaCadastro);
        caMatricula = view.findViewById(R.id.campoMatriculaCadastro);
        caNome = view.findViewById(R.id.campoNomeCadastro);
        cadastrarUsuario = view.findViewById(R.id.btnCadastrarUsuario);
        caEmail = view.findViewById(R.id.campoEmailCadastro);
    }

}
