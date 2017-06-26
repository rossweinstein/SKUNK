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
		return this.input.askBinaryQuestion("Do you want to roll? (y/n)", "y", "n");
	}
}
