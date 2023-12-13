package picasso.parser.language.expressions;

import picasso.parser.language.ExpressionTreeNode;

public abstract class BinaryFunction extends ExpressionTreeNode {

	ExpressionTreeNode param_left;
	ExpressionTreeNode param_right;

	/**
	 * 
	 * @param param
	 */
	public BinaryFunction(ExpressionTreeNode param_left, ExpressionTreeNode param_right) {
		this.param_left = param_left;
		this.param_right = param_right;
	}

	/**
	 * Returns the string representation of the function in the format "<ClassName>:
	 * <parameter>"
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		String classname = this.getClass().getName();
		return classname.substring(classname.lastIndexOf(".")) + "(" + param_left + ", " + param_right + ")";
	}

	@Override
	public boolean equals(Object o) {
		if (o == this) {
			return true;
		}

		if (!(o instanceof BinaryFunction)) {
			return false;
		}

		// Make sure the objects are the same type

		if (o.getClass() != this.getClass()) {
			return false;
		}

		BinaryFunction bo = (BinaryFunction) o;

		// check if their parameters are equal
		if (!this.param_left.equals(bo.param_left) && !this.param_right.equals(bo.param_right)) {
			return false;
		}
		return true;
	}
}
