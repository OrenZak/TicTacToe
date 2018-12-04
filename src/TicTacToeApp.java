import control.TicTacToeControllerImpl;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.IBoard;
import model.IPlayer;
import model.TicTacToeBoard;
import model.TicTacToePlayer;
import view.RubricPane;
import view.RubricsFrame;

public class TicTacToeApp extends Application {

	@Override
	public void start(Stage PrimaryStage) throws Exception {
		IPlayer firstPlayer = new TicTacToePlayer("Moshe");
		IPlayer secPlayer = new TicTacToePlayer("Peretz");
		IBoard board = new TicTacToeBoard(firstPlayer, secPlayer);
		RubricPane view = new RubricPane();
		TicTacToeControllerImpl controller = new TicTacToeControllerImpl(firstPlayer, secPlayer, view, board);
		
		
		Scene scene = new Scene(new RubricsFrame(view),800,600);
		PrimaryStage.setTitle("Tic Tac Toe"); // Set stage title
		PrimaryStage.setScene(scene); 
		PrimaryStage.setResizable(false);
		PrimaryStage.setAlwaysOnTop(false);
		PrimaryStage.show(); 
		
	}

	public static void main(String[] args) {
		launch(args);
	}
}
