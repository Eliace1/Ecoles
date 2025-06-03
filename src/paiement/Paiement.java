package paiement;

import paiement.ModePaiement;
import paiement.Notification;
import user.Enfant;
import user.Parent;

import javax.swing.*;
import java.util.HashMap;


public class Paiement implements Notification {
	private double montant;
	protected ModePaiement modePaiement;
	protected Enfant enfant;
	private Parent parent;
	public Paiement(Enfant enfant, double montant, Parent parent) {
		this.enfant=enfant;
		this.montant=montant;
		this.parent=parent;
	}



	public void setModePaiement(ModePaiement modePaiement){
		this.modePaiement=modePaiement;
	}

	public void setMontant(double montant) {
		this.montant = montant;
	}


	public void effectuerPaiement(){
		String messagePaiement=modePaiement.paiement(montant,parent);
		notifier(messagePaiement);
	}
	public void verifierPaiement(){
		if(modePaiement instanceof PaiementEchelonne){
			String verifierPaiement = ((PaiementEchelonne) modePaiement).verifierPaiement();
			notifier(verifierPaiement);
		}
	}

	@Override
	public void notifier(String message) {
		JOptionPane.showMessageDialog(null,"Notification: "+message);
	}
}
