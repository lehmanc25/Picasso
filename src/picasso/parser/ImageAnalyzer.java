package picasso.parser;

import java.util.Stack;
import picasso.parser.language.ExpressionTreeNode;
import picasso.parser.language.expressions.Image;
import picasso.parser.tokens.ImageToken;
import picasso.parser.tokens.Token;

/**
 * A semantic analyzer for processing Image expressions and generating expression trees.
 * This class implements the SemanticAnalyzerInterface and is responsible for handling Image expressions.
 * 
 * @author Ford Scott
 */
public class ImageAnalyzer implements SemanticAnalyzerInterface {
	
	/**
	 * Generate an expression tree for the Image expression.
	 * 
	 * @param tokens A stack of tokens to process.
	 * @return An ExpressionTreeNode representing the parsed Image expression.
	 * @throws ParseException If an Image Token is not found or if the found token is not an ImageToken.
	 */
	@Override
	public ExpressionTreeNode generateExpressionTree(Stack<Token> tokens) throws ParseException {
		Token t = tokens.pop();
		if (!(t instanceof ImageToken)) {
			throw new ParseException("Expected an Image.");
		}
		ImageToken it = (ImageToken) t;
		return new Image(it.getString());
	}
}
