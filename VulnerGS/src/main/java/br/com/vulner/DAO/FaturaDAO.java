package br.com.vulner.DAO;

import br.com.vulner.beans.Cliente;
import br.com.vulner.beans.Fatura;
import br.com.vulner.beans.PessoaCliente;
import br.com.vulner.beans.EmpresaCliente;
import br.com.vulner.conexao.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class FaturaDAO {
    public Connection faturaConexao;

    public FaturaDAO() throws SQLException, ClassNotFoundException {
        this.faturaConexao = new ConnectionFactory().conexao();
    }

    public void insertFatura(Fatura fatura) throws SQLException {
        String sql = "INSERT INTO Fatura (valor, dtEmissao, pessoaClienteId, empresaClienteId) VALUES (?, TO_DATE(?, 'YYYY/MM/DD'), ?, ?)";
        PreparedStatement ps = faturaConexao.prepareStatement(sql);

        ps.setDouble(1, fatura.getValor());
        ps.setString(2, fatura.getDtEmissao());

        // Verifica o tipo de destinatário e define o ID correto
        if (fatura.getDestinatario() instanceof PessoaCliente) {
            ps.setInt(3, ((PessoaCliente) fatura.getDestinatario()).getPessoaClienteId());
            ps.setNull(4, java.sql.Types.INTEGER);
        } else if (fatura.getDestinatario() instanceof EmpresaCliente) {
            ps.setNull(3, java.sql.Types.INTEGER);
            ps.setInt(4, ((EmpresaCliente) fatura.getDestinatario()).getEmpresaClienteId());
        }

        ps.execute();
        ps.close();
    }

    public void deleteFatura(int id) throws SQLException {
        String sql = "DELETE FROM Fatura WHERE faturaId = ?";
        PreparedStatement ps = faturaConexao.prepareStatement(sql);

        ps.setInt(1, id);
        ps.execute();
        ps.close();
    }

    public void updateFatura(Fatura fatura) throws SQLException {
        String sql = "UPDATE Fatura SET valor = ?, dtEmissao = TO_DATE(?, 'YYYY-MM-DD'), pessoaClienteId = ?, empresaClienteId = ? WHERE faturaId = ?";
        PreparedStatement ps = faturaConexao.prepareStatement(sql);

        ps.setDouble(1, fatura.getValor());
        ps.setString(2, fatura.getDtEmissao());

        // Verifica o tipo de destinatário e define o ID correto
        if (fatura.getDestinatario() instanceof PessoaCliente) {
            ps.setInt(3, ((PessoaCliente) fatura.getDestinatario()).getPessoaClienteId());
            ps.setNull(4, java.sql.Types.INTEGER);
        } else if (fatura.getDestinatario() instanceof EmpresaCliente) {
            ps.setNull(3, java.sql.Types.INTEGER);
            ps.setInt(4, ((EmpresaCliente) fatura.getDestinatario()).getEmpresaClienteId());
        }

        ps.setInt(5, fatura.getFaturaId());
        ps.execute();
        ps.close();
    }

    public ArrayList<Fatura> readFatura() throws SQLException {
        String sql = "SELECT * FROM Fatura";
        PreparedStatement ps = faturaConexao.prepareStatement(sql);
        ArrayList<Fatura> faturas = new ArrayList<>();
        ResultSet result = ps.executeQuery();

        while (result.next()) {
            Fatura fatura = new Fatura();
            fatura.setFaturaId(result.getInt("faturaId"));
            fatura.setValor(result.getDouble("valor"));
            fatura.setDtEmissao(result.getString("dtEmissao"));

            Cliente destinatario = null;

            // Verificar pessoaClienteId
            int pessoaClienteId = result.getInt("pessoaClienteId");
            if (!result.wasNull()) {
                PessoaCliente pessoa = new PessoaCliente();
                pessoa.setPessoaClienteId(pessoaClienteId);
                destinatario = pessoa;
            } else {
                // Verificar empresaClienteId
                int empresaClienteId = result.getInt("empresaClienteId");
                if (!result.wasNull()) {
                    EmpresaCliente empresa = new EmpresaCliente();
                    empresa.setEmpresaClienteId(empresaClienteId);
                    destinatario = empresa;
                }
            }

            fatura.setDestinatario(destinatario);
            faturas.add(fatura);
        }

        result.close();
        ps.close();
        return faturas;
    }

}
