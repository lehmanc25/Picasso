package tests;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import picasso.parser.tokens.QuoteToken;
import picasso.parser.language.expressions.Image;
import picasso.parser.language.expressions.RGBColor;

public class ImageTest {

    @Test
    public void testImageCreation() {
        // Assuming you have a test image file in the correct location
        QuoteToken filenameToken = new QuoteToken("vortex.jpg");
        Image image = new Image(filenameToken);

        // Test that image is created successfully (not null)
        assertNotNull(image, "Image should be created successfully");
    }

    @Test
    public void testImageEvaluate() {
        QuoteToken filenameToken = new QuoteToken("vortex.jpg");
        Image image = new Image(filenameToken);

        // Test the evaluate method at a specific coordinate
        RGBColor color = image.evaluate(0.5, 0.5); // Example coordinates

        // Check if color is as expected (this depends on your test image)
        assertNotNull(color, "Color should not be null");
        // Additional assertions based on expected color values
    }

    // Additional test cases for edge cases, error handling, etc.
}
