package picasso.parser;

import java.util.Stack;
import picasso.parser.language.ExpressionTreeNode;
import picasso.parser.language.expressions.Clamp;
import picasso.parser.tokens.Token;

/**
 * Handles parsing the clamp function.
 * This class extends UnaryFunctionAnalyzer and is responsible for parsing the clamp function.
 * 
 * @author Ford Scott
 */
public class ClampAnalyzer extends UnaryFunctionAnalyzer {
	
	/**
	 * Generate an expression tree for the clamp function.
	 * 
	 * @param tokens A stack of tokens to process.
	 * @return An ExpressionTreeNode representing the parsed clamp function.
	 */
	@Override
	public ExpressionTreeNode generateExpressionTree(Stack<Token> tokens) {
		tokens.pop();
		return new Clamp(SemanticAnalyzer.getInstance().generateExpressionTree(tokens));
	}
}
