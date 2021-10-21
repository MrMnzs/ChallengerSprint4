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
		
		u1.setId(11);
		u1.setNome("Caue");
		u1.setEmail("caue@caue");		
		u1.setDataNascimento(LocalDate.of(2000, 06, 26));
		u1.setSenha("123899");
		u1.setGenero("H");
		u1.setEstadoCivil("Solteiro");
		u1.setEstadoUf("SP");		
								
//		dao.insert(u1);
		
		
		u1.setNome("Andrézão");
		u1.setEmail("andrazao@andrazao");		
		u1.setDataNascimento(LocalDate.of(2008, 06, 26));
		u1.setSenha("123899");
		u1.setGenero("H");
		u1.setEstadoCivil("Solteiro");
		u1.setEstadoUf("SP");
		u1.setId(2);
		
//		dao.update(u1);
		
		u1.setId(1);
		
//		dao.delete(u1);
		
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
