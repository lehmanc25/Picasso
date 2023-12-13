package picasso.parser;

import java.util.Stack;
import picasso.parser.language.ExpressionTreeNode;
import picasso.parser.language.expressions.Random;
import picasso.parser.tokens.Token;

/**
 * Handles parsing the random function.
 * 
 * @author Nabil Youssef.
 */

public class RandomAnalyzer implements SemanticAnalyzerInterface {

	public RandomAnalyzer() {

	}

	/**
	 * Generate an expression tree for the random expression.
	 * 
	 * @param tokens
	 * @return
	 * @see picasso.parser.SemanticAnalyzerInterface#generateExpressionTree(java.util.Stack)
	 */
	@Override
	public ExpressionTreeNode generateExpressionTree(Stack<Token> tokens) {
		tokens.pop();
		return new Random();
	}
}