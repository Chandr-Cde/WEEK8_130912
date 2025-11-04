package week08.chandra.id.ac.umn;

public class Credit extends PaymentTugas {
	private int installment;
	private int maxInstallmentAmount;
	
	public Credit (Item item, int maxInstallmentAmount) {
		super(item);
		this.maxInstallmentAmount = maxInstallmentAmount;
		this.installment = 0;
	}
	
	public int pay() {
		if(this.isPaidOff) {
			return 0;
		}
		this.installment++;
		
		int payment = this.item.getPrice() / this.maxInstallmentAmount;
		
		if (this.installment == this.maxInstallmentAmount) {
			this.isPaidOff = true;
			int remainder = this.item.getPrice() % this.maxInstallmentAmount;
			return payment + remainder;
		}else {
			return payment;
		}
	}
	
	public int getRemainingAmount() {
		if (this.isPaidOff) {
            return 0;
		}
		int payment = this.item.getPrice() / this.maxInstallmentAmount;
		int amountPaid = payment * this.installment;
		return this.item.getPrice() - amountPaid;
	}
	
	public String getClassName() {
        return "CREDIT";
    }
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
