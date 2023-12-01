/**
 * 
 */
package picasso.parser.language.expressions;

import picasso.parser.language.ExpressionTreeNode;

/**
 * Represents the addition expression in the Picasso language
 */
public class Addition extends ExpressionTreeNode {
	
	ExpressionTreeNode param1;
	ExpressionTreeNode param2;

	/**
	 * Create an addition expression that takes the given expressions as parameters
	 * @param1 the first parameter to be added 
	 * @param2 the second parameter to be added
	 */
	public Addition(ExpressionTreeNode param1, ExpressionTreeNode param2) {
		this.param1 = param1;
		this.param2 = param2;
	}

	/**
	 * Evaluates this expression at the given x, y point by evaluating the sum of the 
	 * function's parameters' evaluations
	 * @param x
	 * @param y
	 * @return the color from evaluating the sum of the function's parameter's evaluations
	 * 
	 */
	@Override
	public RGBColor evaluate(double x, double y) {
		
		RGBColor result1 = param1.evaluate(x, y);
		RGBColor result2 = param2.evaluate(x, y);
		
		double red = result1.getRed() + result2.getRed();
		double green = result1.getGreen() + result2.getGreen();
		double blue = result1.getBlue() + result2.getBlue();
		
 		return new RGBColor(red, green, blue);
	}
	@Override
	/**
	 * This should also be in the abstract class talked about below.
	 * @return the string representation of the addition expression.
	 */
	public String toString() {
		return "(" + this.param1 + "+" + this.param2 + ")";
	}
	@Override
	/**
	 * Compares two addition expressions and checks if they're equal
	 * This should probably be designed better: we should have a binary operator abstract
	 * class that implements this method, and then have this class inherit from it. 
	 * @return true if both expressions are equal, or false otherwise. 
	 */
	public boolean equals(Object o) {
		if (o == this) {
			return true;
		}
		if (!(o instanceof Addition)) {
			return false;
		}
		if (o.getClass() != this.getClass()) {
			return false;
		}
		Addition add = (Addition) o;
		
		if (!this.param1.equals(add.param1) || !this.param2.equals(add.param2)) {
			return false;
		}
		return true;
	}
}
