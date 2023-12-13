package picasso.parser.language.expressions;

import picasso.parser.language.ExpressionTreeNode;

/**
 * Represents the negate function in the Picasso language.
 * 
 * @author Connor Lehman.
 */
public class Neg extends UnaryFunction {
	public Neg(ExpressionTreeNode param) {
		super(param);
	}

	/**
	 * @param x
	 * @param unused
	 * @return
	 * @see picasso.parser.language.ExpressionTreeNode#evaluate(double, double)
	 */
	@Override
	public RGBColor evaluate(double x, double unused) {
		RGBColor result = param.evaluate(x, unused);

		return new RGBColor(-result.getRed(), -result.getGreen(), -result.getBlue());
	}

	@Override
	public String toString() {
		return "! " + this.param;
	}
}
