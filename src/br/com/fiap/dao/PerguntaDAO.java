package br.com.fiap.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import br.com.fiap.connection.ConnectionFactory;
import br.com.fiap.model.Pergunta;

/**
 * Classe que grava e consulta dados relacionados a pergunta no banco de dados.
 * @author Giulio Cesar
 *
 */
public class PerguntaDAO {
	
	/**
	 * Insere dados de uma pergunta no banco de dados.
	 * @param Objeto do tipo Pergunta
	 * @throws SQLException
	 */
	public void insert (Pergunta pergunta) throws SQLException {
		
		Connection conexao = new ConnectionFactory().getConnection();
		
		PreparedStatement stmt = conexao.prepareStatement(
				"INSERT INTO T_APL_PERGUNTAS (id_perguntas, id_respostas, id_quiz, nr_perguntas, ds_perguntas) VALUES (?, ?, ?, ?, ?)");
		try {
			stmt.setInt(1,pergunta.getId());
			stmt.setInt(2,pergunta.getResposta().getId());
			stmt.setInt(3, pergunta.getQuiz().getId());		
			stmt.setInt(4, pergunta.getNrPergunta());
			stmt.setString(5, pergunta.getDsPergunta());
		}catch(NullPointerException e){
	        System.out.print("NullPointerException");
	    }
		
		try {
			stmt.execute();
			System.out.println("Insert executado");
		}catch(java.sql.SQLIntegrityConstraintViolationException e) {
			System.out.println("Você tentou inserir um valor que já existe no banco, verifique o ID e o texto da pergunta");
		}
		
		try {
			stmt.close();
			conexao.close();
		}catch(SQLException ex){
			ex.printStackTrace();
		}
	}
	
	/**
	 * Atualiza um registro de pergunta no banco de dados
	 * @param Objeto do tipo Pergunta
	 * @throws SQLException
	 */
	public void update (Pergunta pergunta) throws SQLException {
		Connection conexao = new ConnectionFactory().getConnection();
		PreparedStatement stmt = conexao.prepareStatement(
				"UPDATE T_APL_PERGUNTAS SET nr_perguntas=?, ds_perguntas=? WHERE id_perguntas=?");

		try {
			stmt.setInt(1, pergunta.getNrPergunta());
			stmt.setString(2, pergunta.getDsPergunta());
			stmt.setInt(3, pergunta.getId());
		}catch(NullPointerException e){
	        System.out.print("NullPointerException caught");
	    }	
		
		try {
			stmt.execute();
			System.out.println("Update executado");
		}catch(java.sql.SQLIntegrityConstraintViolationException e) {
			System.out.println("Houve um erro a atualizar a pergunta. Lembre-se, para atualizar é necessário que o ID exista no banco e que perguntas não devem se repetir");
		}
		try {
			stmt.close();
			conexao.close();
		}catch(SQLException ex){
            ex.printStackTrace();
        }
	}
	
	/**
	 * Deleta um resitro de pergunta do banco de dados.
	 * @param pergunta
	 * @throws SQLException
	 */
	public void delete (Pergunta pergunta) throws SQLException {
		Connection conexao = new ConnectionFactory().getConnection();
		PreparedStatement stmt = conexao.prepareStatement(
				"DELETE FROM T_APL_PERGUNTAS WHERE id_perguntas=?");
		try {
			stmt.setInt(1, pergunta.getId());
		}catch(NullPointerException e) {
			System.out.println("NullPointerException caught");
		}
		
		try {
			stmt.execute();
			System.out.println("Delete executado");			
		}catch(java.sql.SQLIntegrityConstraintViolationException e) {
			System.out.println("Houve um erro ao deletar a pergunta, veifique se o ID da pergunta existe no banco de dados");
		}
		
		try {
			stmt.close();
			conexao.close();
		}catch(SQLException ex){
            ex.printStackTrace();
        }
	}
		
	/**
	 * Lista todos os registros de pergunta no banco de dados.
	 * @return Lista com todas as perguntas no banco de dados.
	 * @throws SQLException
	 */
	public ArrayList<Pergunta> getPerguntas() throws SQLException{
		ArrayList<Pergunta> lista = new ArrayList<>();
		
		Connection conexao = new ConnectionFactory().getConnection();
		
		PreparedStatement stmt = conexao.prepareStatement(
				"SELECT id_perguntas, nr_perguntas, ds_perguntas FROM T_APL_PERGUNTAS order by id_perguntas");
		
		try {
			stmt.execute();
		}catch(java.sql.SQLIntegrityConstraintViolationException e) {
			System.out.println("Houve um erro na leitura, se existem dados no banco");
		}
		
		ResultSet rs = stmt.getResultSet();
		
		while(rs.next()) {
			Pergunta pergunta = new Pergunta();
			pergunta.setId(rs.getInt("id_perguntas"));							
			pergunta.setNrPergunta(rs.getInt("nr_perguntas"));
			pergunta.setDsPergunta(rs.getString("ds_perguntas"));		
			lista.add(pergunta);
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