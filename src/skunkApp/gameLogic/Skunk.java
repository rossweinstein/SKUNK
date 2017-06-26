package skunkApp.gameLogic;

import java.util.Arrays;
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

	public SkunkPlayer theWinner() {
		
		List<SkunkPlayer> sortedPlayers = this.sortPlayersInDesceningOrder();

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

	public boolean atLeastOnePlayerAtLeast100Points() {

		return this.sortPlayersInDesceningOrder().get(0).scoreAtLeast100();
	}

	public List<SkunkPlayer> findNonHighScorePlayers() {

		return this.sortPlayersInDesceningOrder().subList(1, this.thePlayers.size() - 1);
	}

	private List<SkunkPlayer> sortPlayersInDesceningOrder() {
		
		return this.thePlayers.stream()
								.sorted((player1, player2) -> player1.compareTo(player2))
								.collect(Collectors.toList());
	}
	
	public int rollTheDice() {
		this.dice.roll();
		return this.dice.getLastDiceRoll();
	}

	public int[] getLastDiceRoll() {
		return new int[] { this.dice.getDieOneValue(), this.dice.getDieTwoValue() };
	}

	public boolean skunkWithDeuceRoll() {
		return this.dice.getLastDiceRoll() == 3;
	}

	public boolean skunkWithoutDeuceRoll() {
		return this.dice.getLastDiceRoll() > 3 && this.dice.getDieOneValue() == 1 || this.dice.getDieTwoValue() == 1;
	}

	public boolean doubleSkunk() {
		return this.dice.getLastDiceRoll() == 2;
	}
}
