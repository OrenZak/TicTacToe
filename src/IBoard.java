
public interface IBoard {
	
	void initBoard();
	boolean winningAchievement(IPlayer currentPlayer);
	boolean isLegalMove (IRubric rubric, IPlayer currentPlayer) throws NotYourTurnException, FullRubricException;
	void chooseRubric (IRubric rubric);
	void updateCurrentPlayer (IPlayer currentPlayer);
	void checkDraw();
}
