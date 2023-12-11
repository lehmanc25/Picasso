/**
 * 
 */
package picasso.parser;

import java.util.Stack;

import picasso.parser.language.ExpressionTreeNode;
import picasso.parser.language.expressions.Negate;
import picasso.parser.tokens.Token;

/**
 * Handles parsing the negate function.
 * 
 * @author Connor Lehman
 */
public class NegAnalyzer implements SemanticAnalyzerInterface {

	/**
	 * @param tokens
	 * @return
	 * @see picasso.parser.SemanticAnalyzerInterface#generateExpressionTree(java.util.Stack)
	 */
	@Override
	public ExpressionTreeNode generateExpressionTree(Stack<Token> tokens) {
		tokens.pop();
		
		ExpressionTreeNode param = SemanticAnalyzer.getInstance().generateExpressionTree(
				tokens);
		return new Negate(param);
	}

}
