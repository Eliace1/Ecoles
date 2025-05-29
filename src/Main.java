import  paiement.*;

import java.util.Date;

public class Main {
    public static void main(String[] args) {
        Paiement paiement = new Paiement() {}; // classe anonyme
        paiement.setMontant(300);

        // Test Paiement Unique
        paiement.setModePaiement(new PaiementUnique());
        paiement.effectuerPaiement();

        // Test Paiement Échelonné + Notification
        PaiementEchelonne echelonne = new PaiementEchelonne();
        paiement.setModePaiement(echelonne);
        paiement.effectuerPaiement();

        // Simulation d’une date d’échéance
        echelonne.verifierPaiement(new Date(), paiement);
    }
}
