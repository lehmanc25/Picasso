package picasso.parser;

import java.util.Stack;

import picasso.parser.language.ExpressionTreeNode;
import picasso.parser.language.expressions.YCrCbToRGB;
import picasso.parser.tokens.Token;

/**
 * Handles parsing the yCrCbToRGB function.
 * 
 * @author Connor Lehman.
 * 
 */
public class YCrCbToRGBAnalyzer implements SemanticAnalyzerInterface {
	/**
	 * Generate an expression tree for the YCrCbToRGB expression.
	 * 
	 * @param tokens
	 * @return
	 * @see picasso.parser.SemanticAnalyzerInterface#generateExpressionTree(java.util.Stack)
	 */
	@Override
	public ExpressionTreeNode generateExpressionTree(Stack<Token> tokens) {
		tokens.pop();

		ExpressionTreeNode paramETN = SemanticAnalyzer.getInstance().generateExpressionTree(tokens);

		return new YCrCbToRGB(paramETN);
	}

}
