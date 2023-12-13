package picasso.parser;

import java.util.Stack;

import picasso.parser.language.ExpressionTreeNode;
import picasso.parser.language.expressions.Ceil;
import picasso.parser.tokens.Token;

/**
 * Handles parsing the Ceil function.
 * 
 * @author Nabil Youssef
 * 
 */
public class CeilAnalyzer extends UnaryFunctionAnalyzer {
	/**
	 * Generate an expression tree for the Ceil expression.
	 * @param tokens
	 * @return
	 * @see picasso.parser.UnaryFunctionAnalyzer#generateExpressionTree(java.util.Stack)
	 */
	@Override
	public ExpressionTreeNode generateExpressionTree(Stack<Token> tokens) {
		tokens.pop(); 

		return new Ceil(SemanticAnalyzer.getInstance().generateExpressionTree(
				tokens));
	}

}