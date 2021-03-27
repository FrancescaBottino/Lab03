package it.polito.tdp.spellchecker;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import it.polito.tdp.spellchecker.model.Dictionary;
import it.polito.tdp.spellchecker.model.RichWord;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class FXMLController {
	
	Dictionary model;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ChoiceBox<String> lingua;

    @FXML
    private TextArea txtTesto;

    @FXML
    private TextArea txtErrori;
    
    @FXML
    private Label txtTempo;
    
    @FXML
    private Label txtRisultatoConErrori;


    @FXML
    void HandleClear(ActionEvent event) {
    	
    	txtTesto.setText("");
    	txtErrori.setText("");	

    }

    @FXML
    void HandleSpellCheck(ActionEvent event) {
    	
    	txtErrori.setText("");
    	
    	if(lingua.getValue()==null) {
    		txtErrori.setText("Inserisci una lingua");
    		return;
    	}
    	
    	//carico il dizionario 
    	
    	if(!model.loadDictionary(lingua.getValue())) {
    		txtErrori.setText("Errore nel caricamento del dizionario");
    		return;
    	}
    	
    	String testoInserito=txtTesto.getText().toLowerCase().replaceAll("[.,\\/#!$%\\^&\\*;:{}=\\-_'~()\\[\\]\"]", "");
    	
    	ArrayList<String> inputTextList=new ArrayList<String>();
    	
        if(testoInserito.isEmpty()) {
        	txtErrori.setText("Inserire un testo da correggere");
        	return;
        }
        
        String arrayParole[]= testoInserito.split(" ");
      
        for(int i=0; i<arrayParole.length; i++) {
        	inputTextList.add(arrayParole[i]);
        }
       
        
       ArrayList<RichWord> outputTextList = new ArrayList<RichWord>();
        
        long start=System.nanoTime();
        
        outputTextList=model.spellCheckText(inputTextList);
        //outputTextList=model.spellCheckTextLinear(inputTextList);
        //outputTextList=model.spellCheckTextDichotomic(InputTextList);
        
        long stop=System.nanoTime();
        
        //gestione errori
       	
        int numeroErrori=0;
        String stampa="";
        
        for(RichWord rw: outputTextList) {
        	numeroErrori++;
        	stampa+=rw.getWord()+"\n";
        }
        
        txtErrori.setText(stampa);
        txtTempo.setText("Spell check completato in "+ (stop-start)/1E9 +" secondi ");
        txtRisultatoConErrori.setText("Il testo contiene: "+numeroErrori+" errori");
    	

    }

    @FXML
    void initialize() {
        assert lingua != null : "fx:id=\"lingua\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtTesto != null : "fx:id=\"txtTesto\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtErrori != null : "fx:id=\"txtErrori\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtTempo != null : "fx:id=\"txtTempo\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtRisultatoConErrori != null : "fx:id=\"txtRisultatoConErrori\" was not injected: check your FXML file 'Scene.fxml'.";

    }
    
    public void setModel(Dictionary model2) {
    	
    	this.model=model2;
    	
    	//anche i bottoni dovrei disattivarli
    	
    	lingua.getItems().addAll("English", "Italian");
    	
    }
}


