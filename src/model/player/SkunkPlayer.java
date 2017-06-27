package model.player;

/**
 * 
 * SkunkPlayer adds the necessary functionality to the more generic Player. It
 * allows a Player to play Skunk by adding the chips variable and supplying
 * methods for the various penalties when they roll a skunk.
 * 
 * @author Ross Weinstein
 *
 */

public abstract class SkunkPlayer extends Player implements Comparable<SkunkPlayer> {

	/*
	 * Chips are needed in skunk to pay penalties. They are lost when a player
	 * rolls a skunk or gained when a player wins a round and collects the Kitty
	 */
	private int chips;
	private boolean bankrupt;

	public SkunkPlayer(String name, int chips) {
		super(name);
		this.chips = chips;
		this.bankrupt = false;
	}

	/**
	 * Returns a String with the SkunkPlayer's name, score, and chips total with
	 * labels
	 */
	@Override
	public String toString() {
		return this.getName() + " | Score: " + this.getScore() + " | Chips: " + this.chips;
	}

	/**
	 * A SkunkPlayer only equals another if they have an identical name, score,
	 * and chips total
	 */
	@Override
	public boolean equals(Object obj) {

		// self check
		if (this == obj) {
			return true;
		}

		// null check and type check
		if (obj == null || !(obj instanceof SkunkPlayer)) {
			return false;
		}

		// cast and comparisons
		SkunkPlayer otherPlayer = (SkunkPlayer) obj;
		return this.getName().equals(otherPlayer.getName()) && this.getScore() == otherPlayer.getScore()
				&& this.chips == otherPlayer.chips;
	}

	/**
	 * Allows for the comparison of one SkunkPlayer to another based on their
	 * score
	 */
	@Override
	public int compareTo(SkunkPlayer o) {

		if (this == o) {
			return 0;
		} else if (this.getScore() < o.getScore()) {
			return 1;
		} else {
			return -1;
		}
	}

	/**
	 * Checks if the player wants to roll the dice again (Abstract)
	 * 
	 * @return boolean true if they want to play again; false otherwise
	 */
	public abstract boolean wantsToRollDice();

	/**
	 * SkunkPlayer rolled a double skunk. SkunkPlayer's score is reverted to 0
	 * and they lose 4 chips.
	 */
	public void rolledDoubleSkunk() {
		this.setChips(-4);
		this.clearScore();
	}

	/**
	 * SkunkPlayer rolled a single skunk without a deuce. SkunkPlayer's loses 1
	 * chip.
	 */
	public void rolledSingleSkunkNoDeuce() {
		this.setChips(-1);
	}

	/**
	 * SkunkPlayer rolled a single skunk with a deuce. SkunkPlayer's loses 2
	 * chips.
	 */
	public void rolledSingleSkunkDeuce() {
		this.setChips(-2);
	}

	/**
	 * SkunkPlayer won the round and wins all the chips in the Kitty to be added
	 * to their chips total
	 * 
	 * @param kittyAmount
	 *            int how much the kitty is worth at the end of the round
	 */
	public void wonKitty(int kittyAmount) {
		this.setChips(kittyAmount);
	}

	/**
	 * Getter for SkunkPlayer chips total
	 * 
	 * @return int how many chips SkunkPlayer has
	 */
	public int getChips() {
		return chips;
	}

	/**
	 * Check to see if SkunkPlayer has enough chips to pay to the kitty. This is
	 * helpful in the game because if there is a moment when the player does not
	 * have enough chips, they will pay their remaining chip total and be
	 * removed from the game.
	 * 
	 * @param chipPenalty
	 *            int how many chips the SkunkPlayer owes to the Kitty
	 * @return boolean can the SkunkPlayer pay or not
	 */
	public boolean hasEnoughChips(int chipPenalty) {
		return this.chips - chipPenalty >= 0;
	}

	/**
	 * Increase SkunkPlayer chip total by given amount
	 * 
	 * @param numOfChips
	 *            int how many chips to add to this SkunkPlayer
	 */
	private void setChips(int numOfChips) {
		this.chips += numOfChips;
	}

	/**
	 * If player does not have enough chips for kitty, set value to -100 so we
	 * can remove player from game
	 */
	public void notEnoughChipsForKitty() {
		this.bankrupt = true;
	}

	/**
	 * Does the player have 0 or more chips during the game?
	 * 
	 * @return boolean true if they have at least 0 chips; false if they
	 *         received a skunk penalty and could not pay in full
	 */
	public boolean isPlayerBankrupt() {
		return this.bankrupt;
	}

	/**
	 * Check to see whether a SkunkPlayer has a score of at least 100. This is
	 * useful to see when we need to begin our final round and each other
	 * SkunkPlayer has one more chance to beat this SkunkPlayer.
	 * 
	 * @return boolean does this SkunkPlayer have a score of at least 100 or not
	 */
	public boolean scoreAtLeast100() {
		return this.getScore() >= 100;
	}
}