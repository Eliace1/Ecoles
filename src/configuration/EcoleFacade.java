package configuration;

import Etat.Cours;
import Etat.Creneau;
import configuration.EcoleFactory;
import paiement.ModePaiement;
import paiement.Notification;
import paiement.Paiement;
import user.Enfant;
import user.Gestionnaire;
import user.Parent;
import user.Utilisateur;

import javax.swing.*;
import java.io.*;
import java.util.*;
import java.util.Timer;

public class EcoleFacade {
    private List<Utilisateur> utilisateurs = new ArrayList<>();
    private List<Cours> listcours = new ArrayList<>();
    private List<Creneau> listcreneaux = new ArrayList<>();
    private Creneau dernierCreneau;
    private Timer timer = new Timer();
    private void chargerUtilisateurs(String fichier) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fichier))) {
            String ligne;
            while ((ligne = reader.readLine()) != null) {
                String[] parties = ligne.split(",");
                if (parties.length == 4) {
                    String type = parties[0];
                    String nom = parties[1];
                    String email = parties[2];
                    String motDePasse = parties[3];

                    Utilisateur u =EcoleFactory.getUtilisateur(type);
                    u.setEmail(email);
                    u.setPassword(motDePasse);
                    u.setNom(nom);
                    utilisateurs.add(u);
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Erreur lors du chargement des utilisateurs");
        }
    }

    //methode connecterUtilisateur
    private Utilisateur connecterUtilisateur(){
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
            for(Utilisateur u: utilisateurs) {
                    if((choix==0 && u instanceof Parent) || (choix==1 && u instanceof Gestionnaire)){
                        u.seConnecterAvecTentatives();
                        return u;
                    }
            }
            return null;
    }

    private void creerCreneau() {
        Set<String> joursValides = new HashSet<>(Arrays.asList(
                "lundi", "mardi", "mercredi", "jeudi", "vendredi", "samedi", "dimanche"
        ));

        String jour;
        while (true) {
            jour = JOptionPane.showInputDialog("Jour du créneau (ex: lundi) :");
            if (jour == null) return; // annulation
            jour = jour.trim().toLowerCase();
            if (joursValides.contains(jour)) break;
            JOptionPane.showMessageDialog(null, "⚠️ Jour invalide. Ex: lundi, mardi...");
        }

        String horaire;
        while (true) {
            horaire = JOptionPane.showInputDialog("Intervalle horaire (ex: 08:00-10:00) :");
            if (horaire == null) return; // annulation

            if (horaire.matches("^([01]?\\d|2[0-3]):[0-5]\\d-([01]?\\d|2[0-3]):[0-5]\\d$")) {
                String[] parts = horaire.split("-");
                if (parts[0].compareTo(parts[1]) < 0) break;
                else JOptionPane.showMessageDialog(null, "⚠️ L'heure de début doit être avant l'heure de fin.");
            } else {
                JOptionPane.showMessageDialog(null, "⚠️ Format invalide. Ex: 08:00-10:00");
            }
        }

        int capacite = -1;
        while (capacite <= 0) {
            String saisieCapacite = JOptionPane.showInputDialog("Capacité du créneau (nombre entier > 0) :");
            if (saisieCapacite == null) return; // annulation

            try {
                capacite = Integer.parseInt(saisieCapacite);
                if (capacite <= 0) {
                    JOptionPane.showMessageDialog(null, "❌ La capacité doit être un entier strictement positif.");
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "⚠️ Veuillez entrer un entier valide.");
            }
        }

        dernierCreneau = new Creneau(jour, horaire, capacite);
        listcreneaux.add(dernierCreneau);
        // Ajouter cr à la liste des créneaux dans ta classe Cours ou ailleurs selon ta structure
        JOptionPane.showMessageDialog(null, "✅ Créneau créé avec succès.");
    }
    private Cours selectionnerCours(List<Cours> listeCours) {
        if (listeCours.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Aucun cours disponible.");
            return null;
        }
        String[] nomsCours = listeCours.stream()
                .map(Cours::getNom)
                .toArray(String[]::new);

        int choix = JOptionPane.showOptionDialog(null, "Choisir un cours :",
                "Cours", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
                null, nomsCours, nomsCours[0]);

        if (choix >= 0) {
            return listeCours.get(choix);
        }
        return null;
    }

    private void inscrireEnfantACreneauCours() {
        Cours cours = selectionnerCours(listcours);
        if (cours == null) return;

        Creneau creneau = selectionnerCreneau(cours.getCreneaux());
        if (creneau == null) return;

        Parent parent = selectionnerParent(utilisateurs);
        if (parent == null) return;

        Enfant enfant = selectionnerEnfant(parent.getEnfants());
        if (enfant == null) return;

        creneau.ajouterEnfant(enfant);

    }

    private Creneau selectionnerCreneau(List<Creneau> creneaux) {
        if (creneaux.isEmpty()) return null;
        String[] noms = creneaux.stream().map(c -> c.getJour() + " " + c.getHoraire()).toArray(String[]::new);
        int choix = JOptionPane.showOptionDialog(null, "Choisir un créneau :", "Créneaux",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
                null, noms, noms[0]);
        if (choix < 0) return null;
        return creneaux.get(choix);
    }

    private Parent selectionnerParent(List<Utilisateur> utilisateurs) {
        List<Parent> parents = utilisateurs.stream()
                .filter(u -> u instanceof Parent)
                .map(u -> (Parent) u)
                .toList();
        if (parents.isEmpty()) return null;
        String[] noms = parents.stream().map(Parent::getNom).toArray(String[]::new);
        int choix = JOptionPane.showOptionDialog(null, "Choisir un parent :", "Parents",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
                null, noms, noms[0]);
        if (choix < 0) return null;
        return parents.get(choix);
    }

    private Enfant selectionnerEnfant(List<Enfant> enfants) {
        if (enfants.isEmpty()) return null;
        String[] noms = enfants.stream().map(Enfant::getNom).toArray(String[]::new);
        int choix = JOptionPane.showOptionDialog(null, "Choisir un enfant :", "Enfants",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
                null, noms, noms[0]);
        if (choix < 0) return null;
        return enfants.get(choix);
    }
    private void effectuerPaiement(Parent parent) {
        if (parent.getEnfants().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Aucun enfant à payer.");
            return;
        }

        Enfant enfant = selectionnerEnfant(parent.getEnfants());
        if (enfant == null) return;

        // Calcul montant total à payer
        double montant = 0;
        for (Cours cours : listcours) {
            for (Creneau c : cours.getCreneaux()) {
                if (c.getEnfantsInscrits().contains(enfant)) {
                    montant += cours.getMontantHoraire();
                }
            }
        }
        if(parent.aPaiementPour(enfant)){
            JOptionPane.showMessageDialog(null, "Cours déjà payé à "+enfant.getNom());
            return;
        }

        if (montant == 0) {
            JOptionPane.showMessageDialog(null, "Aucun cours à payer pour cet enfant.");
            return;
        }

        // Choix mode de paiement
        String[] labels = {"En une fois", "Échelonné"};
        String[] types = {"paiementUnique", "paiementEchelonne"};

        int choix = JOptionPane.showOptionDialog(null,
                "Montant à payer : " + montant + " €\nChoisissez le mode de paiement :",
                "Paiement",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                labels,
                labels[0]);

        if (choix == -1) return; // si l'utilisateur annule

        String typePaiement = types[choix];
        Paiement paiement = new Paiement(enfant, montant, parent);

        ModePaiement modePaiement = Configuration.getInstance().creerModePaiement(typePaiement);
        paiement.setModePaiement(modePaiement);
        paiement.effectuerPaiement();
        parent.enregistrerPaiement(enfant,paiement);

    }



    public void demarrer() {
        chargerUtilisateurs("src/configuration/utilisateurs.txt");
        while (true) {
            Utilisateur utilisateur = connecterUtilisateur();
            if (utilisateur == null) break;

            if (utilisateur instanceof Parent) {
                menuParent((Parent) utilisateur);
            } else if (utilisateur instanceof Gestionnaire) {
                menuGestionnaire((Gestionnaire) utilisateur);
            }
        }
    }


    //menu du parent
    private void menuParent(Parent parent) {
        int choix;
        do {
            parent.notifier();
            choix = JOptionPane.showOptionDialog(
                    null,
                    "Bienvenue Mr " + parent.getNom() + "\nMenu parent",
                    "Actions",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.INFORMATION_MESSAGE,
                    null,
                    new String[]{"Ajouter un enfant", "Afficher mes enfants", "Payer", "Quitter"},
                    "Ajouter un enfant"
            );

            switch (choix) {
                case 0:
                    // Ajouter enfant
                    String nom = JOptionPane.showInputDialog("Nom de l'enfant:");
                    int age = Integer.parseInt(JOptionPane.showInputDialog("Âge de l'enfant:"));
                    Enfant enfant = new Enfant(nom, age);
                    parent.ajouterEnfant(enfant);
                    JOptionPane.showMessageDialog(null, "Enfant ajouté.");
                    break;

                case 1:
                    // Afficher enfants
                    parent.afficherEnfants();
                    break;
                case 2:
                    effectuerPaiement(parent);
                break;
                case 3:
                    JOptionPane.showMessageDialog(null, "Se déconnecter");
                    return;
            }
        } while (choix != 3);
    }

    private void menuGestionnaire(Gestionnaire gestionnaire) {
        int choix;
        do {
            choix = JOptionPane.showOptionDialog(
                    null,
                    new StringBuilder().append("Bienvenue Mr ").append(gestionnaire.getNom()).append("\nMenu parent").toString(),
                    "Actions",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.INFORMATION_MESSAGE,
                    null,
                    new String[]{"Créer un créneau",
                            "Créer un cours",
                            "Ajouter un créneau à un cours",
                            "Inscrire un enfant à un créneau et un cours",
                            "Afficher la liste des enfants inscrits à un cours",
                            "Afficher la liste des créneaux d'un cours",
                            "Quitter"},
                    "Créer un créneau"
            );
            switch (choix){
                case 0:
                    creerCreneau();
                    break;
                case 1:
                    String nomCours = JOptionPane.showInputDialog("Nom du cours:");
                    int prix = Integer.parseInt(JOptionPane.showInputDialog("Prix par créneau (en €):"));
                    Cours c = new Cours(nomCours, prix);
                    listcours.add(c);
                    JOptionPane.showMessageDialog(null, "Cours ajouté.");
                    break;
                case 2:
                    Cours selectedCours = selectionnerCours(listcours);
                    if (selectedCours == null) {
                        JOptionPane.showMessageDialog(null, "Aucun cours sélectionné.");
                        return;
                    }

                    if (listcreneaux.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Aucun créneau disponible.");
                        return;
                    }

                    // sélection d’un créneau
                    Creneau creneauChoisi = selectionnerCreneau(listcreneaux);
                    if (creneauChoisi == null) {
                        JOptionPane.showMessageDialog(null, "Aucun créneau sélectionné.");
                        return;
                    }
                    selectedCours.ajouterCreneau(creneauChoisi);
                    break;

                case 3:
                    inscrireEnfantACreneauCours();
                    break;
                case 4:
                    Cours coursAffichage = selectionnerCours(listcours);
                    if (coursAffichage == null) break;

                    Set<Enfant> enfantsInscrits = new HashSet<>();
                    for (Creneau creneau : coursAffichage.getCreneaux()) {
                        enfantsInscrits.addAll(creneau.getEnfantsInscrits());
                    }

                    if (enfantsInscrits.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Aucun enfant inscrit à ce cours.");
                    } else {
                        StringBuilder sb1 = new StringBuilder("Enfants inscrits au cours " + coursAffichage.getNom() + ":\n");
                        for (Enfant e : enfantsInscrits) {
                            sb1.append("- ").append(e.getNom()).append("\n");
                        }
                        JOptionPane.showMessageDialog(null, sb1.toString());
                    }
                    break;
                case 5:
                    if (listcours.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Aucun cours disponible.");
                        break;
                    }
                    Cours coursSelectionne = selectionnerCours(listcours);
                    if (coursSelectionne != null) {
                        coursSelectionne.afficherCreneaux(); // affiche via JOptionPane
                    }
                    break;
                case 6:
                    JOptionPane.showMessageDialog(null,"Déconnexion");
                    break;
            }
        } while (choix != 6);
        return ;
    }
}
