package view;

import model.IRubric;
import view.RubricPane.MassegeType;

public interface RubricView {

	public void setSign(IRubric rubric);

	public void massege(String to, String massege, MassegeType massegeType);

	void setRubricClickListener(RubricClickListener rubricClickListener);

	public interface RubricClickListener {

		void onClick(int x, int y);

	}
}
