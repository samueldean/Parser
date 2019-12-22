/************************************
 * Samuel J. Dean
 *11/15/2019
 ************************************/

package parserPackage;

import java.io.*;
import java.util.Scanner;

public class Parse{

	/************************************
	<assign>  -> id = <expr> 
	<expr>   -> <term> {(+ | -) <term>} 
	<term>   -> <factor> {(* | /) <factor>} 
	<factor>  -> id | int_constant | ( <expr> )
	 ************************************/

	public static String nextToken = "START";
	public static String currParse = "START";
	static final String IDENT = "IDENT";
	static final String INT_LIT = "INT_LIT";
	static final String ASSIGN_OP = "ASSIGN_OP";
	static final String ADD_OP = "ADD_OP";
	static final String SUB_OP = "SUB_OP";
	static final String MULT_OP = "MULT_OP";
	static final String DIV_OP = "DIV_OP";
	static final String LEFT_PAREN = "LEFT_PAREN";
	static final String RIGHT_PAREN = "RIGHT_PAREN";
	static final String END_OF_FILE = "END_OF_FILE";
	public static Scanner scan = null;
	static File file = new File ("parseOut.txt");

	//Parse expression rule: <assign> -> id = <expr>
	public static void assign() {
		System.out.println("Enter <assign>");
		if (nextToken.equals(IDENT)) {
			lex();
			if (nextToken.equals(ASSIGN_OP)) {
				lex();
				expr();
			}
		}
		System.out.println("Exit <assign>");
	}

	//Parse expression rule: <expr> -> <term> {(+ | -) <term>} 
	public static void expr() {
		System.out.println("Enter <expr>");
		term();
		while (nextToken.equals(ADD_OP) || nextToken.equals(SUB_OP)) {
			lex();
			term();
		}
		System.out.println("Exit <expr>");
	}

	//Parse term rule: <term> -> <factor> {(* | /) <factor>}
	public static void term() {
		System.out.println("Enter <term>");
		factor();
		while (nextToken.equals(MULT_OP) || nextToken.equals(DIV_OP)) {
			lex();
			factor();
		}
		System.out.println("Exit <term>");
	}

	//Parse factor rule: <factor> -> id | int_constant | ( <expr> )
	public static void factor() {
		System.out.println("Enter <factor>");
		if (nextToken.equals(IDENT) || nextToken.equals(INT_LIT)) {
			lex();
		}
		else {
			if (nextToken.equals(LEFT_PAREN)) {
				lex();
				expr();
				if (nextToken.equals(RIGHT_PAREN)) {
					lex();
				}
				else {
					error();
				}
			}
			else {
				error();
			}
		}
		System.out.println("Exit <factor>");
	}

	static void error() {
		if (nextToken.equals(END_OF_FILE)) {
			//Do nothing
		}
		else {
			System.out.println("Error - Token unknown");
		}
	}

	public static void main(String [] args) throws IOException {
		System.setOut(new PrintStream(file)); //prints to file
		System.out.println("****************************************************");
		System.out.println("Samuel J. Dean, CSCI4200, Fall 2019, Parser");
		System.out.println("****************************************************");
		try {
			scan = new Scanner(new FileReader("statements.txt"));
		} catch (FileNotFoundException e) {
			System.out.println("File could not be read.");
			e.printStackTrace();
		}
		nextToken = scan.nextLine().trim(); //skips past initial "START" token
		while (scan.hasNextLine()) { //if there is a next line within the file, go to parser method
			parser();
		}
	}

	public static void parser() {
		currParse = nextToken; //registers parsing the statement phrase to catch when a different one is entered
		System.out.println(currParse); //prints parsing statement
		lex();
		if (!nextToken.startsWith("Parsing the statement:")) {
			assign();
		}
	}

	public static void lex(){
		if (scan.hasNextLine()) {
			nextToken = scan.nextLine().trim(); //get next line
			if (!nextToken.startsWith("Parsing the statement:")) { //print token when it is not parsing statement line
				System.out.println("Next token is: "+nextToken);
			}
		}
	}
}
