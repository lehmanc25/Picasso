package picasso.parser.language.expressions;

import picasso.parser.language.ExpressionTreeNode;

/**
 * Represents the 'abs' function in the Picasso language. This function applies
 * the absolute value operation to an expression node, operating on each color
 * component (red, green, blue) of the evaluated RGBColor.
 * 
 * @author Ford Scott
 */
public class Abs extends UnaryFunction {

	/**
	 * Constructor for AbsValue.
	 * 
	 * @param param The expression node that this function will be applied to.
	 */
	public Abs(ExpressionTreeNode param) {
		super(param);
	}

	/**
	 * Evaluates the absolute value function on the parameter.
	 * 
	 * @param x The x-coordinate at which to evaluate the function.
	 * @param y The y-coordinate at which to evaluate the function.
	 * @return The result of applying the absolute value function to the parameter.
	 */
	@Override
	public RGBColor evaluate(double x, double y) {
		// First, evaluate the parameter (expression node) at the given x and y
		RGBColor color = param.evaluate(x, y);

		// Apply the absolute value function to each component of the RGBColor
		double red = Math.abs(color.getRed());
		double green = Math.abs(color.getGreen());
		double blue = Math.abs(color.getBlue());

		// Return the new RGBColor with the absolute values
		return new RGBColor(red, green, blue);
	}
}
