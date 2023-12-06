package picasso.parser.language.expressions;

import picasso.parser.language.ExpressionTreeNode;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * Represents an image in Picasso. This class handles loading
 * an image from a file and retrieving a color from a specific pixel in the image.
 * The image file path is relative to a 'images' directory in the project.
 */
public class Image extends ExpressionTreeNode {
    private String filename;
    private BufferedImage image;

    /**
     * Constructor for Image class.
     * 
     * @param filename The filename of the image to load.
     * 
     * @author Ford Scott
     */
    public Image(String filename) {
        this.filename = filename;
        try {
            File file = new File("images/" + filename);
            this.image = ImageIO.read(file);
        } catch (IOException e) {
            e.printStackTrace();
            // Handle exception or set image to a default/placeholder image if required
        }
    }

    /**
     * Scales a value from the expression coordinate system to image pixel coordinates.
     *
     * @param value  The value to scale (typically x or y coordinate).
     * @param bounds The bounds of the image (width or height).
     * @return The scaled value.
     */
    public static int scale(double value, int bounds) {
        return (int) (((value + 1) / 2) * (bounds - 1));
    }

    /**
     * Evaluates the expression by returning the color of the image at the given x and y coordinates.
     * Coordinates are scaled to match the dimensions of the image.
     *
     * @param x The x-coordinate to evaluate.
     * @param y The y-coordinate to evaluate.
     * @return The RGBColor at the specified coordinates in the image.
     */
    @Override
    public RGBColor evaluate(double x, double y) {
        int xScaled = scale(x, image.getWidth());
        int yScaled = scale(y, image.getHeight());
        Color color = new Color(image.getRGB(xScaled, yScaled));
        return new RGBColor(color.getRed() / 255.0, color.getGreen() / 255.0, color.getBlue() / 255.0);
    }
}
