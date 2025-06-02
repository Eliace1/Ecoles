package configuration;

//import paiement.Paiement;

import paiement.ModePaiement;
import paiement.PaiementEchelonne;
import paiement.PaiementUnique;
import user.Gestionnaire;
import user.Parent;
import user.Utilisateur;

public class EcoleFactory {
    public static ModePaiement getModePaiement(String type){
        switch (type){
            case "paiementEchelonne":
                return new PaiementEchelonne();
            case "paiementUnique":
                return new PaiementUnique();
            default:
                throw new  IllegalArgumentException("Type de paiement inconnue :"+type);
        }
    }
    public static Utilisateur getUtilisateur(String type){
        switch (type){
            case "Parent":
                return new Parent();
            case "Gestionnaire":
                return new Gestionnaire();
            default:
                throw new IllegalArgumentException("Type d'utilisateur inconnue");
        }
    }
}
