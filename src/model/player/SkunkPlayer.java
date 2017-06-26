package model.player;

public abstract class SkunkPlayer extends Player implements Comparable<SkunkPlayer> {

	private int chips;

	public SkunkPlayer(String name, int chips) {
		super(name);
		this.chips = chips;
	}

	@Override
	public String toString() {
		return this.getName() + " | Score: " + this.getScore() + " | Chips: " + this.chips;
	}

	@Override
	public boolean equals(Object obj) {

		// self check
		if (this == obj) {
			return true;
		}

		// null check and type check
		if (obj == null || !(obj instanceof SkunkPlayer)) {
			return false;
		}

		// cast and comparisons
		SkunkPlayer otherPlayer = (SkunkPlayer) obj;
		return this.getName().equals(otherPlayer.getName()) && this.getScore() == otherPlayer.getScore() && this.chips == otherPlayer.chips;
	}

	@Override
	public int compareTo(SkunkPlayer o) {

		if (this == o) {
			return 0;
		} else if (this.getScore() < o.getScore()) {
			return 1;
		} else {
			return -1;
		}
	}

	public abstract boolean wantsToRollDice();

	public void rolledDoubleSkunk() {
		this.clearScore();
		this.setChips(-4);
	}

	public void rolledSingleSkunkNoDeuce() {
		this.setChips(-1);
	}

	public void rolledSingleSkunkDeuce() {
		this.setChips(-2);
	}

	public void wonKitty(int kittyAmount) {
		this.setChips(kittyAmount);
	}

	public int getChips() {
		return chips;
	}

	public boolean hasEnoughChips(int chipPenalty) {
		return this.chips - chipPenalty >= 0;
	}

	public void setChips(int numOfChips) {
		this.chips += numOfChips;
	}

	public boolean scoreAtLeast100() {
		return this.getScore() >= 100;
	}
}