package br.com.fiap.model;

public class Pergunta {
	private String texto;
	private Resposta alternativa;
	
	public String getTexto() {
		return texto;
	}
	public void setTexto(String texto) {
		this.texto = texto;
	}
	public Resposta getAlternativa() {
		return alternativa;
	}
	public void setAlternativa(Resposta alternativa) {
		this.alternativa = alternativa;
	}
}
