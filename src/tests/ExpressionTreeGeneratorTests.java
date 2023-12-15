package tests;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Stack;

import org.junit.jupiter.api.BeforeEach;

import picasso.parser.ExpressionTreeGenerator;
import picasso.parser.language.ExpressionTreeNode;
import picasso.parser.language.expressions.*;
import picasso.parser.tokens.IdentifierToken;
import picasso.parser.tokens.QuoteToken;
import picasso.parser.tokens.Token;
import picasso.parser.tokens.operations.AssignmentToken;
import picasso.parser.tokens.operations.MultiplyToken;
import picasso.parser.tokens.operations.PlusToken;
import picasso.parser.tokens.IdentifierToken;

/**
 * Tests of creating an expression tree from a string expression. Will have
 * compiler errors until some code is created.
 * 
 * @author Sara Sprenkle
 * 
 */
public class ExpressionTreeGeneratorTests {

	private ExpressionTreeGenerator parser;

	@BeforeEach
	public void setUp() throws Exception {
		parser = new ExpressionTreeGenerator();
	}

	@Test
	public void constantExpressionTests() {
		ExpressionTreeNode e = parser.makeExpression("[1,-1, 1]");
		assertEquals(new RGBColor(1, -1, 1), e);
	}

	@Test
	public void variableExpressionTests() {
		ExpressionTreeNode e = parser.makeExpression("x");
		assertEquals(new X(), e);
	}

	@Test
	public void PlusExpressionTests() {
		ExpressionTreeNode e = parser.makeExpression("x + y");
		assertEquals(new Plus(new X(), new Y()), e);

		// no spaces!
		e = parser.makeExpression("x+y");
		assertEquals(new Plus(new X(), new Y()), e);

		e = parser.makeExpression("[1,.3,-1] + y");
		assertEquals(new Plus(new RGBColor(1, .3, -1), new Y()), e);

		e = parser.makeExpression("x + y + [ -.51, 0, 1]");
		assertEquals(new Plus(new Plus(new X(), new Y()), new RGBColor(-.51, 0, 1)), e);
	}

	@Test
	public void MinusExpressionTests() {
		ExpressionTreeNode e = parser.makeExpression("x - y");
		assertEquals(new Minus(new X(), new Y()), e);

		// no spaces!
		e = parser.makeExpression("x-y");
		assertEquals(new Minus(new X(), new Y()), e);

		e = parser.makeExpression("[1,.3,-1] - y");
		assertEquals(new Minus(new RGBColor(1, .3, -1), new Y()), e);

		e = parser.makeExpression("x - y - [ -.51, 0, 1]");
		assertEquals(new Minus(new Minus(new X(), new Y()), new RGBColor(-.51, 0, 1)), e);
	}

	@Test
	public void MultiplyExpressionTests() {
		ExpressionTreeNode e = parser.makeExpression("x * y");
		assertEquals(new Multiply(new X(), new Y()), e);

		// no spaces!
		e = parser.makeExpression("x*y");
		assertEquals(new Multiply(new X(), new Y()), e);

		e = parser.makeExpression("[1,.3,-1] * y");
		assertEquals(new Multiply(new RGBColor(1, .3, -1), new Y()), e);

		e = parser.makeExpression("x * y * [ -.51, 0, 1]");
		assertEquals(new Multiply(new Multiply(new X(), new Y()), new RGBColor(-.51, 0, 1)), e);
	}

	@Test
	public void DivideExpressionTests() {
		ExpressionTreeNode e = parser.makeExpression("x / y");
		assertEquals(new Divide(new X(), new Y()), e);

		// no spaces!
		e = parser.makeExpression("x/y");
		assertEquals(new Divide(new X(), new Y()), e);

		e = parser.makeExpression("[1,.3,-1] / y");
		assertEquals(new Divide(new RGBColor(1, .3, -1), new Y()), e);

		e = parser.makeExpression("x / y / [ -.51, 0, 1]");
		assertEquals(new Divide(new Divide(new X(), new Y()), new RGBColor(-.51, 0, 1)), e);
	}

	@Test
	public void moduloExpressionTests() {
		ExpressionTreeNode e = parser.makeExpression("x % y");
		assertEquals(new Mod(new X(), new Y()), e);

		// no spaces!
		e = parser.makeExpression("x%y");
		assertEquals(new Mod(new X(), new Y()), e);

		e = parser.makeExpression("[1,.3,-1] % y");
		assertEquals(new Mod(new RGBColor(1, .3, -1), new Y()), e);

		e = parser.makeExpression("x % y % [ -.51, 0, 1]");
		assertEquals(new Mod(new Mod(new X(), new Y()), new RGBColor(-.51, 0, 1)), e);
	}

	@Test
	public void NegExpressionTests() {
		ExpressionTreeNode e = parser.makeExpression("! x");
		assertEquals(new Neg(new X()), e);

		// no spaces!
		e = parser.makeExpression("!x");
		assertEquals(new Neg(new X()), e);

		// with parens
		e = parser.makeExpression("!(x)");
		assertEquals(new Neg(new X()), e);
	}

	public void exponentiateExpressionTests() {
		ExpressionTreeNode e = parser.makeExpression("x ^ y");
		assertEquals(new Exponentiate(new X(), new Y()), e);

		// no spaces!
		e = parser.makeExpression("x^y");
		assertEquals(new Exponentiate(new X(), new Y()), e);

		e = parser.makeExpression("[1,.3,-1] ^ y");
		assertEquals(new Exponentiate(new RGBColor(1, .3, -1), new Y()), e);

		e = parser.makeExpression("x ^ y ^ [ -.51, 0, 1]");
		assertEquals(new Exponentiate(new Exponentiate(new X(), new Y()), new RGBColor(-.51, 0, 1)), e);
	}

	@Test
	public void parenthesesExpressionTests() {
		ExpressionTreeNode e = parser.makeExpression("( x + y )");
		assertEquals(new Plus(new X(), new Y()), e);

		e = parser.makeExpression("( x + (y + [ 1, 1, 1] ) )");
		assertEquals(new Plus(new X(), new Plus(new Y(), new RGBColor(1, 1, 1))), e);
	}

	@Test
	public void arithmeticAndAssignmentStackTests() {
		Stack<Token> stack = parser.infixToPostfix("a = x * y");
		Stack<Token> expected = new Stack<>();

		expected.push(new IdentifierToken("a"));
		expected.push(new IdentifierToken("x"));
		expected.push(new IdentifierToken("y"));
		expected.push(new MultiplyToken());
		expected.push(new AssignmentToken());

		assertEquals(expected, stack);
	}

	@Test
	public void arithmeticStackTests() {
		Stack<Token> stack = parser.infixToPostfix("x + y * x");

		Stack<Token> expected = new Stack<>();
		expected.push(new IdentifierToken("x"));
		expected.push(new IdentifierToken("y"));
		expected.push(new IdentifierToken("x"));
		expected.push(new MultiplyToken());
		expected.push(new PlusToken());

		assertEquals(expected, stack);
	}

	@Test
	public void floorFunctionTests() {
		ExpressionTreeNode e = parser.makeExpression("floor( x )");
		assertEquals(new Floor(new X()), e);

		e = parser.makeExpression("floor( x + y )");
		assertEquals(new Floor(new Plus(new X(), new Y())), e);
	}

	@Test
	public void coSinFunctionTests() {
		ExpressionTreeNode e = parser.makeExpression("cos( x )");
		assertEquals(new Cos(new X()), e);

		e = parser.makeExpression("cos( x + y )");
		assertEquals(new Cos(new Plus(new X(), new Y())), e);

	}

	@Test
	public void ceilFunctionTests() {
		ExpressionTreeNode e = parser.makeExpression("ceil( x )");
		assertEquals(new Ceil(new X()), e);

		e = parser.makeExpression("ceil( x + y )");
		assertEquals(new Ceil(new Plus(new X(), new Y())), e);
	}

	@Test
	public void logFunctionTests() {
		ExpressionTreeNode e = parser.makeExpression("log( x )");
		assertEquals(new Log(new X()), e);

		e = parser.makeExpression("log( x + y )");
		assertEquals(new Log(new Plus(new X(), new Y())), e);
	}

	@Test
	public void sinFunctionTests() {
		ExpressionTreeNode e = parser.makeExpression("sin( x )");
		assertEquals(new Sin(new X()), e);

		e = parser.makeExpression("sin( x + y )");
		assertEquals(new Sin(new Plus(new X(), new Y())), e);
	}

	@Test
	public void tanFunctionTests() {
		ExpressionTreeNode e = parser.makeExpression("tan( x )");
		assertEquals(new Tan(new X()), e);

		e = parser.makeExpression("tan( x + y )");
		assertEquals(new Tan(new Plus(new X(), new Y())), e);
	}

	@Test
	public void atanFunctionTests() {
		ExpressionTreeNode e = parser.makeExpression("atan( x )");
		assertEquals(new Atan(new X()), e);

		e = parser.makeExpression("atan( x + y )");
		assertEquals(new Atan(new Plus(new X(), new Y())), e);
	}

	@Test
	public void expFunctionTests() {
		ExpressionTreeNode e = parser.makeExpression("exp( x )");
		assertEquals(new Exp(new X()), e);

		e = parser.makeExpression("exp( x + y )");
		assertEquals(new Exp(new Plus(new X(), new Y())), e);
	}

	@Test
	public void clampFunctionTests() {
		ExpressionTreeNode e = parser.makeExpression("clamp( x )");
		assertEquals(new Clamp(new X()), e);

		e = parser.makeExpression("clamp( x + y )");
		assertEquals(new Clamp(new Plus(new X(), new Y())), e);
	}

	@Test
	public void wrapFunctionTests() {
		ExpressionTreeNode e = parser.makeExpression("wrap( x )");
		assertEquals(new Wrap(new X()), e);

		e = parser.makeExpression("wrap( x + y )");
		assertEquals(new Wrap(new Plus(new X(), new Y())), e);
	}
	
	@Test
	public void rgbToYCrCbFunctionTests() {
		ExpressionTreeNode e = parser.makeExpression("rgbToYCrCb( x )");
		assertEquals(new RgbToYCrCb(new X()), e);

		e = parser.makeExpression("rgbToYCrCb( x + y )");
		assertEquals(new RgbToYCrCb(new Plus(new X(), new Y())), e);
	}

	@Test
	public void YCrCbToRGBFunctionTests() {
		ExpressionTreeNode e = parser.makeExpression("yCrCbToRGB( x )");
		assertEquals(new YCrCbToRGB(new X()), e);

		e = parser.makeExpression("yCrCbToRGB( x + y )");
		assertEquals(new YCrCbToRGB(new Plus(new X(), new Y())), e);
	}

	@Test
	public void perlinBWFunctionTests() {
		ExpressionTreeNode e = parser.makeExpression("perlinBW( x, y )");
		assertEquals(new PerlinBW(new X(), new Y()), e);

		e = parser.makeExpression("perlinBW( x + x, y + y )");
		assertEquals(new PerlinBW(new Plus(new X(), new X()), new Plus(new Y(), new Y())), e);
	}
	
	@Test
	public void perlinColorFunctionTests() {
	    // Test for PerlinColor with two single variables (x and y)
	    ExpressionTreeNode e = parser.makeExpression("perlinColor( x, y )");
	    assertEquals(new PerlinColor(new X(), new Y()), e);

	    // Test for PerlinColor with compound expressions (x + y and x - y)
	    e = parser.makeExpression("perlinColor( x + y, x - y )");
	    assertEquals(new PerlinColor(new Plus(new X(), new Y()), new Minus(new X(), new Y())), e);
	}

	@Test
	public void imageClipFunctionTests() {
		ExpressionTreeNode e = parser.makeExpression("imageClip(\"vortex.jpg\", x, y)");
		assertEquals(new ImageClip(new Image(new Quote("vortex.jpg")), new X(), new Y()), e);

		e = parser.makeExpression("imageClip(\"vortex.jpg\", x + y, x - y)");
		assertEquals(new ImageClip(new Image(new Quote("vortex.jpg")), new Plus(new X(), new Y()), new Minus(new X(), new Y())), e);
	}
	
	@Test
	public void imageWrapFunctionTests() {
	    ExpressionTreeNode e = parser.makeExpression("imageWrap(\"vortex.jpg\", x, y)");
	    assertEquals(new ImageWrap(new Image(new Quote("vortex.jpg")), new X(), new Y()), e);

	    e = parser.makeExpression("imageWrap(\"vortex.jpg\", x + y, x - y)");
	    assertEquals(new ImageWrap(new Image(new Quote("vortex.jpg")), new Plus(new X(), new Y()), new Minus(new X(), new Y())), e);
	}
	
	@Test
	public void absFunctionTests() {
	    // Test for Abs with a single variable (x)
	    ExpressionTreeNode e = parser.makeExpression("abs( x )");
	    assertEquals(new Abs(new X()), e);

	    // Test for Abs with a compound expression (x + y)
	    e = parser.makeExpression("abs( x + y )");
	    assertEquals(new Abs(new Plus(new X(), new Y())), e);
	}
	
	@Test
	public void randomFunctionTests() {
	    ExpressionTreeNode e = parser.makeExpression("random()");
	    assertTrue(e instanceof Random); // Check if the parsed expression is an instance of Random class.
	}
	@Test
	public void assignmentWithSingleVariableTest() {
	    // Test for assignment with a single variable (x) to another variable
	    ExpressionTreeNode e = parser.makeExpression("b = x");
	    assertEquals(new Assignment(new Variable("b"), new X()), e);
	}

	@Test
	public void assignmentWithCompoundExpressionTest() {
	    // Test for assignment with a compound expression (x + y) to a variable
	    ExpressionTreeNode e = parser.makeExpression("c = x + y");
	    assertEquals(new Assignment(new Variable("c"), new Plus(new X(), new Y())), e);
	}

}
