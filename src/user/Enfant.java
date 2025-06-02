package user;

public class Enfant {
	private int id;
	private static int compterId=0;
	private String nom;
	private int age;

	public Enfant( String nom, int age) {
		compterId++;
		this.id=compterId;
		this.nom = nom;
		this.age = age;
	}

	public Enfant() {

	}

	public void setId(int id) {
		this.id = id;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public void setAge(int age) {
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