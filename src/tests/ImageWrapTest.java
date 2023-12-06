package tests; 

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import picasso.parser.language.expressions.*;
import picasso.parser.language.*;

/**
 * Tests for the ImageWrap class using JUnit Jupiter.
 * 
 * @author Ford Scott
 */
public class ImageWrapTest {

    private ImageWrap imageWrap;
    private Image testImage;
    private ExpressionTreeNode xExpr;
    private ExpressionTreeNode yExpr;

    @BeforeEach
    public void setUp() {
        testImage = new Image("thread.jpg"); 
        xExpr = new Constant(-1); 
        yExpr = new Constant(-1); 
        imageWrap = new ImageWrap(testImage, xExpr, yExpr);
    }

    @Test
    public void testEvaluate() {
        RGBColor result = imageWrap.evaluate(0, 0);
        assertNotNull(result, "Evaluate method should return a color");
        // Additional assertions as needed
    }

    // Additional tests can be added here
}

