package picasso.parser;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import picasso.parser.language.ExpressionTreeNode;
import picasso.parser.language.expressions.Assignment;
import picasso.parser.tokens.IdentifierToken;
import picasso.parser.tokens.Token;

/**
 * Handles parsing assignment functions.
 * 
 * @author Desire Asinya.
 */
public class AssignmentAnalyzer implements SemanticAnalyzerInterface {

	/**
	 * Map that maps a variable to an expression. 
	 */
	static Map<String, ExpressionTreeNode> idToExpr = new HashMap<String, ExpressionTreeNode>();
	
	/**
	 * Returns an expression mapped to a value
	 * @param var
	 * @return the expression mapped to var
	 */
	public static ExpressionTreeNode getExpression(String var) {
		return idToExpr.get(var);
	}
	/**
	 * Checks if a variable is mapped to an expression or not. 
	 * @param var
	 * @return true if the variable is mapped to the expression or false otherwise
	 */
	public static boolean checkForKey(String var) {
		return idToExpr.containsKey(var);
	}
	
	/**
	 * Returns the string representation of the map, to be used by the "view saved variables" GUI button.
	 * @return the string representation of the map
	 */
	public static String getMapString() {
		if (idToExpr.isEmpty()) {
			return "You have no saved variables yet :(";
		}
		StringBuilder mapString = new StringBuilder();
		for (String key : idToExpr.keySet()) {
			ExpressionTreeNode value = idToExpr.get(key);
			mapString.append(key.toString() + " = " + value.toString());
			mapString.append("\n");
		}
		
		return mapString.toString();
	}
	/**
	 * Generate an expression tree for an Assigment expression.
	 * @param tokens
	 * @return 
	 * @see picasso.parser.SemanticAnalyzerInterface#generateExpressionTree(java.util.Stack)
	 */
	@Override
	public ExpressionTreeNode generateExpressionTree(Stack<Token> tokens) {
		tokens.pop();

		ExpressionTreeNode expression = SemanticAnalyzer.getInstance().generateExpressionTree(
				tokens);

		Token t = tokens.peek();
		if (t instanceof IdentifierToken) {
			IdentifierToken token = (IdentifierToken) tokens.peek();
			if (tokens.size() > 1) {
				throw new ParseException("Expression is invalid and cannot be assigned to a value");
			}
			if (token.getName().equals("x") || token.getName().equals("y")) {
				throw new ParseException("Token " + token.getName() + "cannot be assigned to an expression");
			}
			idToExpr.put(token.getName(), expression);
			
			ExpressionTreeNode variable = SemanticAnalyzer.getInstance().generateExpressionTree(
					tokens);
			return new Assignment(variable, expression);
		} else {
			throw new ParseException(t.toString() + " is not an identifier and cannot be assigned to an expression.");
		}
	}


}
