package tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import picasso.parser.language.expressions.ImageClip;
import picasso.parser.tokens.functions.ImageClipToken;
import picasso.parser.language.expressions.RGBColor;
import picasso.parser.language.expressions.X;
import picasso.parser.language.expressions.Y;
import picasso.parser.language.expressions.Image;
import picasso.parser.tokens.QuoteToken;
import picasso.parser.language.ExpressionTreeNode;

public class ImageClipTest {
	
	@Test
	public void TestImageClipTokenDescription() {
		ImageClipToken token = new ImageClipToken();
		assertEquals(token.toString(), "ImageClip Function Token");
	}
	
	@Test
	public void testImageClipTokenFunction() {
		ImageClipToken token = new ImageClipToken();
		assertTrue(token.isFunction()== true);
	}
	
	@Test
	public void testImageClipTokenNotConstant() {
		ImageClipToken token = new ImageClipToken();
		assertTrue(token.isConstant()== false);
	}
	
	@Test
	public void testImageClipEvaluation() {
	    QuoteToken filenameToken = new QuoteToken("\"vortex.jpg\""); // Correctly create QuoteToken
	    Image image = new Image(filenameToken); // Create Image instance

	    // Assuming you have constant expression classes X and Y
	    ExpressionTreeNode xExpr = new X(); // Replace with your actual expression implementation
	    ExpressionTreeNode yExpr = new Y(); // Replace with your actual expression implementation

	    ImageClip myTree = new ImageClip(image, xExpr, yExpr);

	    // Test the evaluation method with actual expected colors
	    // These colors should be determined based on the content of "vortex.jpg"
	    // Example:
	    RGBColor expectedColor = new RGBColor(-6, -6, -6); // Replace with actual expected color
	    RGBColor actualColor = myTree.evaluate(-1, -1);
	    assertEquals(expectedColor, actualColor, "Color should match expected value");

	    // Additional tests can be added here
		double[] tests = {7, -10, 100, -.5};
		
		for (double testVal : tests) {
			if(testVal>1) {
				double clampOfTestVal = 1;
				assertEquals(new RGBColor(clampOfTestVal, clampOfTestVal, clampOfTestVal), myTree.evaluate(1, -1));
			}
			if(testVal<-1) {
				double clampOfTestVal = -1;
				assertEquals(new RGBColor(clampOfTestVal, clampOfTestVal, clampOfTestVal), myTree.evaluate(-1, -1));
			}
			else {
				double clampOfTestVal = testVal;
				assertEquals(new RGBColor(clampOfTestVal, clampOfTestVal, clampOfTestVal), myTree.evaluate(testVal, -1));
			}
		}
	}
}