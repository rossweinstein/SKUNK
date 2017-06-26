package model.player;

import java.util.ArrayList;
import java.util.List;

public class PlayerFactory {
	
	public static List<SkunkPlayer> CreatePlayers(int numOfPlayers, String[] users, int chipStartingAmount) {
		
		List<SkunkPlayer> thePlayers = new ArrayList<>();
		List<String> CPUNames = getCPUPlayerNames();
		
		for (int i = 0; i < numOfPlayers; i++) {
			
			if (i < users.length) {
				thePlayers.add(new UserController(users[i], chipStartingAmount));
			} else {
				thePlayers.add(new CPUController(CPUNames.get(i - users.length) + " (CPU)", chipStartingAmount));
			}	
		}	
		return thePlayers;
	}
	
	private static List<String> getCPUPlayerNames() {
		
		List<String> nameList = new ArrayList<>();
		nameList.add("Linus");
		nameList.add("Newt");
		nameList.add("Dina");
		nameList.add("Shmi");
		nameList.add("Helios");
		nameList.add("Helena");
		nameList.add("Cosima");
		nameList.add("Sarah");
		return nameList;
	}
}
