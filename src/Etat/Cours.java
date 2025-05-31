package Etat;

import java.util.*;

public class Cours {

	private String nom;
	private List<Creneau> creneaux;

	public Cours(String nom) {
		this.nom = nom;
		this.creneaux = new ArrayList<>();
	}

	public void ajouterCreneau(Creneau c) {
		creneaux.add(c);
	}

	public void afficherCreneaux() {
		System.out.println("Cours : " + nom);
		for (Creneau c : creneaux) {
			System.out.println("- Créneau ID " + c.getId() + " (" + c.getHoraire() + ")");
		}
	}


	public List<Creneau> getCreneaux() {
		return creneaux;
	}


		public String getNom(){
			return nom;
		}


}