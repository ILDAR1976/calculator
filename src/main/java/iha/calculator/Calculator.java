package iha.calculator;

import java.util.ArrayList;
import java.io.IOException;
import java.lang.StringBuilder;

public class Calculator {
	
	public ArrayList<Token> tokens;
	private ArrayList<Symbol> alphabet;
	private boolean romanianNumber; 
	private static final String[] numerals = {"M", "CM", "D", "CD", "C", "XC",
			"L", "XL", "X", "IX", "V", "IV", "I"};
	private static final int[] values = {1000, 900, 500, 400, 100, 90, 50, 40, 
			10, 9, 5, 4, 1};
	
	public Calculator(){
		inizialize();
	}
	
	public ArrayList<Token> getTokens(StringBuilder inputString) throws Exception {
		
		ArrayList<Token> outTokens = new ArrayList<Token>();
		Token token = new Token();
		boolean terminalToken = false;
		Symbol symbol;
		int counterRomanianSymbolsPerToken = 0;
		
		int counter = 0;
		while (counter < inputString.length()) {
			symbol = new Symbol(inputString.charAt(counter),false);
			
			if (alphabet.indexOf(symbol) == -1) throw 
			new Exception("т.к. формат математической операции не удовлетворяет "
					+ "заданию - два операнда и один оператор (+, -, /, *)");

			Symbol result = alphabet.get(alphabet.indexOf(symbol));

			
			
			if (result != null) {
				if (!terminalToken && result.terminal) {
					if (counterRomanianSymbolsPerToken == token.name.length()) {
						token.setRomanianFlag();
						counterRomanianSymbolsPerToken = 0;
					}
					outTokens.add(token);
					token = new Token();
					terminalToken = true;
				} else if (terminalToken && !result.terminal) {
					outTokens.add(token);
					token = new Token();
					terminalToken = false;
				}
				
				token.name += result.signature;
				
				if (result.isRomanian) counterRomanianSymbolsPerToken++;
			}
			counter++;
		}
		if (counterRomanianSymbolsPerToken == token.name.length()) {
			token.setRomanianFlag();
		}
		outTokens.add(token);
		outTokens.add(new Token());
		return outTokens;
	}
	
	public ArrayList<Token> checkingСonditions(ArrayList<Token> tokens) throws Exception {
		
		if (!(tokens.get(0).getRomanianFlag() && tokens.get(1).getRomanianFlag()) &&
				!(!tokens.get(0).getRomanianFlag() && !tokens.get(1).getRomanianFlag())
				) throw
				new Exception("т.к. используются одновременно разные системы счисления");
		
		if (!(tokens.get(0).getRomanianFlag() && tokens.get(1).getRomanianFlag()) &&
				!(!tokens.get(0).getRomanianFlag() && !tokens.get(1).getRomanianFlag())
				) throw
				new Exception("т.к. используются одновременно разные системы счисления");
		
		if (Integer.parseInt(tokens.get(0).name) > 10 || Integer.parseInt(tokens.get(1).name) > 10)
				throw
				new Exception("т.к. в одном из операндов используются значения больше 10");
		
		if ((Integer.parseInt(tokens.get(0).name) < Integer.parseInt(tokens.get(1).name)) &&
			(tokens.get(2).operation instanceof Substract))
			throw
			new Exception("т.к. результат операции отрацительный");
		
		return tokens;
	}
	
	public ArrayList<Token> normalization(ArrayList<Token> tokens) throws Exception {
		if (!(tokens.size() == 4)) throw 
		new Exception("т.к. формат математической операции не удовлетворяет "
				+ "заданию - два операнда и один оператор (+, -, /, *)");
		
		ArrayList<Token> outTokens = new ArrayList<Token>(); 
		Parser parse = new Parser();
		outTokens = parse.expr(tokens);  
		
		if (outTokens.get(0).getRomanianFlag() && outTokens.get(1).getRomanianFlag())
			this.romanianNumber = true;
	
		if (this.romanianNumber) {
			Token converToken = outTokens.get(0);
			converToken.name = Integer.toString(romanToInt(converToken.name));
			converToken = outTokens.get(1);
			converToken.name = Integer.toString(romanToInt(converToken.name));
		}
		
		return outTokens;
	}
	
	public String calculate(ArrayList<Token> tokens) {
		int result = tokens.get(2).operation.execute(
				Integer.parseInt(tokens.get(0).name), 
				Integer.parseInt(tokens.get(1).name));
		if (this.romanianNumber) {
			return intToRoman(result);
		} else {
			return Integer.toString(result);
		}
	}
	
	public static int romanToInt(String roman) {
	    int result = 0;
	    for (int i = 0; i < numerals.length; i++) {
	        while (roman.startsWith(numerals[i])) {
	            result += values[i];
	            roman = roman.substring(numerals[i].length());
	        }
	    }
	    return result;
	}
	
	public static String intToRoman(int num) {
	    StringBuilder sb = new StringBuilder();
	    int times = 0;
	    String[] romans = new String[] { "I", "IV", "V", "IX", "X", "XL", "L",
	            "XC", "C", "CD", "D", "CM", "M" };
	    int[] ints = new int[] { 1, 4, 5, 9, 10, 40, 50, 90, 100, 400, 500,
	            900, 1000 };
	    for (int i = ints.length - 1; i >= 0; i--) {
	        times = num / ints[i];
	        num %= ints[i];
	        while (times > 0) {
	            sb.append(romans[i]);
	            times--;
	        }
	    }
	    return sb.toString();
	} 
	
	private void inizialize() {
		alphabet = new ArrayList<Symbol>();
		alphabet.add(new Symbol('*',true));
		alphabet.add(new Symbol('/',true));
		alphabet.add(new Symbol('+',true));
		alphabet.add(new Symbol('-',true));
		alphabet.add(new Symbol('1',false));
		alphabet.add(new Symbol('2',false));
		alphabet.add(new Symbol('3',false));
		alphabet.add(new Symbol('4',false));
		alphabet.add(new Symbol('5',false));
		alphabet.add(new Symbol('6',false));
		alphabet.add(new Symbol('7',false));
		alphabet.add(new Symbol('8',false));
		alphabet.add(new Symbol('9',false));
		alphabet.add(new Symbol('0',false));
		alphabet.add(new Symbol('I',false,true));
		alphabet.add(new Symbol('V',false,true));
		alphabet.add(new Symbol('X',false,true));
	}
	

}

