package game;

import dice.DiceSet;
import player.Player;
import player.PlayerFactory;

public class SkunkGame {

	private Player[] thePlayers;
	private Kitty theKitty;
	private DiceSet dice;
	private int playerTurn = 0;

	public SkunkGame(int numOfPlayers, String[] users) {
		this.thePlayers = PlayerFactory.CreatePlayers(numOfPlayers, users, 50);
		this.theKitty = new Kitty();
		this.dice = new DiceSet();
	}

	public void playGame() {
		
		boolean stillPlay = true;
		
		while (stillPlay) {
		
			this.playRound();
			
			if (!playMore()) {
				stillPlay = false;
			}
		}
	}
	
	private boolean playMore() {
		// TODO Auto-generated method stub
		return false;
	}

	private int nextPlayer() {
		this.playerTurn = (this.playerTurn + 1) % thePlayers.length;
		return this.playerTurn;
	}
	
	public void playRound() {
		
		
		
	}

	public void turn(Player activePlayer) {

		int turnScore = 0;
		int currentRollValue = 0;
		boolean stillWantsToRoll = true;

		while (stillWantsToRoll) {
			
			currentRollValue = this.dice.roll();

			if (this.doubleSkunk()) {
				activePlayer.rolledDoubleSkunk();
				stillWantsToRoll = false;
				
			} else if (this.skunkWithDeuceRoll()) {
				activePlayer.setChips(-2);
				stillWantsToRoll = false;
				
			} else if (this.skunkWithoutDeuceRoll()) {
				activePlayer.setChips(-1);
				stillWantsToRoll = false;
				
			} else {
				turnScore += currentRollValue;
				
				if (!rollMore()) {
					activePlayer.addRollToScore(turnScore);
					stillWantsToRoll = false;
				}
			}
		}
	}

	private boolean rollMore() {
		// TODO Auto-generated method stub
		return false;
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
