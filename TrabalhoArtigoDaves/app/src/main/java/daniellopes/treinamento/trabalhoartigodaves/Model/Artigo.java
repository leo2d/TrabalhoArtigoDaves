package daniellopes.treinamento.trabalhoartigodaves.Model;

import java.io.Serializable;

public class Artigo implements Serializable {

    private int id;
    private String nome;
    private String resenha;
    private String texto;
    private int numeroAvaliacaoes;
    private double mediaAvaliacaoes;
    private String status;
    private Autor autor;

    public Artigo() {
    }

    public Artigo(int id, String nome, String resenha, String texto, int numeroAvaliacaoes, int mediaAvaliacaoes, String status, Autor autor) {
        this.id = id;
        this.nome = nome;
        this.resenha = resenha;
        this.texto = texto;
        this.numeroAvaliacaoes = numeroAvaliacaoes;
        this.mediaAvaliacaoes = mediaAvaliacaoes;
        this.status = status;
        this.autor = autor;
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

    public String getResenha() {
        return resenha;
    }

    public void setResenha(String resenha) {
        this.resenha = resenha;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public int getNumeroAvaliacaoes() {
        return numeroAvaliacaoes;
    }

    public void setNumeroAvaliacaoes(int numeroAvaliacaoes) {
        this.numeroAvaliacaoes = numeroAvaliacaoes;
    }

    public double getMediaAvaliacaoes() {
        return mediaAvaliacaoes;
    }

    public void setMediaAvaliacaoes(double mediaAvaliacaoes) {
        this.mediaAvaliacaoes = mediaAvaliacaoes;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String aprovado) {
        this.status = aprovado;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }
}
