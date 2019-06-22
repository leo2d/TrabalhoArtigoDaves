package daniellopes.treinamento.trabalhoartigodaves.Model;

import java.io.Serializable;

public class Usuario implements Serializable {

    private String email;
    private int id;
    private String login;
    private String matricula;
    private String nome;
    private String senha;


    public Usuario() {
    }

    public Usuario(String email, int id, String login, String matricula, String nome, String senha) {
        this.email = email;
        this.id = id;
        this.login = login;
        this.matricula = matricula;
        this.nome = nome;
        this.senha = senha;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
