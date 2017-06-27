package skunkApp.gameLogic;

import java.util.List;

import model.player.SkunkPlayer;

public class SkunkGameController {

	private Skunk skunkGame;
	
	public SkunkGameController(int numOfPlayers, String[] userNames) {
		this.skunkGame = new Skunk(numOfPlayers, userNames);
	}
	
	public void displayPlayers() {
		System.out.println("Players:");
		
	}
	
	public void PlaySkunk() {
		
		this.displayPlayers();

	}
	
public void playGame() {
		
		while (!this.skunkGame.atLeastOnePlayerAtLeast100Points(this.skunkGame.getPlayers())) {
			this.playRound();
		}
		
		this.playFinalRound();
	
	}
	
	public void playerTakesTurn(SkunkPlayer activePlayer) {

		int turnScore = 0;
		int[] currentRollValue = null;
		boolean stillWantsToRoll = true;
		
		

		while (stillWantsToRoll) {
			
			currentRollValue = this.skunkGame.rollTheDice();
			

			if (this.skunkGame.doubleSkunk(currentRollValue)) {
			
				System.out.println(activePlayer.getName() + " rolled a double SKUNK!");
				activePlayer.rolledDoubleSkunk();
				this.skunkGame.getKitty().addToKitty(4);
				stillWantsToRoll = false;
				
			} else if (this.skunkGame.skunkWithDeuceRoll(currentRollValue)) {
				
				System.out.println(activePlayer.getName() + " rolled a SKUNK!");
				activePlayer.setChips(-2);
				this.skunkGame.getKitty().addToKitty(2);
				stillWantsToRoll = false;
				
			} else if (this.skunkGame.skunkWithoutDeuceRoll(currentRollValue)) {
				
				System.out.println(activePlayer.getName() + " rolled a SKUNK!");
				activePlayer.setChips(-1);
				this.skunkGame.getKitty().addToKitty(1);
				stillWantsToRoll = false;
				
			} else {
				turnScore += currentRollValue[0] + currentRollValue[1];
				System.out.println(activePlayer.getName() + " rolls a(n) " + currentRollValue );
				
				if (!activePlayer.wantsToRollDice()) {
					
					activePlayer.addToScore(turnScore);
					stillWantsToRoll = false;
					System.out.println(activePlayer.getName() + " passes the dice, now has " + activePlayer.getScore() + " points");
				}
			}
		}
	}
	
	public void shufflePlayers() {
		
	}
public void playRound() {
		
		for (SkunkPlayer activePlayer : this.skunkGame.getPlayers()) {
			
			System.out.println("\n" + activePlayer.getName() + "'s Turn\n");
			
			if (activePlayer.wantsToRollDice()) {
				this.playerTakesTurn(activePlayer);
			} else {
				System.out.println(activePlayer.getName() + " decides to skip their turn");
			}
		}
	}
	
	private void playFinalRound() {
		
		List<SkunkPlayer> playersToRoll = this.skunkGame.findNonHighScorePlayers(this.skunkGame.getPlayers());
		
		for (SkunkPlayer activePlayer : playersToRoll) {
			this.playerTakesTurn(activePlayer);
		}
	}
	
	public static void main (String[] args) {
		
		SkunkGameController skunky = new SkunkGameController(4, new String[] {"Ross", "Weinstein"});
		skunky.PlaySkunk();
	}
	
}
