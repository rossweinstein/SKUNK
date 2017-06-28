package modelTest.playerTest;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import model.player.CPUSkunkPlayer;
import model.player.PlayerFactory;
import model.player.SkunkPlayer;
import model.player.HumanSkunkPlayer;

public class PlayerFactoryTest {
	
	private List<SkunkPlayer> thePlayers;
	
	@Before
	public void SetUp() {
		this.thePlayers = new ArrayList<>();
		this.thePlayers.add(new HumanSkunkPlayer("Ross", 50));
		this.thePlayers.add(new HumanSkunkPlayer("Weinstein", 50));
		this.thePlayers.add(new CPUSkunkPlayer("Linus (CPU)", 50));
		this.thePlayers.add(new CPUSkunkPlayer("Newt (CPU)", 50));
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
