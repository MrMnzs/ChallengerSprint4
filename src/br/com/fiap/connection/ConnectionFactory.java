package br.com.fiap.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * This class build a connection to a oracle database by using JDBC
 * It follows the 'Factory' pattern to code reuse
 * @author Giulio Cesar
 *
 */
public class ConnectionFactory {

	/**
	 * Method that returns a Oracle database connection
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
