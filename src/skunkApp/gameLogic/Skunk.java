package skunkApp.gameLogic;

import java.util.List;
import java.util.stream.Collectors;

import model.Kitty;
import model.dice.DiceSet;
import model.player.PlayerFactory;
import model.player.SkunkPlayer;

public class Skunk {

	private List<SkunkPlayer> thePlayers;
	private Kitty theKitty;
	private DiceSet dice;

	public Skunk(int numOfPlayers, String[] users) {
		this.thePlayers = PlayerFactory.CreatePlayers(numOfPlayers, users, 50);
		this.theKitty = new Kitty();
		this.dice = new DiceSet();
	}

	public List<SkunkPlayer> getPlayers() {
		return this.thePlayers;
	}

	public Kitty getKitty() {
		return this.theKitty;
	}

	public SkunkPlayer theWinner(List<SkunkPlayer> skunkPlayers) {
		
		List<SkunkPlayer> sortedPlayers = this.sortPlayersInDesceningOrder(skunkPlayers);

		if (this.weHaveAWinner(sortedPlayers)) {
			return sortedPlayers.get(0);

		} else {
			return null;
		}
	}

	private boolean weHaveAWinner(List<SkunkPlayer> currentPlayers) {
		return currentPlayers.get(0).scoreAtLeast100()
				&& currentPlayers.get(0).getScore() > currentPlayers.get(1).getScore();
	}

	public boolean atLeastOnePlayerAtLeast100Points(List<SkunkPlayer> skunkPlayers) {

		return this.sortPlayersInDesceningOrder(skunkPlayers).get(0).scoreAtLeast100();
	}

	public List<SkunkPlayer> findNonHighScorePlayers(List<SkunkPlayer> skunkPlayers) {

		return this.sortPlayersInDesceningOrder(skunkPlayers).subList(1, this.thePlayers.size());
	}

	public List<SkunkPlayer> sortPlayersInDesceningOrder(List<SkunkPlayer> skunkPlayers) {
		
		return skunkPlayers.stream()
								.sorted((player1, player2) -> player1.compareTo(player2))
								.collect(Collectors.toList());
	}
	
	public int[] rollTheDice() {
		return this.dice.roll();
	}

	public boolean skunkWithDeuceRoll(int[] lastRoll) {
		return lastRoll[0] + lastRoll[1] == 3;
	}

	public boolean skunkWithoutDeuceRoll(int[] lastRoll) {
		return lastRoll[0] + lastRoll[1] > 3 && lastRoll[0] == 1 || lastRoll[1] == 1;
	}

	public boolean doubleSkunk(int[] lastRoll) {
		return lastRoll[0] + lastRoll[1] == 2;
	}
}
