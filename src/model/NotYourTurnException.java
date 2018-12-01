package model;

public class NotYourTurnException extends Exception {

	public NotYourTurnException(String playerName) {
		super(playerName + " is not allowed to play - it is not your turn");
	}
}
