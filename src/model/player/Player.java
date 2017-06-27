package model.player;

/**
 * 
 * This basic player class is only responsible for two things: name and score.
 * 
 * @author Ross Weinstein
 *
 */

public abstract class Player {

	private int score;
	private String name;

	public Player(String name) {
		this.score = 0;
		this.name = name;
	}

	/**
	 * Returns the player's name and current score with labels
	 */
	@Override
	public String toString() {
		return "Player Name: " + name + " | Score: " + score;
	}

	/**
	 * Player is only equal to another if they have the exact same name and
	 * score
	 */
	@Override
	public boolean equals(Object obj) {

		// self check
		if (this == obj) {
			return true;
		}

		// null check and type check
		if (obj == null || !(obj instanceof Player)) {
			return false;
		}

		// cast and comparisons
		Player otherPlayer = (Player) obj;
		return this.name.equals(otherPlayer.name) && this.score == otherPlayer.score;
	}

	/**
	 * Getter for the players score
	 * 
	 * @return int the player's current score
	 */
	public int getScore() {
		return this.score;
	}

	/**
	 * Increases the player's score by the provided amount
	 * 
	 * @param value
	 *            int the amount wished to be added to the player's score
	 */
	public void addToScore(int value) {
		this.score += value;
	}

	/**
	 * Reverts player's score back to 0
	 */
	public void clearScore() {
		this.score = 0;
	}

	/**
	 * Getter for the player's name
	 * 
	 * @return String the player's name
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Setter for the player's name
	 * 
	 * @param name String the new desired name for the player
	 */
	public void setName(String name) {
		this.name = name;
	}
}