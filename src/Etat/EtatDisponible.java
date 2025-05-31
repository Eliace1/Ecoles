package Etat;

public class EtatDisponible extends EtatCreneau {
    public EtatDisponible(Creneau creneau) {
        super(creneau);
    }

    @Override
    public void etatDisponible(Creneau creneau) {
        System.out.println("Le créneau est déjà disponible.");

    }

    @Override
    public void etatComplet(Creneau creneau) {
        creneau.setEtatCreneau( new EtatComplet(creneau));
        System.out.println("Le créneau est complet.");
    }


    }


