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
	 * Método que insere uma resposta no banco de dados
	 * Respostas não devem ter ID's iguais
	 * O número da resposta é um inteiro de 1 a 5
	 * O texto de uma resposta deve ter no máximo 15 caracteres
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
			System.out.println("Essa resposta já existe no banco, informe um ID diferente");
		}else if(r.getNrResposta() < 1 || r.getNrResposta() > 5) {
			System.out.println("O número da resposta deve estar entre 1 e 5");
		}else if(r.getDsResposta().length() > 15) {
			System.out.println("A resposta deve ter até 15 letras");
		}else {
			dao.insert(r);			
		}
	}
	
	/**
	 * Método para atualizar uma resposta já existente no banco de dados.
	 * Só é possível atualizar a resposta se for informado o ID da mesma.
	 * O número da resposta é um inteiro de 1 a 5
	 * O texto de uma resposta deve ter no máximo 15 caracteres
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
			System.out.println("Você só pode atualizar uma resposta que já existe no sistema");
		}else if(r.getNrResposta() < 1 || r.getNrResposta() > 5) {
			System.out.println("O número da resposta deve estar entre 1 e 5");
		}else if(r.getDsResposta().length() > 15) {
			System.out.println("A resposta deve ter até 15 letras");
		}
		
		dao.update(r);
	}

	/**
	 * Método para deletar uma resposta já cadastrada
	 * É necessário informar o ID da resposta para deleta-la
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
			System.out.println("É necessário informar um ID para deletar a resposta");
		}else {
			dao.delete(r);			
		}
	}
	
	/**
	 * Lista todos os registros de respostas do banco de dados, formatando-os para visualização.
	 */
	public void listar() {
		try {
			ArrayList<Resposta> resposta = dao.getRespostas();
			for(Resposta r : resposta) {
				System.out.println("Id Resposta: " + r.getId());
				System.out.println("Número da resposta: " + r.getNrResposta());
				System.out.println("Descrição da resposta: " + r.getDsResposta());
				System.out.println("------------------");
			}
		}catch(SQLException e) {
			System.out.println("Houve um erro na listagem, verifique se há dados no banco");
		}
	}
}
