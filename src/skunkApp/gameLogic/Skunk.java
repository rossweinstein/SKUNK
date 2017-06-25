package skunkApp.gameLogic;

import java.util.Arrays;

import model.Kitty;
import model.dice.DiceSet;
import model.player.PlayerFactory;
import model.player.SkunkPlayer;

public class Skunk {

	private SkunkPlayer[] thePlayers;
	private Kitty theKitty;
	private DiceSet dice;

	public Skunk(int numOfPlayers, String[] users) {
		this.thePlayers = PlayerFactory.CreatePlayers(numOfPlayers, users, 50);
		this.theKitty = new Kitty();
		this.dice = new DiceSet();
	}

	public void playGame() {
		
		while (!this.atLeastOnePlayerAtLeast100Points()) {
			this.playRound();
		}
		
		this.playFinalRound();
		this.roundResults();
	}
	
	private void roundResults() {
		Arrays.sort(this.thePlayers, (first, second) -> first.compareTo(second));
		System.out.println("WINNER: " + this.thePlayers[0].getPlayer().getName() + "!");
	}
	
	private boolean atLeastOnePlayerAtLeast100Points() {
		
		for (SkunkPlayer player : this.thePlayers) {
			if (player.scoreAtLeast100()) {
				return true;
			}
		}
		return false;
	}
	
	public void playRound() {
		
		for (SkunkPlayer activePlayer : this.thePlayers) {
			
			if (activePlayer.wantsToRollDice()) {
				this.playerTakesTurn(activePlayer);
			}
		}
	}
	
	private void playFinalRound() {
		
		SkunkPlayer[] playersToRoll = this.findNonHighScorePlayers();
		
		for (SkunkPlayer activePlayer : playersToRoll) {
			this.playerTakesTurn(activePlayer);
		}
	}

	private SkunkPlayer[] findNonHighScorePlayers() {
		
		Arrays.sort(this.thePlayers, (first, second) -> first.compareTo(second));
		return Arrays.copyOfRange(this.thePlayers, 1, this.thePlayers.length - 1);
	}

	public void playerTakesTurn(SkunkPlayer activePlayer) {

		int turnScore = 0;
		int currentRollValue = 0;
		boolean stillWantsToRoll = true;

		while (stillWantsToRoll) {
			
			currentRollValue = this.dice.roll();

			if (this.doubleSkunk()) {
				activePlayer.rolledDoubleSkunk();
				this.theKitty.addToKitty(4);
				stillWantsToRoll = false;
				
			} else if (this.skunkWithDeuceRoll()) {
				activePlayer.setChips(-2);
				this.theKitty.addToKitty(2);
				stillWantsToRoll = false;
				
			} else if (this.skunkWithoutDeuceRoll()) {
				activePlayer.setChips(-1);
				this.theKitty.addToKitty(1);
				stillWantsToRoll = false;
				
			} else {
				turnScore += currentRollValue;
				
				if (!activePlayer.wantsToRollDice()) {
					activePlayer.addRollToScore(turnScore);
					stillWantsToRoll = false;
				}
			}
		}
	}

	private boolean skunkWithDeuceRoll() {
		return this.dice.getLastDiceRoll() == 3;
	}

	private boolean skunkWithoutDeuceRoll() {
		return this.dice.getLastDiceRoll() > 3 && this.dice.getDieOneValue() == 1 || this.dice.getDieTwoValue() == 1;
	}

	private boolean doubleSkunk() {
		return this.dice.getLastDiceRoll() == 2;
	}
}
