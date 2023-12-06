package picasso.parser;

import java.util.Stack;
import picasso.parser.language.ExpressionTreeNode;
import picasso.parser.language.expressions.Image;
import picasso.parser.language.expressions.ImageWrap;
import picasso.parser.tokens.ImageToken;
import picasso.parser.tokens.Token;

/**
 * Handles parsing of the image wrap function in the Picasso language.
 * 
 * @author Ford Scott
 */
public class ImageWrapAnalyzer implements SemanticAnalyzerInterface {

    /**
     * Generates an expression tree node for the image wrap function.
     * 
     * @param tokens the stack of tokens, which should contain the image wrap parameters
     * @return an ImageWrap expression tree node
     * @throws ParseException if the correct tokens are not found
     */
    @Override
    public ExpressionTreeNode generateExpressionTree(Stack<Token> tokens) {
        tokens.pop(); // Pop the ImageWrap function token

        // Evaluate the x and y coordinate expressions
        ExpressionTreeNode paramx = SemanticAnalyzer.getInstance().generateExpressionTree(tokens);
        ExpressionTreeNode paramy = SemanticAnalyzer.getInstance().generateExpressionTree(tokens);

        // Check for and process the Image token
        if (tokens.peek() instanceof ImageToken) {
            ImageAnalyzer imageAnalyzer = new ImageAnalyzer();
            ExpressionTreeNode imageNode = imageAnalyzer.generateExpressionTree(tokens);
            return new ImageWrap((Image) imageNode, paramx, paramy);
        } else {
            throw new ParseException("No Image Token Found.");
        }
    }
}
