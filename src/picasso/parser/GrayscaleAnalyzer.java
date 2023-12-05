package picasso.parser;

import java.util.Stack;
import picasso.parser.language.ExpressionTreeNode;
import picasso.parser.language.expressions.GrayscaleFunction;
import picasso.parser.tokens.Token;

/**
 * Semantic Analyzer for the Grayscale function.
 * Parses tokens and generates an expression tree node representing
 * the grayscale function.
 */
public class GrayscaleAnalyzer implements SemanticAnalyzerInterface {

    @Override
    public ExpressionTreeNode generateExpressionTree(Stack<Token> tokens) {
        tokens.pop(); // Remove the Grayscale token
        ExpressionTreeNode param = SemanticAnalyzer.getInstance().generateExpressionTree(tokens);
        return new GrayscaleFunction(param);
    }
}
