package Etat;

import java.util.*;

public class Cours {

	private String nom;
	private int capacite;
	private int disponibilte;
	private Collection<Creneau> creneaux;

	public String getNom() {
		return nom;
	}

	public int getCapacite() {
		return capacite;
	}

	public Cours(String nom, int capacite, int disponibilte) {
		this.nom = nom;
		this.capacite = capacite;
		this.disponibilte = disponibilte;
		this.creneaux = creneaux;
	}

	public int getDisponibilte() {
		return disponibilte;
	}

	public Collection<Creneau> getCreneaux() {
		return creneaux;
	}
}