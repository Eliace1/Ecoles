package paiement;

import user.Parent;

import java.util.Date;

public class PaiementUnique implements ModePaiement {

    public PaiementUnique() {
    }
    @Override
    public String paiement(double montant, Parent parent) {
        return "Paiement en une fois accepter d'un montant de " + montant+" £";
    }
}