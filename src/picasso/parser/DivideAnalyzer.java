/**
 * 
 */
package picasso.parser;

import java.util.Stack;

import picasso.parser.language.ExpressionTreeNode;
import picasso.parser.language.expressions.Divide;
import picasso.parser.tokens.Token;
/**
 * The semantic analyzer for Division
 * 
 * @author Connor Lehman
 * 
 */

public class DivideAnalyzer implements SemanticAnalyzerInterface {
	
	@Override
	public ExpressionTreeNode generateExpressionTree(Stack<Token> tokens) {
		tokens.pop(); 

				
		ExpressionTreeNode Param2 = SemanticAnalyzer.getInstance().generateExpressionTree(tokens);
		ExpressionTreeNode Param1 = SemanticAnalyzer.getInstance().generateExpressionTree(tokens);
		
		return new Divide(Param1, Param2);
	}

}