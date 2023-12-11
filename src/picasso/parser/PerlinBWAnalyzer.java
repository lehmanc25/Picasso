package picasso.parser;

import java.util.Stack;

import picasso.parser.language.ExpressionTreeNode;
import picasso.parser.language.expressions.PerlinBW;
import picasso.parser.tokens.Token;

/**
 * Handles parsing the perlinBW function.
 * 
 * @author Desire Asinya.
 * 
 */
public class PerlinBWAnalyzer implements SemanticAnalyzerInterface {

	@Override
	public ExpressionTreeNode generateExpressionTree(Stack<Token> tokens) {
		tokens.pop();
		
		ExpressionTreeNode param2 = SemanticAnalyzer.getInstance().generateExpressionTree(
				tokens);
		ExpressionTreeNode param1 = SemanticAnalyzer.getInstance().generateExpressionTree(
				tokens);
		
		return new PerlinBW(param1, param2);
	}

}
