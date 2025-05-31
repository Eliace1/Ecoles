package user;

import javax.swing.JOptionPane;

public abstract class Utilisateur {
	protected int id;
	protected String email;
	protected String nom;
	protected String password;

	public Utilisateur(int id, String email, String nom, String password) {
		this.id = id;
		this.email = email;
		this.nom = nom;
		this.password = password;
	}

	public void seConnecterAvecTentatives() {
		boolean estConnecte = false;
		int tentatives = 0;
		final int MAX_TENTATIVES = 5;

		while (!estConnecte && tentatives < MAX_TENTATIVES) {
			String emailSaisi = JOptionPane.showInputDialog(null, "Entrez votre email :");

			if (emailSaisi == null) {
				JOptionPane.showMessageDialog(null, "❌ Connexion annulée par l'utilisateur.");
				return; // arrêt immédiat de la connexion
			}

			if (emailSaisi.trim().isEmpty()) {
				JOptionPane.showMessageDialog(null, "⚠️ Veuillez entrer un email.");
				continue; // ne compte pas comme tentative
			}

			if (!this.email.equals(emailSaisi)) {
				JOptionPane.showMessageDialog(null, "❌ Email incorrect.");
				tentatives++;
				continue;
			}

			// Email correct → demande du mot de passe
			String mdpSaisi = JOptionPane.showInputDialog(null, "Entrez votre mot de passe :");

			if (mdpSaisi == null) {
				JOptionPane.showMessageDialog(null, "❌ Connexion annulée par l'utilisateur.");
				return; // arrêt immédiat
			}

			if (mdpSaisi.trim().isEmpty()) {
				JOptionPane.showMessageDialog(null, "⚠️ Veuillez entrer un mot de passe.");
				continue; // ne compte pas comme tentative
			}

			if (!this.password.equals(mdpSaisi)) {
				JOptionPane.showMessageDialog(null, "❌ Mot de passe incorrect.");
				tentatives++;
			} else {
				JOptionPane.showMessageDialog(null, "✅ Connexion réussie. Bienvenue " + nom + " !");
				estConnecte = true;
			}
		}

		if (!estConnecte) {
			JOptionPane.showMessageDialog(null, "⛔ Trop de tentatives ou connexion annulée. Connexion refusée.");
		}
	}

	public abstract void afficherRole();
}
