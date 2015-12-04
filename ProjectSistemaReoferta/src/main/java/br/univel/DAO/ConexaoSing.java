package br.univel.DAO;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConexaoSing {

	private static ConexaoSing instancia;
	static Connection con = conectar();
		
	public static synchronized ConexaoSing getInstance() {
		if (instancia == null) {
			instancia = new ConexaoSing();
		}
		return instancia;
	}

	public static Connection conectar() {
		try {

			String url = "jdbc:postgresql://localhost:8400/projectSistemaReoferta";
			String usuario = "postgres";
			String senha = "postgres";

			Class.forName("org.postgresql.Driver");

			

			con = DriverManager.getConnection(url, usuario, senha);
			return con;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	
}
