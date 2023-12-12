package tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import picasso.parser.language.expressions.ImageWrap;
import picasso.parser.tokens.functions.ImageWrapToken;
import picasso.parser.language.expressions.RGBColor;
import picasso.parser.language.expressions.X;
import picasso.parser.language.expressions.Y;
import picasso.parser.language.expressions.Image;
import picasso.parser.tokens.QuoteToken;
import picasso.parser.language.ExpressionTreeNode;

public class ImageWrapTests {
	
	@Test
	public void TestImageWrapTokenDescription() {
		ImageWrapToken token = new ImageWrapToken();
		assertEquals(token.toString(), "ImageWrap Function Token");
	}
	
	@Test
	public void testImageWrapTokenFunction() {
		ImageWrapToken token = new ImageWrapToken();
		assertTrue(token.isFunction()== true);
	}
	
	@Test
	public void testImageWrapTokenNotConstant() {
		ImageWrapToken token = new ImageWrapToken();
		assertTrue(token.isConstant()== false);
	}
	
	@Test
	public void testImageWrapEvaluation() {
	    // Ensure proper initialization of the Image object with a QuoteToken
	    QuoteToken filenameToken = new QuoteToken("vortex.jpg");
	    Image image = new Image(filenameToken);

	    // Initialize X and Y expressions
	    ExpressionTreeNode xExpr = new X(); 
	    ExpressionTreeNode yExpr = new Y(); 

	    ImageWrap myTree = new ImageWrap(image, xExpr, yExpr);

	    // Replace the expected RGBColor values with those you expect
	    assertEquals(new RGBColor(1, 1, 1), myTree.evaluate(1, -1));
	    assertEquals(new RGBColor(0, 0, 0), myTree.evaluate(0, -1));
	    assertEquals(new RGBColor(1, 1, 1), myTree.evaluate(-1, -1));
	    
		
		for(int i=-1; i<=1; i++) {
			assertEquals(new RGBColor(i, i, i), myTree.evaluate(i, -i));
			assertEquals(new RGBColor(i, i, i), myTree.evaluate(i, i));
		}
		
		double[] tests = {1.1, -1.1, 1.5, -.5};
		
		for (double testVal : tests) {
			if(testVal>1) {
				double difference = testVal - 1;
				double wrapOfTestVal = -1 + difference;
				assertEquals(new RGBColor(wrapOfTestVal, wrapOfTestVal, wrapOfTestVal), myTree.evaluate(wrapOfTestVal, -1));
			}
			if(testVal<-1) {
				double difference = -1 - testVal;
				double wrapOfTestVal = 1 - difference;
				assertEquals(new RGBColor(wrapOfTestVal, wrapOfTestVal, wrapOfTestVal), myTree.evaluate(wrapOfTestVal, -1));
			}
			else {
				double wrapOfTestVal = testVal;
				assertEquals(new RGBColor(wrapOfTestVal, wrapOfTestVal, wrapOfTestVal), myTree.evaluate(wrapOfTestVal, -1));
			}
		}
	}
}