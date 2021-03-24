package it.polito.tdp.spellchecker.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Dictionary {
	
	ArrayList<String> dizionario= new ArrayList<String>();
	
	
	public void loadDictionary(String language) {
		
		//carica in memoria il dizionario della linqua desiderata 
		
		dizionario.clear();
		
		try {
			FileReader fr= new FileReader ("src/main/resources/"+language+".txt");
			BufferedReader br=new BufferedReader(fr);
			
			String word;
			while((word=br.readLine())!=null) {
				dizionario.add(word);
			}
			br.close();
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
	}

}
