package player;

public class PlayerFactory {
	
	public static Player[] CreatePlayers(int numOfPlayers, String[] users, int chipStartingAmount) {
		Player[] thePlayers = new Player[numOfPlayers];
		String[] CPUNames = getCPUPlayerNames();
		
		for (int i = 0; i < numOfPlayers; i++) {
			
			if (i < users.length) {
				thePlayers[i] = new Player(users[i], chipStartingAmount, true);
			} else {
				thePlayers[i] = new Player(CPUNames[i - users.length] + " (CPU)", chipStartingAmount, false);
			}	
		}	
		return thePlayers;
	}
	
	private static String[] getCPUPlayerNames() {
		
		String[] nameList = new String[8];
		nameList[0] = "Linus";
		nameList[1] = "Newt";
		nameList[2] = "Dina";
		nameList[3] = "Shmi";
		nameList[4] = "Helios";
		nameList[5] = "Helena";
		nameList[6] = "Donnie";
		nameList[7] = "Allison";
		return nameList;
	}
}
