package control;

import model.ChosenRubricData;
import model.FullGameException;
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
		//TODO create View
		this.currentPlayer = new TicTacToePlayer("Moshe");
		this.otherPlayer = new TicTacToePlayer("Peretz");	
		this.board = new TicTacToeBoard(currentPlayer, otherPlayer);
	}
	
	public void initGame() {
		board.initBoard();
	}

	public void verifyMove(IPlayer player, IRubric rubric) {
		 try {
			board.isLegalMove(rubric, player);
			ChosenRubricData rubricData = board.chooseRubric(rubric);
			//TODO tell the view to select the rubric
			if (board.winningAchievement(player)) {
				//TODO view show player win message.
			} else {
				if (board.checkDraw()) {
					//TODO view show draw message
				} else {
					board.updateCurrentPlayer(otherPlayer);
					swapPlayers();
				}
				
			}
		} catch (NotYourTurnException | FullRubricException e) {
			//TODO view show error message to player
		}
	}

	private void swapPlayers() {
		IPlayer temp = currentPlayer;
		currentPlayer = otherPlayer;
		otherPlayer = temp;
	}
}
