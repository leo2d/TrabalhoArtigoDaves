package daniellopes.treinamento.trabalhoartigodaves.Model;

import java.io.Serializable;

public class Eventos implements Serializable {

    private int id;
    private String nome;
    private String descricao;
    private String dataInicioEvento;
    private String dataFimEvento;
    private String dataInicioSubmissao;
    private String dataFimSubmissao;
    private int artigosSubmetidos;
    private int artigosAprovados;
    private int artigosReprovados;
    private int artigosNaoAvaliados;
    private String status;

    public Eventos() {
    }

    public Eventos(int id, String nome, String descricao, String dataInicioEvento, String dataFimEvento, String dataInicioSubmissao, String dataFimSubmissao, int artigosSubmetidos, int artigosAprovados, int artigosReprovados, int artigosNaoAvaliados, String status) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.dataInicioEvento = dataInicioEvento;
        this.dataFimEvento = dataFimEvento;
        this.dataInicioSubmissao = dataInicioSubmissao;
        this.dataFimSubmissao = dataFimSubmissao;
        this.artigosSubmetidos = artigosSubmetidos;
        this.artigosAprovados = artigosAprovados;
        this.artigosReprovados = artigosReprovados;
        this.artigosNaoAvaliados = artigosNaoAvaliados;
        this.status = status;
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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getDataInicioEvento() {
        return dataInicioEvento;
    }

    public void setDataInicioEvento(String dataInicioEvento) {
        this.dataInicioEvento = dataInicioEvento;
    }

    public String getDataFimEvento() {
        return dataFimEvento;
    }

    public void setDataFimEvento(String dataFimEvento) {
        this.dataFimEvento = dataFimEvento;
    }

    public String getDataInicioSubmissao() {
        return dataInicioSubmissao;
    }

    public void setDataInicioSubmissao(String dataInicioSubmissao) {
        this.dataInicioSubmissao = dataInicioSubmissao;
    }

    public String getDataFimSubmissao() {
        return dataFimSubmissao;
    }

    public void setDataFimSubmissao(String dataFimSubmissao) {
        this.dataFimSubmissao = dataFimSubmissao;
    }

    public int getArtigosSubmetidos() {
        return artigosSubmetidos;
    }

    public void setArtigosSubmetidos(int artigosSubmetidos) {
        this.artigosSubmetidos = artigosSubmetidos;
    }

    public int getArtigosAprovados() {
        return artigosAprovados;
    }

    public void setArtigosAprovados(int artigosAprovados) {
        this.artigosAprovados = artigosAprovados;
    }

    public int getArtigosReprovados() {
        return artigosReprovados;
    }

    public void setArtigosReprovados(int artigosReprovados) {
        this.artigosReprovados = artigosReprovados;
    }

    public int getArtigosNaoAvaliados() {
        return artigosNaoAvaliados;
    }

    public void setArtigosNaoAvaliados(int artigosNaoAvaliados) {
        this.artigosNaoAvaliados = artigosNaoAvaliados;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
