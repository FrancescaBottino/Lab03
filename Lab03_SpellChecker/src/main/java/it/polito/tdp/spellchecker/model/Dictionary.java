package it.polito.tdp.spellchecker.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Dictionary {
	
	ArrayList<String> dizionario= new ArrayList<String>();
	private String language;
	
	
	public boolean loadDictionary(String language) {
		
		//carica in memoria il dizionario della linqua desiderata 
		
		
		try {
			FileReader fr= new FileReader ("src/main/resources/"+language+".txt");
			BufferedReader br=new BufferedReader(fr);
			
			String word;
			while((word=br.readLine())!=null) {
				dizionario.add(word.toLowerCase());
			}
			Collections.sort(dizionario);
			
			br.close();
			return true;
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
		
	}
	
	public ArrayList<RichWord> spellCheckText(ArrayList<String> inputTextList){
		
		//trovo parole sbagliate
		
		ArrayList<RichWord> parole= new ArrayList<RichWord>();
		
		for(String s: inputTextList) {
			RichWord rw= new RichWord(s);
			
			if(dizionario.contains(s.toLowerCase())) //se è contenuta, è corretta
				rw.setCorrect(true);
			else
				rw.setCorrect(false);
			
			
			parole.add(rw);
		}
		
		return parole;
		
		
	}
	
	//altro modo per trovare, ALTERNATIVA A CONTAINS, metodo lineare
	
	public List<RichWord> spellCheckTextLinear(List<String> inputTextList){
		
		ArrayList<RichWord> parole= new ArrayList<RichWord>();
		
		for(String s: inputTextList) {
			RichWord rw= new RichWord(s);
			
			//implemetare il contains a mano 
			
			boolean found= false;
			
			for(String w: dizionario) {
				if(w.equalsIgnoreCase(s)) {
					found=true;
					break;
				}
			}
			
			if(found==true)
				rw.setCorrect(true);
			else 
				rw.setCorrect(false);
			
			parole.add(rw);
				
			
		}
		return parole;	
		
		
	}
	
	
	public List<RichWord> spellChceckTextDichotomic(List<String> inputTextList){
		
		List<RichWord> parole= new ArrayList<RichWord>();
		
		for(String s: inputTextList) {
			RichWord rw= new RichWord(s);
			
			if(binarySearch(s.toLowerCase())==true) {
				rw.setCorrect(true);
			}
			else
				rw.setCorrect(false);
			
			parole.add(rw);
			
			
		}
		

		return parole;
		
		
		
	}
	
	//metodo più efficiente

	private boolean binarySearch(String sTemp) { 
		
		//cosa fa la ricerca binaria/dicotomica, inizio da metà
		
		int inizio=0;
		int fine=dizionario.size();
		
		while(inizio!=fine) {
			
			int medio=inizio+(fine-inizio)/2;
			
			if(sTemp.compareToIgnoreCase(dizionario.get(medio))==0)
				return true;
			else if(sTemp.compareToIgnoreCase(dizionario.get(medio))>0)
				inizio=medio+1;
			else
				fine=medio;		
			
		}
		///non c'è nel dizionario
		return false;
		
	}

}
