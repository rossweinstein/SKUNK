package dice;

import java.util.Random;

public class Die {
	
	private Random rndVal;
	private int dieValue;
	
	public Die() {
		this.rndVal = new Random();
		this.dieValue = -1;
	}
	
	public int getLastRoll() {
		return this.dieValue;
	}
	
	public int roll() {
		this.dieValue = this.rndVal.nextInt(6) + 1;
		return this.dieValue;
	}
	
	public static void main (String[] args) {
		Die theDie = new Die();
		System.out.println(theDie.roll());
	}
}
