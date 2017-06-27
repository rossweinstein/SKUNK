package model.dice;

/**
 * 
 * This class simulates the rolling of a set of dice. Its one method, roll, will
 * return an array of size two where each die's value can be access separately.
 * 
 * @author Ross Weinstein
 * 
 */

public class DiceSet {

	private Die[] theDice;

	public DiceSet() {
		this.theDice = new Die[2];
		this.theDice[0] = new Die();
		this.theDice[1] = new Die();
	}

	/**
	 * Simulates the roll of a set of dice
	 * 
	 * @return int[] an array of size 2 where each individual die's value is
	 *         represented
	 */
	public int[] roll() {
		return new int[] { this.theDice[0].roll(), this.theDice[1].roll() };
	}
}
