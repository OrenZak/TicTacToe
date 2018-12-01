
public class TicTacToePlayer extends Player implements Finals {

	private Sign sign;
	private static int idGen = ID_START;
	private int id;
	
	public TicTacToePlayer(String playerName) throws FullGameException {
		super(playerName);
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
}
