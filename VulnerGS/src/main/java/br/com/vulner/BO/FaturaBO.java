package br.com.vulner.BO;

import br.com.vulner.DAO.FaturaDAO;
import br.com.vulner.beans.Fatura;

import java.sql.SQLException;
import java.util.ArrayList;

public class FaturaBO {
    private FaturaDAO faturaDAO;

    public ArrayList<Fatura> selecionarBo() throws SQLException, ClassNotFoundException {
        faturaDAO = new FaturaDAO();
        return faturaDAO.readFatura();
    }

    public void inserirBo(Fatura fatura) throws SQLException, ClassNotFoundException {
        faturaDAO = new FaturaDAO();
        faturaDAO.insertFatura(fatura);
    }

    public void atualizarBo(Fatura fatura) throws SQLException, ClassNotFoundException {
        faturaDAO = new FaturaDAO();
        faturaDAO.updateFatura(fatura);
    }

    public void deletarBo(int id) throws SQLException, ClassNotFoundException {
        faturaDAO = new FaturaDAO();
        faturaDAO.deleteFatura(id);
    }
}
