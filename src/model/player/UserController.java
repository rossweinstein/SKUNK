package model.player;

import helpers.InputHelper;

/**
 * 
 * Handles with whether the User wants to continue rolling or pass the dice by a
 * simple prompt.
 * 
 * @author Ross Weinstein
 *
 */

public class UserController extends SkunkPlayer {

	private InputHelper input;

	public UserController(String name, int chips) {
		super(name, chips);
		this.input = new InputHelper();
	}

	/**
	 * Prompts the user, asking if they want to roll the dice again
	 */
	@Override
	public boolean wantsToRollDice() {
		return this.input.askBinaryQuestion("Do you want to roll? (y/n)", "y", "n");
	}
}
