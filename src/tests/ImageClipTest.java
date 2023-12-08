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
	    QuoteToken filenameToken = new QuoteToken("vortex.jpg");
	    Image image = new Image(filenameToken);

	    ExpressionTreeNode xExpr = new X(); // Example x coordinate
	    ExpressionTreeNode yExpr = new Y(); // Example y coordinate

	    ImageClip myTree = new ImageClip(image, xExpr, yExpr);

        assertEquals(new RGBColor(-6, -6, -6), myTree.evaluate(-1, -1));
        assertEquals(new RGBColor(0, 0, 0), myTree.evaluate(0, -1));
        assertEquals(new RGBColor(6, 6, 6), myTree.evaluate(1, -1));
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