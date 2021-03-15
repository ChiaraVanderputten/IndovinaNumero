/**
 * Sample Skeleton for 'Scene.fxml' Controller Class
 */

package it.polito.tdp.IndovinaNumero;

import java.net.URL;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ProgressBar;

public class FXMLController {

	private int NMAX = 0; //final perchè non lo cambierò
	private int TMAX = 0;
	private int segreto;
	private int tentativiFatti;
	private boolean inGioco = false;
	double pr=0;
	private Set <Integer> numeriScelti = new HashSet <Integer>();

	
    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;
    
    @FXML 
    private HBox layoutTentativo; 

    @FXML 
    private Button btnNuovaPartita; 

    @FXML 
    private TextField txtTentativi; 

    @FXML 
    private TextField txtTentativoUtente; 

    @FXML 
    private Button btnProva; 

    @FXML 
    private TextArea txtRisultato; 

    @FXML
    private MenuButton btnDifficolta;

    @FXML
    private MenuItem mnBassa;

    @FXML
    private MenuItem mnMedia;

    @FXML
    private MenuItem mnAlta;
    
    @FXML
    private ProgressBar progresso;

    @FXML
    void doDifBassa(ActionEvent event) {
    	
    	NMAX = 100; 
    	TMAX = 5;	
    	btnNuovaPartita.setDisable(false);
    }

    @FXML
    void doDiffAlta(ActionEvent event) {
    	
    	NMAX = 300; 
    	TMAX = 10;
    	btnNuovaPartita.setDisable(false);
    }

    @FXML
    void doDiffMedia(ActionEvent event) {
    	
    	NMAX = 200; 
    	TMAX = 8;
    	btnNuovaPartita.setDisable(false);
    }
    
    @FXML
    void doScegliDifficolta(ActionEvent event) {

    	btnNuovaPartita.setDisable(false);
    }


    
    @FXML
    void doNuovaPartita(ActionEvent event) {
    	//gestione inizio nuova partita
    	this.segreto = (int) (Math.random() * NMAX) +1;
    	this.tentativiFatti = 0; //ogni volta che schiaccio nuova partita ne comincio una nuova
    	this.inGioco = true;
    	
      
    	
    	//gestione dell'interfaccia
    	this.txtTentativi.setText(Integer.toString(TMAX));
    	this.layoutTentativo.setDisable(false);
    	btnDifficolta.setDisable(true); //quando comincia la partita è disabilita la scelta di difficoltà

        numeriScelti.clear();
        progresso.setProgress(0.0);
    }

    @FXML
    void doTentativo(ActionEvent event) {
    	//lettura input dell'utente
    	String ts = txtTentativoUtente.getText();
    	int tentativo;
    	
    	try {
    		tentativo = Integer.parseInt(ts);
    	}catch(NumberFormatException e) {
    		txtRisultato.setText("Devi inserire un numero!");
    		return;  //esci perchÃ¨ deve cambiare il numero

    	}

    	this.txtTentativoUtente.setText(""); //serve per pulire dove ho inserito il mio tentativo
    	
    	this.tentativiFatti ++; //incrementa la variabile che conta i tentativi che sto facendo
    	this.txtTentativi.setText(Integer.toString(TMAX-this.tentativiFatti)); //stampa i tentativi che mi rimangono
    	
    	pr+=(double)(1/(double)TMAX);
    	progresso.setProgress(pr);
    	
    	if(tentativo == this.segreto) {
    		//HO INDOVINATO!
    		txtRisultato.setText("HAI VINTO CON " + this.tentativiFatti + "TENTATIVI");
    		this.inGioco = false;
    		this.layoutTentativo.setDisable(true);
    		return;
    	}
    	
    	if(this.tentativiFatti == TMAX) {
    		//ho esaurito i tentativi
    		txtRisultato.setText("HAI PERSO. IL SEGRETO ERA: " + this.segreto);
    		this.inGioco = false;
    		this.layoutTentativo.setDisable(true);
    		return;  //esco ho finito i tentativi
    	}
    	
    /*	if(numeriScelti.size()==0)
    		numeriScelti.add(Integer.parseInt(txtTentativoUtente.getText()));
    	else {

    		if(numeriScelti.contains(Integer.parseInt(txtTentativoUtente.getText()))) {
    		    txtRisultato.clear();
    			txtRisultato.appendText("Numero giÃ  provato,cambialo!!");
    			return;
    		}else 
    			numeriScelti.add(Integer.parseInt(txtTentativoUtente.getText()));
    		
    	      }
*/
    	
    	
    	//Non ho vinto -> devo informare l'utente circa la bontà del suo tentativo
    	if(tentativo < this.segreto) {
    		txtRisultato.setText("TENTATIVO TROPPO BASSO");
    	} else {
    		txtRisultato.setText("TENTATIVO TROPPO ALTO");
    	}
    	
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
    	assert btnDifficolta != null : "fx:id=\"btnDifficolta\" was not injected: check your FXML file 'Scene.fxml'.";
        assert mnBassa != null : "fx:id=\"mnBassa\" was not injected: check your FXML file 'Scene.fxml'.";
        assert mnMedia != null : "fx:id=\"mnMedia\" was not injected: check your FXML file 'Scene.fxml'.";
        assert mnAlta != null : "fx:id=\"mnAlta\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnNuovaPartita != null : "fx:id=\"btnNuovaPartita\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtTentativi != null : "fx:id=\"txtTentativi\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtTentativoUtente != null : "fx:id=\"txtTentativoUtente\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnProva != null : "fx:id=\"btnProva\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtRisultato != null : "fx:id=\"txtRisultato\" was not injected: check your FXML file 'Scene.fxml'.";
        assert progresso != null : "fx:id=\"progrsso\" was not injected: check your FXML file 'Scene.fxml'.";
        assert layoutTentativo != null : "fx:id=\"layoutTentativo\" was not injected: check your FXML file 'Scene.fxml'.";

    }
}
