package user;

import javax.swing.JOptionPane;

public abstract class Utilisateur {
	protected static int id;
	protected String email;
	protected String nom;
	protected String password;

	public Utilisateur() {
		id++;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public String getNom() {
		return nom;
	}

	public String getPassword() {
		return password;
	}

	public void seConnecterAvecTentatives() {
		boolean estConnecte = false;
		int tentatives = 0;
		final int MAX_TENTATIVES = 5;

		while (!estConnecte && tentatives < MAX_TENTATIVES) {
			String emailSaisi = JOptionPane.showInputDialog(null, "Entrez votre email :");

			if (emailSaisi == null) {
				JOptionPane.showMessageDialog(null, "❌ Connexion annulée par l'utilisateur.");
				System.exit(0); // 🔴 quitte complètement le programme
			}

			if (emailSaisi.trim().isEmpty()) {
				JOptionPane.showMessageDialog(null, "⚠️ Veuillez entrer un email.");
				continue;
			}

			if (!this.email.equals(emailSaisi)) {
				JOptionPane.showMessageDialog(null, "❌ Email incorrect.");
				tentatives++;
				continue;
			}

			String mdpSaisi = JOptionPane.showInputDialog(null, "Entrez votre mot de passe :");

			if (mdpSaisi == null) {
				JOptionPane.showMessageDialog(null, "❌ Connexion annulée par l'utilisateur.");
				System.exit(0); // 🔴 quitte complètement le programme
			}

			if (mdpSaisi.trim().isEmpty()) {
				JOptionPane.showMessageDialog(null, "⚠️ Veuillez entrer un mot de passe.");
				continue;
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
			JOptionPane.showMessageDialog(null, "⛔ Trop de tentatives. Connexion refusée.");
			System.exit(0); // 🔴 quitte si échec
		}
	}

}
