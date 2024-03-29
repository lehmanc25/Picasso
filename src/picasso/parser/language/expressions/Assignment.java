package picasso.parser.language.expressions;
import java.util.Objects;
import picasso.parser.language.ExpressionTreeNode;

/**
 * Represents the assignment function in the Picasso language.
 * 
 * @author Desire Asinya.
 */
public class Assignment extends ExpressionTreeNode {
	
	private ExpressionTreeNode var;
	private ExpressionTreeNode expr;

	/**
	 * Constructor for assignment
	 * @param x
	 * @param y
	 * @return
	 * @see picasso.parser.language.ExpressionTreeNode#evaluate(double, double)
	 */
	public Assignment(ExpressionTreeNode var, ExpressionTreeNode expr) {
		this.var = var;
		this.expr = expr;
	}
	/**
	 * @param x
	 * @param y
	 * @return
	 * @see picasso.parser.language.ExpressionTreeNode#evaluate(double, double)
	 */

	@Override
	public RGBColor evaluate(double x, double y) {
		// TODO Auto-generated method stub
		return expr.evaluate(x, y);
	}

	/**
	 * @return
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return var + " = " + expr;
	}

	/**
	 * @return
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return Objects.hash(expr, var);
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
		Assignment other = (Assignment) obj;
		return Objects.equals(expr, other.expr) && Objects.equals(var, other.var);
	}
	


}
