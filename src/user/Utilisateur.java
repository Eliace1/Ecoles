package user;

public class Utilisateur {

	protected int id;
	protected string email;
	protected string nom;
	protected string password;

	public string getEmail() {
		return this.email;
	}

	public void setEmail(string email) {
		this.email = email;
	}

	public string getNom() {
		return this.nom;
	}

	public void setNom(string nom) {
		this.nom = nom;
	}

	public string getPassword() {
		return this.password;
	}

	public void setPassword(string password) {
		this.password = password;
	}

	public void seConnecter() {
		// TODO - implement Utilisateur.seConnecter
		throw new UnsupportedOperationException();
	}

}