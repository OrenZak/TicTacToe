package model;

public class FullRubricException extends Exception {

	public FullRubricException(String sign) {
		super("The selected rubric is already filled !!");
	}
}
