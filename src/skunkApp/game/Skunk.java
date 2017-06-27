package skunkApp.game;

import java.util.List;
import java.util.stream.Collectors;

import model.Kitty;
import model.dice.DiceSet;
import model.player.PlayerFactory;
import model.player.SkunkPlayer;

/**
 * 
 * Contains all the functionality for the playing of the dice game skunk.
 * 
 * @author Ross Weinstein
 *
 */

public class Skunk {

	private List<SkunkPlayer> thePlayers;
	private Kitty theKitty;
	private DiceSet dice;

	public Skunk(int numOfPlayers, String[] users) {
		this.thePlayers = PlayerFactory.CreatePlayers(numOfPlayers, users, 50);
		this.theKitty = new Kitty();
		this.dice = new DiceSet();
	}

	/**
	 * Getter for the current SkunkPlayers
	 * 
	 * @return List of SkunkPlayer
	 */
	public List<SkunkPlayer> getPlayers() {
		return this.thePlayers;
	}

	/**
	 * Setter for the SkunkPlayer
	 * 
	 * @param players
	 *            List the SkunkPlayers you want to play the game
	 */
	public void setPlayers(List<SkunkPlayer> players) {
		this.thePlayers = players;
	}

	/**
	 * Getter for the Kitty
	 * 
	 * @return Kitty the current Kitty
	 */
	public Kitty getKitty() {
		return this.theKitty;
	}

	/**
	 * Finds out if there is a winner or not. Checks to see if the player with
	 * the highest score has a score of at least 100. If no player exists, it
	 * will return null instead of the player.
	 * 
	 * @param skunkPlayers
	 *            List all the current SkunkPlayers
	 * @return SkunkPlayer the winner player; if no winner is found it will
	 *         return null
	 */
	public SkunkPlayer theWinner(List<SkunkPlayer> skunkPlayers) {

		List<SkunkPlayer> sortedPlayers = this.sortPlayersInDesceningOrder(skunkPlayers);

		if (this.weHaveAWinner(sortedPlayers)) {
			return sortedPlayers.get(0);

		} else {
			return null;
		}
	}

	/**
	 * Checks to see if the current layer with the high score has a score that
	 * is at least 100 and is greater than the player is second place
	 * 
	 * @param currentPlayers
	 *            List the current SkunkPlayer List
	 * @return boolean true if we have a winner; false otherwise
	 */
	private boolean weHaveAWinner(List<SkunkPlayer> currentPlayers) {
		return currentPlayers.get(0).scoreAtLeast100()
				&& currentPlayers.get(0).getScore() > currentPlayers.get(1).getScore();
	}

	/**
	 * Checks to see if at least one player has 100 points. This is needed to
	 * trigger the final round where every other player has an opportunity to
	 * beat this play with one more turn rolling the dice.
	 * 
	 * @param skunkPlayers
	 *            List the current SkunkPlayer List
	 * @return boolean true if we have a player with at least 100 points; false
	 *         otherwise
	 */
	public boolean hasAtLeastOnePlayerWith100Points(List<SkunkPlayer> skunkPlayers) {

		return this.sortPlayersInDesceningOrder(skunkPlayers).get(0).scoreAtLeast100();
	}

	/**
	 * Returns a list of all players but the one with the high score. This is
	 * needed for the final round where all of these players get one more chance
	 * rolling the dice in order to beat the player with the high score
	 * 
	 * @param skunkPlayers
	 *            List the current SkunkPlayer List
	 * @return List the SkunkPlayer that are allowed one more turn in order to
	 *         try and beat the player with the current high score
	 */
	public List<SkunkPlayer> findNonHighScorePlayers(List<SkunkPlayer> skunkPlayers) {

		return this.sortPlayersInDesceningOrder(skunkPlayers).subList(1, this.thePlayers.size());
	}

	/**
	 * Returns a sorted List where the SkunkPlayers are sorted in descending
	 * order
	 * 
	 * @param skunkPlayers
	 *            List the current SkunkPlayer List
	 * @return List all the SkunkPlayers sorted in descending order
	 */
	public List<SkunkPlayer> sortPlayersInDesceningOrder(List<SkunkPlayer> skunkPlayers) {

		return skunkPlayers.stream().sorted((player1, player2) -> player1.compareTo(player2))
				.collect(Collectors.toList());
	}

	/**
	 * Simulates the SkunkPlayer rolling a set of dice
	 * 
	 * @return int[] an array of size 2 where each individual die's value is
	 *         represented
	 */
	public int[] rollTheDice() {
		return this.dice.roll();
	}

	/**
	 * Determines if the roll was a Skunk with a deuce
	 * 
	 * @param lastRoll
	 *            int[] the last dice roll
	 * @return boolean true if a skunk with a deuce; false otherwise
	 */
	public boolean skunkWithDeuceRoll(int[] lastRoll) {
		return lastRoll[0] + lastRoll[1] == 3;
	}

	/**
	 * Determines if the roll was a Skunk without a deuce
	 * 
	 * @param lastRoll
	 *            int[] the last dice roll
	 * @return boolean true if a skunk without a deuce; false otherwise
	 */
	public boolean skunkWithoutDeuceRoll(int[] lastRoll) {
		return lastRoll[0] + lastRoll[1] > 3 && lastRoll[0] == 1 || lastRoll[1] == 1;
	}

	/**
	 * Determines if the roll was a Double Skunk
	 * 
	 * @param lastRoll
	 *            int[] the last dice roll
	 * @return boolean true if a Double Skunk; false otherwise
	 */
	public boolean doubleSkunk(int[] lastRoll) {
		return lastRoll[0] + lastRoll[1] == 2;
	}
}
