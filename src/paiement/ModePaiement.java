package paiement;

import user.Parent;

import java.util.Date;

public interface ModePaiement {

	/**
	 * 
	 * @param montant
	 */
	public String paiement(double montant, Parent parent) ;

}
