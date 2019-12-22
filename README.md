# Parser
Program to parse an input file containing statements using preset grammar rules.

Input file must be named statements.txt
Output will be placed in a file named parseOut.txt

Example parsing:
Input-
Parsing the statement: sumTotal = (sum + 47    ) / total
IDENT		
ASSIGN_OP	
LEFT_PAREN	
IDENT		
ADD_OP		
INT_LIT		
RIGHT_PAREN	
DIV_OP		
IDENT

Output-
****************************************************
Samuel J. Dean, CSCI4200, Fall 2019, Parser
****************************************************
Parsing the statement: sumTotal = (sum + 47    ) / total
Next token is: IDENT
Enter <assign>
Next token is: ASSIGN_OP
Next token is: LEFT_PAREN
Enter <expr>
Enter <term>
Enter <factor>
Next token is: IDENT
Enter <expr>
Enter <term>
Enter <factor>
Next token is: ADD_OP
Exit <factor>
Exit <term>
Next token is: INT_LIT
Enter <term>
Enter <factor>
