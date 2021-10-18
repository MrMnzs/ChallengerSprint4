package br.com.fiap.model;

import java.time.LocalDate;

public class Quiz {
	private int id;
	private Pergunta pergunta;
	private Resposta alternativa;
	private int resultado;
	private LocalDate data;
	private Usuario usuario;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Pergunta getPergunta() {
		return pergunta;
	}
	public void setPergunta(Pergunta pergunta) {
		this.pergunta = pergunta;
	}
	public Resposta getAlternativa() {
		return alternativa;
	}
	public void setAlternativa(Resposta alternativa) {
		this.alternativa = alternativa;
	}
	public int getResultado() {
		return resultado;
	}
	public void setResultado(int resultado) {
		this.resultado = resultado;
	}
	public LocalDate getData() {
		return data;
	}
	public void setData(LocalDate data) {
		this.data = data;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
}
