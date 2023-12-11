/**
 * 
 */
package picasso.parser;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import picasso.parser.language.ExpressionTreeNode;
import picasso.parser.language.expressions.Assignment;
import picasso.parser.tokens.IdentifierToken;
import picasso.parser.tokens.Token;

/**
 * 
 */
public class AssignmentAnalyzer implements SemanticAnalyzerInterface {

	/**
	 * @param tokens
	 * @return
	 * @see picasso.parser.SemanticAnalyzerInterface#generateExpressionTree(java.util.Stack)
	 */
	static Map<String, ExpressionTreeNode> idToExpr = new HashMap<String, ExpressionTreeNode>();
	
	public static ExpressionTreeNode getExpression(String var) {
		return idToExpr.get(var);
	}
	
	public static boolean checkForKey(String var) {
		return idToExpr.containsKey(var);
	}
	@Override
	public ExpressionTreeNode generateExpressionTree(Stack<Token> tokens) {
		tokens.pop();
		
		ExpressionTreeNode expression = SemanticAnalyzer.getInstance().generateExpressionTree(
				tokens);

		Token t = tokens.peek();
		if (t instanceof IdentifierToken) {
			IdentifierToken token = (IdentifierToken) tokens.peek();
			if (!(token.getName().equals("x") || token.getName().equals("y"))) {
			idToExpr.put(token.getName(), expression);
			
			ExpressionTreeNode variable = SemanticAnalyzer.getInstance().generateExpressionTree(
					tokens);
			return new Assignment(variable, expression);
			}
			else {
				throw new ParseException("Token " + token.getName() + " is a special identifier and cannot be assigned to an expression.");
			}
		} else {
			throw new ParseException("Token on top of stack is not an identifier and cannot be assigned to an expression.");
		}
	}


}
