package model.player;

import helpers.InputHelper;

public class UserController extends SkunkPlayer {
	
	private InputHelper input;
	
	public UserController(String name, int chips) {
		super(name, chips);
		this.input = new InputHelper();
	}

	@Override
	public boolean wantsToRollDice() {
		return this.input.askBinaryQuestion("Do you want to roll?", "y", "n");
	}

	@Override
	public int compareTo(SkunkPlayer o) {
		
		if (this == o) {
			return 0;
		} else if (this.getPlayer().getScore() < o.getPlayer().getScore()) {
			return 1;
		} else {
			return -1;
		}
	}
}
