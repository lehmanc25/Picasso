package picasso.parser.language.expressions;

import picasso.parser.language.ExpressionTreeNode;

/**
 * Represents a quoted string in the Picasso language.
 * This class extends ExpressionTreeNode and represents a string enclosed in quotes.
 * 
 * @author Ford Scott
 */
public class Quote extends ExpressionTreeNode {
    
    protected String value;

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
     * Evaluates the Quote expression.
     * Depending on the context, this method might return a default value or be handled differently.
     * 
     * @param x The x-coordinate (unused).
     * @param y The y-coordinate (unused).
     * @return An RGBColor (example: returning black by default).
     */
    @Override
    public RGBColor evaluate(double x, double y) {
        // Depending on the context, this method might return a default color,
        // or handle the quote differently.
        return new RGBColor(0, 0, 0); // Example: returning black by default.
    }
}
