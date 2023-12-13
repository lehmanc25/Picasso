package picasso.parser;

import java.util.Stack;
import picasso.parser.language.ExpressionTreeNode;
import picasso.parser.language.expressions.Image;
import picasso.parser.tokens.QuoteToken; // Import the QuoteToken class
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
     * @param tokens A stack of tokens to process.
     * @return An ExpressionTreeNode representing the parsed Image expression.
     * @throws ParseException If a Quote Token is not found or if the found token is not a QuoteToken.
     */
    @Override
    public ExpressionTreeNode generateExpressionTree(Stack<Token> tokens) throws ParseException {
        Token t = tokens.pop();
        if (!(t instanceof QuoteToken)) {
            throw new ParseException("Expected a filename in quotes.");
        }
        QuoteToken qt = (QuoteToken) t;
        return new Image(qt);
    }
}
