package br.com.fiap.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Classe que constr�i uma conex�o com um banco de dados oracle usando JDBC
 * Segue padr�o de projeto 'Factory' para reutiliza��o de c�digo
 * @author Giulio Cesar
 *
 */
public class ConnectionFactory {

	/**
	 * Retorna uma conex�o com um banco de dados Oracle
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