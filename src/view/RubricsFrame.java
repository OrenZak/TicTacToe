package view;
import javax.naming.InitialContext;

import javafx.geometry.Pos;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class RubricsFrame extends VBox {

	
	public RubricsFrame() {

		RubricPane rubricPane = new RubricPane();
		UpperPart upperPart = new UpperPart(rubricPane); 
		
		
		 setVgrow(rubricPane, Priority.ALWAYS);
	        setAlignment(Pos.CENTER);
		this.getChildren().addAll(upperPart,rubricPane);
		
	}
	
}
