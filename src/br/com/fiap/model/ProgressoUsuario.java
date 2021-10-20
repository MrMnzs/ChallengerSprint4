package br.com.fiap.model;

import java.util.ArrayList;

public class ProgressoUsuario {
	private int id;
	private Usuario usuario;
	private Quiz quiz;
	private int vlSintoma;
	private String dsSintoma;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public Quiz getQuiz() {
		return quiz;
	}
	public void setQuiz(Quiz quiz) {
		this.quiz = quiz;
	}
	
	public void relatorioProgresso(Usuario usuario, Quiz quiz) {
		//TO DO
	}
	public int getVlSintoma() {
		
		ArrayList<Double> medias = new ArrayList<>();
		ArrayList<Integer> resultados = new ArrayList<>();

		resultados.add(this.quiz.getResultado());
		
		for(int r = 0; r < resultados.size(); r++ ) {
			int soma = resultados.stream().mapToInt(Integer::intValue).sum();
			this.vlSintoma = soma / 10;
		}
		;
		medias.add((double) vlSintoma);
		System.out.println(medias);
		return vlSintoma;
	}
	
	
	public void setVlSintoma(int vlSintoma) {
		this.vlSintoma = vlSintoma;
	}
	public String getDsSintoma() {
		return dsSintoma;
	}
	public void setDsSintoma(String dsSintoma) {
		this.dsSintoma = dsSintoma;
	}
	
	
	
	
	
}
