package model;
import java.util.ArrayList;

public class TicTacToeBoard implements IBoard {

	public static final int BOARD_SIZE = 9;	
	public static final int LINE_SIZE = (int)Math.sqrt(BOARD_SIZE);
	public static final int[][] VICTORIES = {{0,1,2}, {3,4,5}, {6,7,8}, {0,3,6}, {1,4,7}, {2,5,8}, {0,4,8}, {2,4,6}};
	private IRubric[][] rubrics = new Rubric[LINE_SIZE][LINE_SIZE];
	
	private IPlayer currentPlayer;
	private IPlayer otherPlayer;
	private VictoryData victoryDetails;
	
	public TicTacToeBoard(IPlayer currentPlayer, IPlayer otherPlayer) {
		this.currentPlayer=currentPlayer;
		this.otherPlayer=otherPlayer;
		generateRubrics();
	}
	
	private void generateRubrics(){
		for (int i = 0; i < rubrics.length; i++){
			for (int j=0; j< rubrics[i].length; j++)
				rubrics[i][j] = new Rubric(i,j);
		}
	}
	
	@Override
	public void initBoard() {
		for (int i = 0; i < rubrics.length; i++){
			for (int j=0; j< rubrics[i].length; j++)
				rubrics[i][j].clearRubric();
		}
	}
	
	/**Bad analysis. we don't need current player so we dropped it*/
	@Override
	public String updateCurrentPlayer(IPlayer currentPlayer) {
		IPlayer temp = this.currentPlayer;
		this.currentPlayer = this.otherPlayer;
		this.otherPlayer = temp;
		return this.currentPlayer.getName();
	}
	
	@Override
	public boolean isLegalMove(IRubric rubric, IPlayer currentPlayer) throws NotYourTurnException, FullRubricException {
		if (!currentPlayer.equals(this.currentPlayer))
			throw new NotYourTurnException(currentPlayer.getName());
		if (!rubrics[rubric.getX()][rubric.getY()].isRubricEmpty())
			throw new FullRubricException(rubric.getSign());
		return true;
	}
	
	/** Bad Analysis. Legality checks should have been part of this function. Plus we shouldn't have got a rubric here. x and y would be better*/
	@Override
	public ChosenRubricData chooseRubric(IRubric rubric) {
		rubrics[rubric.getX()][rubric.getY()].markRubric(currentPlayer.getSign());
		return new ChosenRubricData(currentPlayer.getName(), currentPlayer.getSign(), rubric.getX(), rubric.getY());
	}

	/** Bad Analysis. Shouldn't have returned boolean. Plus we shouldn't have got a player here. we know the current player*/
	@Override
	public boolean winningAchievement(IPlayer currentPlayer) {
		//All possible victory streaks are kept in VICTORIES matrix
		for (int[] victory : VICTORIES){
			for (int i = 0; i < victory.length-1; i++){
				if (!rubrics[victory[i]].equals(rubrics[victory[i+1]]))
					break;
				if (i==victory.length-1){
					victoryDetails = new VictoryData(currentPlayer.getName(), currentPlayer.getSign(), victory);
					return true;
				}
			}
		}
		return false;
	}
	
	public boolean checkDraw(){
		boolean empty = false;
		for (int i = 0; i < rubrics.length; i++){
			for (int j=0; j< rubrics[i].length; j++){
				if (rubrics[i][j].isRubricEmpty()){
					empty = true;
					break;
				}
			}
		}
		return !empty;
	}

	public VictoryData getVictoryDetails() {
		return victoryDetails;
	}
	
}
