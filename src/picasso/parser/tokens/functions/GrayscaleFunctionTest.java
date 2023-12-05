package picasso.parser.tokens.functions;
import static org.junit.Assert.*;
import org.junit.Test;
import picasso.parser.language.expressions.Constant;
import picasso.parser.language.expressions.RGBColor;
import picasso.parser.language.expressions.GrayscaleFunction;

/**
 * Tests for the GrayscaleFunction class.
 */
public class GrayscaleFunctionTest {

    /**
     * Test for the evaluate method of GrayscaleFunction.
     * Ensures that it correctly converts colors to grayscale.
     */
    @Test
    public void testEvaluate() {
        RGBColor color = new RGBColor(0.5, -0.2, 0.1);
        GrayscaleFunction grayscale = new GrayscaleFunction(new Constant(color));
        RGBColor result = grayscale.evaluate(0, 0);
        double expectedValue = (0.5 - 0.2 + 0.1) / 3;
        RGBColor expectedColor = new RGBColor(expectedValue, expectedValue, expectedValue);
        assertEquals(expectedColor, result);
    }
}

