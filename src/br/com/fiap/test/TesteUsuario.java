package br.com.fiap.test;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import br.com.fiap.dao.UsuarioDAO;
import br.com.fiap.model.Usuario;

public class TesteUsuario {

	public static void main(String[] args) throws SQLException {
		
		UsuarioDAO dao = new UsuarioDAO();
		
		Usuario u1 = new Usuario();
		
		u1.setId(7);
		u1.setNome("Andre");
		u1.setEmail("rafael@rafael");		
		u1.setDataNascimento(LocalDate.of(2015, 06, 26));
		u1.setSenha("123899");
		u1.setGenero("H");
		u1.setEstadoCivil("Casado");
		u1.setEstadoUf("BH");		
								
		//dao.insert(u1);
		
		
		u1.setNome("Fabio");
		u1.setEmail("Fabio@rafael");		
		u1.setDataNascimento(LocalDate.of(2013, 06, 26));
		u1.setSenha("123899");
		u1.setGenero("H");
		u1.setEstadoCivil("Solteiro");
		u1.setEstadoUf("BH");
		u1.setId(2);
		
		//dao.update(u1);
		
		u1.setId(2);
		
		//dao.delete(u1);
		
		ArrayList<Usuario> usuario = dao.getUsuarios();
		
		for(Usuario u: usuario) {
			System.out.println(u.getId());
			System.out.println(u.getNome());
			System.out.println(u.getEmail());
			System.out.println(u.getDataNascimento());
			System.out.println(u.getGenero());
			System.out.println(u.getEstadoCivil());
			System.out.println(u.getEstadoUf());
			System.out.println("_________________");
		
		}
		
	}

}
