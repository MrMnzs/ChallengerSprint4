package br.com.fiap.model;

import java.time.LocalDate;

public class Usuario {
	private int id;
	private String nome;
	private LocalDate dataNascimento;
	private String genero;
	private String estadoUf;
	private String estadoCivil;
	private String email;
	private String senha;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public LocalDate getDataNascimento() {
		return dataNascimento;
	}
	
	public void setDataNascimento(LocalDate dataNascimento) {

		this.dataNascimento = dataNascimento;
	}
	
	public String getGenero() {
		return genero;
	}
	public void setGenero(String genero) {
		this.genero = genero;
	}
	public String getEstadoUf() {
		return estadoUf;
	}
	public void setEstadoUf(String estadoUf) {
		this.estadoUf = estadoUf;
	}
	public String getEstadoCivil() {
		return estadoCivil;
	}
	public void setEstadoCivil(String estadoCivil) {
		this.estadoCivil = estadoCivil;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	
}
