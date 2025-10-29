package week08.chandra.id.ac.umn;

public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		AbstractPayment creditCardPayment = new CreditCardPayment(100.0, "1234-5678-9012-3456");
		AbstractPayment bankTransferPayment = new BankTransferPayment(300.0, "9876543210");
		
		creditCardPayment.paymentDetail();
		creditCardPayment.processPayment();
		
		bankTransferPayment.paymentDetail();
		bankTransferPayment.processPayment();
	}

}
