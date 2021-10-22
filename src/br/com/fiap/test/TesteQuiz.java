package br.com.fiap.test;

import java.sql.SQLException;
import java.time.LocalDate;

import br.com.fiap.dao.QuizDAO;
import br.com.fiap.model.Pergunta;
import br.com.fiap.model.ProgressoUsuario;
import br.com.fiap.model.Quiz;
import br.com.fiap.model.Resposta;
import br.com.fiap.model.Usuario;

public class TesteQuiz {
	public static void main(String[] args) throws SQLException {
		Quiz q1 = new Quiz();
		QuizDAO dao = new QuizDAO();
		
		Resposta r1 = new Resposta();
		r1.setDsResposta("Muito");
		r1.setId(1);
		r1.setNrResposta(1);
		
		Pergunta p1 = new Pergunta();
		p1.setId(1);
		p1.setQuiz(q1);
		p1.setNrPergunta(5);
		p1.setResposta(r1);
		p1.setDsPergunta("oi?");
		
		ProgressoUsuario prog1 = new ProgressoUsuario();
		prog1.setDsSintoma(null);
		prog1.setId(1);
		prog1.setQuiz(q1);
		prog1.setUsuario(null);
		prog1.setVlSintoma(0);
		
		Usuario u1 = new Usuario();
		u1.setId(6);
		u1.setNome("Andre");
		u1.setEmail("caue@caue");		
		u1.setDataNascimento(LocalDate.of(2005, 06, 26));
		u1.setSenha("123899");
		u1.setGenero("H");
		u1.setEstadoCivil("Solteiro");
		u1.setEstadoUf("SP");	
		
		q1.setData(LocalDate.of(2021, 10, 01));
		q1.setId(22);
		q1.setProgresso(prog1);
		q1.setResultado(10);
		q1.setUsuario(u1);
		
		//teste para update
		q1.setData(LocalDate.of(2021, 10, 10));
		q1.setId(7);
		q1.setProgresso(prog1);
		q1.setResultado(45);
		q1.setUsuario(u1);
		
		//dao.insert(q1);
		//dao.update(q1);
		dao.delete(q1);
	}
}
