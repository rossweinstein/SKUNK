package skunkApp.gameLogic;

import java.util.Arrays;

public class SkunkGameController {

	private Skunk skunkGame;
	
	public SkunkGameController(int numOfPlayers, String[] userNames) {
		this.skunkGame = new Skunk(numOfPlayers, userNames);
	}
	
	public void displayPlayers() {
		System.out.println("Players:");
		Arrays.stream(this.skunkGame.getPlayers()).forEach(player -> System.out.println("----  " + player.getName()));
	}
	
	public void PlaySkunk() {
		
		this.displayPlayers();
		

			
			this.skunkGame.playGame();
			
			
		
		
		
		
	}
	
	public void shufflePlayers() {
		
	}
	
	public static void main (String[] args) {
		
		SkunkGameController skunky = new SkunkGameController(4, new String[] {"Ross", "Weinstein"});
		skunky.PlaySkunk();
	}
	
}
