package playerTest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import player.Player;
import player.PlayerFactory;

public class PlayerFactoryTest {
	
	private Player[] thePlayers;
	
	@Before
	public void SetUp() {
		this.thePlayers = new Player[4];
		this.thePlayers[0] = new Player("Ross", 50, true);
		this.thePlayers[1] = new Player("Weinstein", 50, true);
		this.thePlayers[2] = new Player("Linus (CPU)", 50, false);
		this.thePlayers[3] = new Player("Newt (CPU)", 50, false);
	}

	@Test
	public void factoryCreatesProperPlayers() {
		String[] names = new String[2];
		names[0] = "Ross";
		names[1] = "Weinstein";
		Player[] factoryPlayers = PlayerFactory.CreatePlayers(4, names, 50);
		assertArrayEquals(factoryPlayers, this.thePlayers);
	}

}
