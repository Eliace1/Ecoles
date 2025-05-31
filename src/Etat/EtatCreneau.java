package Etat;

public abstract class EtatCreneau {
   protected Creneau creneau;

   public EtatCreneau(Creneau creneau){
	   this.creneau=creneau;
   }
	public abstract  void etatDisponible(Creneau creneau) ;
		// TODO - implement EtatCreneau.etatDisponible


	public abstract void etatComplet(Creneau creneau) ;
		// TODO - implement EtatCreneau.etatComplet

	}

