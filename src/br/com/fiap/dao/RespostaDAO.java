package br.com.fiap.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import br.com.fiap.connection.ConnectionFactory;
import br.com.fiap.model.Resposta;

/**
 * Classe responsável por gravar e consultar dados relacionados a Resposta no banco de dados
 * @author Giulio Cesar
 *
 */
public class RespostaDAO {
	
	/**
	 * Método responsável por inserir uma Resposta no banco de dados na tabela Resposta
	 * @param resposta
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
			System.out.println("Você tentou inserir um valor que já existe no banco, verifique o ID e o texto da pergunta");
		}
		stmt.close();
		conexao.close();
	}
	
	/**
	 * Método que atualiza uma resposta já existente no banco de dados.
	 * @param resposta
	 * @throws SQLException
	 */
	public void update (Resposta resposta) throws SQLException {
		Connection conexao = new ConnectionFactory().getConnection();
		PreparedStatement stmt = conexao.prepareStatement(
				"UPDATE  T_APL_RESPOSTA SET nr_resposta=?, ds_resposta=? where id_resposta=?");
		
		stmt.setInt(1, resposta.getNrResposta());
		stmt.setString(2, resposta.getDsResposta());
		stmt.setInt(3, resposta.getId());

		stmt.execute();
		
		System.out.println("Update executado");

		stmt.close();
		conexao.close();
	}
	
	/**
	 * Método que deleda uma pergunta existente no banco de dados referente ao ID passado
	 * @param resposta
	 * @throws SQLException
	 */
	public void delete (Resposta resposta) throws SQLException {
		Connection conexao = new ConnectionFactory().getConnection();
		PreparedStatement stmt = conexao.prepareStatement(
				"DELETE FROM T_APL_RESPOSTA WHERE id_resposta=?");
		
		stmt.setInt(1, resposta.getId());
		stmt.execute();
		
		System.out.println("Delete executado");
		stmt.close();
		conexao.close();
	}

	/**
	 * Método que lista todas as respostas existentes no banco de dados
	 * @return Objeto Lista contendo todas as respostas no banco de dados
	 * @throws SQLException
	 */
	public ArrayList<Resposta> getRespostas() throws SQLException{
		ArrayList<Resposta> lista = new ArrayList<>();
		
		Connection conexao = new ConnectionFactory().getConnection();
		
		PreparedStatement stmt = conexao.prepareStatement(
				"SELECT id_respostas, nr_respostas, ds_respostas FROM T_APL_RESPOSTAS order by id_respostas");
		
		stmt.execute();
		
		ResultSet rs = stmt.getResultSet();
		
		while(rs.next()) {
			Resposta resposta = new Resposta();
			
			resposta.setId(rs.getInt("id_respostas"));
			resposta.setNrResposta(rs.getInt("nr_respostas"));
			resposta.setDsResposta(rs.getString("ds_respostas"));			
			lista.add(resposta);
		}
		
		System.out.println(lista);	
		
		rs.close();
		stmt.close();
		conexao.close();
		
		return lista;
	}
}
