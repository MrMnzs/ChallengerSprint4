package br.com.fiap.model;

public class Pergunta {
	private int id;
	private Resposta resposta;
	private Quiz quiz;
	private int nrPergunta;
	private String dsPergunta;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Resposta getResposta() {
		return resposta;
	}
	public void setResposta(Resposta resposta) {
		this.resposta = resposta;
	}
	public Quiz getQuiz() {
		return quiz;
	}
	public void setQuiz(Quiz quiz) {
		this.quiz = quiz;
	}
	public int getNrPergunta() {
		return nrPergunta;
	}
	public void setNrPergunta(int nrPergunta) {
		this.nrPergunta = nrPergunta;
	}
	public String getDsPergunta() {
		return dsPergunta;
	}
	public void setDsPergunta(String dsPergunta) {
		this.dsPergunta = dsPergunta;
	}
}