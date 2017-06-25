package model.player;

public abstract class SkunkPlayer implements Comparable<SkunkPlayer>{

	private Player thePlayer;
	private int chips;

	public SkunkPlayer(String name, int chips) {
		this.thePlayer = new Player(name);
		this.chips = chips;
	}

	@Override
	public String toString() {
		return this.thePlayer.getName() + "\t| Score: " + this.thePlayer.getScore() + "\n| Chips: " + this.chips;
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
		return this.thePlayer.equals(otherPlayer.thePlayer) && this.chips == otherPlayer.chips;
	}
	
	@Override
	public int compareTo(SkunkPlayer o) {
		
		if (this == o) {
			return 0;
		} else if (this.getPlayer().getScore() < o.getPlayer().getScore()) {
			return 1;
		} else {
			return -1;
		}
	}

	public abstract boolean wantsToRollDice();

	public void rolledDoubleSkunk() {
		this.thePlayer.clearScore();
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

	public void addRollToScore(int rollScore) {
		this.thePlayer.addToScore(rollScore);
	}

	public int getChips() {
		return chips;
	}
	
	public Player getPlayer() {
		return this.thePlayer;
	}

	public boolean hasEnoughChips(int chipPenalty) {
		return this.chips - chipPenalty >= 0;
	}

	public void setChips(int numOfChips) {
		this.chips += numOfChips;
	}
	
	public boolean scoreAtLeast100 () {
		return this.thePlayer.getScore() >= 100;
	}
}