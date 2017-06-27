package model.dice;

import java.util.Random;

public class Die {
	
	private Random rndVal;
	
	public Die() {
		this.rndVal = new Random();
	}
	
	public int roll() {
		return this.rndVal.nextInt(6) + 1;
	}
}
