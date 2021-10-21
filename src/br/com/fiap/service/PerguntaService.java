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
	 * M�todo que insere uma pergunta no banco de dados. Esse m�todo recebe uma pergunta da classe Controller.
	 * Esse m�todo valida se a o ID da pergunta ou o texto da pergunta j� existe no banco de dados e tamb�m verifica se o n�mero do ID e o n�mero da pergunta est�o entre 1 e 999,
	 * caso n�o exista e caso o n�mero de ID e pergunta estejam corretos de acordo com a regra de neg�cio, o m�todo chama a classe
	 * PerguntaDAO para utilizar o m�todo 'insert'.
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
		
		for(Pergunta p1 : pergunta) {
			listaDsPergunta.add(p1.getDsPergunta());
		}
		
		if(p.getNrPergunta() < 1 || p.getNrPergunta() > 999){
			System.out.println("O n�mero da pergunta deve esaaaaaaatar entre 1 e 999");
		}else if(listaDsPergunta.contains(p.getDsPergunta())) {
			System.out.println("A descri��o que voc� est� tentando atualizar j� existe em outra pergunta no banco");
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
}