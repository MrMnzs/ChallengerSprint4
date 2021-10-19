package br.com.fiap.test;

import br.com.fiap.dao.ProgressoUsuarioDAO;
import br.com.fiap.model.ProgressoUsuario;
import br.com.fiap.model.Usuario;

public class TesteProgressoUsuario {

	public static void main(String[] args) {


		ProgressoUsuarioDAO dao = new ProgressoUsuarioDAO();
		
		ProgressoUsuario p1 = new ProgressoUsuario();
		
		p1.setId(1);
		p1.setUsuario(null);;
		

	}

}
