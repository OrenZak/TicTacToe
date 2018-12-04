package control;

import model.ChosenRubricData;
import model.FullRubricException;
import model.IBoard;
import model.IPlayer;
import model.IRubric;
import model.NotYourTurnException;
import model.Rubric;
import model.VictoryData;
import view.RubricPane.MassegeType;
import view.RubricView;
import view.RubricView.RubricClickListener;

public class TicTacToeControllerImpl {
	private IPlayer currentPlayer;
	private IPlayer otherPlayer;
	private final IBoard board;
	private final RubricView view;
	

	public TicTacToeControllerImpl(IPlayer currentPlayer, IPlayer otherPlayer, RubricView view, IBoard board) {
		this.view = view;
		this.currentPlayer = currentPlayer;
		this.otherPlayer = otherPlayer;
		this.board = board;
		setViewListener();
	}

	private void setViewListener() {
		view.setRubricClickListener(new RubricClickListener() {

			@Override
			public void onClick(int x, int y) {
				IRubric clickedRunbric = createRubric(x, y);
				verifyMove(currentPlayer, clickedRunbric);
			}
		});
	}

	protected IRubric createRubric(int x, int y) {
		return new Rubric(x, y);
	}

	public void initGame() {
		board.initBoard();
	}

	public void verifyMove(IPlayer player, IRubric rubric) {
		try {
			board.isLegalMove(rubric, player);
			board.chooseRubric(rubric);
			if (board.winningAchievement(player)) {
				VictoryData victoryData = board.getVictoryDetails();
				view.massege(currentPlayer.getName(), createWinningMsg(victoryData), MassegeType.INFORMATION);
			} else {
				if (board.checkDraw()) {
					view.massege(currentPlayer.getName() + " and " + otherPlayer.getName(), "Game Tie", MassegeType.INFORMATION);
				} else {
					board.updateCurrentPlayer(otherPlayer);
					swapPlayers();
				}

			}
		} catch (NotYourTurnException | FullRubricException e) {
			view.massege("", e.getMessage(), MassegeType.ERROR);
		}
	}

	private String createWinningMsg(VictoryData victoryData) {
		return new StringBuilder(" You Win").append("\n")
				.append("Your sign:").append(victoryData.getSign()).append("\n")
				.append("And victory strike: ").append(victoryData.getVictory().toString()).toString();
	}

	private void swapPlayers() {
		IPlayer temp = currentPlayer;
		currentPlayer = otherPlayer;
		otherPlayer = temp;
	}
}
