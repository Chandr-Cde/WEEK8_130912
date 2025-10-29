package week08.chandra.id.ac.umn;

public abstract class Payment {
	protected double amount;
	
	public Payment(double amount) {
		this.amount = amount;
	}
	
	abstract void processPayment();
	
	public void paymentDetail() {
		System.out.println("Processing payment of $" + amount);
	}
}

