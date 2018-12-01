
public class Rubric implements IRubric, Finals {

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
}
