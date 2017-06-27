package skunkApp.gameLogic;

import java.util.List;
import java.util.stream.Collectors;

import helpers.InputHelper;
import model.player.SkunkPlayer;

public class SkunkGameController {

	private Skunk skunkGame;
	private InputHelper input;

	public SkunkGameController(int numOfPlayers, String[] userNames) {
		this.skunkGame = new Skunk(numOfPlayers, userNames);
		this.input = new InputHelper();
	}

	public void playSkunk() {

		this.displayPlayers();
		this.playGame();
	}

	private void playGame() {

		boolean wantsToPlayAnotherRound = true;
		while (wantsToPlayAnotherRound) {

			this.playRound();

			if (this.theyWantToPlayMoreSkunk() && this.atLeastTwoPlayersHaveEnoughChips(this.skunkGame.getPlayers())) {
				this.skunkGame.setPlayers(this.getRidOfPlayersWithZeroChips(this.skunkGame.getPlayers()));
				this.clearPlayerScores(this.skunkGame.getPlayers());
			} else {
				wantsToPlayAnotherRound = false;
			}
		}
		
		System.out.println("End of Program...");
	}

	private void clearPlayerScores(List<SkunkPlayer> players) {
		players.stream().forEach(player -> player.clearScore());
	}

	private boolean atLeastTwoPlayersHaveEnoughChips(List<SkunkPlayer> players) {
		return players.stream().filter(player -> player.getChips() > 0).collect(Collectors.toList()).size() >= 2;
	}

	private List<SkunkPlayer> getRidOfPlayersWithZeroChips(List<SkunkPlayer> players) {
		return players.stream().filter(player -> player.getChips() > 0).collect(Collectors.toList());
	}

	private boolean theyWantToPlayMoreSkunk() {
		return this.input.askBinaryQuestion("\nPlay another round? (y/n)", "y", "n");
	}

	private void displayPlayers() {
		System.out.println("Now Playing:");
		this.skunkGame.getPlayers().stream().forEach(player -> System.out.println("--- " + player.getName()));
	}

	private void playRound() {
		
		int currentPlayersTurn = 0;

		while (!this.skunkGame.hasAtLeastOnePlayerWith100Points(this.skunkGame.getPlayers())) {
			this.turn(this.skunkGame.getPlayers().get(currentPlayersTurn++ % this.skunkGame.getPlayers().size()));
		}

		this.playFinalRound();
		this.printWinner();
	}

	private void printWinner() {
		SkunkPlayer gameWinner = this.skunkGame.theWinner(this.skunkGame.getPlayers());
		gameWinner.wonKitty(this.skunkGame.getKitty().getAmount());
		System.out.println("\nCONGRATULATIONS " + gameWinner.getName() + "! YOU WON THE ROUND AND YOU COLLECT "
				+ this.skunkGame.getKitty().getAmount() + " CHIPS FROM THE KITTY. " + gameWinner.getName() + "  NOW HAS " + gameWinner.getChips() + " CHIPS!");
		
		this.skunkGame.getKitty().paidOutKitty();

	}

	private void playFinalRound() {
		
		System.out.println("\n-------------------FINAL ROUND-------------------\n");
		
		List<SkunkPlayer> playersToRoll = this.skunkGame.findNonHighScorePlayers(this.skunkGame.getPlayers());
		playersToRoll.stream().forEach(player -> this.finalTurn(player));
	}

	private void turn(SkunkPlayer activePlayer) {

		int turnScore = 0;

		System.out.println("\n-----------" + activePlayer.getName() + "'s turn----------- " + activePlayer.getScore()
				+ " pts | " + activePlayer.getChips() + " chips");

		boolean stillWantsToRoll = true;
		while (stillWantsToRoll) {

			int[] currentRollValue = this.skunkGame.rollTheDice();

			if (this.skunkGame.doubleSkunk(currentRollValue)) {

				stillWantsToRoll = this.handleDoubleSkunk(activePlayer);

			} else if (this.skunkGame.skunkWithDeuceRoll(currentRollValue)) {

				stillWantsToRoll = this.handleSkunkWithDeuce(activePlayer);

			} else if (this.skunkGame.skunkWithoutDeuceRoll(currentRollValue)) {

				stillWantsToRoll = this.handleSkunkWithoutDeuce(activePlayer);

			} else {

				turnScore += currentRollValue[0] + currentRollValue[1];
				System.out
						.println(activePlayer.getName() + " rolls a(n) " + (currentRollValue[0] + currentRollValue[1]));

				if (!activePlayer.wantsToRollDice()) {

					stillWantsToRoll = this.playerEndsTurnWithoutSkunkRoll(activePlayer, turnScore);
				}
			}
		}
	}
	
	private void finalTurn(SkunkPlayer activePlayer) {

		int turnScore = 0;

		System.out.println("\n-----------" + activePlayer.getName() + "'s turn----------- " + activePlayer.getScore()
				+ " pts | " + activePlayer.getChips() + " chips");

		boolean stillWantsToRoll = true;
		while (stillWantsToRoll) {

			int[] currentRollValue = this.skunkGame.rollTheDice();

			if (this.skunkGame.doubleSkunk(currentRollValue)) {

				stillWantsToRoll = this.handleDoubleSkunk(activePlayer);

			} else if (this.skunkGame.skunkWithDeuceRoll(currentRollValue)) {

				stillWantsToRoll = this.handleSkunkWithDeuce(activePlayer);

			} else if (this.skunkGame.skunkWithoutDeuceRoll(currentRollValue)) {

				stillWantsToRoll = this.handleSkunkWithoutDeuce(activePlayer);

			} else {

				turnScore += currentRollValue[0] + currentRollValue[1];
				System.out
						.println(activePlayer.getName() + " rolls a(n) " + (currentRollValue[0] + currentRollValue[1]));

				if (activePlayer.getScore() >= this.skunkGame.theWinner(this.skunkGame.getPlayers()).getScore() && !activePlayer.wantsToRollDice()) {

					stillWantsToRoll = this.playerEndsTurnWithoutSkunkRoll(activePlayer, turnScore);
				}
			}
		}
	}

	private boolean playerEndsTurnWithoutSkunkRoll(SkunkPlayer activePlayer, int turnScore) {

		activePlayer.addToScore(turnScore);
		System.out.println(activePlayer.getName() + " passes the dice, now has " + activePlayer.getScore() + " points");
		return false;
	}

	private boolean handleDoubleSkunk(SkunkPlayer activePlayer) {
		activePlayer.rolledDoubleSkunk();
		return this.handeSkunk(activePlayer, "Double SKUNK", 4);
	}

	private boolean handleSkunkWithDeuce(SkunkPlayer activePlayer) {
		activePlayer.rolledSingleSkunkDeuce();
		return this.handeSkunk(activePlayer, "SKUNK", 2);
	}

	private boolean handleSkunkWithoutDeuce(SkunkPlayer activePlayer) {
		activePlayer.rolledSingleSkunkNoDeuce();
		return this.handeSkunk(activePlayer, "SKUNK", 1);
	}

	private boolean handeSkunk(SkunkPlayer activePlayer, String skunkType, int kittyPenalty) {

		System.out.println(activePlayer.getName() + " rolled a " + skunkType + "!");
		this.skunkGame.getKitty().addToKitty(kittyPenalty);
		return false;
	}
}