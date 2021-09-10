package iha.calculator;

public class Divide implements Operation {

	@Override
	public int execute(int x, int y) {
		return Math.round(x/y);
	}

}
