package daniellopes.treinamento.trabalhoartigodaves.Model;

public class UsuarioCadastro extends Usuario {
    private String senha;
    private String login;
    private String matricula;

    public UsuarioCadastro(String email, int id, String nome, String senha, String login, String matricula) {
        super(email, id, nome);
        this.senha = senha;
        this.login = login;
        this.matricula = matricula;
    }

    public UsuarioCadastro() {
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
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
}
