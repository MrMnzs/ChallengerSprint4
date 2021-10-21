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
 * Classe responsável por gravar e consultar dados relacionados a Usuario no banco de dados
 * @author Giulio Cesar
 *
 */
public class UsuarioDAO {
	
	/**
	 * Método utilizado para inserir um usuário no banco de dados
	 * @param usuario
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
			System.out.println("Você tentou inserir um valor que já existe no banco, verifique o ID e o texto da pergunta");
		}
		stmt.close();
		conexao.close();
	}
	
	/**
	 * Método utilizado para atualizar um cadastro já existente de um usuário
	 * @param usuario
	 * @throws SQLException
	 */
	public void update (Usuario usuario) throws SQLException {
		Connection conexao = new ConnectionFactory().getConnection();
		PreparedStatement stmt = conexao.prepareStatement(
				"UPDATE T_APL_USUARIO SET nm_usuario=?, ds_email=?, dt_nascimento=?, ds_senha=?, ds_genero=?, ds_estado_civil=?, ds_estado_uf=? where id_usuario=?");
		
		stmt.setString(1, usuario.getNome());
		stmt.setString(2, usuario.getEmail());
		stmt.setDate(3, Date.valueOf(usuario.getDataNascimento()));
		stmt.setString(4, usuario.getSenha());
		stmt.setString(5, usuario.getGenero());
		stmt.setString(6, usuario.getEstadoCivil());
		stmt.setString(7, usuario.getEstadoUf());
		stmt.setInt(8, usuario.getId());
		
		stmt.execute();
		
		System.out.println("Update executado");

		stmt.close();
		conexao.close();
	}
	
	/**
	 * Método utilizado para deletar um usário existente no banco de dados
	 * @param usuario
	 * @throws SQLException
	 */
	public void delete (Usuario usuario) throws SQLException {
		Connection conexao = new ConnectionFactory().getConnection();
		PreparedStatement stmt = conexao.prepareStatement(
				"DELETE FROM T_APL_USUARIO WHERE id_usuario=?");
		
		stmt.setInt(1, usuario.getId());
		stmt.execute();
		
		System.out.println("Delete executado");
		stmt.close();
		conexao.close();
	}
	
	/**
	 * Método utilizado para listar todos os usuários existentes no banco de dados
	 * @return Objeto Lista contendo todos os dados dos usuários cadastrados no banco de dados
	 * @throws SQLException
	 */
	public ArrayList<Usuario> getUsuarios() throws SQLException{
		ArrayList<Usuario> lista = new ArrayList<>();
		
		Connection conexao = new ConnectionFactory().getConnection();
		
		PreparedStatement stmt = conexao.prepareStatement(
				"SELECT id_usuario, nm_usuario, ds_email, dt_nascimento, ds_senha, ds_genero, ds_estado_civil, ds_estado_uf FROM T_APL_USUARIO order by id_usuario");
		
		stmt.execute();
		
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
		
		System.out.println(lista);
		
		
		rs.close();
		stmt.close();
		conexao.close();
		
		return lista;
	}
}
