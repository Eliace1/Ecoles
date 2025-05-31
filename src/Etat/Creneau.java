package Etat;

public class Creneau {
	private int id;
	private String horaire;
	private EtatCreneau etatCreneau;

	public int getId() {
		return id;
	}

	public String getHoraire() {
		return horaire;
	}

	public EtatCreneau getEtatCreneau() {
		return etatCreneau;
	}

	public Creneau(int id, String horaire) {
		this.id = id;
		this.horaire = horaire;
	}

	public void etatDisponible() {
		// TODO - implement Creneau.etatDisponible
		throw new UnsupportedOperationException();
	}

	public void etatComplet() {
		// TODO - implement Creneau.etatComplet
		throw new UnsupportedOperationException();
	}

}