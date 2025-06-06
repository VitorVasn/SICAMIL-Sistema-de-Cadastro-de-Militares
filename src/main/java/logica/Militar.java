package logica;

import jakarta.persistence.*;

@Entity
public class Militar {

    @Id
    private String saram; // chave primária

    private String nomeCompleto;
    private String nomeGuerra;
    private String identidadeMilitar;
    private String secao;
    private String turma;

    @Enumerated(EnumType.STRING)
    private Patente patente;

    @Enumerated(EnumType.STRING)
    private Situacao situacao;

    public Militar() {}

    public Militar(String saram, String nomeCompleto, String nomeGuerra, String identidadeMilitar, String secao, String turma, Patente patente, Situacao situacao) {
        this.saram = saram;
        this.nomeCompleto = nomeCompleto;
        this.nomeGuerra = nomeGuerra;
        this.identidadeMilitar = identidadeMilitar;
        this.secao = secao;
        this.turma = turma;
        this.patente = patente;
        this.situacao = situacao;
    }

    // Getters e Setters
    public String getSaram() {
        return saram;
    }

    public void setSaram(String saram) {
        this.saram = saram;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public String getNomeGuerra() {
        return nomeGuerra;
    }

    public void setNomeGuerra(String nomeGuerra) {
        this.nomeGuerra = nomeGuerra;
    }

    public String getIdentidadeMilitar() {
        return identidadeMilitar;
    }

    public void setIdentidadeMilitar(String identidadeMilitar) {
        this.identidadeMilitar = identidadeMilitar;
    }

    public String getSecao() {
        return secao;
    }

    public void setSecao(String secao) {
        this.secao = secao;
    }

    public String getTurma() {
        return turma;
    }

    public void setTurma(String turma) {
        this.turma = turma;
    }

    public Patente getPatente() {
        return patente;
    }

    public void setPatente(Patente patente) {
        this.patente = patente;
    }

    public Situacao getSituacao() {
        return situacao;
    }

    public void setSituacao(Situacao situacao) {
        this.situacao = situacao;
    }
}
