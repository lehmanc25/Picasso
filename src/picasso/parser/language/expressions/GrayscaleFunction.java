package picasso.parser.language.expressions;

import picasso.parser.language.ExpressionTreeNode;

/**
 * Represents the grayscale function in the Picasso language.
 * This function computes the average of the RGB values of a color
 * and sets all RGB components to this average, resulting in a grayscale color.
 */
public class GrayscaleFunction extends UnaryFunction {

    /**
     * Constructor for the GrayscaleFunction.
     *
     * @param param the expression tree node to be converted to grayscale
     */
    public GrayscaleFunction(ExpressionTreeNode param) {
        super(param);
    }

    @Override
    public RGBColor evaluate(double x, double y) {
        RGBColor color = param.evaluate(x, y);
        double average = (color.getRed() + color.getGreen() + color.getBlue()) / 3;
        return new RGBColor(average, average, average);
    }
}
