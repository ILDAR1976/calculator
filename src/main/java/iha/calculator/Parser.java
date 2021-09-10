package iha.calculator;
import java.io.*;
import java.util.ArrayList; 

public class Parser {

	static Token lookahead;
	static ArrayList<Token> Tokens;
	static ArrayList<Token> outTokens;
	static int counter;
	
	public Parser() throws IOException {
		this.outTokens = new ArrayList<Token>(); 
	}

	ArrayList<Token> expr(ArrayList<Token> inp) throws IOException {
		this.Tokens = inp;
		lookahead = inp.get(counter);
		term();
		while (true) {
			if (lookahead.name.equals("*")) {
				match("*");
				term();
				setToken("*",true,new Multiply());
			} else if (lookahead.name.equals("/")) {
				match("/");
				term();
				setToken("/",true,new Divide());
			} else if (lookahead.name.equals("+")) {
				match("+");
				term();
				setToken("+",true,new Plus());
			} else if (lookahead.name.equals("-")) {
				match("-");
				term();
				setToken("-",true,new Substract());
			} else
				return this.outTokens;
		}
	}

	void term() throws IOException {
		if (!lookahead.terminal) {
			setToken(lookahead.name,lookahead.getRomanianFlag());
			match(lookahead.name);
		} else
			throw new Error("syntax error");
	}

	void match(String t) throws IOException {
		if (lookahead.name.equals(t))
			lookahead = this.Tokens.get(++counter);
		else
			throw new Error("syntax error");
	}
	
	void setToken(String str,boolean isRomanian) {
		Token token = new Token(str, false);
		if (isRomanian) token.setRomanianFlag();
		this.outTokens.add(token);
	}
	
	void setToken(String str,boolean terminal, Operation operation) {
		Token token = new Token(str, terminal);
		 
		token.setOperation(operation);
		this.outTokens.add(token);
	}
}