package control;

import model.FullRubricException;
import model.IBoard;
import model.IPlayer;
import model.IRubric;
import model.NotYourTurnException;
import model.TicTacToeBoard;
import model.TicTacToePlayer;

public class TicTacToeControllerImpl {
	private IPlayer currentPlayer;
	private IPlayer otherPlayer;
	private final IBoard board;
	public TicTacToeControllerImpl() {
		this.currentPlayer = new TicTacToePlayer("First Player");
		this.otherPlayer = new TicTacToePlayer("Sec Player");
		this.board = new TicTacToeBoard(currentPlayer, otherPlayer);
		//TODO create View
	}
	
	public void initGame() {
		board.initBoard();
	}

	public void verifyMove(IPlayer player, IRubric rubric) {
		boolean isLegal = false;
		 try {
			isLegal = board.isLegalMove(rubric, player);
		} catch (NotYourTurnException | FullRubricException e) {
			//TODO view show error message to player
		}
		if (isLegal) {
			board.chooseRubric(rubric);
			if (board.winningAchievement(player)) {
				//TODO view show player win message.
			} else {
				
				if (board.checkDraw()) {
					//TODO view show draw message
				} else {
					//TODO should the controller have the players ?
					//TODO why do i need to give the player to the board if he has them
					board.updateCurrentPlayer(?????);
				}
				
			}
		}
	}
}
