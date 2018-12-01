
public class FullGameException extends Exception {


	public FullGameException(String playerName) {
		super("Unable to create new player" + playerName + " because the game is full");
	}
	
	
}
