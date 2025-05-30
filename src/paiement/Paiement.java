package paiement;

import paiement.ModePaiement;
import paiement.Notification;


public abstract class Paiement extends Notification {
	private double montant;
	private String date;
	protected ModePaiement modePaiement;

	public void setModePaiement(ModePaiement modePaiement){
		this.modePaiement=modePaiement;
	}

	public void setMontant(double montant) {
		this.montant = montant;
	}

	public void setDate(String date) {
		this.date = date;
	}
	public void effectuerPaiement(){
		String messagePaiement=modePaiement.paiement(montant);
		notifier(messagePaiement);
	}
}
