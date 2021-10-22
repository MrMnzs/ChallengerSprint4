package br.com.fiap.service;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.time.LocalDate;
import java.util.ArrayList;
import br.com.fiap.dao.UsuarioDAO;
import br.com.fiap.model.Usuario;

public class UsuarioService {
	private UsuarioDAO dao;
	
	public UsuarioService() {
		dao = new UsuarioDAO();
	}
	
	

	/**
	 * Método para inserir um novo usário do sistema, chamando o método 'insert()' da classe UsuarioDAO
	 * Várias validações são feitas nesse método a fim de enviar os dados para o banco apenas se as inforações tiverem sido fornecidas corretamente.
	 * @param Objeto do tipo usuário
	 * @throws SQLException
	 */
	public void inserir(Usuario u) throws SQLException{
		ArrayList<Usuario> usuarios = dao.getUsuarios();
		ArrayList<Integer> listaIdUsuario = new ArrayList<Integer>();
		
		for(Usuario u1 : usuarios) {
			listaIdUsuario.add(u1.getId());
		}
		
		if(u.getId() < 1 || u.getId() > 99999) {
			System.out.println("O ID do usuário deve ser um número entre 1 e 99.999");
		}
		else if(listaIdUsuario.contains(u.getId())) {
			System.out.println("Esse ID de usuário não existe no banco de dados");
		}
		else if(u.getNome().length() < 2 || u.getNome().length() > 40) {
			System.out.println("O nome do usuário não pode ter menos do que 2 caracteres e não pode ter mais do que 40 caracteres");
		}
		else if(u.getEmail().length() < 2 || u.getEmail().length() > 40) {
			System.out.println("O email do usuário não pode ter menos do que 2 caracteres e não pode ter mais do que 40 caracteres");
		}
		else if(u.getDataNascimento().isAfter(LocalDate.of(2010, 01, 01))) {
			System.out.println("O usuário não tem a idade do nosso público-alvo");
		}
		else if(u.getSenha().length() < 1 || u.getSenha().length() > 12){
			if(u.getSenha().length() <=3 ) {
				System.out.println("A senha do usuário é muito fraca");
			}
			System.out.println("A senha do usuário deve ter mais do que 1 caracter no máximo 12");
		}	
		else if(u.getGenero().toUpperCase() != "H" && u.getGenero().toUpperCase() != "M" && u.getGenero().toUpperCase() != "NB") {
			System.out.println("O gênero deve ser 'H' para homem, 'M' para mulher e 'NB' para não-binário");
		}
		else if(u.getEstadoCivil().length() < 5 && u.getEstadoCivil().length() < 15) {
			System.out.println("O gênero foi definido incorretamente. Tenet 'viúvo', 'solteiro', 'divorciado' ou 'casado'");
		}
		else if(u.getEstadoUf().length() != 2) {
			System.out.println("O estado federativo deve ser uma sigla com dois caracteres");
		}
		else {
			dao.insert(u);
		}

	}
	
	
	/**
	 * Método que chama a função 'update()' da classe UsuarioDAO
	 * É necessário informar o ID do usuário para fazer uma alteação
	 * São feitas várias validações a fim de enviar os dados corretos para o banco
	 * @param Objeto do tipo Usuario
	 * @throws SQLException
	 */
	public void atualizar(Usuario u) throws SQLException{
		ArrayList<Usuario> usuarios = dao.getUsuarios();
		ArrayList<Integer> listaIdUsuario = new ArrayList<Integer>();
		for(Usuario u1 : usuarios) {
			listaIdUsuario.add(u1.getId());
		}
		
		if(!listaIdUsuario.contains(u.getId())) {
			System.out.println("Só é possível atualizar um usuário que já existe no banco, forneça outro ID");
		}else if(u.getNome().length() < 2 || u.getNome().length() > 40) {
			System.out.println("O nome do usuário não pode ter menos do que 2 caracteres e não pode ter mais do que 40 caracteres");
		}
		else if(u.getEmail().length() < 2 || u.getEmail().length() > 40) {
			System.out.println("O email do usuário não pode ter menos do que 2 caracteres e não pode ter mais do que 40 caracteres");
		}
		else if(u.getDataNascimento().isAfter(LocalDate.of(2010, 01, 01))) {
			System.out.println("O usuário não tem a idade do nosso público-alvo");
		}
		else if(u.getSenha().length() < 1 || u.getSenha().length() > 12){
			if(u.getSenha().length() <=3 ) {
				System.out.println("A senha do usuário é muito fraca");
			}
			System.out.println("A senha do usuário deve ter mais do que 1 caracter no máximo 12");
		}	
		else if(u.getGenero().toUpperCase() != "H" && u.getGenero().toUpperCase() != "M" && u.getGenero().toUpperCase() != "NB") {
			System.out.println("O gênero deve ser 'H' para homem, 'M' para mulher e 'NB' para não-binário");
		}
		else if(u.getEstadoCivil().length() < 5 && u.getEstadoCivil().length() < 15) {
			System.out.println("O gênero foi definido incorretamente. Tenet 'viúvo', 'solteiro', 'divorciado' ou 'casado'");
		}
		else if(u.getEstadoUf().length() != 2) {
			System.out.println("O estado federativo deve ser uma sigla com dois caracteres");
		}else {
			dao.update(u);			
		}
	}

	/**
	 * Método que chama a função 'delete()' da classe UsuarioDAO
	 * É necessário fornecer o ID do usuário que será deletado
	 * @param Objeto do tipo Usuario
	 * @throws SQLException
	 * @throws SQLIntegrityConstraintViolationException
	 */
	public void deletar(Usuario u) throws SQLException, SQLIntegrityConstraintViolationException {
		ArrayList<Usuario> usuarios = dao.getUsuarios();
		ArrayList<Integer> listaIdUsuario = new ArrayList<Integer>();
		
		for(Usuario u1 : usuarios) {
			listaIdUsuario.add(u1.getId());
		}
		
		if(!listaIdUsuario.contains(u.getId())) {
			System.out.println("Só é possível deletar um ID que existe no banco, você passou um ID que não existe");
		}else{
			dao.delete(u);
		}
	}	
	
	/**
	 * Lista todos os registros de usuário no sistema, formatando-os para visualização
	 */
	public void listar() {
		try {
			ArrayList<Usuario> usuario = dao.getUsuarios();
			for(Usuario u : usuario) {
				System.out.println("Id do usuário: " + u.getId());
				System.out.println("Nome do usuário: " + u.getNome());
				System.out.println("Data de nascimento do usuário: " + u.getDataNascimento());
				System.out.println("Gênero do usuário: " + u.getGenero());
				System.out.println("Estado federativo do usuário: " + u.getEstadoUf());
				System.out.println("Estado civil do usuário: " + u.getEstadoCivil());
				System.out.println("E-mail do usuário: " + u.getEmail());
				System.out.println("Senha do usuário: " + u.getSenha());
				
				System.out.println("------------------");
			}
		}catch(SQLException e) {
			System.out.println("Houve um erro na listagem, verifique se há dados no banco");
		}
	}
}