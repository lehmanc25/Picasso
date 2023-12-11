/**
 * 
 */
package picasso.parser;

import java.util.Stack;

import picasso.parser.language.ExpressionTreeNode;
import picasso.parser.language.expressions.RbgToYCrCb;
import picasso.parser.tokens.Token;

/**
 * Handles parsing the rgbToYCrCb function.
 * 
 * @author Desire Asinya. 
 * 
 */
public class RgbToYCrCbAnalyzer implements SemanticAnalyzerInterface {

	@Override
	public ExpressionTreeNode generateExpressionTree(Stack<Token> tokens) {
		tokens.pop();
		
		ExpressionTreeNode paramETN = SemanticAnalyzer.getInstance().generateExpressionTree(tokens);
		
		return new RbgToYCrCb(paramETN);
	}

}
