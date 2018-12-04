package model;

public class ChosenRubricData {

	private String playerName;
	private String sign;
	private int x;
	private int y;
	
	public ChosenRubricData(String playerName, String sign, int x, int y) {
		this.playerName = playerName;
		this.sign = sign;
		this.x = x;
		this.y = y;
	}

	public String getPlayerName() {
		return playerName;
	}

	public String getSign() {
		return sign;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
}
