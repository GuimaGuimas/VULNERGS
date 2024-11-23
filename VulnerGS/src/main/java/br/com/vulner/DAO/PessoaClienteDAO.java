package br.com.vulner.DAO;

import br.com.vulner.beans.PessoaCliente;
import br.com.vulner.conexao.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PessoaClienteDAO {
    public Connection pessoaClienteConexao;

    public PessoaClienteDAO() throws SQLException, ClassNotFoundException {
        this.pessoaClienteConexao = new ConnectionFactory().conexao();
    }

    public void insertPessoaCliente(PessoaCliente pessoaCliente) throws SQLException {
        String sql = "INSERT INTO pessoaCliente (nome, email, senha, cpf, telefone, enderecoEnderecoId)" +
                "VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement ps = pessoaClienteConexao.prepareStatement(sql);

        ps.setString(1, pessoaCliente.getNome());
        ps.setString(2, pessoaCliente.getEmail());
        ps.setString(3, pessoaCliente.getSenha());
        ps.setString(4, pessoaCliente.getCpf());
        ps.setString(5, pessoaCliente.getTelefone());
        ps.setInt(6, pessoaCliente.getEnderecoEnderecoId());

        ps.execute();
        ps.close();
    }

    public void deletePessoaCliente(int id) throws SQLException {
        String sql = "DELETE FROM pessoaCliente WHERE pessoaClienteId = ?";
        PreparedStatement ps = pessoaClienteConexao.prepareStatement(sql);

        ps.setInt(1, id);

        ps.execute();
        ps.close();
    }

    public void updatePessoaCliente(PessoaCliente pessoaCliente) throws SQLException {
        String sql = "UPDATE PessoaCliente SET nome = :1 , email = :2 , senha = :3 , cpf = :4 , telefone = :5, enderecoEnderecoId = :6 WHERE pessoaClienteId = :7";
        PreparedStatement ps = pessoaClienteConexao.prepareStatement(sql);

        ps.setString(1, pessoaCliente.getNome());
        ps.setString(2, pessoaCliente.getEmail());
        ps.setString(3, pessoaCliente.getSenha());
        ps.setString(4, pessoaCliente.getCpf());
        ps.setString(5, pessoaCliente.getTelefone());
        ps.setInt(6, pessoaCliente.getEnderecoEnderecoId());
        ps.setInt(7, pessoaCliente.getPessoaClienteId());

        ps.execute();
        ps.close();
    }

    public ArrayList<PessoaCliente> readPessoaClientes() throws SQLException {
        String sql = "SELECT pessoaClienteId, nome, email, cpf, telefone, enderecoEnderecoId FROM PessoaCliente";
        PreparedStatement ps = pessoaClienteConexao.prepareStatement(sql);
        ArrayList<PessoaCliente> pessoasClientes = new ArrayList<>();
        ResultSet result = ps.executeQuery();

        while (result.next()) {
            PessoaCliente pessoaCliente = new PessoaCliente();
            pessoaCliente.setPessoaClienteId(result.getInt("pessoaClienteId"));
            pessoaCliente.setNome(result.getString("nome"));
            pessoaCliente.setEmail(result.getString("email"));
            pessoaCliente.setCpf(result.getString("cpf"));
            pessoaCliente.setDtNascimento(result.getString("telefone"));
            pessoaCliente.setEnderecoEnderecoId(result.getInt("enderecoEnderecoId"));

            pessoasClientes.add(pessoaCliente);
        }

        result.close();
        ps.close();
        return pessoasClientes;
    }
}
