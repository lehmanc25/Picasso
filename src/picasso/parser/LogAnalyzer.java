package picasso.parser;

import java.util.Stack;

import picasso.parser.language.ExpressionTreeNode;
import picasso.parser.language.expressions.Log;
import picasso.parser.tokens.Token;
/**
 * The semantic analyzer for the logarithmic function
 * 
 * @author Connor Lehman
 * 
 */
public class LogAnalyzer extends UnaryFunctionAnalyzer {
	/**
	 * Generate an expression tree for the log expression.
	 * @param tokens
	 * @return
	 * @see picasso.parser.UnaryFunctionAnalyzer#generateExpressionTree(java.util.Stack)
	 */
	@Override
	public ExpressionTreeNode generateExpressionTree(Stack<Token> tokens) {
	
		tokens.pop(); 
		return new Log(SemanticAnalyzer.getInstance().generateExpressionTree(
				tokens));
	}

}