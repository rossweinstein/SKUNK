package skunkApp.game;

import helpers.InputHelper;
import helpers.MenuBuilder;

/**
 * 
 * SkunkGame is responsible for the game setup. It will present the main menu,
 * find the number of human and cpu players, get their names, and then pass all
 * the needed information to the SkunkGameController to start the game.
 * 
 * @author Ross Weinstein
 *
 */

public class SkunkSetUp {

	private InputHelper input;
	private MenuBuilder mainMenu;

	public SkunkSetUp() {
		this.input = new InputHelper();
		this.mainMenu = new MenuBuilder("Skunk", "Play Game", "Exit");
	}

	public void playGame() {

		// display main menu
		System.out.println(this.mainMenu.displayMenuWithBanner());

		// get selection for what user wants to do
		int selection = this.input.askForSelection(mainMenu.getMenuItems());

		if (selection == 1) {
			this.playSkunk();

		} else if (selection == 2) {
			System.out.println("\nProgram Ends...");
		}
	}

	/**
	 * Sets up the Skunk game by getting number of players and their name and
	 * then start the game
	 */
	private void playSkunk() {

		// give a line and ask how many user?
		System.out.println();
		int numOfPlayers = this.askForNumberOfHumanPlayers();

		// give a line and ask for their names?
		System.out.println();
		String[] playerNames = this.getPlayerHumanNames(numOfPlayers);

		// give a line and ask if they want any cpu players?
		System.out.println();
		numOfPlayers += this.anyCPUPlayers(numOfPlayers);

		// start the game
		SkunkController skunk = new SkunkController(numOfPlayers, playerNames);
		skunk.playSkunk();
	}

	/**
	 * Prompts if the User would like any CPU players
	 * 
	 * @param numOfPlayers
	 *            int how many User do we have. Game is limited to 8 players so
	 *            the affects the number of CPU players available
	 * @return int the number of CPU players desired
	 */
	private int anyCPUPlayers(int numOfPlayers) {
		
		int numOfCPUPlayers = 0;
		
		if (numOfPlayers < 8) {

			numOfCPUPlayers = this.askForNumberOfComputerPlayers(8 - numOfPlayers);
		}
		return numOfCPUPlayers;
	}

	/**
	 * Prompts the User to set the each name for the Human Players
	 * 
	 * @param numOfPlayers
	 *            int the number of Human Players for the game
	 * @return String[] each User name
	 */
	private String[] getPlayerHumanNames(int numOfPlayers) {

		String[] playerNames = new String[numOfPlayers];

		for (int i = 0; i < numOfPlayers; i++) {
			playerNames[i] = this.input.askForString("Enter Player " + (i + 1) + " Name: ");
		}

		return playerNames;
	}

	/**
	 * Prompts if the User for the number of Human Players (limited to 8)
	 * 
	 * @return int the number of Human players desired
	 */
	private int askForNumberOfHumanPlayers() {

		int numOfPlayers = 0;

		boolean validNumOfPlayers = false;
		while (!validNumOfPlayers) {

			numOfPlayers = this.input.askForInteger("How many human players (1-8): ");

			if (numOfPlayers >= 1 && numOfPlayers <= 8) {
				validNumOfPlayers = true;
			} else {
				System.out.println("You must enter an integer between 1 and 8");
			}
		}

		return numOfPlayers;
	}

	/**
	 * Ensures that we can only have a certain number of CPU players. This is
	 * due to our game having at max 8 players.
	 * 
	 * @param availableNumForCPUPlayers
	 *            int the number that determines the max amount of CPU players
	 *            possible
	 * @return int the desired number of CPU players
	 */
	private int askForNumberOfComputerPlayers(int availableNumForCPUPlayers) {

		int numOfCPUPlayers = 0;

		boolean validNumOfPlayers = false;
		while (!validNumOfPlayers) {

			if (availableNumForCPUPlayers == 1) {

				numOfCPUPlayers = this.wouldYouLikeOneCPUPlayer();

			} else {

				numOfCPUPlayers = this.input
						.askForInteger("How many computer players (1-" + (availableNumForCPUPlayers) + "): ");

				if (numOfCPUPlayers >= 1 && numOfCPUPlayers <= (availableNumForCPUPlayers)) {
					validNumOfPlayers = true;
				} else {
					System.out.println("You must enter an integer between 1 and " + (availableNumForCPUPlayers));
				}
			}
		}

		return numOfCPUPlayers;
	}

	/**
	 * Prompts if user wants the last player spot to be filled by a CPU player
	 * 
	 * @return boolean true if they want a CPU player; false otherwise
	 */
	private int wouldYouLikeOneCPUPlayer() {
		int numOfCPUPlayers;
		if (this.input.askBinaryQuestion("Would you like 1 computer player? (y/n)", "y", "n")) {
			numOfCPUPlayers = 1;
		} else {
			numOfCPUPlayers = 0;
		}
		return numOfCPUPlayers;
	}
}
