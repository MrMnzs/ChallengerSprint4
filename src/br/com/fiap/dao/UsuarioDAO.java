package br.com.fiap.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import br.com.fiap.connection.ConnectionFactory;
import br.com.fiap.model.Usuario;

/**
 * Classe que grava e consulta dados relacionados a Usuario no banco de dados. 
 * * @author Giulio Cesar
 *
 */
public class UsuarioDAO {
	
	/**
	 * Insere dados de um usuário no banco de dados.
	 * @param Objeto do tipo usuário
	 * @throws SQLException
	 */
	public void insert (Usuario usuario) throws SQLException {
				
		Connection conexao = new ConnectionFactory().getConnection();
		
		PreparedStatement stmt = conexao.prepareStatement(
				"INSERT INTO T_APL_USUARIO (id_usuario, nm_usuario, ds_email, dt_nascimento, ds_senha, ds_genero, ds_estado_civil, ds_estado_uf) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
		try {
			stmt.setInt(1,usuario.getId());
			stmt.setString(2,usuario.getNome());
			stmt.setString(3,usuario.getEmail());
			stmt.setDate(4, Date.valueOf(usuario.getDataNascimento()));
			stmt.setString(5,usuario.getSenha());
			stmt.setString(6,usuario.getGenero());
			stmt.setString(7,usuario.getEstadoCivil());
			stmt.setString(8,usuario.getEstadoUf());
		}catch(NullPointerException e){
	        System.out.print("NullPointerException caught");
	    }
		
		try {
			stmt.execute();
			System.out.println("Insert executado");
		}catch(java.sql.SQLIntegrityConstraintViolationException e) {
			System.out.println("Você tentou inserir um valor que já existe no banco, verifique o ID");
		}
		try {
			stmt.close();
			conexao.close();
		}catch(SQLException ex){
	        ex.printStackTrace();
	    }
	}
	
	/**
	 * Atualiza um registro de usuário no banco de dados.
	 * @param Objeto do tipo usuário
	 * @throws SQLException
	 */
	public void update (Usuario usuario) throws SQLException {
		Connection conexao = new ConnectionFactory().getConnection();
		PreparedStatement stmt = conexao.prepareStatement(
				"UPDATE T_APL_USUARIO SET nm_usuario=?, ds_email=?, dt_nascimento=?, ds_senha=?, ds_genero=?, ds_estado_civil=?, ds_estado_uf=? where id_usuario=?");
		try {
			stmt.setString(1, usuario.getNome());
			stmt.setString(2, usuario.getEmail());
			stmt.setDate(3, Date.valueOf(usuario.getDataNascimento()));
			stmt.setString(4, usuario.getSenha());
			stmt.setString(5, usuario.getGenero());
			stmt.setString(6, usuario.getEstadoCivil());
			stmt.setString(7, usuario.getEstadoUf());
			stmt.setInt(8, usuario.getId());
		}catch(NullPointerException e){
	        System.out.print("NullPointerException caught");
	    }
		
		try {
			stmt.execute();			
			System.out.println("Update executado");
		}catch(java.sql.SQLIntegrityConstraintViolationException e) {
			System.out.println("Você tentou atualizar um valor que não existe no banco");
		}

		try {
			stmt.close();
			conexao.close();
		}catch(SQLException ex){
	        ex.printStackTrace();
	    }
	}
	
	/**
	 * Deleta um registro de usuario do banco de dados.
	 * @param Objeto do tipo usuário
	 * @throws SQLException
	 */
	public void delete (Usuario usuario) throws SQLException {
		Connection conexao = new ConnectionFactory().getConnection();
		
		
		PreparedStatement stmt1 = conexao.prepareStatement(
				"DELETE FROM T_APL_QUIZ WHERE id_usuario=?");
				
		PreparedStatement stmt2 = conexao.prepareStatement(
				"DELETE FROM T_APL_USUARIO WHERE id_usuario=?");
		
		try {
			stmt1.setInt(1, usuario.getId());
			stmt2.setInt(1, usuario.getId());
		}catch(NullPointerException e){
	        System.out.print("NullPointerException caught");
	    }
		
		try {
			stmt1.execute();
			stmt2.execute();
			System.out.println("Delete executado");
		}catch(java.sql.SQLIntegrityConstraintViolationException e) {
			System.out.println("Você tentou deletar um valor que não existe no banco");
		}
		
		try {
			stmt1.close();
			stmt2.close();
			conexao.close();
		}catch(SQLException ex){
	        ex.printStackTrace();
	    }
	}
	
	/**
	 * Lista todos os registros de pergunta no banco de dados.
	 * @return Lista com todos os usuários no banco de dados
	 * @throws SQLException
	 */
	public ArrayList<Usuario> getUsuarios() throws SQLException{
		ArrayList<Usuario> lista = new ArrayList<>();
		
		Connection conexao = new ConnectionFactory().getConnection();
		
		PreparedStatement stmt = conexao.prepareStatement(
				"SELECT id_usuario, nm_usuario, ds_email, dt_nascimento, ds_senha, ds_genero, ds_estado_civil, ds_estado_uf FROM T_APL_USUARIO order by id_usuario");
		try {
			stmt.execute();			
		}catch(java.sql.SQLIntegrityConstraintViolationException e) {
			System.out.println("Houve um erro ao listar os dados. Verifique se há dados");
		}
		
		ResultSet rs = stmt.getResultSet();
		
		while(rs.next()) {
			Usuario usuario = new Usuario();
			
			usuario.setId(rs.getInt("id_usuario"));
			usuario.setNome(rs.getString("nm_usuario"));
			usuario.setEmail(rs.getString("ds_email"));
			usuario.setDataNascimento(rs.getDate(4).toLocalDate());
			usuario.setSenha(rs.getString("ds_senha"));
			usuario.setGenero(rs.getString("ds_genero"));
			usuario.setEstadoCivil(rs.getString("ds_estado_civil"));
			usuario.setEstadoUf(rs.getString("ds_estado_uf"));
			
			lista.add(usuario);
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
