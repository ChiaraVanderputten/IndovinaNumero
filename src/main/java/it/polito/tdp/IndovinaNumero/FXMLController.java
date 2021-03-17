/**
 * Sample Skeleton for 'Scene.fxml' Controller Class
 */

package it.polito.tdp.IndovinaNumero;

import java.net.URL;
import java.security.InvalidParameterException;
import java.util.ResourceBundle;
import it.polito.tdp.IndovinaNumero.model.Model;
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
	
	//riferimento al modello
	private Model model;  //variabile vuota, chi crea il modello?dove? Magari all'initialize? No, non è
	                      //sbagliato ma creiamo una relazione troppo forte noi vorremmo disaccoppiare cambiando il modello
	                     //senza modificare il controller. Allora lo creiamo dall'esterno

	

	
    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;
    
    @FXML 
    private HBox layoutTentativo; 

    @FXML
    private Button btnModalitaAssistita;
    
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
    void doAssistita(ActionEvent event) {

    }
    
//    @FXML
//    void doDifBassa(ActionEvent event) {
//    	
//    	NMAX = 100; 
//    	TMAX = 5;	
//    	btnNuovaPartita.setDisable(false);
//    }
//
//    @FXML
//    void doDiffAlta(ActionEvent event) {
//    	
//    	NMAX = 300; 
//    	TMAX = 10;
//    	btnNuovaPartita.setDisable(false);
//    }
//
//    @FXML
//    void doDiffMedia(ActionEvent event) {
//    	
//    	NMAX = 200; 
//    	TMAX = 8;
//    	btnNuovaPartita.setDisable(false);
//    }
    
    @FXML
    void doScegliDifficolta(ActionEvent event) {

    	btnNuovaPartita.setDisable(false);
    }
    
    @FXML
    void doNuovaPartita(ActionEvent event) {
    	
    	//inizio la partita
    	this.model.nuovaPartita();
    	  
    	//gestione dell'interfaccia rimane qua
    	
    	this.txtTentativi.setText(Integer.toString(this.model.getTMAX())); //errore perchè è nel modello allora devo chiedere al modello
    	this.layoutTentativo.setDisable(false);
    	btnDifficolta.setDisable(true); //quando comincia la partita è disabilita la scelta di difficoltà

//        numeriScelti.clear();
//        progresso.setProgress(0.0);
    }

    @FXML
    void doTentativo(ActionEvent event) {
    	
    	//lettura input dell'utente
    	String tentativo = txtTentativoUtente.getText();
    	//    	int c=this.model.getNMAX();

    	int ten;
    	try {                                 //possiamo lasciarlo qui è relativo all'interfaccia
    		ten = Integer.parseInt(tentativo);
    	}catch(NumberFormatException e) {
    		txtRisultato.setText("Devi inserire un numero!");
    		return;  //esci perchÃ¨ deve cambiare il numero

    	}
    	
//    		if(numeriScelti.contains(Integer.parseInt(txtTentativoUtente.getText()))) {
//    		    txtRisultato.clear();
//    			txtRisultato.appendText("Numero gia' provato,cambialo!!");
//    			return;
//    		}else 
//    			numeriScelti.add(Integer.parseInt(txtTentativoUtente.getText()));
//    		
    		
    		
        //ciò che modifica l'interfaccia resta qua
    	this.txtTentativoUtente.setText(""); //serve per pulire dove ho inserito il mio tentativo
    	
    	// tentativiFatti()++; //incrementa la variabile che conta i tentativi che sto facendo
    	this.txtTentativi.setText(Integer.toString(this.model.getNMAX()-this.model.getTentativiFatti())); //stampa i tentativi che mi rimangono
    	
//    	pr+=(double)(1/(double)TMAX);
//    	progresso.setProgress(pr);
    	
    	int result;
    	
    	try {
    	result=this.model.tentativo(Integer.parseInt(tentativo));
    	}catch(IllegalStateException se) {
    		txtRisultato.setText(se.getMessage());
    		this.layoutTentativo.setDisable(true);
    		return;
    	}catch(InvalidParameterException pe) {
    		txtRisultato.setText(pe.getMessage());
    		return;
    	}
    	
    	if(result==0) {
    		//HO INDOVINATO!
    		txtRisultato.setText("HAI VINTO CON " + this.model.getTentativiFatti() + "TENTATIVI");
    		this.layoutTentativo.setDisable(true);
    		return;
    	}else if(result<0) {
    		txtRisultato.setText("TENTATIVO TROPPO BASSO");
    	}
    	else
    	txtRisultato.setText("TENTATIVO TROPPO ALTO");
    	
    	
    	
    	/*if(numeriScelti.size()==1 && txtRisultato.getText().equals("TENTATIVO TROPPO ALTO"))
    		txtRisultato.setText("Il valore segreto è compreso tra: 0 e " +numeriScelti.toString());
    	else if(numeriScelti.size()==1 && txtRisultato.getText().equals("TENTATIVO TROPPO BASSO"))
    		txtRisultato.setText("Il valore segreto è compreso tra: "+NMAX+" e " +numeriScelti.toString());
    	else {
    		System.out.println("bho");
    	}
//    	for(Integer i : numeriScelti)
//    			if(Math.abs(segreto-i)<c)
//    				c=i;
//    		
//    		txtTentativi.setText("Il valore segreto è compreso tra: ");
 
       */
    	
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
        assert btnModalitaAssistita != null : "fx:id=\"btnModalitaAssistita\" was not injected: check your FXML file 'Scene.fxml'.";
    }
    
    public void setModel (Model model) {
    	this.model=model;
    }
}
