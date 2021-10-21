package br.com.fiap.test;

import java.sql.SQLException;
import java.util.ArrayList;
import br.com.fiap.dao.PerguntaDAO;
import br.com.fiap.model.Pergunta;
import br.com.fiap.model.Quiz;
import br.com.fiap.model.Resposta;
import br.com.fiap.service.PerguntaService;


public class TestePergunta {

	public static void main(String[] args) throws SQLException {
		
		Pergunta p1 = new Pergunta();
		Resposta r1 =  new Resposta();
		Quiz q1 = new Quiz();
		
		q1.setId(2);
		
		r1.setId(1);
		r1.setNrResposta(1);
		r1.setDsResposta("nunca");
		
		p1.setId(99);
		p1.setResposta(r1);
		p1.setQuiz(q1);
		p1.setNrPergunta(22);
		p1.setDsPergunta("teste123");
		
		//dao.insert(p1);
		//dao.delete(p1);
		//dao.update(p1);
				
//		ArrayList<Pergunta> pergunta = dao.getPerguntas();
//		for(Pergunta p : pergunta) {
//			System.out.println("Id Pergunta: " + p.getId());
//			System.out.println("Número da Pergunta: " + p.getNrPergunta());
//			System.out.println("Texto da Pergunta: " + p.getDsPergunta());
//			System.out.println("------------------");			
//		}
		PerguntaService ps = new PerguntaService();
		ps.listar();
	}

}
