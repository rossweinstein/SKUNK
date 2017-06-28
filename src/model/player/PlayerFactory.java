package model.player;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * This factory allows for the building of our SkunkPlayers for the game. It'll
 * be able to set up the appropriate amount of Users and CPU players each with a
 * name and the correct amount of chips.
 * 
 * @author Ross Weinstein
 *
 */

public class PlayerFactory {

	/**
	 * Sets up all our SkunkPlayers (both Users and CPU players) and return a
	 * List containing those players
	 * 
	 * @param numOfPlayers
	 *            int what is the total number of players (Users and CPU
	 *            players)
	 * @param users
	 *            String[] an array containing the name of each User
	 * @param chipStartingAmount
	 *            int how many chips do we want our players to start with
	 *            (usually 50)
	 * @return List containing all our players properly set up
	 */
	public static List<SkunkPlayer> CreatePlayers(int numOfPlayers, String[] users, int chipStartingAmount) {

		List<SkunkPlayer> thePlayers = new ArrayList<>();
		List<String> CPUNames = getCPUPlayerNames();

		for (int i = 0; i < numOfPlayers; i++) {

			if (i < users.length) {
				thePlayers.add(new HumanSkunkPlayer(users[i], chipStartingAmount));
			} else {
				thePlayers.add(new CPUSkunkPlayer(CPUNames.get(i - users.length) + " (CPU)", chipStartingAmount));
			}
		}
		return thePlayers;
	}

	/**
	 * A set of names for our CPU players
	 * 
	 * @return List with each name contained within it
	 */
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
