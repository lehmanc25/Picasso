package picasso.parser;

import java.util.Stack;
import picasso.parser.language.ExpressionTreeNode;
import picasso.parser.language.expressions.Exp;
import picasso.parser.tokens.Token;

/**
 * The semantic analyzer for the exponential function.
 * 
 * @author Connor Lehman
 * 
 */

public class ExpAnalyzer implements SemanticAnalyzerInterface {
	/**
	 * Generate an expression tree for the exponential expression.
	 * @param tokens
	 * @return
	 * @see picasso.parser.SemanticAnalyzerInterface#generateExpressionTree(java.util.Stack)
	 */
	@Override
	public ExpressionTreeNode generateExpressionTree(Stack<Token> tokens) {
		tokens.pop();
		
		ExpressionTreeNode paramETN = SemanticAnalyzer.getInstance().generateExpressionTree(
				tokens);
				
		return new Exp(paramETN);

	}

}
