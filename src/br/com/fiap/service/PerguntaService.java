package br.com.fiap.service;

import java.sql.SQLException;
import java.util.ArrayList;

import br.com.fiap.dao.PerguntaDAO;
import br.com.fiap.model.Pergunta;

public class PerguntaService {
	private PerguntaDAO dao;
	
	public PerguntaService() {
		dao = new PerguntaDAO();
	}
	
	public void inserir(Pergunta p) throws SQLException {
		ArrayList<Pergunta> pergunta = dao.getPerguntas();
		
		if(pergunta.contains(p)) {
			System.out.println("Essa pergunta já existe no banco de dados");
		}else {
			dao.insert(p);
		}
		
		
		
	}
	
	
}