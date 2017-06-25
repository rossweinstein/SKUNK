package skunkApp.mainMenu;

import helpers.InputHelper;
import helpers.MenuBuilder;
import skunkApp.gameLogic.Skunk;

public class MainGame {

	private InputHelper input;

	public MainGame() {
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
		int numOfPlayers = this.askForNumberOfPlayers();
		System.out.println();
		String[] playerNames = this.getPlayerNames(numOfPlayers);

		Skunk skunk = new Skunk(numOfPlayers, playerNames);
		skunk.playGame();
	}

	private String[] getPlayerNames(int numOfPlayers) {

		String[] playerNames = new String[numOfPlayers];

		for (int i = 0; i < numOfPlayers; i++) {
			playerNames[i] = this.input.askForString("Enter Player " + (i + 1) + " Name: ");
		}

		return playerNames;
	}

	private int askForNumberOfPlayers() {

		int numOfPlayers = 0;

		boolean validNumOfPlayers = false;
		while (!validNumOfPlayers) {

			numOfPlayers = this.input.askForInteger("How many players (2-8): ");

			if (numOfPlayers >= 2 && numOfPlayers <= 8) {
				validNumOfPlayers = true;
			} else {
				System.out.println("You must enter an integer between 2 and 8");
			}
		}

		return numOfPlayers;
	}

	public static void main(String[] args) {
		MainGame game = new MainGame();
		game.playGame();
	}

}
