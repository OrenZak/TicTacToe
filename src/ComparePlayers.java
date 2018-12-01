import java.util.Comparator;

public class ComparePlayers<T extends IPlayer> implements Comparator<T> {
	
	@Override
	public int compare(T player1, T player2) {
		return player1.getID()-player2.getID();
	}

}
