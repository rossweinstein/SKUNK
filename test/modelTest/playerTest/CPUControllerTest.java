package modelTest.playerTest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import model.player.CPUSkunkPlayer;
import model.player.SkunkPlayer;

public class CPUControllerTest {
	
	private SkunkPlayer cpuPlayer;
	
	
	@Before
	public void SetUp() {
		this.cpuPlayer = new CPUSkunkPlayer("Ross", 50);
	}

	@Test
	public void playerInitializedCorrectly() {
		assertTrue(this.cpuPlayer.getName().equals("Ross"));
		assertTrue(this.cpuPlayer.getChips() == 50);
		assertTrue(this.cpuPlayer.getScore() == 0);
	}
	
	@Test
	public void playerAddsToScoreCorrectly() {
		this.cpuPlayer.addToScore(12);
		this.cpuPlayer.addToScore(10);
		assertTrue(this.cpuPlayer.getScore() == 22);
	}
	
	@Test
	public void playerPenalizedForSkunkAndDeuce() {
		this.cpuPlayer.addToScore(12);
		this.cpuPlayer.addToScore(10);
		this.cpuPlayer.rolledSingleSkunkDeuce();
		assertTrue(this.cpuPlayer.getChips() == 48);
		assertTrue(this.cpuPlayer.getScore() == 22);
	}
	
	@Test
	public void playerPenalizedForSkunkAndDeuce2() {
		this.cpuPlayer.addToScore(12);
		this.cpuPlayer.addToScore(10);
		this.cpuPlayer.rolledSingleSkunkDeuce();
		assertFalse(this.cpuPlayer.getChips() == 52);
		assertTrue(this.cpuPlayer.getScore() == 22);
	}
	
	@Test
	public void playerPenalizedForSkunkWithoutDeuce() {
		this.cpuPlayer.addToScore(12);
		this.cpuPlayer.addToScore(10);
		this.cpuPlayer.rolledSingleSkunkNoDeuce();
		assertTrue(this.cpuPlayer.getChips() == 49);
		assertTrue(this.cpuPlayer.getScore() == 22);
	}
	
	@Test
	public void playerPenalizedForSkunkWithoutDeuce2() {
		this.cpuPlayer.addToScore(12);
		this.cpuPlayer.addToScore(10);
		this.cpuPlayer.rolledSingleSkunkNoDeuce();
		assertFalse(this.cpuPlayer.getChips() == 51);
		assertTrue(this.cpuPlayer.getScore() == 22);
	}
	
	@Test
	public void playerPenalizedForDoubleSkunk() {
		this.cpuPlayer.addToScore(12);
		this.cpuPlayer.addToScore(10);
		this.cpuPlayer.rolledDoubleSkunk();
		assertTrue(this.cpuPlayer.getChips() == 46);
		assertTrue(this.cpuPlayer.getScore() == 0);
	}
	
	@Test
	public void playerPenalizedForDoubleSkunk2() {
		this.cpuPlayer.addToScore(12);
		this.cpuPlayer.addToScore(10);
		this.cpuPlayer.rolledDoubleSkunk();
		assertTrue(this.cpuPlayer.getChips() == 46);
		assertFalse(this.cpuPlayer.getScore() == 50);
	}
	
	@Test
	public void playerDoesNotHaveEnoughChips() {
		assertFalse(this.cpuPlayer.hasEnoughChips(51));
	}
	
	@Test
	public void playerDoesHaveEnoughChips() {
		assertTrue(this.cpuPlayer.hasEnoughChips(5));
	}
	
	@Test
	public void playerDoesHaveEnoughChips2() {
		assertTrue(this.cpuPlayer.hasEnoughChips(50));
	}
	
	@Test
	public void playerScoreNotAtLeast100() {
		this.cpuPlayer.addToScore(4);
		assertFalse(this.cpuPlayer.scoreAtLeast100());
	}
	
	@Test
	public void playerScoreIs100() {
		this.cpuPlayer.addToScore(100);
		assertTrue(this.cpuPlayer.scoreAtLeast100());
	}
	
	@Test
	public void playerScoreIsGreaterThan100() {
		this.cpuPlayer.addToScore(105);
		assertTrue(this.cpuPlayer.scoreAtLeast100());
	}
	
	@Test
	public void playerWonKitty1() {
		this.cpuPlayer.wonKitty(20);
		assertTrue(this.cpuPlayer.getChips() == 70);
	}
	
	@Test
	public void playerWonKitty2() {
		this.cpuPlayer.wonKitty(20);
		assertFalse(this.cpuPlayer.getChips() == 30);
	}
	
	@Test
	public void playerWonKitty3() {
		this.cpuPlayer.wonKitty(20);
		assertFalse(this.cpuPlayer.getChips() == 50);
	}
	
	

}
