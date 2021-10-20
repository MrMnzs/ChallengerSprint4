package br.com.fiap.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import br.com.fiap.connection.ConnectionFactory;
import br.com.fiap.model.Pergunta;
import br.com.fiap.model.ProgressoUsuario;
import br.com.fiap.model.Quiz;
import br.com.fiap.model.Resposta;
import br.com.fiap.model.Usuario;

public class QuizDAO {


	public void insert (Quiz quiz) throws SQLException {
		
		Connection conexao = new ConnectionFactory().getConnection();
		
		PreparedStatement stmt = conexao.prepareStatement(
				"INSERT INTO T_APL_QUIZ (id_quiz, id_usuario, id_progresso, dt_quiz, vl_quiz) VALUES (?, ?, ?, ?, ?)");
		
		stmt.setInt(1,quiz.getId());
		stmt.setInt(2,quiz.getUsuario().getId());
		stmt.setInt(3, quiz.getProgresso().getId());		
		stmt.setDate(4, Date.valueOf(quiz.getData()));
		stmt.setInt(5, quiz.getResultado());
		
		stmt.execute();
		System.out.println("Insert executado");
		stmt.close();
		conexao.close();
	}
	
	public void update (Quiz quiz) throws SQLException {
		Connection conexao = new ConnectionFactory().getConnection();
		PreparedStatement stmt = conexao.prepareStatement(
				"UPDATE T_APL_QUIZ SET id_usuario=?, id_progresso=?, dt_quiz=?, vl_quiz=? WHERE id_quiz=?");
		
		stmt.setInt(1, quiz.getUsuario().getId());
		stmt.setInt(2, quiz.getProgresso().getId());
		stmt.setDate(3, Date.valueOf(quiz.getData()));
		stmt.setInt(4, quiz.getResultado());
		stmt.setInt(5, quiz.getId());
		
		stmt.execute();
		
		System.out.println("Update executado");

		stmt.close();
		conexao.close();
	}
	
	public void delete (Quiz quiz) throws SQLException {
		Connection conexao = new ConnectionFactory().getConnection();
		PreparedStatement stmt = conexao.prepareStatement(
				"DELETE FROM T_APL_QUIZ WHERE id_quiz=?");
		stmt.setInt(1, quiz.getId());
		stmt.execute();
		
		System.out.println("Delete executado");
		stmt.close();
		conexao.close();
	}
	
	public ArrayList<Quiz> getQuiz() throws SQLException{
		ArrayList<Quiz> lista = new ArrayList<>();
		
		Connection conexao = new ConnectionFactory().getConnection();
		PreparedStatement stmt = conexao.prepareStatement(
				"SELECT id_quiz, id_suuario, id_progresso, dt_quiz, vl_quiz FROM T_APL_QUIZ ORDER BY id_quiz");
		stmt.execute();
		
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
			quiz.setResultado(rs.getInt("vl_quiz"));
			
			lista.add(quiz);
		}
		
		
		rs.close();
		stmt.close();
		conexao.close();
		
		return lista;
	}
	
	public ArrayList<Quiz> getPontuacao(Usuario u) throws SQLException{
		ArrayList<Quiz> lista = new ArrayList<>();
		Connection conexao = new ConnectionFactory().getConnection();
		PreparedStatement stmt = conexao.prepareStatement(
				"SELECT U.NM_USUARIO, U.ID_USUARIO, Q.ID_QUIZ, Q.VL_QUIZ FROM T_APL_USUARIO U INNER JOIN T_APL_QUIZ Q ON (U.ID_USUARIO = Q.ID_USUARIO) WHERE U.ID_USUARIO=?");
		stmt.setInt(1, u.getId());
		stmt.execute();
	
		ResultSet rs = stmt.getResultSet();
		
		while(rs.next()) {

			Quiz quiz = new Quiz();
			
			u.setNome(rs.getString("NM_USUARIO"));
			u.setId(rs.getInt("ID_USUARIO"));
			quiz.setUsuario(u);
			
			quiz.setId(rs.getInt("ID_QUIZ"));
			quiz.setResultado(rs.getInt("VL_QUIZ"));
			
			lista.add(quiz);

		}
		
		
		rs.close();
		stmt.close();
		conexao.close();
		
		return lista;

	}
	
}


