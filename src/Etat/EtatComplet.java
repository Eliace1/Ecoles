package Etat;

public class EtatComplet extends EtatCreneau {
    public EtatComplet(Creneau creneau) {
        super(creneau);
    }

    @Override
    public void etatDisponible(Creneau creneau) {
        creneau.setEtatCreneau( new EtatComplet(creneau));
        System.out.println("Le créneau est disponible");


    }

    @Override
    public void etatComplet(Creneau creneau) {
        System.out.println("Le créneau est déjà complet");

    }



}
