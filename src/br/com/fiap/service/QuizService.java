package br.com.fiap.service;

import java.sql.SQLException;
import java.util.ArrayList;
import br.com.fiap.dao.QuizDAO;
import br.com.fiap.model.Quiz;

/**
 * Utiliza m�todos da DAO para fazer valida��es ao enviar e receber dados do quiz no banco de dados
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
	 * Insere um quiz no banco de dados, fazendo todas as valida��es de acordo com as regras de neg�cio
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
			System.out.println("N�o � poss�vel inserir um quiz que j� existe no banco de dados");
		}else {
			dao.insert(q);			
		}
	}
	
	/**
	 * Atualiza um quiz existente no banco de dados. Fazendo todas as valida��es necess�rias
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
			System.out.println("Voc� est� tentando atualizar um quiz que n�o existe");			
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
			System.out.println("� necess�rio informar um ID para deletar o quiz");
		}else {
			dao.delete(q);			
		}
	}	
	
	/**
	 * Lista todos os dados relacionados a Quiz existentes no banco de dados de maneira formatada para visualiza��o.
	 */
	public void listar() {
		try {
			ArrayList<Quiz> quiz = dao.getQuiz();
			for(Quiz q : quiz) {
				System.out.println("Id Quiz: " + q.getId());
				System.out.println("Pontua��o do quiz: " + q.getProgresso().getDsSintoma());
				System.out.println("Resultado do quiz: " + q.getResultado());
				System.out.println("Data de realiza��o do quiz: " + q.getData());
				System.out.println("Nome do usu�rio que jogou o quiz: " + q.getUsuario().getNome());
				System.out.println("------------------");
			}
		}catch(SQLException e) {
			System.out.println("Houve um erro na listagem, verifique se h� dados no banco");
		}
	}
}