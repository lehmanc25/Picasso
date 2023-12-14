package picasso.parser.language.expressions;

import java.util.Objects;

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
	 * @return the hash value of this image wrap object
	 */
	@Override
	public int hashCode() {
		return Objects.hash(image, xExpr, yExpr);
	}

	/**
	 * @param obj
	 * @return true if both image wrap objects are equal, and false otherwise. 
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof ImageWrap)) {
			return false;
		}
		ImageWrap other = (ImageWrap) obj;
		return this.image.equals(other.image) && this.xExpr.equals(other.xExpr)
				&& this.yExpr.equals(other.yExpr);
	}


    /**
	 * @return the string representation of the image wrap object
	 */
	@Override
	public String toString() {
		return "ImageWrap(" + image + " , " + xExpr + " , " + yExpr + " )";
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
