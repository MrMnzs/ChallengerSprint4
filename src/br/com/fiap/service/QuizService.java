package br.com.fiap.service;

import java.sql.SQLException;
import java.util.ArrayList;
import br.com.fiap.dao.QuizDAO;
import br.com.fiap.model.Quiz;

/**
 * Utiliza métodos da DAO para fazer validações ao enviar e receber dados do quiz no banco de dados
 * @author giulio
 *
 */
public class QuizService {
	private QuizDAO dao;
	
	/**
	 * Construtor para iniciar objeto dao sempre que a classe for instanciada em um objeto
	 */
	public QuizService() {
		dao = new QuizDAO();
	}

	/**
	 * Insere um quiz no banco de dados, fazendo todas as validações de acordo com as regras de negócio
	 * @param Objeto do tipo Quiz
	 * @throws SQLException
	 */
	public void inserir(Quiz q) throws SQLException{
		ArrayList<Quiz> quiz = dao.getQuiz();
		
		ArrayList<Integer> listaIdQuiz = new ArrayList<Integer>();
		for(Quiz q1 : quiz) {
			listaIdQuiz.add(q1.getId());
		}
		if(listaIdQuiz.contains(q.getId())) {
			System.out.println("Não é possível inserir um quiz que já existe no banco de dados");
		}else {
			dao.insert(q);			
		}
	}
	
	/**
	 * Atualiza um quiz existente no banco de dados. Fazendo todas as validações necessárias
	 * @param Objeto do tipo Quizz
	 * @throws SQLException
	 */
	public void atualizar(Quiz q) throws SQLException{
		ArrayList<Quiz> quiz = dao.getQuiz();
		
		ArrayList<Integer> listaIdQuiz = new ArrayList<Integer>();

		for(Quiz q1 : quiz) {
			listaIdQuiz.add(q1.getId());
		}
		
		if(!listaIdQuiz.contains(q.getId())) {
			System.out.println("Você está tentando atualizar um quiz que não existe");			
		}
		else {
			dao.update(q);						
		}
	}

	/**
	 * Deleta um registro de quiz no banco de dados
	 * @param Objeto do tipo Quiz
	 * @throws SQLException
	 */
	public void deletar(Quiz q) throws SQLException {
		ArrayList<Quiz> quiz = dao.getQuiz();
		ArrayList<Integer> listaIdQuiz = new ArrayList<Integer>();
		for(Quiz q1 : quiz) {
			listaIdQuiz.add(q1.getId());
		}
		
		if(!listaIdQuiz.contains(q.getId())) {
			System.out.println("É necessário informar um ID para deletar o quiz");
		}else {
			dao.delete(q);			
		}
	}	
	
	/**
	 * Lista todos os dados relacionados a Quiz existentes no banco de dados de maneira formatada para visualização.
	 */
	public void listar() {
		try {
			ArrayList<Quiz> quiz = dao.getQuiz();
			for(Quiz q : quiz) {
				System.out.println("Id Quiz: " + q.getId());
				System.out.println("Pontuação do quiz: " + q.getProgresso().getDsSintoma());
				System.out.println("Resultado do quiz: " + q.getResultado());
				System.out.println("Data de realização do quiz: " + q.getData());
				System.out.println("Nome do usuário que jogou o quiz: " + q.getUsuario().getNome());
				System.out.println("------------------");
			}
		}catch(SQLException e) {
			System.out.println("Houve um erro na listagem, verifique se há dados no banco");
		}
	}
}