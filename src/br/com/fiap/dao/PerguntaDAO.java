package br.com.fiap.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import br.com.fiap.connection.ConnectionFactory;
import br.com.fiap.model.Pergunta;

/**
 * Classe responsável por gravar e consultar dados relacionados a pergunta no banco de dados
 * @author Giulio Cesar
 *
 */
public class PerguntaDAO {
	
	/**
	 * Método que faz a inserção de uma Pergunta na tabela T_APL_PERGUNTA no banco de dados
	 * @param pergunta
	 * @throws SQLException
	 */
	public void insert (Pergunta pergunta) throws SQLException {
		
		Connection conexao = new ConnectionFactory().getConnection();
		
		PreparedStatement stmt = conexao.prepareStatement(
				"INSERT INTO T_APL_PERGUNTAS (id_perguntas, id_respostas, id_quiz, nr_perguntas, ds_perguntas) VALUES (?, ?, ?, ?, ?)");
		
		stmt.setInt(1,pergunta.getId());
		stmt.setInt(2,pergunta.getResposta().getId());
		stmt.setInt(3, pergunta.getQuiz().getId());		
		stmt.setInt(4, pergunta.getNrPergunta());
		stmt.setString(5, pergunta.getDsPergunta());
		
		stmt.execute();
		System.out.println("Insert executado");
		stmt.close();
		conexao.close();
	}
	
	/**
	 * Método que faz a atualização de um ou todos os dados de Pergunta no banco de dados
	 * @param pergunta
	 * @throws SQLException
	 */
	public void update (Pergunta pergunta) throws SQLException {
		Connection conexao = new ConnectionFactory().getConnection();
		PreparedStatement stmt = conexao.prepareStatement(
				"UPDATE T_APL_PERGUNTAS SET nr_perguntas=?, ds_perguntas=? WHERE id_perguntas=?");

		stmt.setInt(1, pergunta.getNrPergunta());
		stmt.setString(2, pergunta.getDsPergunta());
		stmt.setInt(3, pergunta.getId());
				
		stmt.execute();
		System.out.println("Update executado");

		stmt.close();
		conexao.close();
	}
	
	/**
	 * Método que deleta alguma pergunta do banco de dados
	 * @param pergunta
	 * @throws SQLException
	 */
	public void delete (Pergunta pergunta) throws SQLException {
		Connection conexao = new ConnectionFactory().getConnection();
		PreparedStatement stmt = conexao.prepareStatement(
				"DELETE FROM T_APL_PERGUNTAS WHERE id_perguntas=?");
		
		stmt.setInt(1, pergunta.getId());
		stmt.execute();
		
		System.out.println("Delete executado");
		stmt.close();
		conexao.close();
	}
		
	/**
	 * Método que lista todas as perguntas existentes no banco de dados
	 * @return Lista de todas as perguntas no banco de dados
	 * @throws SQLException
	 */
	public ArrayList<Pergunta> getPerguntas() throws SQLException{
		ArrayList<Pergunta> lista = new ArrayList<>();
		
		Connection conexao = new ConnectionFactory().getConnection();
		
		PreparedStatement stmt = conexao.prepareStatement(
				"SELECT id_perguntas, nr_perguntas, ds_perguntas FROM T_APL_PERGUNTAS order by id_perguntas");
		
		stmt.execute();
		
		ResultSet rs = stmt.getResultSet();
		
		while(rs.next()) {
			Pergunta pergunta = new Pergunta();
			pergunta.setId(rs.getInt("id_perguntas"));							
			pergunta.setNrPergunta(rs.getInt("nr_perguntas"));
			pergunta.setDsPergunta(rs.getString("ds_perguntas"));		
			lista.add(pergunta);
		}
		
		System.out.println(lista);
				
		rs.close();
		stmt.close();
		conexao.close();
		
		return lista;
	}
}
