package model.dice;

import java.util.Random;

/**
 * 
 * This class simulates the rolling of a single die. Its one method, roll, will
 * return a random value between 1 and 6.
 * 
 * @author Ross Weinstein
 * 
 */

public class Die {
	
	private Random rndVal;
	
	public Die() {
		this.rndVal = new Random();
	}
	
	/**
	 * Simulates the roll of a single die
	 * 
	 * @return int a random value between 1 and 6
	 */
	public int roll() {
		return this.rndVal.nextInt(6) + 1;
	}
}
