package picasso.parser;

import java.util.Stack;
import picasso.parser.language.ExpressionTreeNode;
import picasso.parser.language.expressions.*;
import picasso.parser.tokens.QuoteToken;
import picasso.parser.tokens.Token;

/**
 * Handles parsing of the ImageWrap function in the Picasso language.
 * 
 * @author Ford Scott
 */
public class ImageWrapAnalyzer implements SemanticAnalyzerInterface {

    /**
     * Parses tokens into an ImageWrap expression.
     *
     * @param tokens A stack of tokens to be parsed.
     * @return An ExpressionTreeNode representing the parsed ImageWrap expression.
     */
    @Override
    public ExpressionTreeNode generateExpressionTree(Stack<Token> tokens) throws ParseException {
        tokens.pop(); // Remove the ImageClipToken.
        
        ExpressionTreeNode paramy = SemanticAnalyzer.getInstance().generateExpressionTree(tokens);
        ExpressionTreeNode paramx = SemanticAnalyzer.getInstance().generateExpressionTree(tokens);
        
        if (tokens.peek() instanceof QuoteToken) {
            Image img = new Image((QuoteToken) tokens.pop());
            return new ImageWrap(img, paramx, paramy);
        } else {
            throw new ParseException("No Image Token (QuoteToken) Found.");
        }
    }
}

