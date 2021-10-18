package br.com.fiap.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.com.fiap.connection.ConnectionFactory;
import br.com.fiap.model.Usuario;

public class PerguntaDAO {
	public void insert(Usuario usuario) throws SQLException {
		
		Connection conexao = new ConnectionFactory().getConnection();
		
		PreparedStatement stmt = conexao.prepareStatement("INSERT INTO T_APL_USUARIO (id_usuario, nm_usuario, ds_email, ds_senha, ds_genero, ds_estado_civil, ds_estado_uf) VALUES (?, ?, ?, ?, ?, ?, ?)");
		
		stmt.setInt(1,usuario.getId());
		stmt.setString(2,usuario.getNome());
		stmt.setString(3,usuario.getEmail());
		//stmt.setDate(4, Date.valueOf(usuario.getDataNascimento()));
		stmt.setString(4,usuario.getSenha());
		stmt.setString(5,usuario.getGenero());
		stmt.setString(6,usuario.getEstadoCivil());
		stmt.setString(7,usuario.getEstadoUf());
		
		
		stmt.execute();
		System.out.println("Insert executado");
		stmt.close();
		conexao.close();
	}
	
	public void update(Usuario usuario) throws SQLException {
		Connection conexao = new ConnectionFactory().getConnection();
		PreparedStatement stmt = conexao.prepareStatement(
				"UPDATE USUARIO SET ATRIBUTO1=X, ATRIBUTO2=X");
		
		stmt.setInt(0, 0);
		stmt.execute();
		System.out.println("Update executado");

		stmt.close();
		conexao.close();
	}
	
	public void delete (Usuario usuario) throws SQLException {
		Connection conexao = new ConnectionFactory().getConnection();
		PreparedStatement stmt = conexao.prepareStatement(
				"DELETE FROM USUARIO WHERE ID=?");
		stmt.setInt(1, usuario.getId());
		stmt.execute();
		
		System.out.println("Delete executado");
		stmt.close();
		conexao.close();
	}
	
	
	public ArrayList<Usuario> getUsuarios() throws SQLException{
		ArrayList<Usuario> lista = new ArrayList<>();
		
		Connection conexao = new ConnectionFactory().getConnection();
		PreparedStatement stmt = conexao.prepareStatement(
				"SELECT ID_USUARIO FROM T_APL_USUARIO");
		stmt.execute();
		
		ResultSet rs = stmt.getResultSet();
		
		while(rs.next()) {
			Usuario usuario = new Usuario();
			usuario.setId(rs.getInt("id_usuario"));
			//fazer todos os outros
			
			lista.add(usuario);
		}
		
		
		rs.close();
		stmt.close();
		conexao.close();
		
		return lista;
	
}
