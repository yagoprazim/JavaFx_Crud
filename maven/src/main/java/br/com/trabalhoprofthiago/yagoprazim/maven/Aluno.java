package br.com.trabalhoprofthiago.yagoprazim.maven;

import javafx.beans.property.SimpleStringProperty;

public class Aluno {
	
	private SimpleStringProperty nome;
	private SimpleStringProperty nota1;
	private SimpleStringProperty nota2;
	private SimpleStringProperty media;
	private SimpleStringProperty situacao;
	
	public Aluno() {
		
	}
	
	public Aluno(String nome, String nota1, String nota2) {
		this.nome = new SimpleStringProperty(nome);
		this.nota1 = new SimpleStringProperty(nota1);
		this.nota2 = new SimpleStringProperty(nota2);
		this.media = new SimpleStringProperty(mediaResult(nota1, nota2));
		this.situacao = new SimpleStringProperty(situacaoResult());
	}
	
	public String getNome() {
		return this.nome.get();
	}
	
	public void setNome(String nome) {
		this.nome.set(nome);
	}

	public String getNota1() {
		return this.nota1.get();
	}

	public void setNota1(String nota1) {
		this.nota1.set(nota1);
	}

	public String getNota2() {
		return this.nota2.get();
	}

	public void setNota2(String nota2) {
		this.nota2.set(nota2);
	}
	
	public String getMedia() {
		return this.media.get();
	}
	
	public String mediaResult(String nota1, String nota2) {
		Double n1 = Double.parseDouble(nota1);
		Double n2 = Double.parseDouble(nota2);
		Double media = (n1 + n2) / 2;
		return String.format("%.2f", media);
	}
	
	public String getSituacao() {
		return this.situacao.get();
	}
	
	public String situacaoResult() {
		String situacaoA = null;
		String mediaStr = getMedia();
	    double media = Double.parseDouble(mediaStr.replace(",", "."));
	    if(media < 4.0) {
	    	situacaoA = "Reprovado!";
	    }else if (media >= 4.0 && media < 7.0){
	    	situacaoA = "Recuperação!";
	    }else if (media >= 7.0 && media <= 10.0){
	    	situacaoA = "Aprovado!";
	    }
	    return situacaoA;
	}
}
