package model;

public class TicTacToePlayer implements IPlayer {

	private enum Sign{
		X ("X"), O ("O");
		
		final String sign;
		private Sign (String sign) {
			this.sign = sign;
		}
		public String getSign (){
			return sign;
		}
	}
	private static final int ID_START = 0;
	private static final int MAX_PLAYERS = 2;

	private Sign sign;
	private static int idGen = ID_START;
	private int id;
	private String playerName;
	
	public TicTacToePlayer(String playerName) throws FullGameException {
		this.playerName=playerName;
		if (idGen==MAX_PLAYERS)
			throw new FullGameException(playerName);
		id = idGen++;
		sign = Sign.values()[id];
	}
	
	@Override
	public String getSign() {
		return sign.getSign();
	}
	
	@Override
	public int getID() {
		return id;
	}
	
	@Override
	public String getName() {
		return playerName;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TicTacToePlayer other = (TicTacToePlayer) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	
	
}
