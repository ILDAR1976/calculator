package iha.calculator;

public class Plus implements Operation{

	@Override
	public int execute(int x, int y) {
		return x + y;
	}

}
