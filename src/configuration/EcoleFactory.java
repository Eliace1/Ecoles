package configuration;

//import paiement.Paiement;

import paiement.ModePaiement;
import paiement.PaiementEchelonne;
import paiement.PaiementUnique;

public class EcoleFactory {
    public static ModePaiement getPaiement(String type){
        switch (type){
            case "paiementEchelonne":
                return new PaiementEchelonne();
            case "paiementUnique":
                return new PaiementUnique();
            default:
                return null;
        }
    }
}
