package iha.calculator;

import java.util.Objects;

public class Symbol {
	public char signature;
	public boolean terminal;
	public boolean isRomanian;
	
	public Symbol(char signature, boolean terminal) {
		this.signature = signature;
		this.terminal = terminal;
	}
	
	public Symbol(char signature, boolean terminal, boolean isRomanian) {
		this.signature = signature;
		this.terminal = terminal;
		this.isRomanian = isRomanian;
	}

	@Override
	public int hashCode() {
		return Objects.hash(signature);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Symbol))
			return false;
		Symbol other = (Symbol) obj;
		return signature == other.signature;
	}
	
	
}
