package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;

public class RubricPane extends GridPane implements RubricView {
	private static final int ROWS_COLS_MAX = 3;
	public final Button[][] rubrics = new Button[ROWS_COLS_MAX][ROWS_COLS_MAX];
	
	public enum MassegeType {
		INFORMATION, ERROR
	};

	private ViewEvents eventListener;

	public RubricPane() {
		setAlignment(Pos.CENTER);
		setPadding(new Insets(15));
		createButtons();
		setupSpacing();
		addButtons();
		setClickListeners();
	}

	private void addButtons() {
		for(int i = 0; i < 3; i++) {
			addRow(i, rubrics[i][0], rubrics[i][1], rubrics[i][2]);
		}
	}

	private void createButtons() {
		for (int i = 0; i < ROWS_COLS_MAX; i++) {
			for (int j = 0; j < ROWS_COLS_MAX; j++) {
				//TODO change into two dimensinal array
				Button button = new Button();
				button.setMaxWidth(500);
				button.setMaxHeight(500);
				button.setStyle("-fx-background-color: MediumSeaGreen");

				rubrics[i][j] = button;

				GridPane.setHgrow(button, Priority.ALWAYS);
				GridPane.setVgrow(button, Priority.ALWAYS);
			}
		}
	}

	private void setClickListeners() {
		for (int i = 0; i < ROWS_COLS_MAX; i++) {
			for (int j = 0; j < ROWS_COLS_MAX; j++) {
				final int x = i;
				final int y = j;
				rubrics[i][j].setOnAction(e -> {
					eventListener.rubricClicked(x, y);
				});
			}
		}
	}

	private void setupSpacing() {
		setAlignment(Pos.CENTER);
		setVgap(10 / 2);
		setHgap(10);
	}

	@Override
	public void setSign(int x, int y, String sign) {
		rubrics[x][y].setText(sign);
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
	public void setEventsListener(ViewEvents eventListener) {
		this.eventListener = eventListener;
	}

	public void onInitGameClicked() {
		eventListener.initGameClicked();
	}

	@Override
	public void clearFields() {
		for (int i = 0; i < ROWS_COLS_MAX; i++) {
			for (int j = 0; j < ROWS_COLS_MAX; j++) {
				rubrics[i][j].setText("");
			}
		}
	}
}
