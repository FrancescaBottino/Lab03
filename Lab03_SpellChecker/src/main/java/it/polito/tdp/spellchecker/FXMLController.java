package it.polito.tdp.spellchecker;

import java.net.URL;
import java.util.ResourceBundle;

import it.polito.tdp.spellchecker.model.Dictionary;
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
    	
    	String testoInserito=txtTesto.getText().toLowerCase().replaceAll("[.,\\/$%\\*;:{}=\\-_'~()\\[\\]\"]", "");
    
    	System.out.println(testoInserito);
    	
    	
  
    	

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
    	lingua.getItems().addAll("English", "Italian");
    	
    }
}


