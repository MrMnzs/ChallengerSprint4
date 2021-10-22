package br.com.fiap.test;

import java.sql.SQLException;
import java.util.ArrayList;
import br.com.fiap.dao.RespostaDAO;
import br.com.fiap.model.Resposta;

public class TesteResposta {

	public static void main(String[] args) throws SQLException {
		//Nem os usu�rios e nem os administradores do sistema far�o altera��es nas perguntas
		//Perguntas UCLA s�o padr�o e n�o devem ser alteradas. Muito menos deve-se adicionar perguntas.
			
		RespostaDAO dao = new RespostaDAO();
		
		ArrayList<Resposta> resposta = dao.getRespostas();
		
		for(Resposta r: resposta) {
			System.out.println(r.getId());
			System.out.println(r.getNrResposta());
			System.out.println(r.getDsResposta());
			System.out.println("_______________");
		}
	}
}