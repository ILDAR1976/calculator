package iha.calculator;

import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class App 
{
	
	public static void main(String[] args) throws Exception { 
		Parser parse = new Parser(); 
		
		Calculator calculator = new Calculator();
		System.out.print("Enter expression: ");
        Scanner in = new Scanner(System.in);
        StringBuilder inputString = new StringBuilder();
        inputString.append(in.nextLine());
        calculator.tokens = calculator.getTokens(inputString);
        calculator.tokens = calculator.normalization(calculator.tokens);
        calculator.tokens = calculator.checkingСonditions(calculator.tokens);
        System.out.println("Result: " + calculator.calculate(calculator.tokens));
		} 
}
