package model;
import java.util.ArrayList;

public class TicTacToeBoard implements IBoard, Finals, IRegistrable {

	public static final int[][] VICTORIES = {{0,1,2}, {3,4,5}, {6,7,8}, {0,3,6}, {1,4,7}, {2,5,8}, {0,4,8}, {2,4,6}};
	private IRubric[][] rubrics = new Rubric[LINE_SIZE][LINE_SIZE];
	private IPlayer currentPlayer;
	private IPlayer otherPlayer;
	private ArrayList<TicTacToeListener> listeners = new ArrayList<TicTacToeListener>();
	
	public TicTacToeBoard(IPlayer currentPlayer, IPlayer otherPlayer) {
		this.currentPlayer=currentPlayer;
		this.otherPlayer=otherPlayer;
		generateRubrics();
		processEvent(new TicTacToeEvent(TicTacToeRequests.Init));
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
		processEvent(new TicTacToeEvent(TicTacToeRequests.Init));
	}
	
	/**Bad analysis. we don't need current player so we dropped it*/
	@Override
	public void updateCurrentPlayer(IPlayer currentPlayer) {
		IPlayer temp = this.currentPlayer;
		this.currentPlayer = this.otherPlayer;
		this.otherPlayer = temp;
		processEvent(new TicTacToeEvent(TicTacToeRequests.Swap, this.currentPlayer.getName()));
	}
	
	/**Bad Analysis. Shouldn't have been an independent function. Plus we shouldn't have got a rubric here.
	 * x and y would be better. Shouldn't have returned boolean*/
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
	public void chooseRubric(IRubric rubric) {
		rubrics[rubric.getX()][rubric.getY()].markRubric(currentPlayer.getSign());
		processEvent(new TicTacToeEvent(TicTacToeRequests.Choose, currentPlayer.getName(), currentPlayer.getSign(), rubric.getX(), rubric.getY()));
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
					processEvent(new TicTacToeEvent(TicTacToeRequests.Victory, currentPlayer.getName(), currentPlayer.getSign(), victory));
					return true;
				}
			}
		}
		return false;
	}
	
	public void checkDraw(){
		boolean empty = false;
		for (int i = 0; i < rubrics.length; i++){
			for (int j=0; j< rubrics[i].length; j++){
				if (rubrics[i][j].isRubricEmpty()){
					empty = true;
					break;
				}
			}
		}
		if (!empty)
			processEvent(new TicTacToeEvent(TicTacToeRequests.Draw));
	}
	
	@Override
	public void registerListener(TicTacToeListener listener) {
		listeners.add(listener);
	}
	
	private void processEvent(TicTacToeEvent event){
		for (TicTacToeListener listener: listeners)
			listener.handleEvent(event);
	}
	
}
