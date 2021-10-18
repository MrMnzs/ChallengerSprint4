package br.com.fiap.test;

import java.sql.SQLException;
import java.time.LocalDate;
import br.com.fiap.dao.UsuarioDAO;
import br.com.fiap.model.Usuario;

public class TesteUsuario {

	public static void main(String[] args) throws SQLException {
		
		UsuarioDAO dao = new UsuarioDAO();
		
		Usuario u1 = new Usuario();
		u1.setId(1);
		u1.setNome("Rafael");
		u1.setEmail("rafael@rafael");
		//u1.setDataNascimento(LocalDate.of(1992, 8, 6));
		u1.setSenha("123456");
		u1.setGenero("H");
		u1.setEstadoCivil("Casado");
		u1.setEstadoUf("SP");
		
		dao.insert(u1);
		

	}

}
