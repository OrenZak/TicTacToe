package view;

import javafx.geometry.Pos;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class RubricsFrame extends VBox {

	public RubricsFrame(RubricPane pane) {
		RubricPane rubricPane = pane;
		UpperPart upperPart = new UpperPart(rubricPane);
		setVgrow(rubricPane, Priority.ALWAYS);
		setAlignment(Pos.CENTER);
		this.getChildren().addAll(upperPart, rubricPane);

	}

}
