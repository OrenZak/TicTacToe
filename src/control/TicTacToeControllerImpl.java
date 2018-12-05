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
import view.RubricView.ViewEvents;

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

		initGame();
	}

	private void setViewListener() {
		view.setEventsListener(new ViewEvents() {

			@Override
			public void rubricClicked(int x, int y) {
				IRubric clickedRunbric = createRubric(x, y);
				verifyMove(currentPlayer, clickedRunbric);
			}

			@Override
			public void initGameClicked() {
				resetGame();
			}
		});
	}

	private void resetGame() {
		view.clearFields();
		board.initBoard();
	}

	protected IRubric createRubric(int x, int y) {
		return new Rubric(x, y);
	}

	private void initGame() {
		board.initBoard();
		setViewListener();
	}

	public void verifyMove(IPlayer player, IRubric rubric) {
		try {
			board.isLegalMove(rubric, player);
			ChosenRubricData rubricData = board.chooseRubric(rubric);
			view.setSign(rubricData.getX(), rubricData.getY(), rubricData.getSign());
			if (board.winningAchievement(player)) {
				VictoryData victoryData = board.getVictoryDetails();
				view.massege(currentPlayer.getName(), createWinningMsg(victoryData), MassegeType.INFORMATION);
				resetGame();
			} else {
				if (board.checkDraw()) {
					view.massege(currentPlayer.getName() + " and " + otherPlayer.getName(), "Game Tie",
							MassegeType.INFORMATION);
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
		return new StringBuilder(" You Win").append("\n").append("Your sign: ").append(victoryData.getSign())
				.append("\n").append("Victory streak: ").append(getVictoryIndexes(victoryData.getVictory()))
				.toString();
	}

	private String getVictoryIndexes(int[][] victory) {
		StringBuffer results = new StringBuffer();
		final String separator = ",";
		for (int i = 0; i < victory.length; ++i) {
			results.append('[');
			for (int j = 0; j < victory[i].length; ++j)
				if (j > 0)
					results.append(victory[i][j]);
				else
					results.append(victory[i][j]).append(separator);
			results.append(']');
		}
		return results.toString();
	}

	private void swapPlayers() {
		IPlayer temp = currentPlayer;
		currentPlayer = otherPlayer;
		otherPlayer = temp;
	}
}
