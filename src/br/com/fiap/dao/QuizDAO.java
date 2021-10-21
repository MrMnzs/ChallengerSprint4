package br.com.fiap.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import br.com.fiap.connection.ConnectionFactory;
import br.com.fiap.model.ProgressoUsuario;
import br.com.fiap.model.Quiz;
import br.com.fiap.model.Usuario;

/**
 *Classe que grava e consulta dados relacionados a pergunta no banco de dados.
 * @author Giulio Cesar
 *
 */
public class QuizDAO {

	/**
	 *Insere dados de um quiz no banco de dados.	
	 * @param Objeto do tipo Quiz
	 * @throws SQLException
	 */
	public void insert (Quiz quiz) throws SQLException {
		
		Connection conexao = new ConnectionFactory().getConnection();
		
		PreparedStatement stmt = conexao.prepareStatement(
				"INSERT INTO T_APL_QUIZ (id_quiz, id_usuario, id_progresso, dt_quiz, vl_quiz) VALUES (?, ?, ?, ?, ?)");
		try {
			stmt.setInt(1,quiz.getId());
			stmt.setInt(2,quiz.getUsuario().getId());
			stmt.setInt(3, quiz.getProgresso().getId());		
			stmt.setDate(4, Date.valueOf(quiz.getData()));
			stmt.setInt(5, quiz.getResultado());
		}catch(NullPointerException e){
	        System.out.print("NullPointerException caught");
	    }
		
		try {
			stmt.execute();
			System.out.println("Insert executado");
		}catch(java.sql.SQLIntegrityConstraintViolationException e) {
			System.out.println("Você tentou inserir um valor que já existe no banco, verifique o ID do quiz");
		}
		try {
			stmt.close();
			conexao.close();
		}catch(SQLException ex){
	        ex.printStackTrace();
	    }
	}
	
	/**
	 * Atualiza um registro de Quiz no banco de dados
	 * @param Objeto do tipo Quiz
	 * @throws SQLException
	 */
	public void update (Quiz quiz) throws SQLException {
		Connection conexao = new ConnectionFactory().getConnection();
		PreparedStatement stmt = conexao.prepareStatement(
				"UPDATE T_APL_QUIZ SET id_usuario=?, id_progresso=?, dt_quiz=?, vl_quiz=? WHERE id_quiz=?");
		
		try {
			stmt.setInt(1, quiz.getUsuario().getId());
			stmt.setInt(2, quiz.getProgresso().getId());
			stmt.setDate(3, Date.valueOf(quiz.getData()));
			stmt.setInt(4, quiz.getResultado());
			stmt.setInt(5, quiz.getId());
		}catch(NullPointerException e){
	        System.out.print("NullPointerException caught");
	    }
		
		try {
			stmt.execute();			
			System.out.println("Update executado");
		}catch(java.sql.SQLIntegrityConstraintViolationException e) {
			System.out.println("Você tentou atualizar um valor que não existe no banco, verifique o ID do quiz");
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
	 * @param Objeto do tipo Quiz
	 * @throws SQLException
	 */
	public void delete (Quiz quiz) throws SQLException {
		Connection conexao = new ConnectionFactory().getConnection();
		PreparedStatement stmt = conexao.prepareStatement(
				"DELETE FROM T_APL_QUIZ WHERE id_quiz=?");
		
		try {
			stmt.setInt(1, quiz.getId());
		}catch(NullPointerException e){
	        System.out.print("NullPointerException caught");
	    }
		try {
			stmt.execute();
			System.out.println("Delete executado");
		}catch(java.sql.SQLIntegrityConstraintViolationException e) {
			System.out.println("Você tentou excluir um valor que não existe no banco, verifique o ID do quiz");
		}
		
		try {
			stmt.close();
			conexao.close();
		}catch(SQLException ex){
	        ex.printStackTrace();
	    }
	}
	
	/**
	 * Lista todos os registros de Quiz no banco de dados.
	 * @return Lista com todos os Quiz cadastrados no banco de dados.
	 * @throws SQLException
	 */
	public ArrayList<Quiz> getQuiz() throws SQLException{
		ArrayList<Quiz> lista = new ArrayList<>();
		
		Connection conexao = new ConnectionFactory().getConnection();
		PreparedStatement stmt = conexao.prepareStatement(
				"SELECT id_quiz, id_usuario, id_progresso, dt_quiz, vl_quiz FROM T_APL_QUIZ ORDER BY id_quiz");
		try {
			stmt.execute();			
		}catch(java.sql.SQLIntegrityConstraintViolationException e) {
			System.out.println("Houve um erro ao ler os dados, verifique se há dados.");
		}
		
		ResultSet rs = stmt.getResultSet();
		
		while(rs.next()) {
			
			Quiz quiz = new Quiz();
			quiz.setId(rs.getInt("id_quiz"));
			
			Usuario u = new Usuario();
			u.setId(rs.getInt("id_usuario"));
			quiz.setUsuario(u);
				
			ProgressoUsuario p = new ProgressoUsuario();
			p.setId(rs.getInt("id_progresso"));
			quiz.setProgresso(p);
			
			quiz.setData(rs.getDate(4).toLocalDate());
				
			lista.add(quiz);
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