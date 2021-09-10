package iha.calculator;

import java.util.Objects;

public class Token {
	public String name;
	public boolean terminal;
	public Operation operation;
	private boolean isRomanian;
	
	public Token(String name, boolean terminal) {
		this.name = name;
		this.terminal = terminal;
	} 
	
	public Token() {
		this.name = "";
		this.terminal = false;
	}

	public void setOperation(Operation operation) {
		this.operation = operation;
	}
	
	public void setRomanianFlag() {
		this.isRomanian = true;
	}

	public Boolean getRomanianFlag() {
		return this.isRomanian;
	}

	
	@Override
	public int hashCode() {
		return Objects.hash(name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Token))
			return false;
		Token other = (Token) obj;
		return Objects.equals(name, other.name);
	} 
	
	
}
