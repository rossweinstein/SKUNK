package skunkApp.game;

import java.util.List;

import helpers.InputHelper;
import model.player.SkunkPlayer;

/**
 * 
 * SkunkGameController brings everything together to play skunk. It handles all
 * the input for the game and ensures the proper functionality for the Skunk
 * Class.
 * 
 * @author Ross Weinstein
 *
 */

public class SkunkController {

	private Skunk skunkGame;
	private InputHelper input;
	private boolean finalRound;

	public SkunkController(int numOfPlayers, String[] userNames) {
		this.skunkGame = new Skunk(numOfPlayers, userNames);
		this.input = new InputHelper();
		this.finalRound = false;
	}

	/*////////////////////////////////////////////
	 * 
	 * BASIC SKUNK PLAYER METHODS
	 * 
	 *////////////////////////////////////////////

	/**
	 * Allows players to play as many rounds of Skunk as they like providing
	 * they have at least 1 chip
	 */
	public void playSkunk() {

		boolean wantsToPlayAnotherRound = true;
		while (wantsToPlayAnotherRound) {

			this.displayPlayers();
			this.playRound();

			/*
			 * after the round is over, and User wants to play more, make sure
			 * players going on to the next round have at least 1 chip, if they
			 * don't, remove them for the next round
			 */
			if (this.theyWantToPlayMoreSkunk()
					&& this.skunkGame.atLeastTwoPlayersHaveEnoughChips(this.skunkGame.getPlayers())) {

				this.skunkGame.setPlayers(this.skunkGame.getRidOfPlayersWithZeroChips(this.skunkGame.getPlayers()));

				// make sure everyone starts with a score of 0 in the next round
				this.skunkGame.clearPlayerScores(this.skunkGame.getPlayers());

				// it is not longer the final round
				this.finalRound = false;

			} else {
				wantsToPlayAnotherRound = false;
			}
		}

		System.out.println("End of Program...");
	}

	/**
	 * Plays a round of Skunk where each player gets a turn rolling the dice
	 * until at least one player has a score of 100 or more
	 */
	private void playRound() {

		int currentPlayersTurn = 0;

		while (!this.skunkGame.hasAtLeastOnePlayerWith100Points(this.skunkGame.getPlayers())) {

			// lets each player take a turn and checks after each turn whether
			// that player has 100 or more points
			this.turn(this.skunkGame.getPlayers().get(currentPlayersTurn++ % this.skunkGame.getPlayers().size()));
		}

		this.playFinalRound();
		this.handleWinner();
	}

	/**
	 * During the main game, each player gets a turn rolling the dice. If they
	 * do not roll a skunk, they will be asked if they want to roll again. If
	 * they do roll a skunk, the proper pentalty will be applied and their turn
	 * is over.
	 * 
	 * @param activePlayer
	 *            List the current SkunkPlayers
	 */
	private void turn(SkunkPlayer activePlayer) {

		int turnScore = 0;

		this.displayTurnBanner(activePlayer);

		boolean stillWantsToRoll = true;
		while (stillWantsToRoll) {

			int[] currentRollValue = this.skunkGame.rollTheDice();

			if (this.skunkGame.playerRolledAnySkunk(currentRollValue)) {

				this.skunkHandler(currentRollValue, activePlayer);
				stillWantsToRoll = false;

			} else {

				// add last roll to turn total
				turnScore += currentRollValue[0] + currentRollValue[1];

				// display the last roll
				this.displayTurnTotal(activePlayer, currentRollValue);

				// if not final round, see if player wants to roll again; if
				// final round, roll until player has a score greater than the
				// current high score before asking if they want to roll again
				stillWantsToRoll = this.scoringRollHandler(turnScore, activePlayer, this.finalRound);
			}
		}
	}

	/**
	 * Prompts the user to see if they want to play another round
	 * 
	 * @return boolean true if they want to play another round; false otherwise
	 */
	private boolean theyWantToPlayMoreSkunk() {
		return this.input.askBinaryQuestion("\nPlay another round? (y/n)", "y", "n");
	}

	/*////////////////////////////////////////////
	 * 
	 * FINAL ROUND METHDOS
	 * 
	 *////////////////////////////////////////////

	/**
	 * To play the final round we need to only allow those players who do not
	 * have the current high score to play. Those players get a turn here.
	 */
	private void playFinalRound() {

		System.out.println("\n-------------------FINAL ROUND-------------------\n");

		this.finalRound = true;

		List<SkunkPlayer> playersToRoll = this.skunkGame.findNonHighScorePlayers(this.skunkGame.getPlayers());
		playersToRoll.stream().forEach(player -> this.turn(player));
	}

	/*////////////////////////////////////////////
	 * 
	 * ROLL METHODS
	 * 
	 *////////////////////////////////////////////

	/**
	 * Handles if it is not the final round, asking the player if they want to
	 * roll again; if it is the final round, the player will roll until they
	 * have a score greater than the current high score or skunk. If their score
	 * exceeding the current high score, then they will be asked if they want to
	 * continue rolling the dice.
	 * 
	 * @param turnScore
	 *            int current value of all the rolls in their turn
	 * @param activePlayer
	 *            SkunkPlayer the current SkunkPlayer
	 * @param lastRound
	 *            boolean whether it is the final round or not
	 * @return boolean true if the SkunkPlayer will roll again; false otherwise
	 */
	private boolean scoringRollHandler(int turnScore, SkunkPlayer activePlayer, boolean lastRound) {

		if (!lastRound) {

			return rollAgain(activePlayer, turnScore);

		} else {

			return rollUntillHasHighScoreOrSkunk(turnScore, activePlayer);
		}
	}

	/**
	 * If it is the final round, SkunkPlayer will not have a choice but to roll
	 * until they exceed the current high score. If they do exceed that score,
	 * they will be prompted to see if they want to roll more.
	 * 
	 * @param turnScore
	 *            int current value of all the rolls in their turn
	 * @param activePlayer
	 *            SkunkPlayer the current SkunkPlayer
	 * @return boolean true if the SkunkPlayer will roll again; false otherwise
	 */
	private boolean rollUntillHasHighScoreOrSkunk(int turnScore, SkunkPlayer activePlayer) {

		// checks if they have the high score before asking if they want to roll
		// again
		if (activePlayer.getScore() + turnScore >= this.skunkGame.theWinner(this.skunkGame.getPlayers()).getScore()
				&& !activePlayer.wantsToRollDice()) {

			this.playerEndsTurnWithoutSkunkRoll(activePlayer, turnScore);
			return false;
		}
		return true;
	}

	/**
	 * Prompts the player to see if they want to roll again. If they do not, add
	 * their turn total to their score and end their turn.
	 * 
	 * @param activePlayer
	 *            SkunkPlayer the current SkunkPlayer
	 * @param turnScore
	 *            int current value of all the rolls in their turn
	 * @return boolean true if the SkunkPlayer will roll again; false otherwise
	 */
	private boolean rollAgain(SkunkPlayer activePlayer, int turnScore) {

		if (!activePlayer.wantsToRollDice()) {

			this.playerEndsTurnWithoutSkunkRoll(activePlayer, turnScore);
			return false;
		}
		return true;
	}

	/**
	 * Adds the SkunkPlayers turn total to their score, displays how many points
	 * they got, and ends their turn
	 * 
	 * @param activePlayer
	 *            SkunkPlayer the current SkunkPlayer
	 * @param turnScore
	 *            int the number of points they got during their turn
	 */
	private void playerEndsTurnWithoutSkunkRoll(SkunkPlayer activePlayer, int turnScore) {

		activePlayer.addToScore(turnScore);
		System.out.println(activePlayer.getName() + " passes the dice, now has " + activePlayer.getScore() + " points");

	}

	/*////////////////////////////////////////////
	 * 
	 * WINNER HANDLER METHODS
	 * 
	 *////////////////////////////////////////////

	/**
	 * Finds the winner, awards them the kitty, print message to console
	 */
	private void handleWinner() {

		// find the winner
		SkunkPlayer gameWinner = this.skunkGame.theWinner(this.skunkGame.getPlayers());

		// pay out kitty to winner
		gameWinner.wonKitty(this.skunkGame.getKitty().getAmount());

		// winner print message
		this.displayWinnerMessage(gameWinner);

		// clear kitty for next round
		this.skunkGame.getKitty().paidOutKitty();
	}

	/*////////////////////////////////////////////
	 * 
	 * SKUNK METHDOS
	 * 
	 *////////////////////////////////////////////

	/**
	 * If the SkunkPlayer rolled a skunk, this sees what kind of Skunk it was
	 * and penalizes the player appropriately and then ends their turn
	 * 
	 * @param currentRollValue
	 *            int current roll value
	 * @param activePlayer
	 *            SkunkPlayer the current SkunkPlayer
	 */
	private void skunkHandler(int[] currentRollValue, SkunkPlayer activePlayer) {

		if (this.skunkGame.doubleSkunk(currentRollValue)) {

			this.handleDoubleSkunk(activePlayer);

		} else if (this.skunkGame.skunkWithDeuceRoll(currentRollValue)) {

			this.handleSkunkWithDeuce(activePlayer);

		} else if (this.skunkGame.skunkWithoutDeuceRoll(currentRollValue)) {

			this.handleSkunkWithoutDeuce(activePlayer);
		}
	}

	/**
	 * Penalizes the player for a Double Skunk roll. Clears their score and they
	 * lose 4 chips to the Kitty.
	 * 
	 * @param activePlayer
	 *            SkunkPlayer the current SkunkPlayer
	 */
	private void handleDoubleSkunk(SkunkPlayer activePlayer) {
		activePlayer.rolledDoubleSkunk();
		this.handleSkunkPenalty(activePlayer, "Double SKUNK", 4);
	}

	/**
	 * Penalizes the player for a Skunk with a deuce roll. They do not record
	 * any points for their turn and lose 2 chips to the Kitty.
	 * 
	 * @param activePlayer
	 *            SkunkPlayer the current SkunkPlayer
	 */
	private void handleSkunkWithDeuce(SkunkPlayer activePlayer) {
		activePlayer.rolledSingleSkunkDeuce();
		this.handleSkunkPenalty(activePlayer, "SKUNK", 2);
	}

	/**
	 * Penalizes the player for a Skunk without a deuce roll. They do not record
	 * any points for their turn and lose 1 chip to the Kitty.
	 * 
	 * @param activePlayer
	 *            SkunkPlayer the current SkunkPlayer
	 */
	private void handleSkunkWithoutDeuce(SkunkPlayer activePlayer) {
		activePlayer.rolledSingleSkunkNoDeuce();
		this.handleSkunkPenalty(activePlayer, "SKUNK", 1);
	}

	/**
	 * Give the appropriate number of chips to the Kitty
	 * 
	 * @param activePlayer
	 *            SkunkPlayer the current SkunkPlayer
	 * @param skunkType
	 *            String for the print message to the console, what type of
	 *            Skunk is this
	 * @param kittyPenalty
	 *            int how many chips must they give to the Kitty
	 */
	private void handleSkunkPenalty(SkunkPlayer activePlayer, String skunkType, int kittyPenalty) {

		System.out.println(activePlayer.getName() + " rolled a " + skunkType + "!");

		if (activePlayer.hasEnoughChips(kittyPenalty)) {

			this.skunkGame.getKitty().addToKitty(kittyPenalty);

		} else {

			this.notEnoughChipsEliminatePlayer(activePlayer);
		}
	}

	/**
	 * If a SkunkPlayer does not have enough chips for thier penalty, all their
	 * remaining chips are put into the kitty and the player is removed from the
	 * game
	 * 
	 * @param activePlayer
	 *            SkunkPlayer the current SkunkPlayer
	 */
	private void notEnoughChipsEliminatePlayer(SkunkPlayer activePlayer) {

		this.displayPlayerDoesNotHaveEnoughChips(activePlayer);

		// player pays to kitty what they can
		this.skunkGame.getKitty().addToKitty(activePlayer.getChips());

		// declare the player as bankrupt and remove them from game
		activePlayer.notEnoughChipsForKitty();
		this.skunkGame.removeBankruptPlayers();

	}

	/*////////////////////////////////////////////
	 * 
	 * CONSOLE DISPLAY METHDOS
	 * 
	 *////////////////////////////////////////////

	/**
	 * Prints the players who are playing the current round to the console
	 */
	private void displayPlayers() {
		System.out.println("Now Playing:");
		this.skunkGame.getPlayers().stream().forEach(player -> System.out.println("--- " + player.getName()));
	}

	/**
	 * Prints to the console the player name and current roll total
	 * 
	 * @param activePlayer
	 *            SkunkPlayer the current SkunkPlayer
	 * @param currentRollValue
	 *            int current roll value
	 */
	private void displayTurnTotal(SkunkPlayer activePlayer, int[] currentRollValue) {
		System.out.println(activePlayer.getName() + " rolls a(n) " + (currentRollValue[0] + currentRollValue[1]));
	}

	/**
	 * Prints to the console the winner of the round and how many chips they won
	 * from the kitty
	 * 
	 * @param gameWinner
	 *            SkunkPlayer the player who won the round
	 */
	private void displayWinnerMessage(SkunkPlayer gameWinner) {
		System.out.println("\nCONGRATULATIONS " + gameWinner.getName() + "! YOU WON THE ROUND AND COLLECT "
				+ this.skunkGame.getKitty().getAmount() + " CHIPS FROM THE KITTY. " + gameWinner.getName()
				+ "  NOW HAS " + gameWinner.getChips() + " CHIPS!");
	}

	/**
	 * Print to the console the players name, score, and current chip total
	 * 
	 * @param activePlayer
	 *            SkunkPlayer the current SkunkPlayer
	 */
	private void displayTurnBanner(SkunkPlayer activePlayer) {
		System.out.println("\n-----------" + activePlayer.getName() + "'s turn----------- " + activePlayer.getScore()
				+ " pts | " + activePlayer.getChips() + " chips");
	}

	/**
	 * Print the the console that the player does not have enough chips
	 * 
	 * @param activePlayer
	 *            SkunkPlayer the current SkunkPlayer
	 */
	private void displayPlayerDoesNotHaveEnoughChips(SkunkPlayer activePlayer) {
		System.out.println(
				"\n" + activePlayer.getName() + " does not have enough chips to play the kitty. Player pays remaining "
						+ activePlayer.getChips() + " to kitty and is removed from game");
	}
}