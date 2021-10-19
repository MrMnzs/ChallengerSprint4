package br.com.fiap.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.com.fiap.connection.ConnectionFactory;
import br.com.fiap.model.Resposta;

public class RespostaDAO {

	public ArrayList<Resposta> getRespostas() throws SQLException{
		ArrayList<Resposta> lista = new ArrayList<>();
		
		Connection conexao = new ConnectionFactory().getConnection();
		
		PreparedStatement stmt = conexao.prepareStatement(
				"SELECT id_respostas, nr_respostas, ds_respostas FROM T_APL_RESPOSTAS order by id_respostas");
		
		stmt.execute();
		
		ResultSet rs = stmt.getResultSet();
		
		while(rs.next()) {
			Resposta resposta = new Resposta();
			
			resposta.setId(rs.getInt("id_respostas"));
			resposta.setNrResposta(rs.getInt("nr_respostas"));
			resposta.setDsResposta(rs.getString("ds_respostas"));			
			lista.add(resposta);
		}
		
		System.out.println(lista);	
		
		rs.close();
		stmt.close();
		conexao.close();
		
		return lista;
	}

}
