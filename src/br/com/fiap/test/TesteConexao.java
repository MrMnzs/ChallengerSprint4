package br.com.fiap.test;

import java.sql.Connection;
import java.sql.SQLException;

import br.com.fiap.connection.ConnectionFactory;

public class TesteConexao {

	public static void main(String[] args) throws SQLException {
		
		Connection conexao = new ConnectionFactory().getConnection();
		
		System.out.println("Conexão aberta");

	}

}
