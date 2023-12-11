package picasso.parser.language.expressions;

import picasso.parser.language.ExpressionTreeNode;
import picasso.model.ImprovedNoise;

/**
 * Represents the perlinBW function in the Picasso language.
 * 
 * @author Desire Asinya.
 * 
 */
public class PerlinBW extends BinaryFunction {
	/**
	 * Create a perlinBW expression that takes as parameters the given expressions
	 * 
	 * @param param the expressions to evaluate the perlinBW values for.
	 */
	public PerlinBW(ExpressionTreeNode param_left, ExpressionTreeNode param_right) {
		super(param_left, param_right);
	}

	/**
	 * Evaluates this expression at the given x,y point by evaluating the perlinBW values of
	 * the function's parameters.
	 * 
	 * @return the color from evaluating the perlinBW values of the expression's parameters
	 */
	@Override
	public RGBColor evaluate(double x, double y) {
		RGBColor result_left = param_left.evaluate(x, y);
		RGBColor result_right = param_right.evaluate(x, y);
		
		double grey = ImprovedNoise.noise(result_left.getRed() + result_right.getRed(), result_left.getGreen() + result_right.getGreen(),
				result_left.getBlue() + result_right.getBlue());
		return new RGBColor(grey, grey, grey);
	}
	
	@Override
    public String toString() {
        return "perlinBW(" + this.param_left + "," + this.param_right + ")";
    }
}
