package configuration;

import paiement.ModePaiement;

public class Configuration {

	private static Configuration instance;
	private ModePaiement modePaiement ;

	private Configuration(){

	}
	public ModePaiement creerModePaiement(String modePaiement){
		return EcoleFactory.getModePaiement(modePaiement);
	}

	public static Configuration getInstance(){
		if(instance==null){
			instance=new Configuration();
		}
		return instance;
	}
}