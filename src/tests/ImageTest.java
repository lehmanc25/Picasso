package tests;

import picasso.parser.language.expressions.*;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests for the Image class.
 * 
 * @author Ford Scott
 */
public class ImageTest {

    private Image testImage;

    @Before
    public void setUp() {
        // Assuming there's a test image
        testImage = new Image("testImage.jpg");
    }

    @Test
    public void testImageLoading() {
        assertNotNull("Image loading failed", testImage);
    }

    @Test
    public void testEvaluateColorAtCenter() {
        // Assuming the center of testImage.jpg is white (RGB: 1, 1, 1)
        RGBColor expectedColor = new RGBColor(1, 1, 1);
        RGBColor actualColor = testImage.evaluate(0, 0); // Center of the image
        assertEquals("Color evaluation at center is incorrect", expectedColor, actualColor);
    }

    @Test
    public void testEvaluateColorAtCorner() {
        // Assuming the top-left corner of testImage.jpg is black (RGB: -1, -1, -1)
        RGBColor expectedColor = new RGBColor(-1, -1, -1);
        RGBColor actualColor = testImage.evaluate(-1, 1); // Top-left corner of the image
        assertEquals("Color evaluation at top-left corner is incorrect", expectedColor, actualColor);
    }

    // Add more tests for different coordinates and color expectations
}
