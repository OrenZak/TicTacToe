
public abstract class Player implements Finals, IPlayer {

	private String playerName;
	
	public Player(String playerName) {
		this.playerName = playerName;
	}
	
	@Override
	public String getName() {
		return playerName;
	}
	
}
