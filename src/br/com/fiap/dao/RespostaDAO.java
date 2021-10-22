package br.com.fiap.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import br.com.fiap.connection.ConnectionFactory;
import br.com.fiap.model.Resposta;

/**
 * Classe que grava e consulta dados relacionados a pergunta no banco de dados.
 * @author Giulio Cesar
 *
 */
public class RespostaDAO {
	
	/**
	 * Insere dados de uma resposta no banco de dados.
	 * @param Objeto do tipo Resposta
	 * @throws SQLException
	 */
	public void insert (Resposta resposta) throws SQLException {			
		Connection conexao = new ConnectionFactory().getConnection();
		PreparedStatement stmt = conexao.prepareStatement(
				"INSERT INTO T_APL_RESPOSTA (id_resposta, nr_resposta, ds_resposta) VALUES (?, ?, ?)");
		try {
			stmt.setInt(1,resposta.getId());
			stmt.setInt(2,resposta.getNrResposta());
			stmt.setString(3,resposta.getDsResposta());
		}catch(NullPointerException e){
	        System.out.print("NullPointerException caught");
	    }
		
		try {
			stmt.execute();
			System.out.println("Insert executado");
		}catch(java.sql.SQLIntegrityConstraintViolationException e) {
			System.out.println("Você tentou inserir uma resposta que já existe");
		}
		
		try {
			stmt.close();
			conexao.close();
		}catch(SQLException ex){
	        ex.printStackTrace();
	    }
	}
	
	/**
	 * Atualiza um registro de resposta no banco de dados.
	 * @param Objeto do tipo Resposta
	 * @throws SQLException
	 */
	public void update (Resposta resposta) throws SQLException {
		Connection conexao = new ConnectionFactory().getConnection();
		PreparedStatement stmt = conexao.prepareStatement(
				"UPDATE  T_APL_RESPOSTA SET nr_resposta=?, ds_resposta=? where id_resposta=?");
		
		try {
			stmt.setInt(1, resposta.getNrResposta());
			stmt.setString(2, resposta.getDsResposta());
			stmt.setInt(3, resposta.getId());
		}catch(NullPointerException e){
	        System.out.print("NullPointerException caught");
	    }
		
		try {
			stmt.execute();			
			System.out.println("Update executado");
		}catch(java.sql.SQLIntegrityConstraintViolationException e) {
			System.out.println("Você tentou atualizar uma resposta que não existe");
		}
		try {
			stmt.close();
			conexao.close();
		}catch(SQLException ex){
	        ex.printStackTrace();
	    }
	}
	
	/**
	 * Deleta um registro de resposta no banco de dados.
	 * @param Objeto do tipo Resposta
	 * @throws SQLException
	 */
	public void delete (Resposta resposta) throws SQLException {
		Connection conexao = new ConnectionFactory().getConnection();
		PreparedStatement stmt = conexao.prepareStatement(
				"DELETE FROM T_APL_RESPOSTA WHERE id_resposta=?");
		try {
			stmt.setInt(1, resposta.getId());
		}catch(NullPointerException e){
	        System.out.print("NullPointerException caught");
	    }
		
		try {
			stmt.execute();
			System.out.println("Delete executado");
		}catch(java.sql.SQLIntegrityConstraintViolationException e) {
			System.out.println("Você tentou deletar uma resposta que não existe");
		}
		
		try {
			stmt.close();
			conexao.close();
		}catch(SQLException ex){
	        ex.printStackTrace();
	    }
	}

	/**
	 * Lista todos os registros de resposta no banco de dados.
	 * @return Lista com todas as respostas no banco de dados.
	 * @throws SQLException
	 */
	public ArrayList<Resposta> getRespostas() throws SQLException{
		ArrayList<Resposta> lista = new ArrayList<>();
		
		Connection conexao = new ConnectionFactory().getConnection();
		
		PreparedStatement stmt = conexao.prepareStatement(
				"SELECT id_respostas, nr_respostas, ds_respostas FROM T_APL_RESPOSTAS order by id_respostas");
		try {
			stmt.execute();
		}catch(java.sql.SQLIntegrityConstraintViolationException e) {
			System.out.println("Houve um erro ao ler os dados. Verifique se há dados");
		}
		ResultSet rs = stmt.getResultSet();
		
		while(rs.next()) {
			Resposta resposta = new Resposta();
			
			resposta.setId(rs.getInt("id_respostas"));
			resposta.setNrResposta(rs.getInt("nr_respostas"));
			resposta.setDsResposta(rs.getString("ds_respostas"));			
			lista.add(resposta);
		}
		
		System.out.println(lista);	
		
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