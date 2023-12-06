package picasso.parser.language.expressions;

import picasso.parser.language.ExpressionTreeNode;
import java.util.Map;
import java.util.HashMap;

/**
 * Represents a variable
 * 
 * @author Sara Sprenkle
 *
 */
public class Variable extends ExpressionTreeNode {

	private String name;

	public Variable(String name) {
		this.name = name;
	}

	@Override
	public RGBColor evaluate(double x, double y) {
		// TODO Auto-generated method stub
		// Should be set to some value using assignment.
		Map<ExpressionTreeNode, ExpressionTreeNode> idToExpression = Assignment.getIdToExpression();
		ExpressionTreeNode expr = idToExpression.get(new Variable(this.name));
		if (expr != null) {
			return expr.evaluate(x, y);
		}
		return null;
	}

	public String getName() {
		return name;
	}

}
