
package picasso.parser.language.expressions;

import picasso.parser.language.ExpressionTreeNode;

public class Negate extends UnaryFunction {

	/**
	 * @param param_right
	 * 
	 * Represents the Negate operator in the Picasso language.
	 */
	public Negate(ExpressionTreeNode param) {
		super(param);
	}

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
