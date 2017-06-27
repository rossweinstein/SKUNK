package skunkApp.mainMenu;

import helpers.InputHelper;
import helpers.MenuBuilder;
import skunkApp.gameLogic.SkunkGameController;

public class SkunkGame {

	private InputHelper input;

	public SkunkGame() {
		this.input = new InputHelper();
	}

	public void playGame() {

		MenuBuilder mainMenu = new MenuBuilder("Skunk", "Play Game", "Exit");
		System.out.println(mainMenu.displayMenuWithBanner());

		int selection = this.input.askForSelection(mainMenu.getMenuItems());

		if (selection == 1) {
			this.playSkunk();

		} else if (selection == 2) {
			System.out.println("\nProgram Ends...");
		}
	}

	private void playSkunk() {
		System.out.println();
		
		int numOfPlayers = 0;
		
		numOfPlayers += this.askForNumberOfHumanPlayers();
		
		System.out.println();
		String[] playerNames = this.getPlayerHumanNames(numOfPlayers);

		if (numOfPlayers < 8) {
			System.out.println();
			numOfPlayers += this.askForNumberOfComputerPlayers(8 - numOfPlayers);
		}

		SkunkGameController skunk = new SkunkGameController(numOfPlayers, playerNames);
		skunk.playSkunk();
	}

	private String[] getPlayerHumanNames(int numOfPlayers) {

		String[] playerNames = new String[numOfPlayers];

		for (int i = 0; i < numOfPlayers; i++) {
			playerNames[i] = this.input.askForString("Enter Player " + (i + 1) + " Name: ");
		}

		return playerNames;
	}

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

	private int askForNumberOfComputerPlayers(int availableNumForCPUPlayers) {
		int numOfCPUPlayers = 0;

		boolean validNumOfPlayers = false;
		while (!validNumOfPlayers) {

			if (availableNumForCPUPlayers == 1) {

				if (this.input.askBinaryQuestion("Would you like 1 computer player? (y/n)", "y", "n")) {
					numOfCPUPlayers = 1;
				} else {
					numOfCPUPlayers = 0;
				}

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
}
