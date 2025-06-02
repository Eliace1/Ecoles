package Etat;
import user.Enfant;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
public class Creneau {
		private static int compteurId=0;
		private int id;
		private String horaire;
		private int capacite;
		private String jour;
		private static int disponibilite;
		private EtatCreneau etatCreneau;
		private List<Enfant> enfantsInscrits;

	public Creneau(String jour,String horaire, int capacite) {
		etatCreneau = new EtatDisponible(this);
		enfantsInscrits=new ArrayList<>();
		this.id=compteurId;
		id++;
		this.jour=jour;
		this.horaire=horaire;
		this.capacite=capacite;
		disponibilite=capacite;
	}

	public  int getId() {
		return id;
	}

	public EtatCreneau getEtatCreneau() {
		return etatCreneau;
	}

	public void setEtatCreneau(EtatCreneau etatCreneau) {
		this.etatCreneau = etatCreneau;
	}

	public void etatDisponible() {
		// TODO - implement Creneau.etatDisponible
		throw new UnsupportedOperationException();
	}

	public void etatComplet() {
		// TODO - implement Creneau.etatComplet
		throw new UnsupportedOperationException();
	}

	public String getJour() {
		return jour;
	}

	public void ajouterEnfant(Enfant enfant) {
		if (enfantsInscrits.contains(enfant)) {
			JOptionPane.showMessageDialog(null, "L'enfant " + enfant.getNom() + " est déjà inscrit.");
			return;
		}

		if (this.etatCreneau instanceof EtatComplet) {
			JOptionPane.showMessageDialog(null, "Créneau complet. Impossible d'inscrire " + enfant.getNom());
		} else {
			enfantsInscrits.add(enfant);
			disponibilite--;
			JOptionPane.showMessageDialog(null, "Enfant inscrit : " + enfant.getNom());

			if (disponibilite == 0) {
				this.etatCreneau.etatComplet(this);
			}
		}
	}
	public String getHoraire() {
		return horaire;
	}

	public void setHoraire(String horaire) {
		this.horaire = horaire;
	}

	public int getCapacite() {
		return capacite;
	}

	public void setCapacite(int capacite) {
		this.capacite = capacite;
	}

	public static int getDisponibilite() {
		return disponibilite;
	}

	public static void setDisponibilite(int disponibilite) {
		Creneau.disponibilite = disponibilite;
	}

	public List<Enfant> getEnfantsInscrits() {
		return enfantsInscrits;
	}

}
