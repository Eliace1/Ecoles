package user;

import java.util.ArrayList;
import java.util.List;

public class Parent extends Utilisateur {
	private List<Enfant> enfants;

	public Parent(int id, String email, String nom, String password) {
		super(id, email, nom, password);
		this.enfants = new ArrayList<>();
	}

	public void ajouterEnfant(Enfant enfant) {
		enfants.add(enfant);
		System.out.println("→ Enfant " + enfant.getNom() + " ajouté à " + nom);
	}

	public List<Enfant> getEnfants() {
		return enfants;
	}

	@Override
	public void afficherRole() {
		System.out.println(nom + " est un parent.");
	}

	public void afficherEnfants() {
		System.out.println("Liste des enfants de " + nom + " :");
		if (enfants.isEmpty()) {
			System.out.println("   Aucun enfant inscrit.");
		} else {
			for (Enfant enfant : enfants) {
				System.out.println("   - " + enfant.getNom() + " (Âge : " + enfant.getAge() + ")");
			}
		}
	}

}