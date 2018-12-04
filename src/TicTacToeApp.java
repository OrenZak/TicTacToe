import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import view.*;

public class TicTacToeApp extends  Application{
    
	@Override
	public void start(Stage PrimaryStage) throws Exception {
 		
		Scene scene = new Scene(new RubricsFrame(),400,500);

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
