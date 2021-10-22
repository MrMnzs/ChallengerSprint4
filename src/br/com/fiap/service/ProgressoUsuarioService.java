package br.com.fiap.service;

import java.sql.SQLException;
import java.util.ArrayList;
import br.com.fiap.dao.ProgressoUsuarioDAO;
import br.com.fiap.model.ProgressoUsuario;
/**
 * Classe que aplica validações de acordo com regras de negócios para enviar dados ao banco e exibir dados para o usuário
 * @author Giulio Cesar
 */
public class ProgressoUsuarioService {
	private ProgressoUsuarioDAO dao;
	
	public ProgressoUsuarioService() {
		dao = new ProgressoUsuarioDAO();
	}
	
	/**
	 * Valida todos os dados do Input para inseri-los no banco de dadaos.
	 * @param Objeto do tipo ProgressoUsuario
	 * @throws SQLException
	 */
	public void inserir(ProgressoUsuario p) throws SQLException{
		ArrayList<ProgressoUsuario> progresso = dao.getProgressos();
		
		ArrayList<Integer> listaIdProgressos = new ArrayList<Integer>();
		for(ProgressoUsuario p1 : progresso) {
			listaIdProgressos.add(p1.getId());
		}
		
		if(listaIdProgressos.contains(p.getId())) {
			System.out.println("Esse ID de progresso já existe no banco de dados");
		}
		else if(p.getId() < 1 || p.getId() > 999999) {
			System.out.println("O ID do progresso deve ser um número entre 1 e 999.999");
		}
		else if(p.getVlSintoma() < 1 || p.getVlSintoma() > 999){
			System.out.println("O valor do sintoma deve estar entre 1 e 999");
		}
		else
			dao.insert(p);
		}
	

	/**
	 * Valida todos os dados do Input para atualiza-lo no banco de dados.
	 * @param Objeto do tipo ProgressoUsuario
	 * @throws SQLException
	 */
	public void atualizar(ProgressoUsuario p) throws SQLException{
		ArrayList<ProgressoUsuario> progresso = dao.getProgressos();
		
		ArrayList<Integer> listaIdProgressos = new ArrayList<Integer>();
		ArrayList<Integer> listaIdUsuario = new ArrayList<Integer>();

		for(ProgressoUsuario p1 : progresso) {
			listaIdProgressos.add(p1.getId());
			listaIdUsuario.add(p1.getUsuario().getId());
		}
		
		if(!listaIdUsuario.contains(p.getUsuario().getId())) {
			System.out.println("Você está tentando atualizar um progresso que está ligado a um usuário não cadastrado");			
		}
		else if(!listaIdProgressos.contains(p.getId())) {
			System.out.println("Você está tentando atualizar um progresso que não está cadastrado no banco");
		}
		else if(p.getVlSintoma() < 1 && p.getVlSintoma() > 999) {
			System.out.println("O valor do sintoma deve estar entre 1 e 999");
		}
		else {
			dao.update(p);						
		}
	
	}

	/**
	 * Valida o ID do progresso para deleta-lo do banco de dados.
	 * @param Objeto do tipo ProgressoUsuario
	 * @throws SQLException
	 */
	public void deletar(ProgressoUsuario p) throws SQLException {
		ArrayList<ProgressoUsuario> progresso = dao.getProgressos();
		ArrayList<Integer> listaIdProgressos = new ArrayList<Integer>();
		for(ProgressoUsuario p1 : progresso) {
			listaIdProgressos.add(p1.getId());
		}
		
		if(!listaIdProgressos.contains(p.getId())) {
			System.out.println("É necessário informar um ID para deletar a pergunta");
		}else {
			dao.delete(p);			
		}
	}	
	
	/**
	 * Lista todos os registros de Progresso do banco de dados de maneira personalizada para visualização.
	 */
	public void listar() {
		try {
			ArrayList<ProgressoUsuario> progresso = dao.getProgressos();
			for(ProgressoUsuario p : progresso) {
				System.out.println("Id Progresso: " + p.getId());
				System.out.println("Nome do usuário: " + p.getUsuario().getNome());
				System.out.println("Quizes jogados: " + p.getQuiz().getId());
				System.out.println("Valor total: " + p.getVlSintoma());
				System.out.println("Resultado: " + p.getDsSintoma());
				System.out.println("------------------");
			}
		}catch(SQLException e) {
			System.out.println("SQL Exception: houve um erro ao buscar os dados, verifique se a tabela contém dados");

		}
	}
	
}
