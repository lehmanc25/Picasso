package picasso.parser.language.expressions;

import picasso.parser.language.ExpressionTreeNode;

public class Exp extends UnaryFunction {

	public Exp(ExpressionTreeNode param) {
		super(param);
	}

	@Override
	public RGBColor evaluate(double x, double y) {
		
		RGBColor result = param.evaluate(x, y);
		double red = Math.exp(result.getRed());
		double green = Math.exp(result.getGreen());
		double blue = Math.exp(result.getBlue());
		
		return new RGBColor(red, green, blue);
	}

}
