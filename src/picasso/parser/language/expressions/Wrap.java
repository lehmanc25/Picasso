package picasso.parser.language.expressions;

import picasso.parser.language.ExpressionTreeNode;

/**
 * Represents the wrap function in the Picasso language. This class extends
 * UnaryFunction and represents a function that wraps color values around the
 * range [-1, 1].
 * 
 * @author Desire Asinya
 */
public class Wrap extends UnaryFunction {

	/**
	 * Constructs a Wrap function with a given parameter.
	 * 
	 * @param param The parameter to apply the wrap function to.
	 */
	public Wrap(ExpressionTreeNode param) {
		super(param);
	}

	/**
	 * Evaluates the wrap function on the given parameter, wrapping color values
	 * around the range [-1, 1].
	 * 
	 * @param x The x-coordinate (unused).
	 * @param y The y-coordinate (unused).
	 * @return The RGB color after applying the wrap function.
	 */
	@Override
	public RGBColor evaluate(double x, double y) {
		RGBColor result = param.evaluate(x, y);
		double red = wrap(result.getRed());
		double green = wrap(result.getGreen());
		double blue = wrap(result.getBlue());

		return new RGBColor(red, green, blue);
	}

	/**
	 * Wraps a value around the range [-1, 1].
	 * 
	 * @param result The value to wrap.
	 * @return The wrapped value.
	 */
	public static double wrap(double result) {
		if (result < -1 || result > 1) {
			double intervalSize = 2.0;
			double wrappedValue = (result - (-1)) % intervalSize;
			if (wrappedValue < 0.0) {
				wrappedValue += 2.0;
			}
			double finalWrappedValue = -1 + wrappedValue;

			return finalWrappedValue;
		} else {
			return result;
		}
	}

}
