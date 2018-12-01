package model;

public interface IRubric {

	boolean isRubricEmpty();
	void clearRubric();
	String getSign();
	void markRubric(String sign);
	int getX();
	int getY();
}
