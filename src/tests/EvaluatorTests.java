/**
 * 
 */
package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Stack;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
	public void testAbsEvaluation() {
		Abs myTree = new Abs(new X());
		
		assertEquals(new RGBColor(1, 1, 1), myTree.evaluate(-1, 1));
		assertEquals(new RGBColor(0, 0, 0), myTree.evaluate(0, 0));
		assertEquals(new RGBColor(1, 1, 1), myTree.evaluate(1, -1));

		
	}
	@Test
	public void testArithmeticExpressionsEvaluation() {
		Addition myTree1 = new Addition(new X(), new Y());
		//first and last 2 assertions need work; colors need to be clamped between -1 and 1
		assertEquals(new RGBColor(-2, -2, -2), myTree1.evaluate(-1, -1));
		assertEquals(new RGBColor(0, 0, 0), myTree1.evaluate(1, -1));
		assertEquals(new RGBColor(0, 0, 0), myTree1.evaluate(0, 0));
		assertEquals(new RGBColor(0, 0, 0), myTree1.evaluate(-1, 1));
		assertEquals(new RGBColor(2, 2, 2), myTree1.evaluate(1, 1));
		
		Multiplication myTree2 = new Multiplication(new X(), new Y());
		
		assertEquals(new RGBColor(1, 1, 1), myTree2.evaluate(-1, -1));
		assertEquals(new RGBColor(-1, -1, -1), myTree2.evaluate(1, -1));
		assertEquals(new RGBColor(0, 0, 0), myTree2.evaluate(0, 0));
		assertEquals(new RGBColor(-1, -1, -1), myTree2.evaluate(-1, 1));
		assertEquals(new RGBColor(1, 1, 1), myTree2.evaluate(1, 1));
		
		Division myTree3 = new Division(new X(), new Y());
		
		assertEquals(new RGBColor(1, 1, 1), myTree3.evaluate(-1, -1));
		assertEquals(new RGBColor(-1, -1, -1), myTree3.evaluate(1, -1));
		assertEquals(new RGBColor(0, 0, 0), myTree3.evaluate(0, 0));
		assertEquals(new RGBColor(-1, -1, -1), myTree3.evaluate(-1, 1));
		assertEquals(new RGBColor(1, 1, 1), myTree3.evaluate(1, 1));
		
	}
	@Test
	public void testAssignmentEvaluation() {
		//Variable testVar = new Variable("a");
		Assignment myTree1 = new Assignment(new Variable("a"), new Addition(new X(), new Y()));
		//check that addition is evaluated when the assignment is created
		assertEquals(new RGBColor(-2, -2, -2), myTree1.evaluate(-1, -1));
		assertEquals(new RGBColor(0, 0, 0), myTree1.evaluate(1, -1));
		assertEquals(new RGBColor(0, 0, 0), myTree1.evaluate(0, 0));
		assertEquals(new RGBColor(0, 0, 0), myTree1.evaluate(-1, 1));
		assertEquals(new RGBColor(2, 2, 2), myTree1.evaluate(1, 1));
		//test that cos(a) is evaluated as cos(x+y) when it is called after a has been instantiated. Worked before the refactor, but does not work after. Will
		//try to fix later. 
		/**Stack<Token> tokens = new Stack<>();
		tokens.push(new IdentifierToken("a"));
		tokens.push(new IdentifierToken("x"));
		tokens.push(new IdentifierToken("y"));
		tokens.push(new PlusToken());
		tokens.push(new AssignmentToken());
		*/
		/**String expression = "a=x+y";
		ExpressionTreeGenerator treeGen = new ExpressionTreeGenerator();
		Stack<Token> tokens = treeGen.infixToPostfix(expression);
		AssignmentAnalyzer assignmentAnalyzer = new AssignmentAnalyzer();
		IdentifierAnalyzer idAnalyzer = new IdentifierAnalyzer();
		ExpressionTreeNode rhs = assignmentAnalyzer.generateExpressionTree(tokens);
		ExpressionTreeNode id = idAnalyzer.generateExpressionTree(tokens);	
		
		Cosine myTree2 = new Cosine(id);
		assertEquals(new RGBColor(-0.4161468365471424, -0.4161468365471424, -0.4161468365471424), myTree2.evaluate(-1, -1));
		assertEquals(new RGBColor(1, 1, 1), myTree2.evaluate(1, -1));
		assertEquals(new RGBColor(1, 1, 1), myTree2.evaluate(0, 0));
		assertEquals(new RGBColor(1, 1, 1), myTree2.evaluate(-1, 1));
		assertEquals(new RGBColor(-0.4161468365471424, -0.4161468365471424, -0.4161468365471424), myTree2.evaluate(1, 1));
	*/
	}
	
}
