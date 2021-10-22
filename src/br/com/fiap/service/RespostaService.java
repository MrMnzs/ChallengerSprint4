package br.com.fiap.service;

import java.sql.SQLException;
import java.util.ArrayList;
import br.com.fiap.dao.RespostaDAO;
import br.com.fiap.model.Resposta;

public class RespostaService {
	private RespostaDAO dao;
	
	public RespostaService() {
		dao = new RespostaDAO();
	}
	
	/**
	 * M�todo que insere uma resposta no banco de dados
	 * Respostas n�o devem ter ID's iguais
	 * O n�mero da resposta � um inteiro de 1 a 5
	 * O texto de uma resposta deve ter no m�ximo 15 caracteres
	 * @param Objeto do tipo Resposta
	 * @throws SQLException
	 */
	public void inserir(Resposta r) throws SQLException{
		ArrayList<Resposta> resposta = dao.getRespostas();
		ArrayList<Integer> listaIdResposta = new ArrayList<Integer>();

		for(Resposta r1 : resposta) {
			listaIdResposta.add(r1.getId());
		}
		
		if(listaIdResposta.contains(r.getId())) {
			System.out.println("Essa resposta j� existe no banco, informe um ID diferente");
		}else if(r.getNrResposta() < 1 || r.getNrResposta() > 5) {
			System.out.println("O n�mero da resposta deve estar entre 1 e 5");
		}else if(r.getDsResposta().length() > 15) {
			System.out.println("A resposta deve ter at� 15 letras");
		}else {
			dao.insert(r);			
		}
	}
	
	/**
	 * M�todo para atualizar uma resposta j� existente no banco de dados.
	 * S� � poss�vel atualizar a resposta se for informado o ID da mesma.
	 * O n�mero da resposta � um inteiro de 1 a 5
	 * O texto de uma resposta deve ter no m�ximo 15 caracteres
	 * @param Objeto do tipo Resposta
	 * @throws SQLException
	 */
	public void atualizar(Resposta r) throws SQLException{
		ArrayList<Resposta> resposta = dao.getRespostas();
		ArrayList<Integer> listaIdResposta = new ArrayList<Integer>();
		
		for(Resposta r1 : resposta) {
			listaIdResposta.add(r1.getId());
		}
		
		if(!listaIdResposta.contains(r.getId())) {
			System.out.println("Voc� s� pode atualizar uma resposta que j� existe no sistema");
		}else if(r.getNrResposta() < 1 || r.getNrResposta() > 5) {
			System.out.println("O n�mero da resposta deve estar entre 1 e 5");
		}else if(r.getDsResposta().length() > 15) {
			System.out.println("A resposta deve ter at� 15 letras");
		}
		
		dao.update(r);
	}

	/**
	 * M�todo para deletar uma resposta j� cadastrada
	 * � necess�rio informar o ID da resposta para deleta-la
	 * @param Objeto do tipo Resposta
	 * @throws SQLException
	 */
	public void deletar(Resposta r) throws SQLException {
		ArrayList<Resposta> respota = dao.getRespostas();
		ArrayList<Integer> listaIdResposta = new ArrayList<Integer>();
		for(Resposta r1 : respota) {
			listaIdResposta.add(r1.getId());
		}
		if(!listaIdResposta.contains(r.getId())) {
			System.out.println("� necess�rio informar um ID para deletar a resposta");
		}else {
			dao.delete(r);			
		}
	}
	
	/**
	 * Lista todos os registros de respostas do banco de dados, formatando-os para visualiza��o.
	 */
	public void listar() {
		try {
			ArrayList<Resposta> resposta = dao.getRespostas();
			for(Resposta r : resposta) {
				System.out.println("Id Resposta: " + r.getId());
				System.out.println("N�mero da resposta: " + r.getNrResposta());
				System.out.println("Descri��o da resposta: " + r.getDsResposta());
				System.out.println("------------------");
			}
		}catch(SQLException e) {
			System.out.println("Houve um erro na listagem, verifique se h� dados no banco");
		}
	}
}
