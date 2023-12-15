package picasso.parser.language.expressions;

import picasso.parser.language.ExpressionTreeNode;
import picasso.parser.tokens.QuoteToken; // Import the QuoteToken class
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

import javax.imageio.*;

/**
 * Represents an image in the Picasso language. This class extends
 * ExpressionTreeNode and provides methods to work with images.
 * 
 * @author Ford Scott
 */
public class Image extends ExpressionTreeNode {

	private String myfilename;
	private BufferedImage myImage;

	/**
	 * Constructs an Image object from a QuoteToken.
	 * 
	 * @param token A QuoteToken representing the image file.
	 */
	public Image(ExpressionTreeNode quote) {
		try {
			Quote qt = (Quote) quote;
			this.myfilename = qt.getValue(); // Extract filename from QuoteToken
			File file = new File("images/" + myfilename);
			//System.out.print(file);
			this.myImage = ImageIO.read(file);

		} catch (IOException e) {
			e.printStackTrace();

		}
	}

	/**
	 * @return the string representation of this image object
	 */
	@Override
	public String toString() {
		return "Image(" + myfilename + ")";
	}

	/**
	 * @return the hashcode of this image object
	 */
	@Override
	public int hashCode() {
		return Objects.hash(myImage, myfilename);
	}

	/**
	 * @param obj
	 * @return true if both images are equal, or false otherwise
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Image)) {
			return false;
		}
		Image other = (Image) obj;
		return Objects.equals(myfilename, other.myfilename);
	}

	/**
	 * Scales a double value to fit within the bounds.
	 * 
	 * @param value  The double value to scale.
	 * @param bounds The upper bound for scaling.
	 * @return The scaled integer value.
	 */
	public static int scale(double value, int bounds) {
		return (int) (((value + 1) / 2) * (bounds - 1));
	}

	/**
	 * Evaluates the image at a specific (x, y) coordinate and returns the RGB
	 * color.
	 * 
	 * @param x The x-coordinate.
	 * @param y The y-coordinate.
	 * @return The RGB color at the specified coordinates.
	 */
	@Override
	public RGBColor evaluate(double x, double y) {
		int xexpr = scale(x, myImage.getWidth());
		int yexpr = scale(y, myImage.getHeight());

		return new RGBColor(new Color(myImage.getRGB(xexpr, yexpr)));
	}
}
