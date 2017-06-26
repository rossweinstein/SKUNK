package modelTest.playerTest;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

import model.player.CPUController;
import model.player.PlayerFactory;
import model.player.SkunkPlayer;
import model.player.UserController;

public class PlayerFactoryTest {
	
	private SkunkPlayer[] thePlayers;
	
	@Before
	public void SetUp() {
		this.thePlayers = new SkunkPlayer[4];
		this.thePlayers[0] = new UserController("Ross", 50);
		this.thePlayers[1] = new UserController("Weinstein", 50);
		this.thePlayers[2] = new CPUController("Linus (CPU)", 50);
		this.thePlayers[3] = new CPUController("Newt (CPU)", 50);
	}

	@Test
	public void factoryCreatesProperPlayers() {
		String[] names = new String[2];
		names[0] = "Ross";
		names[1] = "Weinstein";
		SkunkPlayer[] factoryPlayers = PlayerFactory.CreatePlayers(4, names, 50);
		assertArrayEquals(factoryPlayers, this.thePlayers);
	}
}
