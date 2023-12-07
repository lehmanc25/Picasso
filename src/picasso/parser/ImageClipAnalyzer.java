package picasso.parser;

import java.util.Stack;
import picasso.parser.language.ExpressionTreeNode;
import picasso.parser.language.expressions.Image;
import picasso.parser.language.expressions.ImageClip;
import picasso.parser.tokens.QuoteToken; // Import QuoteToken class
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
        
        if (tokens.peek() instanceof QuoteToken) {
            Image img = new Image((QuoteToken) tokens.pop());
            return new ImageClip(img, paramx, paramy);
        } else {
            throw new ParseException("No Image Token (QuoteToken) Found.");
        }
    }
}

