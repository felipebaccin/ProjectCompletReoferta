package br.univel.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;

import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import org.jboss.logging.Logger;

public class Conexao {

	private static EntityManagerFactory fabrica;

	static {
		fabrica = Persistence.createEntityManagerFactory("java2trab4bim");
	}

	public static EntityManager getEntityManager() {
		return fabrica.createEntityManager();
	}

	public static void fechar() {
		fabrica.close();
	}

	private static final String DRIVER = "org.postgres.jdbc.Driver";
	private static final String URL = "jdbc:postgres://localhost:8400/projectSistemaReoferta";
	private static final String SENHA = "postgres";
	private static final String USUARIO = "postgres";

	public static Connection conectar() {
		try {

			String url = URL;
			String usuario = USUARIO;
			String senha = SENHA;

			Class.forName("org.postgresql.Driver");

			Connection con = null;

			con = DriverManager.getConnection(url, usuario, senha);

			System.out.println("Conexão realizada com sucesso");

			DriverManager.registerDriver(new org.postgresql.Driver());

			return con;

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e1) {
			Logger.getLogger(Conexao.class.getName()).log(null, null,
					Level.SEVERE, null, e1);
			;
		}
		return null;
	}

}
