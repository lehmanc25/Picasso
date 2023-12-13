package picasso.parser;

import java.util.Stack;
import picasso.parser.language.ExpressionTreeNode;
import picasso.parser.language.expressions.PerlinColor;
import picasso.parser.tokens.Token;

/**
 * Handles parsing the perlinColor function.
 * 
 * @author Connor Lehman.
 * 
 */
public class PerlinColorAnalyzer implements SemanticAnalyzerInterface {
	/**
	 * Generate an expression tree for the perlinColor expression.
	 * 
	 * @param tokens
	 * @return
	 * @see picasso.parser.SemanticAnalyzerInterface#generateExpressionTree(java.util.Stack)
	 */
	@Override
	public ExpressionTreeNode generateExpressionTree(Stack<Token> tokens) {
		tokens.pop();

		ExpressionTreeNode Param2 = SemanticAnalyzer.getInstance().generateExpressionTree(tokens);
		ExpressionTreeNode Param1 = SemanticAnalyzer.getInstance().generateExpressionTree(tokens);

		return new PerlinColor(Param1, Param2);
	}

}