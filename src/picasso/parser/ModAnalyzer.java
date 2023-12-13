package picasso.parser;

import java.util.Stack;

import picasso.parser.language.ExpressionTreeNode;
import picasso.parser.language.expressions.Mod;
import picasso.parser.tokens.Token;
/**
 * The semantic analyzer for Division
 * 
 * @author Connor Lehman
 * 
 */

public class ModAnalyzer implements SemanticAnalyzerInterface {
	/**
	 * Generate an expression tree for the mod expression.
	 * @param tokens
	 * @return
	 * @see picasso.parser.SemanticAnalyzerInterface#generateExpressionTree(java.util.Stack)
	 */
	@Override
	public ExpressionTreeNode generateExpressionTree(Stack<Token> tokens) {
		tokens.pop(); 

		ExpressionTreeNode param2 = SemanticAnalyzer.getInstance().generateExpressionTree(tokens);
		ExpressionTreeNode param1 = SemanticAnalyzer.getInstance().generateExpressionTree(tokens);
		
		return new Mod(param1, param2);
	}

}