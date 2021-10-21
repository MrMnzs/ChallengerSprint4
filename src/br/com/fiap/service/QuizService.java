package br.com.fiap.service;

import java.sql.SQLException;
import java.util.ArrayList;
import br.com.fiap.dao.QuizDAO;
import br.com.fiap.model.Quiz;

public class QuizService {
	private QuizDAO dao;
	
	public QuizService() {
		dao = new QuizDAO();
	}

	
	public void inserir(Quiz q) throws SQLException{
		ArrayList<Quiz> quiz = dao.getQuiz();
		
		ArrayList<Integer> listaIdQuiz = new ArrayList<Integer>();
		for(Quiz q1 : quiz) {
			listaIdQuiz.add(q1.getId());
		}
		if(listaIdQuiz.contains(q.getId())) {
			System.out.println("N�o � poss�vel inserir um quiz que j� existe no banco de dados");
		}else {
			dao.insert(q);			
		}
	}
	public void atualizar(Quiz q) throws SQLException{
		ArrayList<Quiz> quiz = dao.getQuiz();
		
		ArrayList<Integer> listaIdQuiz = new ArrayList<Integer>();

		for(Quiz q1 : quiz) {
			listaIdQuiz.add(q1.getId());
		}
		
		if(!listaIdQuiz.contains(q.getId())) {
			System.out.println("Voc� est� tentando atualizar um quiz que n�o existe");			
		}
		else {
			dao.update(q);						
		}
	}

	public void deletar(Quiz q) throws SQLException {
		ArrayList<Quiz> quiz = dao.getQuiz();
		ArrayList<Integer> listaIdQuiz = new ArrayList<Integer>();
		for(Quiz q1 : quiz) {
			listaIdQuiz.add(q1.getId());
		}
		
		if(!listaIdQuiz.contains(q.getId())) {
			System.out.println("� necess�rio informar um ID para deletar o quiz");
		}else {
			dao.delete(q);			
		}
	}	
}
