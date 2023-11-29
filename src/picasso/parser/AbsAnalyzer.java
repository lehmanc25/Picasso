package picasso.parser;

import java.util.Stack;

import picasso.parser.language.ExpressionTreeNode;
import picasso.parser.tokens.Token;

/**
 * Handles parsing for the absolute value function.
 */
public class AbsAnalyzer implements SemanticAnalyzerInterface {

    /**
     * Generate an expression tree node for the absolute value function.
     * @param tokens The stack of tokens from the tokenizer.
     * @return An expression tree node representing the absolute value function.
     */
    @Override
    public ExpressionTreeNode generateExpressionTree(Stack<Token> tokens) {
        // Remove the ABS token from the stack
        tokens.pop();
        
        // Generate the expression tree for the parameter of the abs function
        ExpressionTreeNode paramETN = SemanticAnalyzer.getInstance().generateExpressionTree(tokens);
        
        // Return a new AbsFunction node with the generated parameter expression tree node
        return new AbsFunction(paramETN);
    }

}
