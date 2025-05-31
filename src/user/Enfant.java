package user;

public class Enfant {
	private int id;
	private String nom;
	private int age;

	public Enfant(int id, String nom, int age) {
		this.id = id;
		this.nom = nom;
		this.age = age;
	}

	public int getId() {
		return id;
	}

	public String getNom() {
		return nom;
	}

	public int getAge() {
		return age;
	}
}