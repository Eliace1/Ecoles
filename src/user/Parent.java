package user;

import configuration.Configuration;
import configuration.EcoleFactory;
import paiement.ModePaiement;
import paiement.Notification;
import paiement.Paiement;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class Parent extends Utilisateur  {
	private List<Enfant> enfants;
	private HashMap<Enfant,Paiement> listPaiement = new HashMap<>();

	public HashMap<Enfant, Paiement> getListPaiement() {
		return listPaiement;
	}
	public void enregistrerPaiement(Enfant enfant, Paiement paiement){
		listPaiement.put(enfant,paiement);
	}
	public boolean aPaiementPour(Enfant enfant) {
		return listPaiement.containsKey(enfant);
	}

	public Parent(){
		this.enfants = new ArrayList<>();
	}
	public void ajouterEnfant(Enfant enfant) {
		enfants.add(enfant);
		System.out.println("→ Enfant " + enfant.getNom() + " ajouté à " + nom);
	}

	public List<Enfant> getEnfants() {
		return enfants;
	}

	public void afficherEnfants() {
		StringBuilder sb = new StringBuilder("👨‍👩‍👧‍👦 Enfants de " + nom + " :\n\n");

		if (enfants.isEmpty()) {
			sb.append("Aucun enfant inscrit.");
		} else {
			for (Enfant enfant : enfants) {
				sb.append("- ").append(enfant.getNom())
						.append(" (Âge : ").append(enfant.getAge()).append(")\n");
			}
		}

		JOptionPane.showMessageDialog(null, sb.toString());
	}

	@Override
	public boolean equals(Object o) {
		if (o == null || getClass() != o.getClass()) return false;
		Parent parent = (Parent) o;
		return nom.equals(parent.nom);
	}

	@Override
	public int hashCode() {
		return nom.hashCode();
	}
	public Paiement RetournePaiement(Enfant enfant){
		return listPaiement.get(enfant);
	}

	public void notifier() {
		if(listPaiement==null)
			return;
		List<Paiement> paiements = new ArrayList<>(listPaiement.values());
		for(Paiement paiement : paiements){
			paiement.verifierPaiement();
		}
	}
}