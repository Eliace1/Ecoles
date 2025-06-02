package user;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Parent extends Utilisateur {
	private List<Enfant> enfants;

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

	@Override
	public void afficherRole() {
		System.out.println(nom + " est un parent.");
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


}