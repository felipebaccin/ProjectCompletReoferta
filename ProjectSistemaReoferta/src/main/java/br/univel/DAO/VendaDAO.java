package br.univel.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;

import br.univel.entity.Cliente;
import br.univel.utils.TransacaoVenda;

public class VendaDAO {
	
	static Connection con = ConexaoSing.con;
	static PreparedStatement stmt;
	String sql;


	public String inserir(Cliente c) {
		try {
			
			sql = "insert into venda(nome,telefone,endereco,cidade,email) values (?,?,?,?,?);";
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
	
	public static void excluir(int idTransacao) {
        EntityManager em = Conexao.getEntityManager();
        try {
            em.getTransaction().begin();
            em.remove(em.find(TransacaoVenda.class, idTransacao));
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }
	
	public static Cliente localizar(int id) {
        EntityManager em = Conexao.getEntityManager();
        try {
            return em.find(Cliente.class, id);
        } catch (Exception e) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
		return null;
    }
	
	public static List<TransacaoVenda> listaTransacoes(){
		EntityManager em = Conexao.getEntityManager();
        try {
            return em.createQuery( "from TransacaoVenda", TransacaoVenda.class ).getResultList();
        } catch (Exception e) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
		return null;
	}
}
