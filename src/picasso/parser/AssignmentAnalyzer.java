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
	 * Generate an expression tree for an Assigment expression.
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
	
	public static String getMapString() {
		StringBuilder mapString = new StringBuilder();
		for (String key : idToExpr.keySet()) {
			ExpressionTreeNode value = idToExpr.get(key);
			mapString.append("Variable: " + key + ";" + " Expression: " + value.toString());
			mapString.append("\n");
		}
		
		return mapString.toString();
	}
	@Override
	public ExpressionTreeNode generateExpressionTree(Stack<Token> tokens) {
		tokens.pop();

		ExpressionTreeNode expression = SemanticAnalyzer.getInstance().generateExpressionTree(
				tokens);

		Token t = tokens.peek();
		if (t instanceof IdentifierToken) {
			IdentifierToken token = (IdentifierToken) tokens.peek();
			idToExpr.put(token.getName(), expression);
			
			ExpressionTreeNode variable = SemanticAnalyzer.getInstance().generateExpressionTree(
					tokens);
			return new Assignment(variable, expression);
		} else {
			throw new ParseException(t.toString() + " is not an identifier and cannot be assigned to an expression.");
		}
	}


}
