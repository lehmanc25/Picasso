package picasso.parser;

import java.util.Stack;

import picasso.parser.language.ExpressionTreeNode;
import picasso.parser.language.expressions.Wrap;
import picasso.parser.tokens.Token;

/**
 * Handles parsing the wrap function. This class extends UnaryFunctionAnalyzer
 * and is responsible for parsing the wrap function.
 * 
 * @author Desire Asinya
 */
public class WrapAnalyzer implements SemanticAnalyzerInterface {

	/**
	 * Generate an expression tree for the wrap function.
	 * 
	 * @param tokens The stack of tokens to process.
	 * @return An ExpressionTreeNode representing the parsed wrap function.
	 */
	@Override
	public ExpressionTreeNode generateExpressionTree(Stack<Token> tokens) {
		tokens.pop();

		ExpressionTreeNode paramETN = SemanticAnalyzer.getInstance().generateExpressionTree(tokens);

		return new Wrap(paramETN);
	}

}
