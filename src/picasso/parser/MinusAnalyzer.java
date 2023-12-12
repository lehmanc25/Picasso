package picasso.parser;

import java.util.Stack;

import picasso.parser.language.ExpressionTreeNode;
import picasso.parser.language.expressions.Minus;
import picasso.parser.tokens.Token;

/**
 * Handles parsing the minus or "subtraction function".
 * 
 * @author Robert C. Duvall
 * @author Sara Sprenkle
 * 
 */
public class MinusAnalyzer implements SemanticAnalyzerInterface {

	@Override
	public ExpressionTreeNode generateExpressionTree(Stack<Token> tokens) {
		tokens.pop(); 
	
		ExpressionTreeNode paramETN2 = SemanticAnalyzer.getInstance().generateExpressionTree(
				tokens);
		ExpressionTreeNode paramETN1 = SemanticAnalyzer.getInstance().generateExpressionTree(
				tokens);
		return new Minus(paramETN1, paramETN2);
	}

}
