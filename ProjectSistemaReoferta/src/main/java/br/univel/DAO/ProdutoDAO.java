package br.univel.DAO;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import br.univel.entity.Produto;

public class ProdutoDAO {

	static Connection con = ConexaoSing.con;
	static PreparedStatement stmt;
	String sql;
	
	
//	CREATE TABLE produto(
//			id serial NOT NULL,
//			codigo_barras int,
//			descricao varchar(255),
//			custo real,
//			margem_lucro real,
//			unidade varchar(255),
//			categoria varchar(255)
//			)
	

	public static  List<Produto> listaprodutos() {
		try {
			String sql = "select id,codigo_barras,descricao,custo,margem_lucro,unidade,categoria";
			stmt = con.prepareStatement(sql);
			ResultSet result = stmt.executeQuery();
			List<Produto> produtos = new ArrayList<Produto>();
			while (result.next()) {
				Produto p = new Produto();
				p.setId(new Integer(result.getInt(1)));
				p.setCodigoBarras(new Integer(result.getInt(2)));
				//c.setGenero(result.getString(3));
				p.setDescricao(result.getString(3));
				p.setCusto(new BigDecimal(result.getDouble(4)));
				p.setMargemLucro(new BigDecimal(result.getDouble(5)));
			//	p.(result.getString(6));
				//c.setEstado(result.getString(8));
				
				produtos.add(p);
			}
			result.close();
			stmt.close();
			
			return produtos;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

	public String inserir(Produto p) {
		try {
			
			sql = "insert into produto(codigo_barras,descricao,custo,margem_lucro) values (?,?,?,?,?);";
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, p.getCodigoBarras());
			stmt.setString(2, p.getDescricao());
			stmt.setBigDecimal(3, p.getCusto());
			stmt.setBigDecimal(4,p.getMargemLucro());
			//stmt.set(5, p.getEmail());
			stmt.executeUpdate();
			stmt.close();
			
			return "Produto cadatrado com sucesso";
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "Produto não cadastrado por algum motivo";
		
	}

	public String deletar(Integer id) {
		try {
			con = Conexao.conectar();
			String sql = "delete from produto where id=?";
			stmt = con.prepareStatement(sql);
			stmt.setInt(1,id.intValue());
			if (stmt.execute()) {
				return "Produto deletada com sucesso";
			} else {
				return null;
			}
		} catch (SQLException ex) {
			return "NO";
		}
	}

	public static Produto getProduto(String descricao){
		EntityManager em = Conexao.getEntityManager();
		
		try {
			Produto produto =  em.createQuery("select c from Produto c where c.descricao =:descricao", Produto.class).setParameter("descricao", descricao).getSingleResult();
			return produto;
		} catch (NoResultException e) {
			System.out.println(e);
			return null;
		} finally {
            em.close();
        }
		
	   
	}

	
	
}
