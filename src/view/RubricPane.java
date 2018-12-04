package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import model.IRubric;

public class RubricPane extends GridPane implements RubricView {

	public final Button[] rubrics = new Button[9];

	private final String BackgroundColor = "-fx-background-color: #F0FFFF";

	private final String XSign = "X";
	private final String OSign = "O";

	private final String Sign = "";

	private int Tocken = 0;

	public enum MassegeType {
		INFORMATION, ERROR
	};

	private RubricClickListener clickListener;

	public RubricPane() {

		setAlignment(Pos.CENTER);

		setPadding(new Insets(15));

		for (int i = 0; i < rubrics.length; i++) {

			Button button = new Button();
			button.setMaxWidth(500);
			button.setMaxHeight(500);
			button.setStyle("-fx-background-color: MediumSeaGreen");

			rubrics[i] = button;

			GridPane.setHgrow(button, Priority.ALWAYS);
			GridPane.setVgrow(button, Priority.ALWAYS);

		}

		setupSpacing();
		addRow(0, rubrics[0], rubrics[1], rubrics[2]);
		addRow(1, rubrics[3], rubrics[4], rubrics[5]);
		addRow(2, rubrics[6], rubrics[7], rubrics[8]);

		rubrics[0].setOnAction(e -> {
			clickListener.onClick(0, 0);
		});

		rubrics[1].setOnAction(e -> {
			clickListener.onClick(0, 1);
		});

		rubrics[2].setOnAction(e -> {
			clickListener.onClick(0, 2);
		});

		rubrics[3].setOnAction(e -> {
			clickListener.onClick(1, 0);
		});

		rubrics[4].setOnAction(e -> {
			clickListener.onClick(1, 1);
		});

		rubrics[5].setOnAction(e -> {
			clickListener.onClick(1, 2);
		});

		rubrics[6].setOnAction(e -> {
			clickListener.onClick(2, 0);
		});

		rubrics[7].setOnAction(e -> {
			clickListener.onClick(2, 1);
		});

		rubrics[8].setOnAction(e -> {
			clickListener.onClick(2, 2);
		});

	}

	private void setupSpacing() {
		setAlignment(Pos.CENTER);
		setVgap(10 / 2);
		setHgap(10);
	}

	@Override
	public void setSign(IRubric rubric) {
		int x = rubric.getX();
		int y = rubric.getY();
		rubrics[y * 3 + x].setText(rubric.getSign());
	}

	@Override
	public void massege(String to, String massege, MassegeType massegeType) {
		if (massegeType == MassegeType.INFORMATION) {
			new Alert(Alert.AlertType.INFORMATION, to + " " + massege).showAndWait();
		} else if (massegeType == MassegeType.ERROR) {
			new Alert(Alert.AlertType.ERROR, to + " " + massege).showAndWait();
		}
	}

	@Override
	public void setRubricClickListener(RubricClickListener rubricClickListener) {
		this.clickListener = rubricClickListener;
	}
}
