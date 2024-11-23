package br.com.vulner.DAO;

import br.com.vulner.beans.Endereco;
import br.com.vulner.conexao.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EnderecoDAO {
    public Connection enderecoConexao;

    public EnderecoDAO() throws SQLException, ClassNotFoundException {
        super();
        this.enderecoConexao = new ConnectionFactory().conexao();
    }

    public void insertEndereco(Endereco endereco) throws SQLException {
        String sql = "INSERT INTO Endereco (numero, cep, logradouro, bairro, cidade, estado) VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement ps = enderecoConexao.prepareStatement(sql);

        ps.setInt(1, endereco.getNumero());
        ps.setString(2, endereco.getCep());
        ps.setString(3, endereco.getLogradouro());
        ps.setString(4, endereco.getBairro());
        ps.setString(5, endereco.getCidade());
        ps.setString(6, endereco.getEstado());

        ps.execute();
        ps.close();
    }

    public void deleteEndereco(int enderecoId) throws SQLException {
        String sql = "DELETE FROM Endereco WHERE enderecoId = ?";
        PreparedStatement ps = enderecoConexao.prepareStatement(sql);

        ps.setInt(1, enderecoId);
        ps.execute();
        ps.close();
    }

    public void updateEndereco(Endereco endereco) throws SQLException {
        String sql = "UPDATE Endereco SET numero = ?, cep = ?, logradouro = ?, bairro = ?, cidade = ?, estado = ? WHERE enderecoId = ?";
        PreparedStatement ps = enderecoConexao.prepareStatement(sql);

        ps.setInt(1, endereco.getNumero());
        ps.setString(2, endereco.getCep());
        ps.setString(3, endereco.getLogradouro());
        ps.setString(4, endereco.getBairro());
        ps.setString(5, endereco.getCidade());
        ps.setString(6, endereco.getEstado());
        ps.setInt(7, endereco.getEnderecoId());

        ps.execute();
        ps.close();
    }

    public ArrayList<Endereco> readEnderecos() throws SQLException {
        String sql = "SELECT * FROM Endereco";
        PreparedStatement ps = enderecoConexao.prepareStatement(sql);
        ArrayList<Endereco> enderecos = new ArrayList<>();
        ResultSet result = ps.executeQuery();

        while (result.next()) {
            Endereco endereco = new Endereco();
            endereco.setEnderecoId(result.getInt("enderecoId"));
            endereco.setNumero(result.getInt("numero"));
            endereco.setCep(result.getString("cep"));
            endereco.setLogradouro(result.getString("logradouro"));
            endereco.setBairro(result.getString("bairro"));
            endereco.setCidade(result.getString("cidade"));
            endereco.setEstado(result.getString("estado"));

            enderecos.add(endereco);
        }

        result.close();
        ps.close();
        return enderecos;
    }
}
