package picasso.parser.language.expressions;

import picasso.model.ImprovedNoise;
import picasso.parser.language.ExpressionTreeNode;

/**
 * Represents the perlinColor function in the Picasso language.
 * 
 * @author Connor Lehman.
 */
public class PerlinColor extends BinaryFunction {

	/**
	 * @param param_left
	 * @param param_right
	 * 
	 *                    Represents the PerlinColor function in the Picasso
	 *                    language.
	 */
	public PerlinColor(ExpressionTreeNode param_left, ExpressionTreeNode param_right) {
		super(param_left, param_right);
	}

	@Override
	public RGBColor evaluate(double x, double y) {
		RGBColor result_left = param_left.evaluate(x, y);
		RGBColor result_right = param_right.evaluate(x, y);

		double red = ImprovedNoise.noise(result_left.getRed() + 0.3, result_right.getRed() + 0.3, 0);
		double blue = ImprovedNoise.noise(result_left.getBlue() + 0.1, result_right.getBlue() + 0.1, 0);
		double green = ImprovedNoise.noise(result_left.getGreen() - 0.8, result_right.getGreen() - 0.8, 0);
		return new RGBColor(red, green, blue);
	}

	@Override
	public String toString() {
		return "perlinColor(" + this.param_left + "," + this.param_right + ")";
	}
}