/**
 * 
 */
package picasso.parser;

import java.util.Stack;

import picasso.parser.language.ExpressionTreeNode;
import picasso.parser.language.expressions.Assignment;
import picasso.parser.tokens.IdentifierToken;
import picasso.parser.tokens.Token;

/**
 * 
 */
public class AssignmentAnalyzer implements SemanticAnalyzerInterface {

	/**
	 * @param tokens
	 * @return
	 * @see picasso.parser.SemanticAnalyzerInterface#generateExpressionTree(java.util.Stack)
	 */
	@Override
	public ExpressionTreeNode generateExpressionTree(Stack<Token> tokens) {
		tokens.pop();
		
		ExpressionTreeNode expression = SemanticAnalyzer.getInstance().generateExpressionTree(
				tokens);
		Token t = tokens.peek();
		if (t instanceof IdentifierToken) {
			ExpressionTreeNode variable = SemanticAnalyzer.getInstance().generateExpressionTree(
					tokens);
			return new Assignment(variable, expression);
		}
		
		return null;
	}

}
