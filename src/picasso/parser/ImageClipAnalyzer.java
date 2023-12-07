package picasso.parser;

import java.util.Stack;
import picasso.parser.language.ExpressionTreeNode;
import picasso.parser.language.expressions.ImageClip;
import picasso.parser.tokens.ImageToken;
import picasso.parser.tokens.Token;

/**
 * A semantic analyzer for processing ImageClip expressions and generating expression trees.
 * This class implements the SemanticAnalyzerInterface and is responsible for handling ImageClip expressions.
 * 
 * @author Ford Scott
 */
public class ImageClipAnalyzer implements SemanticAnalyzerInterface {
	
	/**
	 * Generate an expression tree for the ImageClip function.
	 * 
	 * @param tokens A stack of tokens to process.
	 * @return An ExpressionTreeNode representing the parsed ImageClip expression.
	 * @throws ParseException If no Image Token is found in the stack.
	 */
	@Override
	public ExpressionTreeNode generateExpressionTree(Stack<Token> tokens) throws ParseException {
		tokens.pop(); // Remove the ImageClipToken.
		
		ExpressionTreeNode paramx = SemanticAnalyzer.getInstance().generateExpressionTree(tokens);
		ExpressionTreeNode paramy = SemanticAnalyzer.getInstance().generateExpressionTree(tokens);
		
		if (tokens.peek() instanceof ImageToken) {
			ImageAnalyzer analyzer = new ImageAnalyzer();
			ExpressionTreeNode newImage = analyzer.generateExpressionTree(tokens);
			return new ImageClip((Image) newImage, paramx, paramy);
		} else {
			throw new ParseException("No Image Token Found.");
		}
	}
}
