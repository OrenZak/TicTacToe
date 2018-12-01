package model;

public class TicTacToeEvent implements Finals {

	private TicTacToeRequests request;
	private String curentPlayer;
	private String sign;
	private int[] victory;
	private int x;
	private int y;
	
	public TicTacToeEvent(TicTacToeRequests request){
		this.request = request;
	}
	
	public TicTacToeEvent(TicTacToeRequests request, String currentPlayer){
		this.request = request;
		this.curentPlayer = currentPlayer;
	}
	
	public TicTacToeEvent(TicTacToeRequests request, String currentPlayer, String sign, int x, int y){
		this.request = request;
		this.curentPlayer = currentPlayer;
		this.sign = sign;
		this.x=x;
		this.y=y;
	}
	
	public TicTacToeEvent(TicTacToeRequests request, String currentPlayer, String sign, int[] victory){
		this.request = request;
		this.curentPlayer = currentPlayer;
		this.sign = sign;
		this.victory=victory;
	}

	public TicTacToeRequests getRequest() {
		return request;
	}

	public String getCurentPlayer() {
		return curentPlayer;
	}

	public String getSign() {
		return sign;
	}

	public int[] getVictory() {
		return victory;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	

}
