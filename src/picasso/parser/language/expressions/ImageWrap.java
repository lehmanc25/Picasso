package picasso.parser.language.expressions;

import picasso.parser.language.ExpressionTreeNode;

/**
 * Represents an image wrapping expression in the Picasso language.
 * This class is responsible for handling the repetition (tiling) of an image
 * based on evaluated x and y coordinate expressions.
 * 
 * @author Ford Scott
 */
public class ImageWrap extends ExpressionTreeNode {
    
    private Image image;
    private ExpressionTreeNode xExpr;
    private ExpressionTreeNode yExpr;

    /**
     * Constructs an ImageWrap expression with the specified image and coordinate expressions.
     *
     * @param image The image to be tiled.
     * @param xExpr The x-coordinate expression.
     * @param yExpr The y-coordinate expression.
     */
    public ImageWrap(Image image, ExpressionTreeNode xExpr, ExpressionTreeNode yExpr) {
        this.image = image;
        this.xExpr = xExpr;
        this.yExpr = yExpr;
    }

    /**
     * Evaluates the ImageWrap expression at a specific (x, y) coordinate.
     *
     * @param x The x-coordinate.
     * @param y The y-coordinate.
     * @return The RGB color of the tiled image at the specified coordinates.
     */
    @Override
    public RGBColor evaluate(double x, double y) {
        double xEval = Wrap.wrap(xExpr.evaluate(x, y).getRed());
        double yEval = Wrap.wrap(yExpr.evaluate(x, y).getRed());
        
        return image.evaluate(xEval, yEval);
    }
}
