package br.com.vulner.beans;

import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "@type")
public class PessoaCliente extends Cliente {
    private int pessoaClienteId;
    private String nome;
    private String email;
    private String senha;
    private String cpf;
    private String telefone;
    private int enderecoEnderecoId;

    // Construtor Vazio
    public PessoaCliente() {
    }

    // Construtor Cheio
    public PessoaCliente(String nome, String email, String senha, String cpf, String telefone, int enderecoEnderecoId) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.cpf = cpf;
        this.telefone = telefone;
        this.enderecoEnderecoId = enderecoEnderecoId;
    }

    // Getters e Setters
    public int getPessoaClienteId() {
        return pessoaClienteId;
    }

    public void setPessoaClienteId(int pessoaClienteId) {
        this.pessoaClienteId = pessoaClienteId;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public int getEnderecoEnderecoId() {
        return enderecoEnderecoId;
    }

    public void setEnderecoEnderecoId(int enderecoEnderecoId) {
        this.enderecoEnderecoId = enderecoEnderecoId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
