package model.player;

import java.util.Random;

public class CPUController extends SkunkPlayer {
	
	private Random rand;
	
	public CPUController(String name, int chips) {
		super(name, chips);
		this.rand = new Random();
	}
	
	public boolean wantsToRollDice() {
		return this.rand.nextBoolean();
	}
}
