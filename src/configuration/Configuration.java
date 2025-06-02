package configuration;

public class Configuration {

	private static Configuration instance;
	private String 		modePaiement = "paiementEchelonne";

	private Configuration(){
	}

	public String getModePaiement() {
		return modePaiement;
	}

	public void setModePaiement(String modePaiement) {
		this.modePaiement = modePaiement;
	}

	public static Configuration getInstance(){
		if(instance==null){
			instance=new Configuration();
		}
		return instance;
	}
}