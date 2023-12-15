/**
 * 
 */
package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Stack;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import picasso.model.ImprovedNoise;
import picasso.parser.AssignmentAnalyzer;
import picasso.parser.ExpressionTreeGenerator;
import picasso.parser.IdentifierAnalyzer;
import picasso.parser.language.ExpressionTreeNode;
import picasso.parser.language.expressions.*;
import picasso.parser.tokens.IdentifierToken;
import picasso.parser.tokens.Token;
import picasso.parser.tokens.operations.AssignmentToken;
import picasso.parser.tokens.operations.PlusToken;

/**
 * Tests of the evaluation of expression trees
 * 
 */
public class EvaluatorTests {

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	public void setUp() throws Exception {
	}

	@Test
	public void testConstantEvaluation() {
		ExpressionTreeNode e = new RGBColor(1, -1, 1);
		for (int i = -1; i <= 1; i++) {
			assertEquals(new RGBColor(1, -1, 1), e.evaluate(i, i));
		}
	}

	@Test
	public void testXEvaluation() {
		X x = new X();
		for (int i = -1; i <= 1; i++) {
			assertEquals(new RGBColor(i, i, i), x.evaluate(i, i));
		}
	}
	@Test
	public void testYEvaluation() {
		Y y = new Y();
		for (int i = -1; i <= 1; i++) {
			assertEquals(new RGBColor(i, i, i), y.evaluate(i, i));
		}
	}

	@Test
	public void testFloorEvaluation() {
		Floor myTree = new Floor(new X());

		// some straightforward tests
		assertEquals(new RGBColor(0, 0, 0), myTree.evaluate(.4, -1));
		assertEquals(new RGBColor(0, 0, 0), myTree.evaluate(.999, -1));
		assertEquals(new RGBColor(-1, -1, -1), myTree.evaluate(-.7, -1));

		// test the ints; remember that y's value doesn't matter
		for (int i = -1; i <= 1; i++) {
			assertEquals(new RGBColor(i, i, i), myTree.evaluate(i, -i));
			assertEquals(new RGBColor(i, i, i), myTree.evaluate(i, i));
		}

		double[] tests = { -.7, -.00001, .000001, .5 };

		for (double testVal : tests) {
			double floorOfTestVal = Math.floor(testVal);
			assertEquals(new RGBColor(floorOfTestVal, floorOfTestVal, floorOfTestVal), myTree.evaluate(testVal, -1));
			assertEquals(new RGBColor(floorOfTestVal, floorOfTestVal, floorOfTestVal),
					myTree.evaluate(testVal, testVal));
		}
	}
  
	@Test
	public void testCeilEvaluation() {
		Ceil myTree = new Ceil(new X());

		// some straightforward tests
		assertEquals(new RGBColor(1, 1, 1), myTree.evaluate(.4, -1));
		assertEquals(new RGBColor(1, 1, 1), myTree.evaluate(.999, -1));
		assertEquals(new RGBColor(0, 0, 0), myTree.evaluate(-.7, -1));

		// test the ints; remember that y's value doesn't matter
		for (int i = -1; i <= 1; i++) {
			assertEquals(new RGBColor(i, i, i), myTree.evaluate(i, -i));
			assertEquals(new RGBColor(i, i, i), myTree.evaluate(i, i));
		}

		double[] tests = { -.7, -.00001, .000001, .5 };

		for (double testVal : tests) {
			double ceilOfTestVal = Math.ceil(testVal);
			assertEquals(new RGBColor(ceilOfTestVal, ceilOfTestVal, ceilOfTestVal), myTree.evaluate(testVal, -1));
			assertEquals(new RGBColor(ceilOfTestVal, ceilOfTestVal, ceilOfTestVal),
					myTree.evaluate(testVal, testVal));
		}
	} 
	@Test
	public void testLogEvaluation() {
		Log myTree = new Log(new X());
		///Basic Corner Tests - All pass initial JUnit testing
		assertEquals(new RGBColor(0, 0, 0), myTree.evaluate(1, -1));
		assertEquals(new RGBColor(0, 0, 0), myTree.evaluate(1, 1));
		assertEquals(new RGBColor(0, 0, 0), myTree.evaluate(-1, 1)); 
		assertEquals(new RGBColor(0, 0, 0), myTree.evaluate(-1, -1));
		
		//Basic Midpoint Tests
		assertEquals(new RGBColor(Double.NEGATIVE_INFINITY, Double.NEGATIVE_INFINITY, Double.NEGATIVE_INFINITY), myTree.evaluate(0, 1));
		assertEquals(new RGBColor(0, 0, 0), myTree.evaluate(1, 0));
		assertEquals(new RGBColor(Double.NEGATIVE_INFINITY, Double.NEGATIVE_INFINITY, Double.NEGATIVE_INFINITY), myTree.evaluate(0, 0));
		assertEquals(new RGBColor(0, 0, 0), myTree.evaluate(-1, 0));
		assertEquals(new RGBColor(Double.NEGATIVE_INFINITY, Double.NEGATIVE_INFINITY, Double.NEGATIVE_INFINITY), myTree.evaluate(0, -1));


		///Double Tests
		double[] tests = { -.7, -.00001, .000001, .5 };

		for (double testVal : tests) {
			double logOfTestVal = Math.log(Math.abs(testVal));
			assertEquals(new RGBColor(logOfTestVal, logOfTestVal, logOfTestVal), myTree.evaluate(testVal, -1));
			assertEquals(new RGBColor(logOfTestVal, logOfTestVal, logOfTestVal), myTree.evaluate(testVal, testVal));
		}
		myTree = new Log(new Y());
		///Basic Corner Tests - All pass initial JUnit testing
		assertEquals(new RGBColor(0, 0, 0), myTree.evaluate(1, -1));
		assertEquals(new RGBColor(0, 0, 0), myTree.evaluate(1, 1));
		assertEquals(new RGBColor(0, 0, 0), myTree.evaluate(-1, 1)); 
		assertEquals(new RGBColor(0, 0, 0), myTree.evaluate(-1, -1));
				
		//Basic Midpoint Tests - All pass initial JUnit testing **Note: The value -744.4400719213812 represents the undefined value for log(0) since the evaluate expression is adding
		//.000001 to account for the value
		assertEquals(new RGBColor(0, 0, 0), myTree.evaluate(0, 1));
		assertEquals(new RGBColor(-744.4400719213812, -744.4400719213812, -744.4400719213812), myTree.evaluate(1, 0));
		assertEquals(new RGBColor(-744.4400719213812, -744.4400719213812, -744.4400719213812), myTree.evaluate(0, 0));
		assertEquals(new RGBColor(-744.4400719213812, -744.4400719213812, -744.4400719213812), myTree.evaluate(-1, 0));
		assertEquals(new RGBColor(0, 0, 0), myTree.evaluate(0, -1));
		
		for (double testVal : tests) {	
			double logOfTestVal = Math.log(Math.abs(testVal));
			assertEquals(new RGBColor(logOfTestVal, logOfTestVal, logOfTestVal), myTree.evaluate(-1, testVal));
			assertEquals(new RGBColor(logOfTestVal, logOfTestVal, logOfTestVal), myTree.evaluate(testVal, testVal));		
		}
	}

	
	@Test
	public void testCosineEvaluation() {
		Cos myTree = new Cos(new X());
		
		assertEquals(new RGBColor(0.5403023058681398, 0.5403023058681398, 0.5403023058681398), myTree.evaluate(-1, -1));
		assertEquals(new RGBColor(0.5403023058681398, 0.5403023058681398, 0.5403023058681398), myTree.evaluate(-1, 1));
		assertEquals(new RGBColor(1, 1, 1), myTree.evaluate(0, 0));
		assertEquals(new RGBColor(0.5403023058681398, 0.5403023058681398, 0.5403023058681398), myTree.evaluate(1, -1));
		assertEquals(new RGBColor(0.5403023058681398, 0.5403023058681398, 0.5403023058681398), myTree.evaluate(1, 1));
		
		double[] tests = { -.7, -.00001, .000001, .5 };

		for (double testVal : tests) {
			double cosOfTestVal = Math.cos(testVal);
			assertEquals(new RGBColor(cosOfTestVal, cosOfTestVal, cosOfTestVal), myTree.evaluate(testVal, -1));
			assertEquals(new RGBColor(cosOfTestVal, cosOfTestVal, cosOfTestVal),
					myTree.evaluate(testVal, testVal));
		}
	}
	@Test
	public void testPerlinColorEvaluation() {
		PerlinColor myTree = new PerlinColor(new X(), new Y());
		
		double red = ImprovedNoise.noise(-1 + 0.3, -1 + 0.3, 0);
		double blue = ImprovedNoise.noise(-1 + 0.1, -1 + 0.1, 0);
		double green = ImprovedNoise.noise(-1 - 0.8, -1 - 0.8, 0);
		assertEquals(new RGBColor(red, green, blue), myTree.evaluate(-1, -1));
		
		red = ImprovedNoise.noise(-1 + 0.3, 1 + 0.3, 0);
		blue = ImprovedNoise.noise(-1 + 0.1, 1 + 0.1, 0);
		green = ImprovedNoise.noise(-1 - 0.8, 1 - 0.8, 0);
		assertEquals(new RGBColor(red, green, blue), myTree.evaluate(-1, 1));
		
		red = ImprovedNoise.noise(0 + 0.3, 0 + 0.3, 0);
		blue = ImprovedNoise.noise(0 + 0.1, 0 + 0.1, 0);
		green = ImprovedNoise.noise(0 - 0.8, 0 - 0.8, 0);
		assertEquals(new RGBColor(red, green, blue), myTree.evaluate(0, 0));
		
		red = ImprovedNoise.noise(1 + 0.3, -1 + 0.3, 0);
		blue = ImprovedNoise.noise(1 + 0.1, -1 + 0.1, 0);
		green = ImprovedNoise.noise(1 - 0.8, -1 - 0.8, 0);
		assertEquals(new RGBColor(red, green, blue), myTree.evaluate(1, -1));
		
		
		red = ImprovedNoise.noise(1 + 0.3, 1 + 0.3, 0);
		blue = ImprovedNoise.noise(1 + 0.1, 1 + 0.1, 0);
		green = ImprovedNoise.noise(1 - 0.8, 1 - 0.8, 0);
		assertEquals(new RGBColor(red, green, blue), myTree.evaluate(1, 1));
		
		
	}
	@Test
	public void testAbsEvaluation() {
		Abs myTree = new Abs(new X());
		
		assertEquals(new RGBColor(1, 1, 1), myTree.evaluate(-1, 1));
		assertEquals(new RGBColor(0, 0, 0), myTree.evaluate(0, 0));
		assertEquals(new RGBColor(1, 1, 1), myTree.evaluate(1, -1));

		
	}
	@Test
	public void testSinEvaluation() {
		Sin myTree = new Sin(new Y());
		
		assertEquals(new RGBColor(-0.8414709848078965, -0.8414709848078965, -0.8414709848078965), myTree.evaluate(-1, -1));
		assertEquals(new RGBColor(0.8414709848078965, 0.8414709848078965, 0.8414709848078965), myTree.evaluate(-1, 1));
		assertEquals(new RGBColor(0, 0, 0), myTree.evaluate(0, 0));
		assertEquals(new RGBColor(-0.8414709848078965, -0.8414709848078965, -0.8414709848078965), myTree.evaluate(1, -1));
		assertEquals(new RGBColor(0.8414709848078965, 0.8414709848078965, 0.8414709848078965), myTree.evaluate(1, 1));
		
		double[] tests = { -.7, -.00001, .000001, .5 };

		for (double testVal : tests) {
			double sinOfTestVal = Math.sin(testVal);
			assertEquals(new RGBColor(sinOfTestVal, sinOfTestVal, sinOfTestVal), myTree.evaluate(-1, testVal));
			assertEquals(new RGBColor(sinOfTestVal, sinOfTestVal, sinOfTestVal),
					myTree.evaluate(testVal, testVal));
		}
	}
	@Test
	public void testTanEvaluation() {
		Tan myTree = new Tan(new Y());
		
		assertEquals(new RGBColor(-1.5574077246549023, -1.5574077246549023, -1.5574077246549023), myTree.evaluate(-1, -1));
		assertEquals(new RGBColor(1.5574077246549023, 1.5574077246549023, 1.5574077246549023), myTree.evaluate(-1, 1));
		assertEquals(new RGBColor(0, 0, 0), myTree.evaluate(0, 0));
		assertEquals(new RGBColor(-1.5574077246549023, -1.5574077246549023, -1.5574077246549023), myTree.evaluate(1, -1));
		assertEquals(new RGBColor(1.5574077246549023, 1.5574077246549023, 1.5574077246549023), myTree.evaluate(1, 1));
		
		double[] tests = { -.7, -.00001, .000001, .5 };

		for (double testVal : tests) {
			double tanOfTestVal = Math.tan(testVal);
			assertEquals(new RGBColor(tanOfTestVal, tanOfTestVal, tanOfTestVal), myTree.evaluate(-1, testVal));
			assertEquals(new RGBColor(tanOfTestVal, tanOfTestVal, tanOfTestVal),
					myTree.evaluate(testVal, testVal));
		}
	}
	@Test
	public void testAtanEvaluation() {
		Atan myTree = new Atan(new Y());
		
		assertEquals(new RGBColor(-0.7853981633974483, -0.7853981633974483, -0.7853981633974483), myTree.evaluate(-1, -1));
		assertEquals(new RGBColor(0.7853981633974483, 0.7853981633974483, 0.7853981633974483), myTree.evaluate(-1, 1));
		assertEquals(new RGBColor(0, 0, 0), myTree.evaluate(0, 0));
		assertEquals(new RGBColor(-0.7853981633974483, -0.7853981633974483, -0.7853981633974483), myTree.evaluate(1, -1));
		assertEquals(new RGBColor(0.7853981633974483, 0.7853981633974483, 0.7853981633974483), myTree.evaluate(1, 1));
		
		double[] tests = { -.7, -.00001, .000001, .5 };

		for (double testVal : tests) {
			double atanOfTestVal = Math.atan(testVal);
			assertEquals(new RGBColor(atanOfTestVal, atanOfTestVal, atanOfTestVal), myTree.evaluate(-1, testVal));
			assertEquals(new RGBColor(atanOfTestVal, atanOfTestVal, atanOfTestVal),
					myTree.evaluate(testVal, testVal));
		}
	}
	
	@Test
	public void testExpEvaluation() {
		Exp myTree = new Exp(new Y());
		
		assertEquals(new RGBColor(0.36787944117144233, 0.36787944117144233, 0.36787944117144233), myTree.evaluate(-1, -1));
		assertEquals(new RGBColor(2.718281828459045, 2.718281828459045, 2.718281828459045), myTree.evaluate(-1, 1));
		assertEquals(new RGBColor(1, 1, 1), myTree.evaluate(0, 0));
		assertEquals(new RGBColor(0.36787944117144233, 0.36787944117144233, 0.36787944117144233), myTree.evaluate(1, -1));
		assertEquals(new RGBColor(2.718281828459045, 2.718281828459045, 2.718281828459045), myTree.evaluate(1, 1));
		
		double[] tests = { -.7, -.00001, .000001, .5 };

		for (double testVal : tests) {
			double expOfTestVal = Math.exp(testVal);
			assertEquals(new RGBColor(expOfTestVal, expOfTestVal, expOfTestVal), myTree.evaluate(-1, testVal));
			assertEquals(new RGBColor(expOfTestVal, expOfTestVal, expOfTestVal),
					myTree.evaluate(testVal, testVal));
		}
	}

	
	@Test
	public void testClampEvaluation() {
		Clamp myTree = new Clamp(new Plus(new X(), new Y()));
		
		assertEquals(new RGBColor(-1, -1, -1), myTree.evaluate(-1, -1));
		assertEquals(new RGBColor(0, 0, 0), myTree.evaluate(-1, 1));
		assertEquals(new RGBColor(0, 0, 0), myTree.evaluate(0, 0));
		assertEquals(new RGBColor(0, 0, 0), myTree.evaluate(1, -1));
		assertEquals(new RGBColor(1, 1, 1), myTree.evaluate(1, 1));
		
	}
	@Test
	public void testWrapEvaluation() {
		Wrap myTree = new Wrap(new Plus(new X(), new Y()));
		
		assertEquals(new RGBColor(0, 0, 0), myTree.evaluate(-1, -1));
		assertEquals(new RGBColor(0, 0, 0), myTree.evaluate(-1, 1));
		assertEquals(new RGBColor(0, 0, 0), myTree.evaluate(0, 0));
		assertEquals(new RGBColor(0, 0, 0), myTree.evaluate(1, -1));
		assertEquals(new RGBColor(0, 0, 0), myTree.evaluate(1, 1));
		
	}
	
	@Test
	public void testRgbToYCrCbEvaluation() {
		RgbToYCrCb myTree = new RgbToYCrCb(new X());
		
		double red = -1 * 0.2989 + -1 * 0.5866 + -1 * 0.1145;
		double green = -1 * -0.1687 + -1 * -0.3312 + -1 * 0.5;
		double blue = -1 * 0.5000 + -1 * -0.4183 + -1 * -0.0816;
		assertEquals(new RGBColor(red, green, blue), myTree.evaluate(-1, -1));
		
		
		red = -1 * 0.2989 + -1 * 0.5866 + -1 * 0.1145;
		green = -1 * -0.1687 + -1 * -0.3312 + -1 * 0.5;
		blue = -1 * 0.5000 + -1 * -0.4183 + -1 * -0.0816;
		assertEquals(new RGBColor(red, green, blue), myTree.evaluate(-1, 1));
		
		assertEquals(new RGBColor(0, 0, 0), myTree.evaluate(0, 0));
		
		red = 1 * 0.2989 + 1 * 0.5866 + 1 * 0.1145;
		green = 1 * -0.1687 + 1 * -0.3312 + 1 * 0.5;
		blue = 1 * 0.5000 + 1 * -0.4183 + 1 * -0.0816;
		assertEquals(new RGBColor(red, green, blue), myTree.evaluate(1, -1));
		
		red = 1 * 0.2989 + 1 * 0.5866 + 1 * 0.1145;
		green = 1 * -0.1687 + 1 * -0.3312 + 1 * 0.5;
		blue = 1 * 0.5000 + 1 * -0.4183 + 1 * -0.0816;
		assertEquals(new RGBColor(red, green, blue), myTree.evaluate(1, 1));
		
	}
	
	@Test
	public void testYCrCbToRGBEvaluation() {
		YCrCbToRGB myTree = new YCrCbToRGB(new X());
		
		double red = -1 + -1 * 1.4022;
		double green = -1 + -1 * -0.3456 + -1 * -0.7145;
		double blue = -1 + -1 * 1.7710;
		assertEquals(new RGBColor(red, green, blue), myTree.evaluate(-1, -1));
		
		red = -1 + -1 * 1.4022;
		green = -1 + -1 * -0.3456 + -1 * -0.7145;
		blue = -1 + -1 * 1.7710;
		assertEquals(new RGBColor(red, green, blue), myTree.evaluate(-1, 1));
		
		assertEquals(new RGBColor(0, 0, 0), myTree.evaluate(0, 0));
		
		red = 1 + 1 * 1.4022;
		green = 1 + 1 * -0.3456 + 1 * -0.7145;
		blue = 1 + 1 * 1.7710;
		assertEquals(new RGBColor(red, green, blue), myTree.evaluate(1, -1));
		
		red = 1 + 1 * 1.4022;
		green = 1 + 1 * -0.3456 + 1 * -0.7145;
		blue = 1 + 1 * 1.7710;
		assertEquals(new RGBColor(red, green, blue), myTree.evaluate(1, 1));
		
	}
	@Test
	public void testArithmeticExpressionsEvaluation() {
		Plus myTree1 = new Plus(new X(), new Y());
		//first and last 2 assertions need work; colors need to be clamped between -1 and 1
		assertEquals(new RGBColor(-2, -2, -2), myTree1.evaluate(-1, -1));
		assertEquals(new RGBColor(0, 0, 0), myTree1.evaluate(1, -1));
		assertEquals(new RGBColor(0, 0, 0), myTree1.evaluate(0, 0));
		assertEquals(new RGBColor(0, 0, 0), myTree1.evaluate(-1, 1));
		assertEquals(new RGBColor(2, 2, 2), myTree1.evaluate(1, 1));
		
		Multiply myTree2 = new Multiply(new X(), new Y());
		
		assertEquals(new RGBColor(1, 1, 1), myTree2.evaluate(-1, -1));
		assertEquals(new RGBColor(-1, -1, -1), myTree2.evaluate(1, -1));
		assertEquals(new RGBColor(0, 0, 0), myTree2.evaluate(0, 0));
		assertEquals(new RGBColor(-1, -1, -1), myTree2.evaluate(-1, 1));
		assertEquals(new RGBColor(1, 1, 1), myTree2.evaluate(1, 1));
		
		Divide myTree3 = new Divide(new X(), new Y());
		
		assertEquals(new RGBColor(1, 1, 1), myTree3.evaluate(-1, -1));
		assertEquals(new RGBColor(-1, -1, -1), myTree3.evaluate(1, -1));
		assertEquals(new RGBColor(-1, -1, -1), myTree3.evaluate(-1, 1));
		assertEquals(new RGBColor(1, 1, 1), myTree3.evaluate(1, 1));
		
		Minus myTree4 = new Minus(new X(), new Y());
		//Some assertions need work; colors need to be clamped between -1 and 1
		assertEquals(new RGBColor(0, 0, 0), myTree4.evaluate(-1, -1));
		assertEquals(new RGBColor(2, 2, 2), myTree4.evaluate(1, -1));
		assertEquals(new RGBColor(0, 0, 0), myTree4.evaluate(0, 0));
		assertEquals(new RGBColor(-2, -2, -2), myTree4.evaluate(-1, 1));
		assertEquals(new RGBColor(0, 0, 0), myTree4.evaluate(1, 1));
		
		Exponentiate myTree5 = new Exponentiate(new X(), new Y());
		
		assertEquals(new RGBColor(-1, -1, -1), myTree5.evaluate(-1, -1));
		assertEquals(new RGBColor(1, 1, 1), myTree5.evaluate(1, -1));
		assertEquals(new RGBColor(1, 1, 1), myTree5.evaluate(0, 0));
		assertEquals(new RGBColor(-1, -1, -1), myTree5.evaluate(-1, 1));
		assertEquals(new RGBColor(1, 1, 1), myTree5.evaluate(1, 1));
		
		Mod myTree7 = new Mod(new X(), new Y());
		assertEquals(new RGBColor(0, 0, 0), myTree7.evaluate(-1, -1));
		assertEquals(new RGBColor(0, 0, 0), myTree7.evaluate(1, -1));
		//Mod 0 is undefined
		//assertEquals(new RGBColor(0, 0, 0), myTree7.evaluate(0, 0));
		assertEquals(new RGBColor(0, 0, 0), myTree7.evaluate(-1, 1));
		assertEquals(new RGBColor(0, 0, 0), myTree7.evaluate(1, 1));

		
		double[] testModX = { -.7, -.00001, .000001, .5 };
		double[] testModY = {1, 0.5, -1, 0.1 };

		for (double testXVal : testModX) {
			for (double testYVal : testModY) {
				double mod = testXVal % testYVal;
				assertEquals(new RGBColor(mod, mod, mod),myTree7.evaluate(testXVal, testYVal));
			}
		}
		
		Neg myTree8 = new Neg(new X());
		assertEquals(new RGBColor(1, 1, 1), myTree8.evaluate(-1, -1));
		assertEquals(new RGBColor(-1, -1, -1), myTree8.evaluate(1, -1));
		assertEquals(new RGBColor(0, 0, 0), myTree8.evaluate(0, 0));
		assertEquals(new RGBColor(1, 1, 1), myTree8.evaluate(-1, 1));
		assertEquals(new RGBColor(-1, -1, -1), myTree8.evaluate(1, 1));
		
		myTree8 = new Neg(new Y());
		assertEquals(new RGBColor(1, 1, 1), myTree8.evaluate(-1, -1));
		assertEquals(new RGBColor(1, 1, 1), myTree8.evaluate(1, -1));
		assertEquals(new RGBColor(0, 0, 0), myTree8.evaluate(0, 0));
		assertEquals(new RGBColor(-1, -1, -1), myTree8.evaluate(-1, 1));
		assertEquals(new RGBColor(-1, -1, -1), myTree8.evaluate(1, 1));
			
	}
	@Test
	public void testAssignmentEvaluation() {
		Assignment myTree1 = new Assignment(new Variable("a"), new Plus(new X(), new Y()));
		//check that Plus is evaluated when the assignment is created
		assertEquals(new RGBColor(-2, -2, -2), myTree1.evaluate(-1, -1));
		assertEquals(new RGBColor(0, 0, 0), myTree1.evaluate(1, -1));
		assertEquals(new RGBColor(0, 0, 0), myTree1.evaluate(0, 0));
		assertEquals(new RGBColor(0, 0, 0), myTree1.evaluate(-1, 1));
		assertEquals(new RGBColor(2, 2, 2), myTree1.evaluate(1, 1));
		
	}
	
	@Test
	public void testRandomEvaluation() {
	    Random myTree = new Random();

	    // Perform multiple evaluations to ensure consistency in the range of generated colors
	    for (int i = 0; i < 10; i++) {
	        RGBColor result = myTree.evaluate(Math.random(), Math.random()); // x and y values are irrelevant for Random.
	        assertNotNull(result); // Check that result is not null.
	        // Check if color components are within the expected range [-1, 1].
	        assertTrue(result.getRed() >= -1 && result.getRed() <= 1);
	        assertTrue(result.getGreen() >= -1 && result.getGreen() <= 1);
	        assertTrue(result.getBlue() >= -1 && result.getBlue() <= 1);
	    }
	}

	@Test
	public void testPerlinBWEvaluation() {
	    ExpressionTreeNode leftExp = new X(); 
	    ExpressionTreeNode rightExp = new Y(); 
	    PerlinBW myTree = new PerlinBW(leftExp, rightExp);

	    // Testing with various coordinates
	    testPerlinBWAtCoordinate(myTree, -1, -1);
	    testPerlinBWAtCoordinate(myTree, -1, 1);
	    testPerlinBWAtCoordinate(myTree, 0, 0);
	    testPerlinBWAtCoordinate(myTree, 1, -1);
	    testPerlinBWAtCoordinate(myTree, 1, 1);
	}
	@Test
	private void testPerlinBWAtCoordinate(PerlinBW perlinBW, double x, double y) {
	    RGBColor result = perlinBW.evaluate(x, y);
	    assertNotNull(result); // Check that result is not null
	    // Check if the color is a valid grayscale (R=G=B) and within range [-1, 1]
	    double grey = result.getRed();
	    assertTrue(grey >= -1 && grey <= 1);
	    assertEquals(grey, result.getGreen());
	    assertEquals(grey, result.getBlue());
	}

	@Test
	public void testImageWrapEvaluation() {
	    ImageWrap myWrap = new ImageWrap(new Image(new Quote("vortex.jpg")), new X(), new Y());

	    RGBColor color = myWrap.evaluate(0.5, 0.5);
	    assertNotNull(color);
	    assertColorRange(color);
	}

	@Test
	public void testImageClipEvaluation() {
	    ImageClip myClip = new ImageClip(new Image(new Quote("vortex.jpg")), new X(), new Y());

	    RGBColor color = myClip.evaluate(0.5, 0.5);
	    assertNotNull(color);
	    assertColorRange(color);
	}
	@Test
	private void assertColorRange(RGBColor color) {
	    assertTrue(color.getRed() >= -1 && color.getRed() <= 1);
	    assertTrue(color.getGreen() >= -1 && color.getGreen() <= 1);
	    assertTrue(color.getBlue() >= -1 && color.getBlue() <= 1);
	}

}
