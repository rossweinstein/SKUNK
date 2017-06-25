package model.player;

public class Player {
	
	private int score;
	private String name;
	
	public Player(String name) {
		this.score = 0;
		this.name = name;
	}

	@Override
	public String toString() {
		return "Player Name: " + name + " | Score: " + score;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + score;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		
		// self check
		if (this == obj) {
			return true;
		}

		// null check and type check
		if (obj == null || !(obj instanceof Player)) {
			return false;
		}

		// cast and comparisons
		Player otherPlayer = (Player) obj;
		return this.name.equals(otherPlayer.name) && this.score == otherPlayer.score;
	}

	public int getScore() {
		return this.score;
	}
	
	public void addToScore(int value) {
		this.score += value;
	}
	
	public void clearScore() {
		this.score = 0;
	}

	public String getName() {
		return this.name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
}