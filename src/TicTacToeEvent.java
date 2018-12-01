
public class TicTacToeEvent implements Finals {

	private TicTacToeRequests request;
	private String[] values;
	
	public TicTacToeEvent(TicTacToeRequests request, String[] values){
		this.request = request;
		this.values = values;
	}

	public TicTacToeRequests getRequest() {
		return request;
	}

	public String[] getValues() {
		return values;
	}
}
