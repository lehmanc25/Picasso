package tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import picasso.parser.language.expressions.*;
import picasso.parser.language.*;

/**
 * Tests for the ImageClip class using JUnit Jupiter.
 * 
 * @author Ford Scott
 */
public class ImageClipTest {

    private ImageClip imageClip;
    private Image testImage;
    private ExpressionTreeNode xExpr;
    private ExpressionTreeNode yExpr;

    @BeforeEach
    public void setUp() {
        testImage = new Image("vortex.jpg"); 
        xExpr = new Constant(0.5); 
        yExpr = new Constant(0.5); 
        imageClip = new ImageClip(testImage, xExpr, yExpr);
    }

    @Test
    public void testEvaluate() {
        RGBColor result = imageClip.evaluate(0, 0);
        assertNotNull(result, "Evaluate method should return a color");
        // Additional assertions as needed
    }

    // Additional tests can be added here
}
