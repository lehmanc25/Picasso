package picasso.parser.language.expressions;

import picasso.parser.language.ExpressionTreeNode;

public class Log extends UnaryFunction {

	/**
	 * Create a log expression that takes as a parameter the given expression
	 * 
	 * @param param the expression to log
	 */
	public Log(ExpressionTreeNode param) {
		super(param);
	}

	/**
	 * Evaluates this expression at the given x,y point by evaluating the log of
	 * the function's parameter.
	 * 
	 * @return the color from evaluating the log of the expression's parameter
	 */
	@Override
	public RGBColor evaluate(double x, double y) {
		//Handling for log(0) undefined cases; defaulting to zero
		RGBColor result;
		if (x < 0 && y < 0) {
			result = param.evaluate(Math.abs(x), Math.abs(y));
		}
		else if (x < 0 && y == 0) {
			result = param.evaluate(Math.abs(x), y+1);
		}
		else if (x < 0 && y > 0) {
			result = param.evaluate(Math.abs(x), y);
			
		}
		else if (x == 0 && y == 0) {
			result = param.evaluate(-1, -1);
		}
		else if (x == 0 && y < 0) {
			result = param.evaluate(-1, Math.abs(y));
		}
		else if (x == 0 && y > 0) {
			result = param.evaluate(-1, y);
		}
		
		else if (x > 0 && y < 0) {
			result = param.evaluate(x, Math.abs(y));
		}
		else if (x > 0 && y == 0) {
			result = param.evaluate(x, y+1);
		}
		else {
			result = param.evaluate(x, y);
		}
		double red = Math.log(result.getRed());
		double green = Math.log(result.getGreen());
		double blue = Math.log(result.getBlue());
		
		return new RGBColor(red, green, blue);

		
	}
	
	@Override
    public String toString() {
        return "log(" + this.param + ")";
    }

}