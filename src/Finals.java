
public interface Finals {

	public enum Sign{
		X ("X"), O ("O");
		
		final String sign;
		private Sign (String sign) {
			this.sign = sign;
		}
		public String getSign (){
			return sign;
		}
	}
	
	public static final int[][] VICTORIES = {{0,1,2}, {3,4,5}, {6,7,8}, {0,3,6}, {1,4,7}, {2,5,8}, {0,4,8}, {2,4,6}};
	
	public static final int BOARD_SIZE = 9;
	
	public static final int ID_START = 0;
	
	public static final int MAX_PLAYERS = 2;
	
	public enum TicTacToeRequests{
		Swap, Victory, Draw, Init, Choose
	}
}
