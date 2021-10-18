package br.com.fiap.model;

import java.time.LocalDate;
import java.time.Period;
import java.util.Calendar;

public class Usuario {
	private int id;
	private String nome;
	//private LocalDate dataNascimento;
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
		int anoAtual = Calendar.getInstance().get(Calendar.YEAR);
		int mesAtual = Calendar.getInstance().get(Calendar.MONTH);
		int diaAtual = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);

		LocalDate dataAtual = LocalDate.of(anoAtual, mesAtual, diaAtual);

		int idade = Period.between(dataNascimento, dataAtual).getYears();

		if(idade <= 4){
			System.out.println("Você ainda não atingiu a idade do nosso público alvo");
		}else if(idade >= 100){
			System.out.println("Idade inválida");
		}else if(idade >= 5 || idade <= 99){
			this.dataNascimento = dataNascimento;
		}else {
			System.out.println("ERRO");
		}
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
