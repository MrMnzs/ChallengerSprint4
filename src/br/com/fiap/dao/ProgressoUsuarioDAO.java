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
 * Classe que grava e consulta dados relacionados a Progresso do usuário no banco de dados.
 * @author Giulio Cesar
 */
public class ProgressoUsuarioDAO {
	
	/**
	 * Insere dados de um progresso do usuário no banco de dados
	 * @param Objeto do tipo ProgressoUsuario
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
			System.out.println("Você tentou inserir um valor que já existe no banco, verifique o ID do progresso");
		}
		
		try {
			stmt.close();
			conexao.close();
		}catch(SQLException ex){
            ex.printStackTrace();
        }
	}

	/**
	 *Atualiza um registro de progresso do usuário no banco de dados	 
	 * @param Objeto do tipo ProgressoUsuario
	 * @throws SQLException
	 */
	public void update (ProgressoUsuario progresso) throws SQLException {
		Connection conexao = new ConnectionFactory().getConnection();
		PreparedStatement stmt = conexao.prepareStatement(
				"UPDATE  T_APL_PROGRESSO SET id_usuario=?, vl_sintoma=?, ds_sintoma=? where id_progresso=?");
		try {
			stmt.setInt(1,progresso.getUsuario().getId());
			stmt.setInt(2,progresso.getVlSintoma());
			stmt.setString(3,progresso.getDsSintoma());
			stmt.setInt(4,progresso.getId());
		}catch(NullPointerException e){
	        System.out.print("NullPointerException caught");
	    }
		try {
			stmt.execute();
			System.out.println("Update executado");			
		}catch(java.sql.SQLIntegrityConstraintViolationException e) {
			System.out.println("Você tentou atualizar um valor que não existe");
		}
		
		try {
			stmt.close();
			conexao.close();
		}catch(SQLException ex){
			ex.printStackTrace();
		}
	}

	/**
	 * Deleta um resitro de progresso do usuário do banco de dados
	 * @param Objeto do tipo ProgressoUsuario
	 * @throws SQLException
	 */
	public void delete (ProgressoUsuario progresso) throws SQLException {
		Connection conexao = new ConnectionFactory().getConnection();
		PreparedStatement stmt = conexao.prepareStatement(
				"DELETE FROM T_APL_PROGRESSO WHERE id_progresso=?");
		try {
			stmt.setInt(1, progresso.getId());
		}catch(NullPointerException e){
	        System.out.print("NullPointerException caught");
	    }
		
		try {
			stmt.execute();
			System.out.println("Delete executado");
		}catch(java.sql.SQLIntegrityConstraintViolationException e) {
			System.out.println("Você tentou deletar um valor que não existe");
		}
		
		try {
			stmt.close();
			conexao.close();
		}catch(SQLException ex){
			ex.printStackTrace();
		}
	}
	
	/**
	 * Lista todos os registros de progresso do usuário no banco de dados
	 * @return Lista com todos os progressos dos usuários no banco de dados
	 * @throws SQLException
	 */
	public ArrayList<ProgressoUsuario> getProgressos() throws SQLException{
		ArrayList<ProgressoUsuario> lista = new ArrayList<>();
		
		Connection conexao = new ConnectionFactory().getConnection();
		
		PreparedStatement stmt = conexao.prepareStatement(
				"SELECT id_progresso, id_usuario, vl_sintoma, ds_sintoma FROM T_APL_PROGRESSO order by id_progresso");
		
		try {
			stmt.execute();
		}catch(java.sql.SQLIntegrityConstraintViolationException e) {
			System.out.println("Houve um erro ao ler os dados, verifique se há dados.");
		}
			
		ResultSet rs = stmt.getResultSet();
		
		while(rs.next()) {
			ProgressoUsuario progresso = new ProgressoUsuario();
			Usuario u = new Usuario();
			u.setId(rs.getInt("id_usuario"));
			
			progresso.setId(rs.getInt("id_progresso"));
			progresso.setUsuario(u);
			progresso.setVlSintoma(rs.getInt("vl_sintoma"));
			progresso.setDsSintoma(rs.getString("ds_sintoma"));

			lista.add(progresso);
		}
		
		try {
			rs.close();
			stmt.close();
			conexao.close();
		}catch(SQLException ex){
			ex.printStackTrace();
		}
		
		return lista;
	}
}