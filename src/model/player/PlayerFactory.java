package model.player;

public class PlayerFactory {
	
	public static SkunkPlayer[] CreatePlayers(int numOfPlayers, String[] users, int chipStartingAmount) {
		SkunkPlayer[] thePlayers = new SkunkPlayer[numOfPlayers];
		String[] CPUNames = getCPUPlayerNames();
		
		for (int i = 0; i < numOfPlayers; i++) {
			
			if (i < users.length) {
				thePlayers[i] = new UserController(users[i], chipStartingAmount);
			} else {
				thePlayers[i] = new CPUController(CPUNames[i - users.length] + " (CPU)", chipStartingAmount);
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
		nameList[6] = "Cosima";
		nameList[7] = "Sarah";
		return nameList;
	}
}
