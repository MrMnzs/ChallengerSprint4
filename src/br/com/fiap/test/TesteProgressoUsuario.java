package br.com.fiap.test;

import java.sql.SQLException;

import br.com.fiap.dao.ProgressoUsuarioDAO;
import br.com.fiap.model.ProgressoUsuario;
import br.com.fiap.model.Quiz;
import br.com.fiap.model.Usuario;

public class TesteProgressoUsuario {

	public static void main(String[] args) throws SQLException {

		ProgressoUsuarioDAO dao = new ProgressoUsuarioDAO();
		
		Quiz q1 = new Quiz();
		Usuario u1 = new Usuario();
		ProgressoUsuario p1 = new ProgressoUsuario();
		
		q1.setResultado(40);
		q1.setResultado(50);
		
		u1.setId(11);
		u1.setNome("Rafael");
		
		p1.setId(1);
		p1.setUsuario(u1);
		p1.setQuiz(q1);
		p1.setDsSintoma("Parabéns");

		p1.setVlSintoma(10);
		
//		System.out.println(p1.getVlSintoma());

		dao.update(p1);
	}

}
