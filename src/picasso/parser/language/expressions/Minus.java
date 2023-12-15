package picasso.parser.language.expressions;

import picasso.parser.language.ExpressionTreeNode;

/**
 * Represents the minus function in the Picasso language.
 * 
 * @author Connor Lehman.
 */

public class Minus extends BinaryFunction {
	public Minus(ExpressionTreeNode param_left, ExpressionTreeNode param_right) {
		super(param_left, param_right);
	}

	/**
	 * @param x
	 * @param y
	 * @return
	 * @see picasso.parser.language.ExpressionTreeNode#evaluate(double, double)
	 */
	@Override
	public RGBColor evaluate(double x, double y) {
		RGBColor result_left = param_left.evaluate(x, y);
		RGBColor result_right = param_right.evaluate(x, y);

		double red = result_left.getRed() - result_right.getRed();
		double green = result_left.getGreen() - result_right.getGreen();
		double blue = result_left.getBlue() - result_right.getBlue();

		return new RGBColor(red, green, blue);
	}
	/**
	 * 
	 * @return
	 * @see picasso.parser.language.expressions.BinaryFunction#toString()
	 */
	@Override
	public String toString() {
		return this.param_left + " - " + this.param_right;
	}

}