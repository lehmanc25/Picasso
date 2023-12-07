package picasso.parser.language.expressions;

import picasso.parser.language.ExpressionTreeNode;

/**
 * Represents the clamp function in the Picasso language.
 * This class extends UnaryFunction and represents a function that clamps color values to the range [-1, 1].
 * 
 * @author Ford Scott
 */
public class Clamp extends UnaryFunction {
	
	/**
	 * Constructs a Clamp function with a given parameter.
	 * 
	 * @param param The parameter to apply the clamp function to.
	 */
	public Clamp(ExpressionTreeNode param) {
		super(param);
	}
	
	/**
	 * Evaluates the clamp function on the given parameter, clamping color values to the range [-1, 1].
	 * 
	 * @param x The x-coordinate (unused).
	 * @param y The y-coordinate (unused).
	 * @return  The RGB color after applying the clamp function.
	 */
	@Override
	public RGBColor evaluate(double x, double y) {
		RGBColor result = param.evaluate(x, y);
		double red = clamp(result.getRed());
		double green = clamp(result.getGreen());
		double blue = clamp(result.getBlue());
		
		return new RGBColor(red, green, blue);
	}
	
	/**
	 * Clamps a value to the range [-1, 1].
	 * 
	 * @param x The value to clamp.
	 * @return  The clamped value.
	 */
	public static double clamp(double x) {
		double result = x;
		if (result > 1) {
			result = 1;
		}
		if (result < -1) {
			result = -1;
		}
		return result;
	}
}
