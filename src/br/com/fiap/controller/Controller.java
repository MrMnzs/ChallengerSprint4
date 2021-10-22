package br.com.fiap.controller;

import java.sql.SQLException;
import java.time.LocalDate;

import br.com.fiap.model.Pergunta;
import br.com.fiap.model.ProgressoUsuario;
import br.com.fiap.model.Quiz;
import br.com.fiap.model.Resposta;
import br.com.fiap.model.Usuario;
import br.com.fiap.service.PerguntaService;
import br.com.fiap.service.ProgressoUsuarioService;
import br.com.fiap.service.QuizService;
import br.com.fiap.service.RespostaService;
import br.com.fiap.service.UsuarioService;

public class Controller {
	public static void main(String[] args) throws SQLException {
		Quiz q1 = new Quiz();
		Usuario u1 = new Usuario();
		Resposta r1 = new Resposta();
		
		r1.setId(1);
		r1.setDsResposta("Eai, tudo bem?");
		r1.setNrResposta(5);
		
		Pergunta p1 = new Pergunta();
		p1.setId(21);
		p1.setQuiz(q1);
		p1.setDsPergunta("Qual o seu nível de ansiedade e tristeza??");
		p1.setNrPergunta(21);
		p1.setResposta(r1);
			
		ProgressoUsuario pro1 = new ProgressoUsuario();
		pro1.setDsSintoma("Ansiedade");
		pro1.setId(30);
		pro1.setQuiz(q1);
		pro1.setUsuario(u1);
		pro1.setVlSintoma(55);
		
		q1.setData(LocalDate.of(2021, 10, 20));
		q1.setId(1);
		q1.setProgresso(pro1);
		q1.setResultado(10);
		q1.setUsuario(u1);
		
		
//		perguntaService.inserir(p1);
		
//		p1.setNrPergunta(1000000);
//		p1.setDsPergunta("Tudo bem?");
//		perguntaService.deletar(p1);
		
//		progressoService.inserir(pro1);
//		progressoService.atualizar(pro1);
//		progressoService.deletar(pro1);
		
//		quizService.inserir(q1);
		
		u1.setId(5);
		
////	usuarioService.inserir(u1);
////	usuarioService.atualizar(u1);
			
		QuizService quizService = new QuizService();
		ProgressoUsuarioService progressoService = new ProgressoUsuarioService();
		PerguntaService perguntaService = new PerguntaService();
		UsuarioService usuarioService = new UsuarioService();
		RespostaService respostaService = new RespostaService();
		
		//quizService.listar();
		//progressoService.listar();
		//perguntaService.listar();
		//usuarioService.listar();
		//respostaService.listar();
		
		usuarioService.deletar(u1);
	}
}