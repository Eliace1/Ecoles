package paiement;

import paiement.ModePaiement;
import paiement.Notification;


public abstract class Paiement extends Notification {
	private double montant;
	protected ModePaiement modePaiement;

	public Paiement() {

	}

	public void setModePaiement(ModePaiement modePaiement){
		this.modePaiement=modePaiement;
	}

	public void setMontant(double montant) {
		this.montant = montant;
	}


	public void effectuerPaiement(){
		String messagePaiement=modePaiement.paiement(montant);
		notifier(messagePaiement);
	}
}
