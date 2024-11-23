package br.com.vulner.BO;

import br.com.vulner.DAO.EmpresaClienteDAO;
import br.com.vulner.beans.EmpresaCliente;

import java.sql.SQLException;
import java.util.ArrayList;

public class EmpresaClienteBO {
    private EmpresaClienteDAO empresaClienteDAO;

    public ArrayList<EmpresaCliente> selecionarBo() throws SQLException, ClassNotFoundException {
        empresaClienteDAO = new EmpresaClienteDAO();
        return empresaClienteDAO.readEmpresaCliente();
    }

    public void inserirBo(EmpresaCliente empresaCliente) throws SQLException, ClassNotFoundException {
        empresaClienteDAO = new EmpresaClienteDAO();

        if (!validarCNPJ(empresaCliente.getCnpj())) {
            throw new RuntimeException("CNPJ Inválido");
        } else {
            empresaClienteDAO.insertEmpresaCliente(empresaCliente);
        }
    }

    public void atualizarBo(EmpresaCliente empresaCliente) throws SQLException, ClassNotFoundException {
        empresaClienteDAO = new EmpresaClienteDAO();

        if (!validarCNPJ(empresaCliente.getCnpj())) {
            throw new RuntimeException("CNPJ Inválido");
        } else {
            empresaClienteDAO.updateEmpresaCliente(empresaCliente);
        }
    }

    public void deletarBo(int id) throws SQLException, ClassNotFoundException {
        empresaClienteDAO = new EmpresaClienteDAO();
        empresaClienteDAO.deleteEmpresaCliente(id);
    }

    public static boolean validarCNPJ(String cnpj) {
        // Remove caracteres não numéricos
        cnpj = cnpj.replaceAll("\\D", "");

        // Verifica se o CNPJ tem 14 dígitos
        if (cnpj.length() != 14) {
            return false;
        }

        // Verifica se todos os dígitos são iguais
        if (cnpj.matches("(\\d)\\1{13}")) {
            return false;
        }

        // Cálculo do primeiro dígito verificador
        int soma = 0;
        int[] pesosPrimeiroDigito = {5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};
        for (int i = 0; i < 12; i++) {
            soma += Character.getNumericValue(cnpj.charAt(i)) * pesosPrimeiroDigito[i];
        }
        int primeiroDigitoVerificador = 11 - (soma % 11);
        if (primeiroDigitoVerificador >= 10) {
            primeiroDigitoVerificador = 0;
        }

        // Verifica o primeiro dígito
        if (primeiroDigitoVerificador != Character.getNumericValue(cnpj.charAt(12))) {
            return false;
        }

        // Cálculo do segundo dígito verificador
        soma = 0;
        int[] pesosSegundoDigito = {6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};
        for (int i = 0; i < 13; i++) {
            soma += Character.getNumericValue(cnpj.charAt(i)) * pesosSegundoDigito[i];
        }
        int segundoDigitoVerificador = 11 - (soma % 11);
        if (segundoDigitoVerificador >= 10) {
            segundoDigitoVerificador = 0;
        }

        // Verifica o segundo dígito
        return segundoDigitoVerificador == Character.getNumericValue(cnpj.charAt(13));
    }
}
