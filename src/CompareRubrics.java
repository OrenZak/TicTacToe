import java.util.Comparator;

public class CompareRubrics<T extends IRubric> implements Comparator<T> {

	@Override
	public int compare(T rubric1, T rubric2) {
		if (rubric1.getSign().equals(rubric2.getSign()))
			return 0;
		return 1;
	}
}
