package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class UpperPart extends HBox {

	private final Button InitGame_btn = new Button("Init Game");

	private final String BackgroundColor = "-fx-background-color: #336699";
	private final Color ItemColor = Color.BLACK;

	private final Label Title = new Label("Welcome to Tic Tac Toe Game");

	public UpperPart(RubricPane rubricPane) {

		Title.setFont(Font.font(30));

		this.setPadding(new Insets(100, 100, 100, 400));
		this.setSpacing(100);

		InitGame_btn.setMaxWidth(Double.MAX_VALUE);
		InitGame_btn.setMaxHeight(Double.MAX_VALUE);

		getChildren().addAll(Title, InitGame_btn);

		setPadding(new Insets(10, 10, 10, 10));

		InitGame_btn.setOnAction(e -> {
			rubricPane.onInitGameClicked();
		});

	}

}
