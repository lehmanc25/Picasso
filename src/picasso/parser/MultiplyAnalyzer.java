/**
 * 
 */
package picasso.parser;

import java.util.Stack;

import picasso.parser.language.ExpressionTreeNode;
import picasso.parser.language.expressions.Multiply;
import picasso.parser.tokens.Token;


public class MultiplyAnalyzer implements SemanticAnalyzerInterface {

	@Override
	public ExpressionTreeNode generateExpressionTree(Stack<Token> tokens) {
		tokens.pop(); 

				
		ExpressionTreeNode Param2 = SemanticAnalyzer.getInstance().generateExpressionTree(tokens);
		ExpressionTreeNode Param1 = SemanticAnalyzer.getInstance().generateExpressionTree(tokens);
		
		return new Multiply(Param1, Param2);
	}

}