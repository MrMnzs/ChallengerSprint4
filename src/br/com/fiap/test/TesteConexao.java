package br.com.fiap.test;

import java.sql.Connection;
import java.sql.SQLException;

import br.com.fiap.connection.ConnectionFactory;

public class TesteConexao {

	public static void main(String[] args) throws SQLException {
		try {
			@SuppressWarnings("unused")
			Connection conexao = new ConnectionFactory().getConnection();
			
			System.out.println("Conex�o aberta");
		}catch (SQLException e1) {
		    System.out.println("N�o foi poss�vel acessar o banco de dados. Verifique suas credenciais. ERRO: " + e1.getMessage());
		    }
	}

}
