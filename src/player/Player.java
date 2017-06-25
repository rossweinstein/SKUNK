package player;

public class Player {
	
	private int score;
	private int chips;
	private String name;
	private boolean isUserControlled;
	
	public Player(String name, int chips, boolean isAUser) {
		this.score = 0;
		this.chips = chips;
		this.name = name;
		this.isUserControlled = isAUser;
	}
	

	@Override
	public String toString() {
		return "Player [score=" + score + ", chips=" + chips + ", name=" + name + ", isUserControlled="
				+ isUserControlled + "]";
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + chips;
		result = prime * result + (isUserControlled ? 1231 : 1237);
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + score;
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Player other = (Player) obj;
		if (chips != other.chips)
			return false;
		if (isUserControlled != other.isUserControlled)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (score != other.score)
			return false;
		return true;
	}


	public int getScore() {
		return score;
	}
	
	public void addRollToScore(int rollScore) {
		this.score += rollScore;
	}
	
	public int getChips() {
		return score;
	}
	
	public boolean hasEnoughChips(int chipPenalty) {
		return this.chips - chipPenalty >= 0;
	}
	
	public void setChips(int numOfChips) {
		this.chips += numOfChips;
	}
	
	public void rolledDoubleSkunk() {
		this.score = 0;
	}

	public String getName() {
		return name;
	}

	public boolean isUserControlled() {
		return isUserControlled;
	}
}