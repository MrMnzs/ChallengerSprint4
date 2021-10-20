package br.com.fiap.model;

import java.time.LocalDate;

public class Quiz {
	private int id;
	private ProgressoUsuario progresso;
	private int resultado;
	private LocalDate data;
	private Usuario usuario;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public ProgressoUsuario getProgresso() {
		return progresso;
	}
	public void setProgresso(ProgressoUsuario progresso) {
		this.progresso = progresso;
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
