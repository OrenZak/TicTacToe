package model;

public class VictoryData {

	private String playerName;
	private String sign;
	private int[] victory;
	
	public VictoryData(String playerName, String sign, int[] victory) {
		this.playerName = playerName;
		this.sign = sign;
		this.victory = victory.clone();
	}

	public String getPlayerName() {
		return playerName;
	}

	public String getSign() {
		return sign;
	}

	public int[] getVictory() {
		return victory.clone();
	}
}
