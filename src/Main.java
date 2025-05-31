
import Etat.Cours;
import Etat.Creneau;
import user.Enfant;
import user.Gestionnaire;
import user.Parent;

import javax.swing.JOptionPane;

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
    }
}