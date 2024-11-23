package br.com.vulner.beans;

public class Fatura {
    private int faturaId;
    private double valor;
    private String dtEmissao;
    private Cliente destinatario;

    // Construtor Vazio
    public Fatura() {
    }

    // Construtor Cheio
    public Fatura(double valor, String dtEmissao, Cliente destinatario) {
        this.valor = valor;
        this.dtEmissao = dtEmissao;
        this.destinatario = destinatario;
    }

    // Getters e Setters
    public int getFaturaId() {
        return faturaId;
    }

    public void setFaturaId(int faturaId) {
        this.faturaId = faturaId;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getDtEmissao() {
        return dtEmissao;
    }

    public void setDtEmissao(String dtEmissao) {
        this.dtEmissao = dtEmissao;
    }

    public Cliente getDestinatario() {
        return destinatario;
    }

    public void setDestinatario(Cliente destinatario) {
        this.destinatario = destinatario;
    }
}
