package br.com.fiap.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Classe que constrói uma conexão com um banco de dados oracle usando JDBC
 * Segue padrão de projeto 'Factory' para reutilização de código
 * @author Giulio Cesar
 *
 */
public class ConnectionFactory {

	/**
	 * Retorna uma conexão com um banco de dados Oracle
	 * @return Connection Object
	 * @throws SQLException
	 */
	public Connection getConnection() throws SQLException {
		
		Connection conexao = DriverManager.getConnection(
				"jdbc:oracle:thin:@oracle.fiap.com.br:1521:ORCL",
				"RM88345",
				"060892");
		
		return conexao;
	}	
}