package picasso.parser.language.expressions;

import java.util.Objects;

import picasso.parser.language.ExpressionTreeNode;

/**
 * Represents an image clipping expression in the Picasso language.
 * This class extends ExpressionTreeNode and represents the clipping of an image at specified coordinates.
 * 
 * @author Ford Scott
 */
public class ImageClip extends ExpressionTreeNode {
    

	public static Image img;
    private ExpressionTreeNode paramx;
    private ExpressionTreeNode paramy;
    
    /**
     * Constructs an ImageClip expression with the specified image and coordinate expressions.
     * 
     * @param img    The image to clip.
     * @param paramx The x-coordinate expression.
     * @param paramy The y-coordinate expression.
     */
    public ImageClip(Image img, ExpressionTreeNode paramx, ExpressionTreeNode paramy) {
        this.paramx = paramx;
        this.paramy = paramy;
        ImageClip.img = img;
    }
    
    /**
	 * @return the hashcode of 2 image clip objects
	 */
	@Override
	public int hashCode() {
		return Objects.hash(paramx, paramy);
	}

	/**
	 * @param obj
	 * @return true if both image clip objects are equal, and false otherwise
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof ImageClip)) {
			return false;
		}
		ImageClip other = (ImageClip) obj;
		return Objects.equals(img, other.img) && Objects.equals(paramx, other.paramx) && Objects.equals(paramy, other.paramy);
	}

	@Override
	public String toString() {
		return "ImageClip(" + img + " , " + paramx + " , " + paramy + " )";
	}
    /**
     * Evaluates the ImageClip expression by evaluating the x and y coordinate expressions
     * and applying the resulting color to the image.
     * 
     * @param x The x-coordinate.
     * @param y The y-coordinate.
     * @return The resulting RGB color after applying the clipping operation.
     */
    public RGBColor evaluate(double x, double y) {
        double xexpr = Clamp.clamp((paramx.evaluate(x, y)).getRed());
        double yexpr = Clamp.clamp((paramy.evaluate(x, y)).getRed());
        
        RGBColor imgColor = img.evaluate(xexpr, yexpr);
        return imgColor;
    }
}

