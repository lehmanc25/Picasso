/**
 * 
 */
package picasso.parser.language.expressions;

import picasso.parser.language.ExpressionTreeNode;

/**
 * Represents the rgbToYCrCb function in the Picasso language.
 * 
 * @author Desire Asinya
 * 
 */
public class RbgToYCrCb extends UnaryFunction {

	/**
	 * Create an rgbToYCrCb expression that takes as a parameter the given expression
	 * 
	 * @param param the expression to compute the rgbToYCrCb value for
	 */
	public RbgToYCrCb(ExpressionTreeNode param) {
		super(param);
	}

	/**
	 * Evaluates this expression at the given x,y point by evaluating the rgbToYCRCb values of
	 * the function's parameter.
	 * 
	 * @return the color from evaluating the rgbToYCrCb value of the expression's parameter
	 */
	@Override
	public RGBColor evaluate(double x, double y) {
		RGBColor result = param.evaluate(x, y);
		double red = result.getRed() * 0.2989 + result.getGreen() * 0.5866 + result.getBlue() * 0.1145;
		double green = result.getRed() * -0.1687 + result.getGreen() * -0.3312 + result.getBlue() * 0.5;
		double blue = result.getRed() * 0.5000 + result.getGreen() * -0.4183 + result.getBlue() * -0.0816;
		
		return new RGBColor(red, green, blue);
	}

}
