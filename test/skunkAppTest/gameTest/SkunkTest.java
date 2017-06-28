package skunkAppTest.gameTest;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import model.player.SkunkPlayer;
import skunkApp.game.Skunk;

public class SkunkTest {
	
	private Skunk skunk;
	
	@Before
	public void SetUp() {
		this.skunk = new Skunk(4, new String[] {"Ross",  "Weinstein"});
		this.skunk.getPlayers().get(0).addToScore(104);
		this.skunk.getPlayers().get(1).addToScore(34);
		this.skunk.getPlayers().get(2).addToScore(150);
		this.skunk.getPlayers().get(3).addToScore(51);
	}
	
	@Test
	public void playerRolledDoubleSkunk() {
		int[] doubleSkunkRoll = new int[] {1, 1};
		assertTrue(this.skunk.doubleSkunk(doubleSkunkRoll));
	}
	
	@Test
	public void playerRolledDoubleSkunkFalse() {
		int[] notDoubleSkunkRoll = new int[] {1, 3};
		assertFalse(this.skunk.doubleSkunk(notDoubleSkunkRoll));
	}
	
	@Test
	public void playerRolledSkunkAndDeuce() {
		int[] skunkAndDeuceRoll = new int[] {1, 2};
		assertTrue(this.skunk.skunkWithDeuceRoll(skunkAndDeuceRoll));
	}
	
	@Test
	public void playerRolledSkunkAndDeuceFalse() {
		int[] notSkunkAndDeuceRoll = new int[] {1, 3};
		assertFalse(this.skunk.skunkWithDeuceRoll(notSkunkAndDeuceRoll));
	}
	
	@Test
	public void playerRolledSkunkWithoutDeuce() {
		int[] skunkWithoutDeuceRoll = new int[] {1, 3};
		assertTrue(this.skunk.skunkWithoutDeuceRoll(skunkWithoutDeuceRoll));
	}
	
	@Test
	public void playerRolledSkunkWithoutDeuceFalse() {
		int[] notSkunkWithoutDeuceRoll = new int[] {3, 3};
		assertFalse(this.skunk.skunkWithoutDeuceRoll(notSkunkWithoutDeuceRoll));
	}
	
	@Test
	public void canTellDifferenceBetweenSingleSkunkRolls() {
		int[] notSkunkWithoutDeuceRoll = new int[] {1, 2};
		assertFalse(this.skunk.skunkWithoutDeuceRoll(notSkunkWithoutDeuceRoll));
	}
	
	@Test
	public void ranksPlayersInDescendingOrder() {
		
		List<SkunkPlayer> comparisonList = new ArrayList<>();
		comparisonList.add(this.skunk.getPlayers().get(2));
		comparisonList.add(this.skunk.getPlayers().get(0));
		comparisonList.add(this.skunk.getPlayers().get(3));
		comparisonList.add(this.skunk.getPlayers().get(1));
		
		List<SkunkPlayer> sortedPlayers = this.skunk.sortPlayersInDesceningOrder(this.skunk.getPlayers());
		assertTrue(sortedPlayers.equals(comparisonList));
	}
	
	@Test
	public void getsAllPlayersButTopScoreForFinalRound() {
		
		List<SkunkPlayer> comparisonList = new ArrayList<>();
		comparisonList.add(this.skunk.getPlayers().get(0));
		comparisonList.add(this.skunk.getPlayers().get(3));
		comparisonList.add(this.skunk.getPlayers().get(1));
		
		List<SkunkPlayer> nonTopPlayers = this.skunk.findNonHighScorePlayers(this.skunk.getPlayers());
		assertTrue(nonTopPlayers.equals(comparisonList));
	}
	
	@Test
	public void getsAllPlayersButTopScoreForFinalRoundFalse() {
		
		List<SkunkPlayer> comparisonList = new ArrayList<>();
		comparisonList.add(this.skunk.getPlayers().get(2));
		comparisonList.add(this.skunk.getPlayers().get(0));
		comparisonList.add(this.skunk.getPlayers().get(3));
		
		List<SkunkPlayer> nonTopPlayers = this.skunk.findNonHighScorePlayers(this.skunk.getPlayers());
		assertFalse(nonTopPlayers.equals(comparisonList));
	}
	
	@Test
	public void somebodyHas100Points() {
		assertTrue(this.skunk.hasAtLeastOnePlayerWith100Points(this.skunk.getPlayers()));
	}
	
	@Test
	public void somebodyDoesNotHave100Points() {
		
		List<SkunkPlayer> lessThan100 = new ArrayList<>();
		lessThan100.add(this.skunk.getPlayers().get(3));
		lessThan100.add(this.skunk.getPlayers().get(1));
		
		assertFalse(this.skunk.hasAtLeastOnePlayerWith100Points(lessThan100));
	}
	
	@Test
	public void weHaveAWinner() {
		assertTrue(this.skunk.theWinner(this.skunk.getPlayers()).equals(this.skunk.getPlayers().get(2)));
	}
	
	@Test
	public void weDontHaveAWinner() {
		
		List<SkunkPlayer> lessThan100 = new ArrayList<>();
		lessThan100.add(this.skunk.getPlayers().get(3));
		lessThan100.add(this.skunk.getPlayers().get(1));
		
		assertNull(this.skunk.theWinner(lessThan100));
	}
	
	@Test
	public void playerRolledAnySkunkTrue1() {
		int[] diceRoll = new int[] {1, 2};
		assertTrue(this.skunk.playerRolledAnySkunk(diceRoll));
	}
	
	@Test
	public void playerRolledAnySkunkTrue2() {
		int[] diceRoll = new int[] {1, 1};
		assertTrue(this.skunk.playerRolledAnySkunk(diceRoll));
	}
	
	@Test
	public void playerRolledAnySkunkTrue3() {
		int[] diceRoll = new int[] {1, 5};
		assertTrue(this.skunk.playerRolledAnySkunk(diceRoll));
	}
	
	@Test
	public void playerRolledAnySkunkFalse() {
		int[] diceRoll = new int[] {2, 2};
		assertFalse(this.skunk.playerRolledAnySkunk(diceRoll));
	}
	
	@Test
	public void endOfGameClearPlayerScores() {
		this.skunk.clearPlayerScores(this.skunk.getPlayers());
		assertTrue(this.skunk.getPlayers().get(0).getScore() == 0);
	}
	
	@Test
	public void atLeastTwoPlayersCanPlay() {
		assertTrue(this.skunk.atLeastTwoPlayersHaveEnoughChips(this.skunk.getPlayers()));
	}
	
	@Test
	public void atLeastTwoPlayersCantPlay() {
		
		this.skunk.getPlayers().get(0).setChips(-104);
		this.skunk.getPlayers().get(1).setChips(-134);
		this.skunk.getPlayers().get(2).setChips(-150);
		this.skunk.getPlayers().get(3).setChips(-51);
		assertFalse(this.skunk.atLeastTwoPlayersHaveEnoughChips(this.skunk.getPlayers()));	
	}
	
	@Test
	public void getRidOfPlayerWithZeroChips() {
		
		this.skunk.getPlayers().get(0).setChips(-104);
		
		List<SkunkPlayer> players = this.skunk.getRidOfPlayersWithZeroChips(this.skunk.getPlayers());
		assertTrue(players.size() == 3);
	}
	
	@Test
	public void allPlayersHaveChipsDontGetRidOfAny() {
		
		List<SkunkPlayer> players = this.skunk.getRidOfPlayersWithZeroChips(this.skunk.getPlayers());
		assertTrue(players.size() == 4);
	}
	
	@Test
	public void removeBankruptPlayerFromGame() {
		this.skunk.getPlayers().get(0).notEnoughChipsForKitty();
		this.skunk.removeBankruptPlayers();
		assertTrue(this.skunk.getPlayers().size() == 3);
	}
	
	@Test
	public void noPlayersAreBankrupt() {
		this.skunk.removeBankruptPlayers();
		assertTrue(this.skunk.getPlayers().size() == 4);
	}
}
