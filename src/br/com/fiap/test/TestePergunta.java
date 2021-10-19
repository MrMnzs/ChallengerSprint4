package br.com.fiap.test;

import java.sql.SQLException;
import java.util.ArrayList;
import br.com.fiap.dao.PerguntaDAO;
import br.com.fiap.model.Pergunta;
import br.com.fiap.model.Quiz;
import br.com.fiap.model.Resposta;


public class TestePergunta {

	public static void main(String[] args) throws SQLException {
		
		PerguntaDAO dao = new PerguntaDAO();
		
		Pergunta p1 = new Pergunta();
		
		Resposta r1 =  new Resposta();
		
		Quiz q1 = new Quiz();
		
		q1.setId(1);
		
		
		r1.setId(5);
		r1.setNrResposta(22);
		r1.setDsResposta("Banana");
		
		p1.setId(21);
		p1.setResposta(r1);
		p1.setQuiz(q1);
		p1.setNrPergunta(21);
		
		p1.setDsPergunta("EAe blz?");
		dao.insert(p1);
	

//		dao.delete(p1);		
		
		
//		p1.setResposta(r1.getId());
//		p1.getQuiz().setId(8);
//		p1.setNrPergunta(20);
//		p1.setDsPergunta("VAAAAAAAAAAAAAAAAAI CARAAAAI");
//		p1.setId(2);
//		
//		dao.update(p1);
		
		
//				
//		ArrayList<Pergunta> pergunta = dao.getPerguntas();
//		
//		for(Pergunta u : pergunta) {
//			System.out.println(u.getId());
//			System.out.println(u.getNrPergunta());
//			System.out.println(u.getDsPergunta());
//		}
		
		
		
	}

}
