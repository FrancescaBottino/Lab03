package it.polito.tdp.spellchecker.model;

public class RichWord {

	//ogni istanza di questa classe conterrà una parola del testo in input 
	//+ indicazione se la parola è corretta o meno
	
	private String word;
	private boolean correct;
	
	
	public RichWord(String word) {
		super();
		this.word = word;
		
	}


	public String getWord() {
		return word;
	}


	public void setWord(String word) {
		this.word = word;
	}


	public boolean isCorrect() {
		return correct;
	}


	public void setCorrect(boolean correct) {
		this.correct = correct;
	}
	
	
	
}
