package paiement;

public abstract class Notification {

	/**
	 * 
	 * @param message
	 */
	public void notifier(String message){
		System.out.println("Notification :"+message);
	}

}