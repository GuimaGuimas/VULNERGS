package br.com.vulner.BO;

import br.com.vulner.DAO.PessoaClienteDAO;
import br.com.vulner.beans.PessoaCliente;

import java.sql.SQLException;
import java.util.ArrayList;

public class PessoaClienteBO {
    PessoaClienteDAO pessoaClienteDAO;

    public ArrayList<PessoaCliente> selecionarBo() throws SQLException, ClassNotFoundException {
        pessoaClienteDAO = new PessoaClienteDAO();

        return (ArrayList<PessoaCliente>) pessoaClienteDAO.readPessoaClientes();
    }

    public void inserirBo(PessoaCliente pessoaCliente) throws ClassNotFoundException, SQLException {
        pessoaClienteDAO = new PessoaClienteDAO();

        if(!validarCPF(pessoaCliente.getCpf())) {
            throw new RuntimeException("CPF Inválido");
        } else {
            pessoaClienteDAO.insertPessoaCliente(pessoaCliente);
        }
    }

    public void atualizarBo(PessoaCliente pessoaCliente) throws SQLException, ClassNotFoundException {
        pessoaClienteDAO = new PessoaClienteDAO();

        if(!validarCPF(pessoaCliente.getCpf())) {
            throw new RuntimeException("CPF Inválido");
        } else {
            pessoaClienteDAO.updatePessoaCliente(pessoaCliente);
        }
    }

    public void deletarBo(int id) throws SQLException, ClassNotFoundException {
        pessoaClienteDAO = new PessoaClienteDAO();

        pessoaClienteDAO.deletePessoaCliente(id);
    }

    public static boolean validarCPF(String cpf) {
        cpf = cpf.replaceAll("\\D", "");

        if (cpf.length() != 11) {
            return false;
        }

        if (cpf.matches("(\\d)\\1{10}")) {
            return false;
        }

        int primeiroDigito = calcularDigito(cpf.substring(0, 9));

        int segundoDigito = calcularDigito(cpf.substring(0, 9) + primeiroDigito);

        return cpf.equals(cpf.substring(0, 9) + primeiroDigito + segundoDigito);
    }

    private static int calcularDigito(String base) {
        int soma = 0;
        for (int i = 0; i < base.length(); i++) {
            soma += Character.getNumericValue(base.charAt(i)) * (base.length() + 1 - i);
        }
        int resto = soma % 11;
        return (resto < 2) ? 0 : 11 - resto;
    }
}
