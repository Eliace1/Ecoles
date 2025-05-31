
import Etat.Cours;
import Etat.Creneau;
import user.Enfant;
import user.Gestionnaire;
import user.Parent;

import javax.swing.JOptionPane;

import Etat.Cours;
import Etat.Creneau;
import Etat.EtatComplet;
import user.Enfant;
import user.Utilisateur;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        // Création des utilisateurs
        Parent parent = new Parent(1, "parent@mail.com", "Mme Dupont", "1234");
        Gestionnaire gestionnaire = new Gestionnaire(2, "gestionnaire@mail.com", "M. Martin", "admin");

        // Menu de sélection
        String[] options = {"Parent", "Gestionnaire"};
        int choix = JOptionPane.showOptionDialog(null,
                "Qui souhaite se connecter ?",
                "Connexion Utilisateur",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]);

        // Traitement selon le type d'utilisateur
        if (choix == 0) {
            parent.seConnecterAvecTentatives();
            parent.afficherRole();

            // Exemple : le parent ajoute des enfants
            Enfant e1 = new Enfant(101, "Emma", 10);
            Enfant e2 = new Enfant(102, "Lucas", 8);
            parent.ajouterEnfant(e1);
            parent.ajouterEnfant(e2);
            parent.afficherEnfants();

        } else if (choix == 1) {
            gestionnaire.seConnecterAvecTentatives();
            gestionnaire.afficherRole();

            // Création simulée d'un cours et d'un créneau
            Cours cours = new Cours("Mathématiques",20,10);
            Creneau creneau = new Creneau(1,"14h");

            // Enfant à inscrire
            Enfant e = new Enfant(103, "Clara", 9);

            // Inscription
            gestionnaire.inscrireEnfant(e, creneau, cours);

            // Paiement
            gestionnaire.gererPaiement(parent);
        }


        // Création d'enfants
        Enfant e1 = new Enfant();
        e1.setId(1);
        e1.setNom("Alice");
        e1.setAge(7);

        Enfant e2 = new Enfant();
        e2.setId(2);
        e2.setNom("Bob");
        e2.setAge(6);

        Enfant e3 = new Enfant();
        e3.setId(3);
        e3.setNom("Charlie");
        e3.setAge(8);

        // Création d'un créneau
        Creneau c1 = new Creneau();
        c1.setId(101);
        c1.setHoraire("10h-11h");
        c1.setCapacite(2);
        Creneau.setDisponibilite(2);  // Initialiser à la même valeur que la capacité

        // Création d’un cours
        Cours cours = new Cours("Danse");
        cours.ajouterCreneau(c1);

        // Inscription des enfants
        c1.AjouterEnfant(e1); // OK
        c1.AjouterEnfant(e2); // OK → État devient complet
        c1.AjouterEnfant(e3); // Refusé car complet

        // Affichage des créneaux et des enfants inscrits
        cours.afficherCreneaux();
        System.out.println("Enfants inscrits dans le créneau :");
        for (Enfant e : c1.getEnfantsInscrits()) {
            System.out.println("- " + e.getNom());
        }

        // Afficher état courant
        if (c1.getEtatCreneau() instanceof EtatComplet) {
            System.out.println("État du créneau : COMPLET");
        } else {
            System.out.println("État du créneau : DISPONIBLE");
        }
    }


}
