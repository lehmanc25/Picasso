package picasso.parser.language.expressions;

import picasso.parser.language.ExpressionTreeNode;
import java.util.Map;
import java.util.Objects;
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

	/**
	 * @return
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return Objects.hash(name);
	}

	/**
	 * @param obj
	 * @return
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Variable other = (Variable) obj;
		return Objects.equals(name, other.name);
	}

	public String getName() {
		return name;
	}

}
