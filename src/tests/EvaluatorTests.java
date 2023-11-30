/**
 * 
 */
package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import picasso.parser.language.ExpressionTreeNode;
import picasso.parser.language.expressions.*;

/**
 * Tests of the evaluation of expression trees
 * 
 * @author Sara Sprenkle
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
	public void testCosineEvaluation() {
		Cosine myTree = new Cosine(new X());
		
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
	public void testAdditionEvaluation() {
		Addition myTree = new Addition(new X(), new Y());
		//first and last 2 assertions need work; colors need to be clamped between -1 and 1
		assertEquals(new RGBColor(-2, -2, -2), myTree.evaluate(-1, -1));
		assertEquals(new RGBColor(0, 0, 0), myTree.evaluate(1, -1));
		assertEquals(new RGBColor(0, 0, 0), myTree.evaluate(0, 0));
		assertEquals(new RGBColor(0, 0, 0), myTree.evaluate(-1, 1));
		assertEquals(new RGBColor(2, 2, 2), myTree.evaluate(1, 1));
	}
	// TODO: More tests of evaluation

}
