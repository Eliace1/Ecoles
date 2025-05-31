package paiement;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class PaiementEchelonne implements ModePaiement {
    private int     nombreFois = 3;
    private HashMap<Date, Double> echeances = new HashMap<Date, Double>();

    public PaiementEchelonne(){
        Calendar calendar = Calendar.getInstance();
        double montantParPaiement = 100/nombreFois;
        for(int i=1; i<nombreFois;i++){
            calendar.add(Calendar.DAY_OF_MONTH,30);
            echeances.put(calendar.getTime(),montantParPaiement);
        }

        // Formatter en français
        Locale localeFr = new Locale("fr", "FR");
        DateFormat formatFr = new SimpleDateFormat("dd MMMM yyyy", localeFr);

        System.out.println("🗓️ Échéances prévues :");
        for (int i = 1; i <= nombreFois; i++) {
            calendar.add(Calendar.DAY_OF_MONTH, 30);
            Date dateEcheance = calendar.getTime();
            echeances.put(dateEcheance, montantParPaiement);

            // ✅ Affichage formaté
            System.out.println("Date : " + formatFr.format(dateEcheance) + " → Montant : " + montantParPaiement + " €");
        }
    }

    @Override
    public String paiement(double montant) {
        return "Paiement échelonné de "+montant/nombreFois+" £ accepté";
    }
    public void verifierPaiement(Date aujourdHui, Notification notif){
        for(HashMap.Entry<Date,Double> entry : echeances.entrySet()){
            if(estMemeJour(entry.getKey(),aujourdHui)){
                notif.notifier("Paiement échelonné de "+entry.getValue()+" £ dû aujourd'hui");
            }
        }
    }
    private boolean estMemeJour(Date d1, Date d2){
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        c1.setTime(d1);
        c2.setTime(d2);
        return c1.get(Calendar.YEAR) == c2.get(Calendar.YEAR)&& c1.get(Calendar.DAY_OF_YEAR) == c2.get(Calendar.DAY_OF_YEAR);
    }

    //Methode de recuperation d'echeances
    public Map<Date, Double> getEcheances() {
        return echeances;
    }
}