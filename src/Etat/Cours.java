package Etat;

import javax.swing.*;
import java.util.*;

public class Cours {

	private String nom;
	private List<Creneau> creneaux;
	private  long montantHoraire;
	private  HashMap<Creneau,Long> creneauMontant = new HashMap<>();

	public Cours(String nom, long montantHoraire) {
		this.nom = nom;
		this.montantHoraire=montantHoraire;
		this.creneaux = new ArrayList<>();
	}

	public void ajouterCreneau(Creneau c) {
		if (creneaux.contains(c)) {
			JOptionPane.showMessageDialog(null, "Ce créneau existe déjà pour le cours " + nom);
		} else {
			creneaux.add(c);
			creneauMontant.put(c,this.montantHoraire);
			JOptionPane.showMessageDialog(null, "Créneau ajouté au cours " + nom);
		}
	}

	public HashMap<Creneau, Long> getCreneauMontant() {
		return creneauMontant;
	}

	public long getMontantHoraire() {
		return montantHoraire;
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