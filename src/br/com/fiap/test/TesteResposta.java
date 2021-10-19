package br.com.fiap.test;

import java.sql.SQLException;
import java.util.ArrayList;
import br.com.fiap.dao.RespostaDAO;
import br.com.fiap.model.Resposta;


public class TesteResposta {

	public static void main(String[] args) throws SQLException {
		
		RespostaDAO dao = new RespostaDAO();
		
		ArrayList<Resposta> resposta = dao.getRespostas();
		
		for(Resposta r: resposta) {
			System.out.println(r.getId());
			System.out.println(r.getNrResposta());
			System.out.println(r.getDsResposta());

		}

	}

}