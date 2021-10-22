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
	 * M�todo para inserir um novo us�rio do sistema, chamando o m�todo 'insert()' da classe UsuarioDAO
	 * V�rias valida��es s�o feitas nesse m�todo a fim de enviar os dados para o banco apenas se as infora��es tiverem sido fornecidas corretamente.
	 * @param Objeto do tipo usu�rio
	 * @throws SQLException
	 */
	public void inserir(Usuario u) throws SQLException{
		ArrayList<Usuario> usuarios = dao.getUsuarios();
		ArrayList<Integer> listaIdUsuario = new ArrayList<Integer>();
		
		for(Usuario u1 : usuarios) {
			listaIdUsuario.add(u1.getId());
		}
		
		if(u.getId() < 1 || u.getId() > 99999) {
			System.out.println("O ID do usu�rio deve ser um n�mero entre 1 e 99.999");
		}
		else if(listaIdUsuario.contains(u.getId())) {
			System.out.println("Esse ID de usu�rio n�o existe no banco de dados");
		}
		else if(u.getNome().length() < 2 || u.getNome().length() > 40) {
			System.out.println("O nome do usu�rio n�o pode ter menos do que 2 caracteres e n�o pode ter mais do que 40 caracteres");
		}
		else if(u.getEmail().length() < 2 || u.getEmail().length() > 40) {
			System.out.println("O email do usu�rio n�o pode ter menos do que 2 caracteres e n�o pode ter mais do que 40 caracteres");
		}
		else if(u.getDataNascimento().isAfter(LocalDate.of(2010, 01, 01))) {
			System.out.println("O usu�rio n�o tem a idade do nosso p�blico-alvo");
		}
		else if(u.getSenha().length() < 1 || u.getSenha().length() > 12){
			if(u.getSenha().length() <=3 ) {
				System.out.println("A senha do usu�rio � muito fraca");
			}
			System.out.println("A senha do usu�rio deve ter mais do que 1 caracter no m�ximo 12");
		}	
		else if(u.getGenero().toUpperCase() != "H" && u.getGenero().toUpperCase() != "M" && u.getGenero().toUpperCase() != "NB") {
			System.out.println("O g�nero deve ser 'H' para homem, 'M' para mulher e 'NB' para n�o-bin�rio");
		}
		else if(u.getEstadoCivil().length() < 5 && u.getEstadoCivil().length() < 15) {
			System.out.println("O g�nero foi definido incorretamente. Tenet 'vi�vo', 'solteiro', 'divorciado' ou 'casado'");
		}
		else if(u.getEstadoUf().length() != 2) {
			System.out.println("O estado federativo deve ser uma sigla com dois caracteres");
		}
		else {
			dao.insert(u);
		}

	}
	
	
	/**
	 * M�todo que chama a fun��o 'update()' da classe UsuarioDAO
	 * � necess�rio informar o ID do usu�rio para fazer uma altea��o
	 * S�o feitas v�rias valida��es a fim de enviar os dados corretos para o banco
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
			System.out.println("S� � poss�vel atualizar um usu�rio que j� existe no banco, forne�a outro ID");
		}else if(u.getNome().length() < 2 || u.getNome().length() > 40) {
			System.out.println("O nome do usu�rio n�o pode ter menos do que 2 caracteres e n�o pode ter mais do que 40 caracteres");
		}
		else if(u.getEmail().length() < 2 || u.getEmail().length() > 40) {
			System.out.println("O email do usu�rio n�o pode ter menos do que 2 caracteres e n�o pode ter mais do que 40 caracteres");
		}
		else if(u.getDataNascimento().isAfter(LocalDate.of(2010, 01, 01))) {
			System.out.println("O usu�rio n�o tem a idade do nosso p�blico-alvo");
		}
		else if(u.getSenha().length() < 1 || u.getSenha().length() > 12){
			if(u.getSenha().length() <=3 ) {
				System.out.println("A senha do usu�rio � muito fraca");
			}
			System.out.println("A senha do usu�rio deve ter mais do que 1 caracter no m�ximo 12");
		}	
		else if(u.getGenero().toUpperCase() != "H" && u.getGenero().toUpperCase() != "M" && u.getGenero().toUpperCase() != "NB") {
			System.out.println("O g�nero deve ser 'H' para homem, 'M' para mulher e 'NB' para n�o-bin�rio");
		}
		else if(u.getEstadoCivil().length() < 5 && u.getEstadoCivil().length() < 15) {
			System.out.println("O g�nero foi definido incorretamente. Tenet 'vi�vo', 'solteiro', 'divorciado' ou 'casado'");
		}
		else if(u.getEstadoUf().length() != 2) {
			System.out.println("O estado federativo deve ser uma sigla com dois caracteres");
		}else {
			dao.update(u);			
		}
	}

	/**
	 * M�todo que chama a fun��o 'delete()' da classe UsuarioDAO
	 * � necess�rio fornecer o ID do usu�rio que ser� deletado
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
			System.out.println("S� � poss�vel deletar um ID que existe no banco, voc� passou um ID que n�o existe");
		}else{
			dao.delete(u);
		}
	}	
	
	/**
	 * Lista todos os registros de usu�rio no sistema, formatando-os para visualiza��o
	 */
	public void listar() {
		try {
			ArrayList<Usuario> usuario = dao.getUsuarios();
			for(Usuario u : usuario) {
				System.out.println("Id do usu�rio: " + u.getId());
				System.out.println("Nome do usu�rio: " + u.getNome());
				System.out.println("Data de nascimento do usu�rio: " + u.getDataNascimento());
				System.out.println("G�nero do usu�rio: " + u.getGenero());
				System.out.println("Estado federativo do usu�rio: " + u.getEstadoUf());
				System.out.println("Estado civil do usu�rio: " + u.getEstadoCivil());
				System.out.println("E-mail do usu�rio: " + u.getEmail());
				System.out.println("Senha do usu�rio: " + u.getSenha());
				
				System.out.println("------------------");
			}
		}catch(SQLException e) {
			System.out.println("Houve um erro na listagem, verifique se h� dados no banco");
		}
	}
}