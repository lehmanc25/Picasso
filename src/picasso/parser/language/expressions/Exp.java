package picasso.parser.language.expressions;

import picasso.parser.language.ExpressionTreeNode;

/**
 * Represents the exponential function in the Picasso language
 * 
 * @author Alexander Mayer
 */
public class Exp extends UnaryFunction{

	/**
	 * Create an exp expression that takes the exponent as a parameter
	 * 
	 * @param param the exponent to raiase
	 */
	public Exp (ExpressionTreeNode param) {
		super(param);
	}
	
	/**
	 * Evaluates raising e to the parameter
	 * @param x The x-coordinate at which to evaluate the function.
	 * @param y The y-coordinate at which to evaluate the function.
	 * @return The result of raising e to the parameter
	 */
	@Override
	public RGBColor evaluate(double x, double y) {
		RGBColor result = param.evaluate(x, y);
		double red = Math.exp(color.getRed());
		double green = Math.abs(color.getGreen());
		double blue = Math.abs(color.getBlue());
		
		return new RGBColor(red, green, blue);
	}
}
