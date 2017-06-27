package model.player;

import java.util.Random;

/**
 * 
 * CPUController handles the decision whether the computer player will continue
 * rolling or pass the dice. No fancy logic here, just a random boolean for the
 * decision.
 * 
 * @author Ross Weinstein
 *
 */

public class CPUController extends SkunkPlayer {

	private Random rand;

	public CPUController(String name, int chips) {
		super(name, chips);
		this.rand = new Random();
	}

	/**
	 * Whether the computer player will continue rolling or pass the dice. Random.
	 */
	@Override
	public boolean wantsToRollDice() {
		return this.rand.nextBoolean();
	}
}
