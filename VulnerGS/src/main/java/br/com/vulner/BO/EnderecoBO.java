package br.com.vulner.BO;

import br.com.vulner.DAO.EnderecoDAO;
import br.com.vulner.beans.Endereco;

import java.sql.SQLException;
import java.util.ArrayList;

public class EnderecoBO {
    private EnderecoDAO enderecoDAO;

    public ArrayList<Endereco> selecionarBo() throws SQLException, ClassNotFoundException {
        enderecoDAO = new EnderecoDAO();
        return enderecoDAO.readEnderecos();
    }

    public void inserirBo(Endereco endereco) throws SQLException, ClassNotFoundException {
        enderecoDAO = new EnderecoDAO();

        if (!validarCep(endereco.getCep())) {
            throw new RuntimeException("CEP Inválido: deve ter 8 dígitos.");
        } else {
            enderecoDAO.insertEndereco(endereco);
        }
    }

    public void atualizarBo(Endereco endereco) throws SQLException, ClassNotFoundException {
        enderecoDAO = new EnderecoDAO();

        if (!validarCep(endereco.getCep())) {
            throw new RuntimeException("CEP Inválido: deve ter 8 dígitos.");
        } else {
            enderecoDAO.updateEndereco(endereco);
        }
    }

    public void deletarBo(int id) throws SQLException, ClassNotFoundException {
        enderecoDAO = new EnderecoDAO();
        enderecoDAO.deleteEndereco(id);
    }

    // Validador de CEP
    private boolean validarCep(String cep) {
        return cep != null && cep.matches("\\d{8}");
    }
}
