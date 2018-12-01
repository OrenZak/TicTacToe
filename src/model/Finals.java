package model;

public interface Finals {

	public static final int BOARD_SIZE = 9;	
	public static final int LINE_SIZE = (int)Math.sqrt(BOARD_SIZE);
	public enum TicTacToeRequests{
		Swap, Victory, Draw, Init, Choose
	}
}
