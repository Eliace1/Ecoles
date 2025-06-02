package user;
import Etat.Cours;
import Etat.Creneau;
public class Gestionnaire extends Utilisateur {

	public Gestionnaire (){
        super();
    }
	public void gererCreneaux() {
		System.out.println("→ " + nom + " gère les créneaux.");
	}

	public void gererPaiement(Parent parent) {

		System.out.println("→ Paiement géré pour le parent : " + parent.nom);
	}

	public void inscrireEnfant(Enfant enfant,  Creneau creneau, Cours cours) {
		System.out.println("→ Enfant " + enfant.getNom() + " inscrit au cours : "+cours.getNom()+" au créneau : "+creneau.getHoraire());
	}

	@Override
	public void afficherRole() {
		System.out.println(nom + " est un gestionnaire.");
	}
}
