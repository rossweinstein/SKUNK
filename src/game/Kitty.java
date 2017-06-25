package game;

public class Kitty {
	
	private int amount;
	
	public Kitty() {
		this.amount = 0;
	}

	public int getAmount() {
		return amount;
	}

	public void addToKitty(int amount) {
		this.amount += amount;
	}
	
	public void paidOutKitty() {
		this.amount = 0;
	}
}
