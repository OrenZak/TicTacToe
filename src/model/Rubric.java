package model;

public class Rubric implements IRubric {

	private int x;
	private int y;
	private String sign;
	
	public Rubric(int x, int y) {
		this.x=x;
		this.y=y;
	}
	
	@Override
	public void markRubric(String sign) {
		this.sign = sign;	
	}
	
	@Override
	public boolean isRubricEmpty() {
		return sign==null;
	}
	
	@Override
	public String getSign() {
		return sign;
	}
	
	@Override
	public void clearRubric() {
		sign=null;	
	}
	
	@Override
	public int getX() {
		return x;
	}
	
	@Override
	public int getY() {
		return y;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Rubric other = (Rubric) obj;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}
	
	
}
