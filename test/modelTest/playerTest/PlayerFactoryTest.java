package modelTest.playerTest;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import model.player.CPUController;
import model.player.PlayerFactory;
import model.player.SkunkPlayer;
import model.player.UserController;

public class PlayerFactoryTest {
	
	private List<SkunkPlayer> thePlayers;
	
	@Before
	public void SetUp() {
		this.thePlayers = new ArrayList<>();
		this.thePlayers.add(new UserController("Ross", 50));
		this.thePlayers.add(new UserController("Weinstein", 50));
		this.thePlayers.add(new CPUController("Linus (CPU)", 50));
		this.thePlayers.add(new CPUController("Newt (CPU)", 50));
	}

	@Test
	public void factoryCreatesProperPlayers() {
		String[] names = new String[2];
		names[0] = "Ross";
		names[1] = "Weinstein";
		List<SkunkPlayer> factoryPlayers = PlayerFactory.CreatePlayers(4, names, 50);
		assertEquals(factoryPlayers, this.thePlayers);
	}
}
