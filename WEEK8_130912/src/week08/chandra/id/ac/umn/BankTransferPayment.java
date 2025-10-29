package week08.chandra.id.ac.umn;

public class BankTransferPayment extends Payment {
	private String bankAccount;

	public BankTransferPayment(double amount, String bankAccount) {
		super(amount);
		this.bankAccount = bankAccount;
	}
	
	void processPayment() {
		System.out.println("Processing payment of $" + amount + " for bank account " + bankAccount);
	}
}
