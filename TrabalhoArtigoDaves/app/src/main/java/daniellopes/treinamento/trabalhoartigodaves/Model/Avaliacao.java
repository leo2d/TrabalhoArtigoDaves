package daniellopes.treinamento.trabalhoartigodaves.Model;

public class Avaliacao {


    //private int id;
    private int nota;
    private String comentario;
    private UsuarioAvaliacao user;

    public Avaliacao() {
    }

    public UsuarioAvaliacao getUser() {
        return user;
    }

    public void setUser(UsuarioAvaliacao user) {
        this.user = user;
    }

    public int getNota() {
        return nota;
    }

    public void setNota(int nota) {
        this.nota = nota;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }
}
