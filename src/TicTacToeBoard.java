import java.util.ArrayList;

public class TicTacToeBoard implements IBoard, Finals, IRegistrable {

	private IRubric[] rubrics = new Rubric[BOARD_SIZE];
	private IPlayer currentPlayer;
	private CompareRubrics<IRubric> rubricComparator = new CompareRubrics<>();
	private ArrayList<TicTacToeListener> listeners = new ArrayList<TicTacToeListener>();
	
	public TicTacToeBoard(IPlayer currentPlaye) {
		this.currentPlayer=currentPlaye;
		generateRubrics();
		processEvent(new TicTacToeEvent(TicTacToeRequests.Init, new String [] {}));
	}
	
	private void generateRubrics(){
		for (int i = 0; i < rubrics.length; i++){
			rubrics[i] = new Rubric((int)(i/Math.sqrt(rubrics.length)), (int)(i%Math.sqrt(rubrics.length)));
		}
	}
	
	@Override
	public void initBoard() {
		for (IRubric rubric : rubrics){
			rubric.clearRubric();
		}
		processEvent(new TicTacToeEvent(TicTacToeRequests.Init, new String [] {}));
	}
	
	@Override
	public void updateCurrentPlayer(IPlayer currentPlayer) {
		this.currentPlayer=currentPlayer;
		processEvent(new TicTacToeEvent(TicTacToeRequests.Swap, new String[] {currentPlayer.getName()}));
	}
	
	/**Bad Analysis. Shouldn't have been an independent function. Plus we shouldn't have got a rubric here.
	 * x and y would be better. Shouldn't have returned boolean*/
	@Override
	public boolean isLegalMove(IRubric rubric, IPlayer currentPlayer) throws NotYourTurnException, FullRubricException {
		if (new ComparePlayers<IPlayer>().compare(this.currentPlayer, currentPlayer) != 0)
			throw new NotYourTurnException(currentPlayer.getName());
		if (!rubrics[rubric.getX()*(int)(Math.sqrt(rubrics.length)) + rubric.getY()].isRubricEmpty())
			throw new FullRubricException(rubric.getSign());
		return true;
	}
	
	/** Bad Analysis. Legality checks should have been part of this function. Plus we shouldn't have got a rubric here. x and y would be better*/
	@Override
	public void chooseRubric(IRubric rubric) {
		rubrics[rubric.getX()*(int)(Math.sqrt(rubrics.length)) + rubric.getY()].markRubric(currentPlayer.getSign());
		//Here you get the current players name and sign and the string values of the marked rubric's x and y
		processEvent(new TicTacToeEvent(TicTacToeRequests.Choose, new String[] {currentPlayer.getName(), currentPlayer.getSign(), ""+rubric.getX(), ""+rubric.getY()}));
	}

	/** Bad Analysis. Shouldn't have returned boolean. Plus we shouldn't have got a player here. we know the current player*/
	@Override
	public boolean winningAchievement(IPlayer currentPlayer) {
		//All possible victory streaks are kept in VICTORIES matrix
		for (int[] victory : VICTORIES){
			for (int i = 0; i < victory.length-1; i++){
				if (rubricComparator.compare(rubrics[victory[i]], rubrics[victory[i+1]]) != 0)
					break;
				if (i==victory.length-1){
					//Here you get the winner's name, his sign, his first rubric of victory streak and the difference between each rubric that is part of his victory streak
					processEvent(new TicTacToeEvent(TicTacToeRequests.Victory, new String[] {currentPlayer.getName(), currentPlayer.getSign(), ""+victory[0], ""+((victory[i+1]-victory[0])/victory.length)}));
					return true;
				}
			}
		}
		return false;
	}
	
	public void checkDraw(){
		boolean empty = false;
		for (IRubric rubric : rubrics){
			if (rubric.isRubricEmpty()){
				empty = true;
				break;
			}
		}
		if (!empty)
			processEvent(new TicTacToeEvent(TicTacToeRequests.Draw, null));
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
