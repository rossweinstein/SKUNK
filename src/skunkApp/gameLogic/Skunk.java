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
	
	public SkunkPlayer[] getPlayers() {
		return this.thePlayers;
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
		System.out.println("WINNER: " + this.thePlayers[0].getName() + "!");
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
			
			System.out.println("\n" + activePlayer.getName() + "'s Turn\n");
			
			if (activePlayer.wantsToRollDice()) {
				this.playerTakesTurn(activePlayer);
			} else {
				System.out.println(activePlayer.getName() + " decides to skip their turn");
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
				this.showDiceRoll();
				System.out.println(activePlayer.getName() + " rolled a double SKUNK!");
				activePlayer.rolledDoubleSkunk();
				this.theKitty.addToKitty(4);
				stillWantsToRoll = false;
				
			} else if (this.skunkWithDeuceRoll()) {
				this.showDiceRoll();
				System.out.println(activePlayer.getName() + " rolled a SKUNK!");
				activePlayer.setChips(-2);
				this.theKitty.addToKitty(2);
				stillWantsToRoll = false;
				
			} else if (this.skunkWithoutDeuceRoll()) {
				this.showDiceRoll();
				System.out.println(activePlayer.getName() + " rolled a SKUNK!");
				activePlayer.setChips(-1);
				this.theKitty.addToKitty(1);
				stillWantsToRoll = false;
				
			} else {
				turnScore += currentRollValue;
				System.out.println(activePlayer.getName() + " rolls a(n) " + currentRollValue );
				
				if (!activePlayer.wantsToRollDice()) {
					
					activePlayer.addToScore(turnScore);
					stillWantsToRoll = false;
					System.out.println(activePlayer.getName() + " passes the dice, now has " + activePlayer.getScore() + " points");
				}
			}
		}
	}
	
	private void showDiceRoll() {
		System.out.println(this.dice.getDieOneValue() + " " + this.dice.getDieTwoValue());
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
