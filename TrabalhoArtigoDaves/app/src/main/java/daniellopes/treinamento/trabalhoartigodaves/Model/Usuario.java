package daniellopes.treinamento.trabalhoartigodaves.Model;

import java.io.Serializable;

public class Usuario implements Serializable {

    private String email;
    private int id;
    private String nome;

    public Usuario() {
    }

    public Usuario(String email, int id, String nome) {
        this.email = email;
        this.id = id;
        this.nome = nome;
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

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
