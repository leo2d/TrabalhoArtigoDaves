package daniellopes.treinamento.trabalhoartigodaves.Model;

public class UsuarioLogin extends  Usuario {

    private String token;

    public UsuarioLogin(String email, int id, String nome, String token) {
        super(email, id, nome);
        this.token = token;
    }

    public UsuarioLogin() {
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
