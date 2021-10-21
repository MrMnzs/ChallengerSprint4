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
	 * Método que insere uma pergunta no banco de dados. Esse método recebe uma pergunta da classe Controller.
	 * Esse método valida se a o ID da pergunta ou o texto da pergunta já existe no banco de dados e também verifica se o número do ID e o número da pergunta estão entre 1 e 999,
	 * caso não exista e caso o número de ID e pergunta estejam corretos de acordo com a regra de negócio, o método chama a classe
	 * PerguntaDAO para utilizar o método 'insert'.
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
			System.out.println("Esse ID de pergunta pergunta já existe no banco de dados");
		}else if(listaDsPergunta.contains(p.getDsPergunta())){
			System.out.println("Essa descrição de pergunta já existe no banco de dados");
		}else if(p.getId() < 1 || p.getId() > 999) {
			System.out.println("O ID da pergunta é um número entre 1 e 999");
		}else if(p.getNrPergunta() < 1 || p.getNrPergunta() > 999) {
			System.out.println("O número da pergunta deve estar entre 1 e 999");
		}else {
			dao.insert(p);
		}
	}
	
	/**
	 * Método responsável por atualizar uma pergunta no banco de dados.
	 * O método verifica se o número da pergunta e o ID estão entre 1 e 999.
	 * O método verifica se a descrição que está tentando ser atualizada já existe, pois não devem haver duas descrições iguais no banco de dados.
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
			System.out.println("O número da pergunta deve esaaaaaaatar entre 1 e 999");
		}else if(listaDsPergunta.contains(p.getDsPergunta())) {
			System.out.println("A descrição que você está tentando atualizar já existe em outra pergunta no banco");
		}else {
			dao.update(p);			
		}
	}

	/**
	 * Método que deleta uma pergunta do banco de dados.
	 * Esse dado verifica se o usuário passa um ID que ele deseja deletar.
	 * Só é possível deletar uma pergunta passando o seu ID
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
			System.out.println("É necessário informar um ID para deletar a pergunta");
		}else {
			dao.delete(p);			
		}
	}
}