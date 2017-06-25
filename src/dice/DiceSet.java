package dice;

public class DiceSet {
	
	private Die[] theDice;
	private int diceRoll;
	
	public DiceSet() {
		this.theDice = new Die[2];
		this.theDice[0] = new Die();
		this.theDice[1] = new Die();
		this.diceRoll = -1;
	}
	
	public int getDieOneValue() {
		return this.theDice[0].getLastRoll();
	}
	
	public int getDieTwoValue() {
		return this.theDice[1].getLastRoll();
	}
	
	public int getLastDiceRoll() {
		return this.diceRoll;
	}
	
	public int roll() {
		this.diceRoll = this.theDice[0].roll() + this.theDice[1].roll();
		return this.diceRoll;
	}
}
