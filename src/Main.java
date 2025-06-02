
import Etat.Cours;
import Etat.Creneau;
import configuration.EcoleFacade;
import configuration.EcoleFactory;
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
        EcoleFacade facade = new EcoleFacade();
        facade.demarrer();
    }
}
