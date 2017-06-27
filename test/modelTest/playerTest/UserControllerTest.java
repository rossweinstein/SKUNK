package modelTest.playerTest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import model.player.SkunkPlayer;
import model.player.UserController;

public class UserControllerTest {

	private SkunkPlayer user;
	
	
	@Before
	public void SetUp() {
		this.user = new UserController("Ross", 50);
	}

	@Test
	public void playerInitializedCorrectly() {
		assertTrue(this.user.getName().equals("Ross"));
		assertTrue(this.user.getChips() == 50);
		assertTrue(this.user.getScore() == 0);
	}
	
	@Test
	public void playerAddsToScoreCorrectly() {
		this.user.addToScore(12);
		this.user.addToScore(10);
		assertTrue(this.user.getScore() == 22);
	}
	
	@Test
	public void playerPenalizedForSkunkAndDeuce() {
		this.user.addToScore(12);
		this.user.addToScore(10);
		this.user.rolledSingleSkunkDeuce();
		assertTrue(this.user.getChips() == 48);
		assertTrue(this.user.getScore() == 22);
	}
	
	@Test
	public void playerPenalizedForSkunkAndDeuce2() {
		this.user.addToScore(12);
		this.user.addToScore(10);
		this.user.rolledSingleSkunkDeuce();
		assertFalse(this.user.getChips() == 52);
		assertTrue(this.user.getScore() == 22);
	}
	
	@Test
	public void playerPenalizedForSkunkWithoutDeuce() {
		this.user.addToScore(12);
		this.user.addToScore(10);
		this.user.rolledSingleSkunkNoDeuce();
		assertTrue(this.user.getChips() == 49);
		assertTrue(this.user.getScore() == 22);
	}
	
	@Test
	public void playerPenalizedForSkunkWithoutDeuce2() {
		this.user.addToScore(12);
		this.user.addToScore(10);
		this.user.rolledSingleSkunkNoDeuce();
		assertFalse(this.user.getChips() == 51);
		assertTrue(this.user.getScore() == 22);
	}
	
	@Test
	public void playerPenalizedForDoubleSkunk() {
		this.user.addToScore(12);
		this.user.addToScore(10);
		this.user.rolledDoubleSkunk();
		assertTrue(this.user.getChips() == 46);
		assertTrue(this.user.getScore() == 0);
	}
	
	@Test
	public void playerPenalizedForDoubleSkunk2() {
		this.user.addToScore(12);
		this.user.addToScore(10);
		this.user.rolledDoubleSkunk();
		assertTrue(this.user.getChips() == 46);
		assertFalse(this.user.getScore() == 50);
	}
	
	@Test
	public void playerDoesNotHaveEnoughChips() {
		assertFalse(this.user.hasEnoughChips(51));
	}
	
	@Test
	public void playerDoesHaveEnoughChips() {
		assertTrue(this.user.hasEnoughChips(5));
	}
	
	@Test
	public void playerDoesHaveEnoughChips2() {
		assertTrue(this.user.hasEnoughChips(50));
	}
	
	@Test
	public void playerScoreNotAtLeast100() {
		this.user.addToScore(4);
		assertFalse(this.user.scoreAtLeast100());
	}
	
	@Test
	public void playerScoreIs100() {
		this.user.addToScore(100);
		assertTrue(this.user.scoreAtLeast100());
	}
	
	@Test
	public void playerScoreIsGreaterThan100() {
		this.user.addToScore(105);
		assertTrue(this.user.scoreAtLeast100());
	}
	
	@Test
	public void playerWonKitty1() {
		this.user.wonKitty(20);
		assertTrue(this.user.getChips() == 70);
	}
	
	@Test
	public void playerWonKitty2() {
		this.user.wonKitty(20);
		assertFalse(this.user.getChips() == 30);
	}
	
	@Test
	public void playerWonKitty3() {
		this.user.wonKitty(20);
		assertFalse(this.user.getChips() == 50);
	}
	

}
