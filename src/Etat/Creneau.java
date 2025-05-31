package Etat;
import user.Enfant;

import java.util.ArrayList;
import java.util.List;
public class Creneau {
		private int id;
		private String horaire;
		private int capacite;
		private static int disponibilite = 0;
		private EtatCreneau etatCreneau;
		private List<Enfant> enfantsInscrits;

	public Creneau() {
		etatCreneau = new EtatDisponible(this);
	}

	public Creneau(int id, String horaire) {
		this.id = id;
		this.horaire = horaire;
	}

	public EtatCreneau getEtatCreneau() {
		return etatCreneau;
	}

	public void setEtatCreneau(EtatCreneau etatCreneau) {
		this.etatCreneau = etatCreneau;
	}

	public void etatDisponible() {
		// TODO - implement Creneau.etatDisponible
		etatCreneau.etatDisponible(this);
		throw new UnsupportedOperationException();
	}

	public void etatComplet() {
		// TODO - implement Creneau.etatComplet
		etatCreneau.etatComplet(this);
		throw new UnsupportedOperationException();
	}

	public void AjouterEnfant(Enfant enfant) {
		if (this.etatCreneau instanceof EtatComplet) {
			System.out.println("Créneau complet. Impossible d'inscrire " + enfant.getNom());
		} else {
			enfantsInscrits.add(enfant);
			disponibilite--;
			System.out.println("Enfant inscrit : " + enfant.getNom());

			if (disponibilite == 0) {
				this.etatCreneau.etatComplet(this);
			}
}

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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