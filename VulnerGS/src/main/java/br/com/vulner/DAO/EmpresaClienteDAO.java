package br.com.vulner.DAO;

import br.com.vulner.beans.EmpresaCliente;
import br.com.vulner.beans.Endereco;
import br.com.vulner.conexao.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EmpresaClienteDAO {
    public Connection empresaClienteConexao;

    public EmpresaClienteDAO() throws SQLException, ClassNotFoundException {
        this.empresaClienteConexao = new ConnectionFactory().conexao();
    }

    public void insertEmpresaCliente(EmpresaCliente empresaCliente) throws SQLException {
        String sql = "INSERT INTO EmpresaCliente (razaoSocial, email, senha, telefone, cnpj, enderecoEnderecoId) VALUES" +
                "(?, ?, ?, ?, ?, ?)";
        PreparedStatement ps = empresaClienteConexao.prepareStatement(sql);

        ps.setString(1, empresaCliente.getNome());
        ps.setString(2, empresaCliente.getEmail());
        ps.setString(3, empresaCliente.getSenha());
        ps.setString(4, empresaCliente.getTelefone());
        ps.setString(5, empresaCliente.getCnpj());
        ps.setInt(6, empresaCliente.getEnderecoEnderecoId());

        ps.execute();
        ps.close();
    }

    public void deleteEmpresaCliente(int id) throws SQLException {
        String sql = "DELETE FROM EmpresaCliente WHERE empresaClienteId = ?";
        PreparedStatement ps = empresaClienteConexao.prepareStatement(sql);

        ps.setInt(1, id);
        ps.execute();
        ps.close();
    }

    public void updateEmpresaCliente(EmpresaCliente empresaCliente) throws SQLException {
        String sql = "UPDATE empresaCliente SET razaoSocial = ?, email = ?, senha = ?, cnpj = ?, telefone = ?, enderecoEnderecoId = ? WHERE empresaClienteId = ?";
        PreparedStatement ps = empresaClienteConexao.prepareStatement(sql);

        ps.setString(1, empresaCliente.getNome());
        ps.setString(2, empresaCliente.getEmail());
        ps.setString(3, empresaCliente.getSenha());
        ps.setString(4, empresaCliente.getCnpj());
        ps.setInt(5, empresaCliente.getTelefone());
        ps.setInt(6, empresaCliente.getEnderecoEnderecoId());
        ps.setInt(7, empresaCliente.getEmpresaClienteId());
        ps.execute();
        ps.close();
    }

    public ArrayList<EmpresaCliente> readEmpresaCliente() throws SQLException {
        String sql = "SELECT empresaClienteId, razaoSocial, email, cnpj, telefone, enderecoEnderecoId FROM empresaCliente";
        PreparedStatement ps = empresaClienteConexao.prepareStatement(sql);
        ArrayList<EmpresaCliente> empresas = new ArrayList<>();
        ResultSet result = ps.executeQuery();

        while (result.next()) {
            EmpresaCliente empresaCliente = new EmpresaCliente();
            empresaCliente.setEmpresaClienteId(result.getInt(1));
            empresaCliente.setNome(result.getString(2));
            empresaCliente.setEmail(result.getString(3));
            empresaCliente.setCnpj(result.getString(4));
            empresaCliente.setTelefone(result.getString(5));
            empresaCliente.setEnderecoEnderecoId(result.getInt("enderecoEnderecoId"));

            empresas.add(empresaCliente);
        }

        result.close();
        ps.close();
        return empresas;
    }
}
