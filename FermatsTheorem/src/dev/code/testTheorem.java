package dev.code;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class testTheorem {

	private static boolean isNumeric(String s) {
		boolean isNumeric = false;
		double doub = 0.0;
		int chkInt;

		try {
			chkInt = Integer.valueOf(s);
			isNumeric = true;

		} catch (NumberFormatException e) {
			isNumeric = false;
			// e.printStackTrace();
		}
		return isNumeric;
	}

	private static int getInteger(String var) {
		int returnInt = 0;
		String inString;
		boolean isNumeric = false;
		boolean process  = true;

		while (process) {
			try {
				System.out.println("Enter value for " + var + " here: ");
				BufferedReader bufferRead = new BufferedReader(
						new InputStreamReader(System.in));
				inString = bufferRead.readLine();

				System.out.println(inString);
				
				if (inString.contentEquals("x") ) {
					break;
				}
				isNumeric = isNumeric(inString);
				if (isNumeric) {
					returnInt = Integer.valueOf(inString);
					System.out
							.println("Value for " + var + " is: " + returnInt);
					break;
				} else {
					System.out
							.println("Please enter a numeric value or enter x to exit: ");
				}

			} catch (IOException e) {
				System.out.println("IOException hit!");
				// e.printStackTrace();
			}
		}
		return returnInt;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String var;
		int a, b, c, n = 0;
		double cCalc, result = 0;

		var = "a";
		a = getInteger(var);
		var = "b";
		b = getInteger(var);
		var = "c";
		c = getInteger(var);
		var = "n";
		n = getInteger(var);
	
		cCalc = Math.pow(c, n);
		result = Math.pow(a, n) + Math.pow(b, n);
		
		System.out.println("cCalc is: " + cCalc);
		System.out.println("result is: " + result);
		
		if ((n > 2) && (result == cCalc)) {
			System.out.println("We have a problem here!");
		}	
		System.out.println("End of job");
	}
}
