package br.com.fiap.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import br.com.fiap.connection.ConnectionFactory;
import br.com.fiap.model.ProgressoUsuario;
import br.com.fiap.model.Usuario;

/**
 * Classe responsável por gravar e consultar dados relacionados a ProgressoUsuario no banco de dados
 * @author Giulio Cesar
 */
public class ProgressoUsuarioDAO {
	
	/**
	 * Método para inserir o progresso do usuário no banco de dados
	 * @param progresso
	 * @throws SQLException
	 */
	public void insert (ProgressoUsuario progresso) throws SQLException {			
		Connection conexao = new ConnectionFactory().getConnection();
		PreparedStatement stmt = conexao.prepareStatement(
				"INSERT INTO T_APL_PROGRESSO (id_progresso, id_usuario, vl_sintoma, ds_sintoma) VALUES (?, ?, ?, ?)");
		try {
			stmt.setInt(1,progresso.getId());
			stmt.setInt(2,progresso.getUsuario().getId());
			stmt.setInt(3,progresso.getVlSintoma());
			stmt.setString(4,progresso.getDsSintoma());
			
		}catch(NullPointerException e){
	        System.out.print("NullPointerException caught");
	    }
		
		try {
			stmt.execute();
			System.out.println("Insert executado");
		}catch(java.sql.SQLIntegrityConstraintViolationException e) {
			System.out.println("Você tentou inserir um valor que já existe no banco, verifique o ID e o texto da pergunta");
		}
		stmt.close();
		conexao.close();
	}

	/**
	 * Método para atualizar algum registro de Progresso já existente no banco de dados, aceitando que sejam atualizados um ou mais atributos de uma única vez
	 * @param progresso
	 * @throws SQLException
	 */
	public void update (ProgressoUsuario progresso) throws SQLException {
		Connection conexao = new ConnectionFactory().getConnection();
		PreparedStatement stmt = conexao.prepareStatement(
				"UPDATE  T_APL_PROGRESSO SET id_usuario=?, vl_sintoma=?, ds_sintoma=? where id_progresso=?");
		
		stmt.setInt(1,progresso.getUsuario().getId());
		stmt.setInt(2,progresso.getVlSintoma());
		stmt.setString(3,progresso.getDsSintoma());
		stmt.setInt(4,progresso.getId());
		
		stmt.execute();
		
		System.out.println("Update executado");

		stmt.close();
		conexao.close();
	}

	/**
	 * Método para deletar algum registro já existente de Progresso do usuário
	 * @param progresso
	 * @throws SQLException
	 */
	public void delete (ProgressoUsuario progresso) throws SQLException {
		Connection conexao = new ConnectionFactory().getConnection();
		PreparedStatement stmt = conexao.prepareStatement(
				"DELETE FROM T_APL_PROGRESSO WHERE id_progresso=?");
		
		stmt.setInt(1, progresso.getId());
		stmt.execute();
		
		System.out.println("Delete executado");
		stmt.close();
		conexao.close();
	}
	
	/**
	 * Método que lista todos os Progressos da tabela 'T_APL_PROGRESSO'
	 * @return Lista contendo todos os progressos de todos os usuários
	 * @throws SQLException
	 */
	public ArrayList<ProgressoUsuario> getProgressos() throws SQLException{
		ArrayList<ProgressoUsuario> lista = new ArrayList<>();
		
		Connection conexao = new ConnectionFactory().getConnection();
		
		PreparedStatement stmt = conexao.prepareStatement(
				"SELECT id_progresso, id_usuario, vl_sintoma, ds_email FROM T_APL_PROGRESSO order by id_progresso");
		
		stmt.execute();
		
		ResultSet rs = stmt.getResultSet();
		
		while(rs.next()) {
			ProgressoUsuario progresso = new ProgressoUsuario();
			Usuario u = new Usuario();
			u.setId(rs.getInt("id_usuario"));
			
			progresso.setId(rs.getInt("id_progresso"));
			progresso.setUsuario(u);
			progresso.setVlSintoma(rs.getInt("vl_sintoma"));
			progresso.setDsSintoma(rs.getString("ds_email"));

			lista.add(progresso);
		}
		
		rs.close();
		stmt.close();
		conexao.close();
		
		return lista;
	}
}
