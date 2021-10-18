package br.com.fiap.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.com.fiap.connection.ConnectionFactory;
import br.com.fiap.model.Quiz;

public class QuizDAO {


	public void insert(Quiz quiz) throws SQLException {
		Connection conexao = new ConnectionFactory().getConnection();
		
		PreparedStatement stmt = conexao.prepareStatement(
				"INSERT INTO QUIZ () VALUES (?, ?, ?, ?, ?)");
		
		stmt.se
		
		
		stmt.execute();
		
		System.out.println("Insert executado");
		stmt.close();
		conexao.close();
	}
	
	public void update(Quiz quiz) throws SQLException {
		Connection conexao = new ConnectionFactory().getConnection();
		PreparedStatement stmt = conexao.prepareStatement(
				"UPDATE QUIZ SET ATRIBUTO1=X, ATRIBUTO2=X");
		
		stmt.setInt(0, 0);
		stmt.execute();
		System.out.println("Update executado");

		stmt.close();
		conexao.close();
	}
	
	public void delete (Quiz quiz) throws SQLException {
		Connection conexao = new ConnectionFactory().getConnection();
		PreparedStatement stmt = conexao.prepareStatement(
				"DELETE FROM QUIZ WHERE ID=?");
		stmt.setInt(1, quiz.getId());
		stmt.execute();
		
		System.out.println("Delete executado");
		stmt.close();
		conexao.close();
	}
	
	public ArrayList<Quiz> getQuiz(){
		ArrayList<Quiz> lista = new ArrayList<>();
		
		Connection conexao = new ConnectionFactory().getConnection();
		PreparedStatement stmt = conexao.prepareStatement(
				"SELECT ID, blablabla, FROM QUIZ ORDER BY ID");
		stmt.execute();
		
		ResultSet rs = stmt.getResultSet();
		
		while(rs.next()) {
			Quiz quiz = new Quiz();
			quiz.setId(rs.getInt());
			//fazer todos os outros
			
			lista.add(quiz);
		}
		
		
		rs.close();
		stmt.close();
		conexao.close();
		
		return lista;
	}
}
