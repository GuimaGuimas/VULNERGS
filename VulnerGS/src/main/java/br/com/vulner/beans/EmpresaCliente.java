package br.com.vulner.beans;

public class EmpresaCliente extends Cliente{
    private int empresaClienteId;
    private String nome;
    private String email;
    private String senha;
    private String cnpj;
    private String telefone;
    private int enderecoEnderecoId;

    // Construtor Vazio
    public EmpresaCliente() {
    }

    // Construtor Cheio


    public EmpresaCliente(int empresaClienteId, String nome, String email, String senha, String cnpj, String telefone, int enderecoEnderecoId) {
        this.empresaClienteId = empresaClienteId;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.cnpj = cnpj;
        this.telefone = telefone;
        this.enderecoEnderecoId = enderecoEnderecoId;
    }

    // Getters e Setters
    public int getEmpresaClienteId() {
        return empresaClienteId;
    }

    public void setEmpresaClienteId(int empresaClienteId) {
        this.empresaClienteId = empresaClienteId;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
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
