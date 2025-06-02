package Etat;

import javax.swing.*;
import java.util.*;

public class Cours {

	private String nom;
	private List<Creneau> creneaux;

	public Cours(String nom) {
		this.nom = nom;
		this.creneaux = new ArrayList<>();
	}

	public void ajouterCreneau(Creneau c) {
		if (creneaux.contains(c)) {
			JOptionPane.showMessageDialog(null, "Ce créneau existe déjà pour le cours " + nom);
		} else {
			creneaux.add(c);
			JOptionPane.showMessageDialog(null, "Créneau ajouté au cours " + nom);
		}
	}


	public void afficherCreneaux() {
		if (creneaux.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Aucun créneau pour le cours " + nom);
			return;
		}
		StringBuilder sb = new StringBuilder("Cours : " + nom + "\n");
		for (Creneau c : creneaux) {
			sb.append("- Créneau ID ").append(c.getId())
					.append(" (").append(c.getHoraire()).append(")\n");
		}
		JOptionPane.showMessageDialog(null, sb.toString());
	}



	public List<Creneau> getCreneaux() {
		return creneaux;
	}


		public String getNom(){
			return nom;
		}


}