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
		
		QuizService quizService = new QuizService();
		ProgressoUsuarioService progressoService = new ProgressoUsuarioService();
		PerguntaService perguntaService = new PerguntaService();
		UsuarioService usuarioService = new UsuarioService();
		RespostaService respostaService = new RespostaService();
		
		Usuario usuario1 = new Usuario();
		usuario1.setNome("Calisson");
		usuario1.setId(40);
		usuario1.setEmail("calisson@gmail.com");
		usuario1.setSenha("calissonprofessor");
		usuario1.setDataNascimento(LocalDate.of(1990, 01, 01));
		usuario1.setEstadoCivil("Solteiro");
		usuario1.setEstadoUf("SP");
		usuario1.setGenero("H");
		
		Quiz quiz1 = new Quiz();
		quiz1.setId(25);
		quiz1.setResultado(45);
		quiz1.setData(LocalDate.of(2021, 10, 10));
		quiz1.setUsuario(usuario1);
		
		ProgressoUsuario progresso1 = new ProgressoUsuario();
		progresso1.setDsSintoma("Você demonstrou ansiedade");
		progresso1.setId(30);
		progresso1.setQuiz(quiz1);
		progresso1.setUsuario(usuario1);
		progresso1.setVlSintoma(55);
		
		//
		quiz1.setProgresso(progresso1);
		//
		
		Resposta resposta1 = new Resposta();
		resposta1.setDsResposta("Muito");
		resposta1.setNrResposta(7);
		resposta1.setId(1);
		
		Pergunta pergunta1 = new Pergunta();
		pergunta1.setId(21);
		pergunta1.setQuiz(quiz1);
		pergunta1.setDsPergunta("Qual o seu nível de ansiedade e tristeza??");
		pergunta1.setNrPergunta(21);
		pergunta1.setResposta(resposta1);

		//Objetos 2
		Usuario usuario2 = new Usuario();
		usuario2.setNome("Rafael");
		usuario2.setId(41);
		usuario2.setEmail("Rafael@gmail.com");
		usuario2.setSenha("rafaelaluno");
		usuario2.setDataNascimento(LocalDate.of(1999, 05, 12));
		usuario2.setEstadoCivil("Casado");
		usuario2.setEstadoUf("SP");
		usuario2.setGenero("H");
		
		Quiz quiz2 = new Quiz();
		quiz2.setId(26);
		quiz2.setResultado(100);
		quiz2.setData(LocalDate.of(2021, 05, 10));
		quiz2.setUsuario(usuario2);
		
		ProgressoUsuario progresso2 = new ProgressoUsuario();
		progresso2.setDsSintoma("Você não demonstrou ansiedade");
		progresso2.setId(31);
		progresso2.setQuiz(quiz2);
		progresso2.setUsuario(usuario2);
		progresso2.setVlSintoma(40);
		
		quiz2.setProgresso(progresso2);
		
		Resposta resposta2 = new Resposta();
		resposta2.setDsResposta("Pouco");
		resposta2.setNrResposta(3);
		resposta2.setId(2);
		
		Pergunta pergunta2 = new Pergunta();
		pergunta2.setId(22);
		pergunta2.setQuiz(quiz2);
		pergunta2.setDsPergunta("Com qual frequencia você se sentiu solitário desde a semana passada?");
		pergunta2.setNrPergunta(22);
		pergunta2.setResposta(resposta2);
		
		//Objetos 1:
		//Métodos inserir
		//quizService.inserir(quiz1);
		//progressoService.inserir(progresso1);
		//perguntaService.inserir(pergunta1);
		//usuarioService.inserir(usuario1);
		//respostaService.inserir(resposta1);
		
		//Métodos atualizar
		//quizService.atualizar(quiz1);
		//progressoService.atualizar(progresso1);
		//perguntaService.atualizar(pergunta1);
		//usuarioService.atualizar(usuario1);
		//respostaService.atualizar(resposta1);
		
		//Métodos deletar
		//quizService.deletar(quiz1);
		//progressoService.deletar(progresso1);
		//perguntaService.deletar(pergunta1);
		//usuarioService.deletar(usuario1);
		//respostaService.deletar(resposta1);		
		
		//Objetos 2:
		//Métodos inserir
		//quizService.inserir(quiz2);
		//progressoService.inserir(progresso2);
		//perguntaService.inserir(pergunta2);
		//usuarioService.inserir(usuario2);
		//respostaService.inserir(resposta2);
		
		//Métodos atualizar
		//quizService.atualizar(quiz2);
		//progressoService.atualizar(progresso2);
		//perguntaService.atualizar(pergunta2);
		//usuarioService.atualizar(usuario2);
		//respostaService.atualizar(resposta2);	
		
		//Métodos deletar
		//quizService.deletar(quiz2);
		//progressoService.deletar(progresso2);
		//perguntaService.deletar(pergunta2);
		//usuarioService.deletar(usuario2);
		//respostaService.deletar(resposta2);		
		
		//Métodos listar
		//quizService.listar();
		//progressoService.listar();
		//perguntaService.listar();
		//usuarioService.listar();
		//respostaService.listar();
	}
}