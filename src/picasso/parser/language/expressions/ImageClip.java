package picasso.parser.language.expressions;

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
		this.img = img;
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
