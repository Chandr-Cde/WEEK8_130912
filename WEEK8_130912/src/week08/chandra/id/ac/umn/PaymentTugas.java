package week08.chandra.id.ac.umn;

public abstract class PaymentTugas {
	protected boolean isPaidOff;
	protected Item item;
	
	public abstract int pay();
	
	public PaymentTugas() {
		this.isPaidOff = false;
		this.item = null;
	}
	
	public PaymentTugas(Item item) {
		this.isPaidOff = false;
		this.item = item;
	}
	
	public boolean isPaidOff() {
		return isPaidOff;
	}
	
	public Item getItem() {
		return item;
	}
	
	public String getItemName() {
		return item.getName();
	}
	
	public String getStatus() {
		if(isPaidOff) {
			return "FINISHED";
		}
		return "ON PROGRESS";
	}
	
	public int getRemainingAmount() {
		if(isPaidOff) {
			return 0;
		}
		return item.getPrice();
	}
}
