
import Etat.Cours;
import Etat.Creneau;
import user.Enfant;
import user.Gestionnaire;
import user.Parent;

import javax.swing.JOptionPane;

import Etat.Cours;
import Etat.Creneau;
import Etat.EtatComplet;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        System.out.printf("Hello and welcome!");

        for (int i = 1; i <= 5; i++) {
            //TIP Press <shortcut actionId="Debug"/> to start debugging your code. We have set one <icon src="AllIcons.Debugger.Db_set_breakpoint"/> breakpoint
            // for you, but you can always add more by pressing <shortcut actionId="ToggleLineBreakpoint"/>.
            System.out.println("i = " + i);
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
        Cours cours = new Cours("Danse", 20, 10);
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
