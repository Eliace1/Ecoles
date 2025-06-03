package paiement;

import user.Parent;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.swing.*;


public class PaiementEchelonne implements ModePaiement {
    private int nombreFois;
    private Map<Parent, Map<Date, Double>> paiementsParents = new HashMap<>();
    private HashMap<Date, Double> echeances = new HashMap<>();


    @Override
    public String paiement(double montant, Parent parent) {
        // Choix du nombre de fois
        String[] options = {"3 fois", "6 fois"};
        int choix = JOptionPane.showOptionDialog(
                null,
                "Choisir le nombre de paiements :",
                "Paiement échelonné",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]
        );

        if (choix == JOptionPane.CLOSED_OPTION) {
            return "❌ Paiement annulé.";
        }

        nombreFois = (choix == 0) ? 3 : 6;
        double montantParPaiement = montant / nombreFois;
        Calendar calendar = Calendar.getInstance();
        this.echeances.clear();
        Locale fr = new Locale("fr", "FR");
        DateFormat df = new SimpleDateFormat("dd MMMM yyyy", fr);
        StringBuilder sb = new StringBuilder("🗓️ Échéances pour " + parent.getNom() + " :\n");

        for (int i = 1; i <= nombreFois; i++) {
            calendar.add(Calendar.DAY_OF_MONTH, 30);
            Date date = calendar.getTime();
            echeances.put(date, montantParPaiement);
            sb.append("Date : ").append(df.format(date)).append(" → Montant : ")
                    .append(String.format(Locale.FRANCE, "%.2f", montantParPaiement)).append(" €\n");
        }

        paiementsParents.put(parent, echeances);
        return sb.toString();
    }

    public String verifierPaiement() {
        StringBuilder sb = new StringBuilder();
        Date aujourdHui = new Date();

        if (echeances == null || echeances.isEmpty()) {
            return "❗ Aucune échéance enregistrée.";
        }

        for (Map.Entry<Date, Double> entry : echeances.entrySet()) {
            Date dateEcheance = entry.getKey();
            double montant = entry.getValue();

            long diff = dateEcheance.getTime() - aujourdHui.getTime();
            long joursRestants = diff / (1000 * 60 * 60 * 24);

            if (estMemeJour(dateEcheance, aujourdHui)) {
                sb.append("📌 Paiement de ")
                        .append(String.format(Locale.FRANCE, "%.2f", montant))
                        .append(" € dû aujourd'hui.\n");
            } else if (joursRestants > 0 && joursRestants <= 3) {
                sb.append("⏳ Paiement de ")
                        .append(String.format(Locale.FRANCE, "%.2f", montant))
                        .append(" € à venir dans ")
                        .append(joursRestants)
                        .append(" jour(s).\n");
            } else if (joursRestants < 0) {
                sb.append("❌ Paiement de ")
                        .append(String.format(Locale.FRANCE, "%.2f", montant))
                        .append(" € en retard depuis ")
                        .append(-joursRestants)
                        .append(" jour(s).\n");
            }
        }

        if (sb.length() == 0) {
            return "✅ Aucun paiement imminent ou en retard.";
        }

        return sb.toString();
    }



    private boolean estMemeJour(Date d1, Date d2) {
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        c1.setTime(d1);
        c2.setTime(d2);
        return c1.get(Calendar.YEAR) == c2.get(Calendar.YEAR) &&
                c1.get(Calendar.DAY_OF_YEAR) == c2.get(Calendar.DAY_OF_YEAR);
    }

}
