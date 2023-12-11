/**
 * 
 */
package picasso.parser.language.expressions;

import picasso.parser.language.ExpressionTreeNode;

/**
 * Reoresents the sine function in the Picasso language
 * 
 * @author Desire Asinya
 */
public class Sine extends UnaryFunction {

	/**
	 * Create a sine expression that takes as parameter the given parameter
	 * 
	 * @param the expression to evaluate the sine of. 
	 */
	public Sine(ExpressionTreeNode param) {
		super(param);
	}

	/**
	 * Evaluates this expression at the given x,y point by evaluating the sin of
	 * the function's parameter.
	 * 
	 * @return the color from evaluating the sin of the expression's parameter
	 */
	@Override
	public RGBColor evaluate(double x, double y) {
		// TODO Auto-generated method stub
		RGBColor result = param.evaluate(x, y);
		double red = Math.sin(result.getRed());
		double green = Math.sin(result.getGreen());
		double blue = Math.sin(result.getBlue());
		
		return new RGBColor(red, green, blue);
	}

}
