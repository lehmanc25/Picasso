package picasso.parser.language.expressions;


import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import picasso.model.Pixmap;
import picasso.parser.language.ExpressionTreeNode;

/**
 * Represents a quoted string in the Picasso language. This class extends
 * ExpressionTreeNode and represents a string enclosed in quotes.
 * 
 * @author Ford Scott
 */
public class Quote extends ExpressionTreeNode {

	protected String value;
	private BufferedImage image;

	/**
	 * Constructs a Quote expression with a given string value.
	 * 
	 * @param value The string value to be enclosed in quotes.
	 */
	public Quote(String value) {
		if (value == null) {
			throw new IllegalArgumentException("Parameter must not be null.");
		}
		this.value = value;
		File file = new File("images/" + value);
		//System.out.print(file);
		try {
			this.image = ImageIO.read(file);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Gets the string value enclosed in quotes.
	 * 
	 * @return The quoted string value.
	 */
	public String getValue() {
		return value;
	}

	/**
	 * Returns a string representation of this Quote expression.
	 * 
	 * @return A string in the format "Quote: value".
	 */
	@Override
	public String toString() {
		return "Quote: " + value;
	}
	
	/**
	 * Evaluates the image represented by the quoted expression at a specific (x, y) coordinate and returns the RGB
	 * color.
	 * 
	 * @param x The x-coordinate.
	 * @param y The y-coordinate.
	 * @return The RGB color at the specified coordinates.
	 */
	@Override
	public RGBColor evaluate(double x, double y) {
		int xexpr = Image.scale(x, image.getWidth());
		int yexpr = Image.scale(y, image.getHeight());

		return new RGBColor(new Color(image.getRGB(xexpr, yexpr)));
	}
}
