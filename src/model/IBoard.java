package model;

public interface IBoard {
	
	void initBoard();
	
	boolean winningAchievement(IPlayer currentPlayer);
	
	boolean isLegalMove (IRubric rubric, IPlayer currentPlayer) throws NotYourTurnException, FullRubricException;
	
	ChosenRubricData chooseRubric (IRubric rubric);
	
	String updateCurrentPlayer (IPlayer currentPlayer);
	
	boolean checkDraw();

	VictoryData getVictoryDetails();
}
