/**
 * 
 */
package picasso.parser;

import java.util.Stack;

import picasso.parser.language.ExpressionTreeNode;
import picasso.parser.language.expressions.Exponentiate;
import picasso.parser.tokens.Token;

/**
 * Handles parsing the exponentiate operator
 * 
 * @author Desire Asinya
 * 
 */
public class ExponentiateAnalyzer implements SemanticAnalyzerInterface {


	@Override
	public ExpressionTreeNode generateExpressionTree(Stack<Token> tokens) {
		tokens.pop();
		
		ExpressionTreeNode right_param = SemanticAnalyzer.getInstance().generateExpressionTree(tokens);
		ExpressionTreeNode left_param = SemanticAnalyzer.getInstance().generateExpressionTree(tokens);
		
		return new Exponentiate(left_param, right_param);
	}

}
