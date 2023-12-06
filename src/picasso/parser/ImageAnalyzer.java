package picasso.parser;

import java.util.Stack;
import picasso.parser.language.ExpressionTreeNode;
import picasso.parser.language.expressions.Image;
import picasso.parser.tokens.ImageToken;
import picasso.parser.tokens.Token;

/**
 * Handles parsing an image token in the Picasso language.
 * This analyzer creates an Image expression tree node from an ImageToken.
 * 
 * @author Ford Scott
 */
public class ImageAnalyzer implements SemanticAnalyzerInterface {

    /**
     * Generates an expression tree node for an image.
     * 
     * @param tokens the stack of tokens, expecting an ImageToken at the top
     * @return an Image expression tree node
     * @throws ParseException if the top token is not an ImageToken
     */
    @Override
    public ExpressionTreeNode generateExpressionTree(Stack<Token> tokens) {
        Token token = tokens.pop();
        if (!(token instanceof ImageToken)) {
            throw new ParseException("Expected an Image token, found " + token);
        }
        ImageToken imageToken = (ImageToken) token;
        return new Image(imageToken.getFilename());
    }
}
