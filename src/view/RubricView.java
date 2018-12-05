package view;

import view.RubricPane.MassegeType;

public interface RubricView {

	public void clearFields();
	
	public void setSign(int x, int y, String sign);

	public void massege(String to, String massege, MassegeType massegeType);

	void setEventsListener(ViewEvents rubricClickListener);

	public interface ViewEvents {

		void rubricClicked(int x, int y);
		
		void initGameClicked();

	}

	
}
