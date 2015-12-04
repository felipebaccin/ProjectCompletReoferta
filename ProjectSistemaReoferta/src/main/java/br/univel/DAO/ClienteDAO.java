package br.univel.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.spi.DirStateFactory.Result;

import br.univel.entity.Cliente;

public class ClienteDAO {
	
	Connection con = ConexaoSing.con;
	PreparedStatement stmt;
	String sql;

	public List<Cliente> listaClientes() {
		try {
			String sql = "select idCliente,nome,telefone,email,endereco,cidade";
			stmt = con.prepareStatement(sql);
			ResultSet result = stmt.executeQuery();
			List<Cliente> clientes = new ArrayList<Cliente>();
			while (result.next()) {
				Cliente c = new Cliente();
				c.setId(new Integer(result.getInt(1)));
				c.setNome(result.getString(2));
				//c.setGenero(result.getString(3));
				c.setTelefone(result.getString(3));
				c.setEmail(result.getString(4));
				c.setEndereco(result.getString(5));
				c.setCidade(result.getString(6));
				//c.setEstado(result.getString(8));
				
				clientes.add(c);
			}
			result.close();
			stmt.close();
			
			return clientes;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

	public String inserir(Cliente c) {
		try {
			
			sql = "insert into cliente(nome,telefone,endereco,cidade,email) values (?,?,?,?,?);";
			stmt = con.prepareStatement(sql);
			stmt.setString(1, c.getNome());
			stmt.setString(2, c.getTelefone());
			stmt.setString(3, c.getEndereco());
			stmt.setString(4, c.getCidade());
			stmt.setString(5, c.getEmail());
			stmt.executeUpdate();
			stmt.close();
			
			return "Cliente cadatrado com sucesso";
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "Cliente não cadastrado por algum motivo";
		
	}

	public String deletar(Integer id) {
		try {
			con = Conexao.conectar();
			String sql = "delete from cliente where idcliente=?";
			stmt = con.prepareStatement(sql);
			stmt.setInt(1,id.intValue());
			if (stmt.execute()) {
				return "Cliente deletada com sucesso";
			} else {
				return null;
			}
		} catch (SQLException ex) {
			return "NO";
		}
	}

}
