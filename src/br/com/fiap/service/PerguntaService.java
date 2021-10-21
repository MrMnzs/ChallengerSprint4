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
	
	/**
	 * Insere uma pergunta no banco de dados. 
	 * Recebe uma pergunta da classe Controller.
	 * Valida se o ID da pergunta ou o texto da pergunta j� existem no banco de dados
	 * Verifica se o n�mero do ID e o n�mero da pergunta est�o entre 1 e 999.
	 * Se o ID e o texto n�o estiverem cadastrados e caso o ID e o n�mero da pergunta estejam de acordo com a regra
	 * Chama o m�todo 'insert' da classe PerguntaDAO caso o ID e o texto n�o estiverem cadastrados e caso o ID e o n�mero da pergunta estejam de acordo com a regra.
	 * @param Objeto do tipo pergunta
	 * @throws SQLException
	 */
	public void inserir(Pergunta p) throws SQLException{
		ArrayList<Pergunta> pergunta = dao.getPerguntas();
		
		ArrayList<Integer> listaIdPergunta = new ArrayList<Integer>();
		ArrayList<String> listaDsPergunta = new ArrayList<String>();
		for(Pergunta p1 : pergunta) {
			listaIdPergunta.add(p1.getId());
			listaDsPergunta.add(p1.getDsPergunta());
		}
		
		if(listaIdPergunta.contains(p.getId())) {
			System.out.println("Esse ID de pergunta pergunta j� existe no banco de dados");
		}else if(listaDsPergunta.contains(p.getDsPergunta())){
			System.out.println("Essa descri��o de pergunta j� existe no banco de dados");
		}else if(p.getId() < 1 || p.getId() > 999) {
			System.out.println("O ID da pergunta � um n�mero entre 1 e 999");
		}else if(p.getNrPergunta() < 1 || p.getNrPergunta() > 999) {
			System.out.println("O n�mero da pergunta deve estar entre 1 e 999");
		}else {
			dao.insert(p);
		}
	}
	
	/**
	 * M�todo respons�vel por atualizar uma pergunta no banco de dados.
	 * O m�todo verifica se o n�mero da pergunta e o ID est�o entre 1 e 999.
	 * O m�todo verifica se a descri��o que est� tentando ser atualizada j� existe, pois n�o devem haver duas descri��es iguais no banco de dados.
	 * @param Objeto do tipo pergunta
	 * @throws SQLException
	 */
	public void atualizar(Pergunta p) throws SQLException{
		ArrayList<Pergunta> pergunta = dao.getPerguntas();
		ArrayList<String> listaDsPergunta = new ArrayList<String>();
		ArrayList<Integer> listaIdPergunta = new ArrayList<Integer>();
		
		for(Pergunta p1 : pergunta) {
			listaDsPergunta.add(p1.getDsPergunta());
			listaIdPergunta.add(p1.getId());
		}
		
		if(p.getNrPergunta() < 1 || p.getNrPergunta() > 999){
			System.out.println("O n�mero da pergunta deve esaaaaaaatar entre 1 e 999");
		}else if(listaDsPergunta.contains(p.getDsPergunta())) {
			System.out.println("A descri��o que voc� est� tentando atualizar j� existe em outra pergunta no banco");
		}else if(!listaIdPergunta.contains(p.getId())) {
			System.out.println("S� � poss�vel atualizar uma pergunta j� existente no banco de dados");
		}else {
			dao.update(p);			
		}
	}

	/**
	 * M�todo que deleta uma pergunta do banco de dados.
	 * Esse dado verifica se o usu�rio passa um ID que ele deseja deletar.
	 * S� � poss�vel deletar uma pergunta passando o seu ID
	 * @param Objeto do tipo pergunta
	 * @throws SQLException
	 */
	public void deletar(Pergunta p) throws SQLException {
		ArrayList<Pergunta> pergunta = dao.getPerguntas();
		ArrayList<Integer> listaIdPergunta = new ArrayList<Integer>();
		for(Pergunta p1 : pergunta) {
			listaIdPergunta.add(p1.getId());
		}
		
		if(!listaIdPergunta.contains(p.getId())) {
			System.out.println("� necess�rio informar um ID para deletar a pergunta");
		}else {
			dao.delete(p);			
		}
	}	
	
	/**
	 * Lista todos os dados da tabela pergunta e os printa na tela de maneira visual
	 */
	public void listar() {
		try {
			ArrayList<Pergunta> pergunta = dao.getPerguntas();
			for(Pergunta p : pergunta) {
				System.out.println("Id Pergunta: " + p.getId());
				System.out.println("N�mero da Pergunta: " + p.getNrPergunta());
				System.out.println("Texto da Pergunta: " + p.getDsPergunta());
				System.out.println("------------------");			
			}			
		}catch(SQLException e) {
			System.out.println("SQL Exception: houve um erro ao buscar os dados, verifique se a tabela cont�m dados");
	}
	}
}