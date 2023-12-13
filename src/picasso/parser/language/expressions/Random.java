package picasso.parser.language.expressions;

import picasso.parser.language.ExpressionTreeNode;

/**
 * Represents the random function in the Picasso language.
 * 
 * @author Nabil Youssef.
 */
public class Random extends ExpressionTreeNode {

	double min = -1.000;
	double max = 1.000;
	double red;
	double green;
	double blue;

	public Random() {
		red = roundToTwoDecimalPlaces((Math.random() * (max - min) + min));
		green = roundToTwoDecimalPlaces((Math.random() * (max - min) + min));
		blue = roundToTwoDecimalPlaces((Math.random() * (max - min) + min));
	}

	private double roundToTwoDecimalPlaces(double value) {
		return (double) Math.round(value * Math.pow(10, 2)) / Math.pow(10, 2);
	}

	/**
	 * @param x
	 * @param y
	 * @return
	 * @see picasso.parser.language.ExpressionTreeNode#evaluate(double, double)
	 */
	@Override
	public RGBColor evaluate(double x, double y) {

		return new RGBColor(red, green, blue);
	}

	@Override
	public String toString() {
		return "[" + red + ", " + green + ", " + blue + "]";
	}

}