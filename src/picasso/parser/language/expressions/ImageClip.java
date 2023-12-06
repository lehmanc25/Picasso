package picasso.parser.language.expressions;

import picasso.parser.language.ExpressionTreeNode;

/**
 * Represents the image clip function ni Picasso.
 * This function clips an image based on evaluated x and y coordinates.
 */
public class ImageClip extends ExpressionTreeNode {

    private Image image;
    private ExpressionTreeNode xExpr;
    private ExpressionTreeNode yExpr;

    /**
     * Creates an image clip expression that takes as parameters an image and x,y coordinates to 
     * be evaluated.
     * 
     * @param image The image to be clipped.
     * @param xExpr The expression for the x-coordinate.
     * @param yExpr The expression for the y-coordinate.
     */
    public ImageClip(Image image, ExpressionTreeNode xExpr, ExpressionTreeNode yExpr) {
        this.image = image;
        this.xExpr = xExpr;
        this.yExpr = yExpr;
    }

    /**
     * Evaluates this expression by evaluating the x and y coordinate expressions, and applying
     * the clip operation to the image.
     *
     * @param x The x-coordinate to evaluate.
     * @param y The y-coordinate to evaluate.
     * @return The RGBColor at the specified coordinates in the image after clipping.
     */
    @Override
    public RGBColor evaluate(double x, double y) {
        double xEvaluated = xExpr.evaluate(x, y).getRed();
        double yEvaluated = yExpr.evaluate(x, y).getRed();

        // Implement the clipping logic
        // For instance, if the coordinates are outside the image bounds, return a default color like black
        if (xEvaluated < -1 || xEvaluated > 1 || yEvaluated < -1 || yEvaluated > 1) {
            return new RGBColor(0, 0, 0); // Black color for out-of-bounds
        }

        // Evaluate the color at the given coordinates within the image
        return image.evaluate(xEvaluated, yEvaluated);
    }
}
