package model.dice;

public class DiceSet {
	
	private Die[] theDice;
	
	public DiceSet() {
		this.theDice = new Die[2];
		this.theDice[0] = new Die();
		this.theDice[1] = new Die();
	}
	
	public int[] roll() {
		return new int[] {this.theDice[0].roll(), this.theDice[1].roll()};
	}
}
