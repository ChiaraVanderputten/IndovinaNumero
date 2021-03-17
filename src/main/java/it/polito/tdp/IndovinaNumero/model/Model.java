package it.polito.tdp.IndovinaNumero.model;

import java.security.InvalidParameterException;
import java.util.HashSet;
import java.util.Set;

public class Model {
	
	private int NMAX = 100; //final perchè non lo cambierò
	private int TMAX = 8;
	private int segreto;
	private int tentativiFatti;
	private boolean inGioco = false;
	double pr=0;
	private Set <Integer> numeriScelti = new HashSet <Integer>();
	
	
	
	
	public int getNMAX() {
		return NMAX;
	}




	public int getTMAX() {
		return TMAX;
	}




	public int getSegreto() {
		return segreto;
	}




	public int getTentativiFatti() {
		return tentativiFatti;
	}




	public boolean isInGioco() {
		return inGioco;
	}




	public double getPr() {
		return pr;
	}




	public Set<Integer> getNumeriScelti() {
		return numeriScelti;
	}




	public void nuovaPartita() {
		//gestione inizio nuova partita
    	this.segreto = (int) (Math.random() * NMAX) +1;
    	this.tentativiFatti = 0; //ogni volta che schiaccio nuova partita ne comincio una nuova
    	this.inGioco = true;
    	this.numeriScelti= new HashSet <Integer>();
    	
	}
	
	public int tentativo(int tentativo) { //riceverà il numero giocato dal tentativo e restituirà un risultato, se era corretto, troppo alto o troppo basso 
		                             //mi ritornerà un valore intero 0-> tentativo corretto -1 -> era troppo basso 1-> era troppo alto
		
		//controllo se la partita è in corso
		if(!inGioco) {
			throw new  IllegalStateException("HAI PERSO IL SEGRETO ERA "+this.segreto);
		}
		//controllo output
		if(!tentativoValido(tentativo))
		throw new InvalidParameterException("Devi inserire un numero tra 1 e"+NMAX+" e non puoi inserire due volte lo stesso numero.");
		
		//arrivati qui il tentativo è valido
		this.tentativiFatti++;
		this.numeriScelti.add(tentativo);
		
		if(this.tentativiFatti==(TMAX-1))
			this.inGioco=false;
		
		if(tentativo == this.segreto) {
			this.inGioco=false;
			return 0;
		}else if(tentativo<this.segreto)
			return -1;
		else
			return 1;
			
		
	}
	private boolean tentativoValido (int tentativo) {
			
		if(tentativo < 1 || tentativo > NMAX) 
        return false;
        
        if(numeriScelti.contains(tentativo)) 
        	return false;
		
			return true;
	}

}
