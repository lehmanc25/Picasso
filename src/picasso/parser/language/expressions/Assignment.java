/**
 * 
 */
package picasso.parser.language.expressions;

import picasso.parser.IdentifierAnalyzer;
import picasso.parser.language.ExpressionTreeNode;
import java.util.Map;
import java.util.HashMap;
/**
 * 
 */
public class Assignment extends ExpressionTreeNode {
	
	private ExpressionTreeNode var;
	private ExpressionTreeNode expr;

	/**
	 * @param x
	 * @param y
	 * @return
	 * @see picasso.parser.language.ExpressionTreeNode#evaluate(double, double)
	 */
	public Assignment(ExpressionTreeNode var, ExpressionTreeNode expr) {
		this.var = var;
		this.expr = expr;
		idToExpression.put(var, expr);
	}
	
	static Map<ExpressionTreeNode, ExpressionTreeNode> idToExpression = new HashMap<ExpressionTreeNode, ExpressionTreeNode>();

	public static Map<ExpressionTreeNode, ExpressionTreeNode> getIdToExpression() {
		return idToExpression;
	}

	@Override
	public RGBColor evaluate(double x, double y) {
		// TODO Auto-generated method stub
		return expr.evaluate(x, y);
	}
	


}
