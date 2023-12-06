package picasso.parser.language.expressions;

import picasso.parser.language.ExpressionTreeNode;

/**
 * Represents the image wrap function in Picasso.
 * This function wraps an image based on evaluated x and y coordinates.
 * 
 * @author Ford Scott
 */
public class ImageWrap extends ExpressionTreeNode {

    private Image image;
    private ExpressionTreeNode xExpr;
    private ExpressionTreeNode yExpr;

    /**
     * Creates an image wrap expression that takes as parameters an image and x,y coordinates to 
     * be evaluated.
     * 
     * @param image The image to be wrapped.
     * @param xExpr The expression for the x-coordinate.
     * @param yExpr The expression for the y-coordinate.
     */
    public ImageWrap(Image image, ExpressionTreeNode xExpr, ExpressionTreeNode yExpr) {
        this.image = image;
        this.xExpr = xExpr;
        this.yExpr = yExpr;
    }

    /**
     * Evaluates this expression by evaluating the x and y coordinate expressions, and applying
     * the wrap operation to the image.
     *
     * @param x The x-coordinate to evaluate.
     * @param y The y-coordinate to evaluate.
     * @return The RGBColor at the specified coordinates in the image after wrapping.
     */
    @Override
    public RGBColor evaluate(double x, double y) {
        double xEvaluated = wrap(xExpr.evaluate(x, y).getRed());
        double yEvaluated = wrap(yExpr.evaluate(x, y).getRed());

        // Wrap the evaluated coordinates within the image bounds
        return image.evaluate(xEvaluated, yEvaluated);
    }

    /**
     * Wraps a coordinate value within the range [-1, 1].
     * 
     * @param value The value to be wrapped.
     * @return The wrapped value.
     */
    private double wrap(double value) {
        value = value % 2; // Wrap value within range [-2, 2]
        if (value > 1) {
            value -= 2;
        } else if (value < -1) {
            value += 2;
        }
        return value;
    }
}
