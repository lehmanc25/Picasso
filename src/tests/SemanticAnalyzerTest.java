/**
 * 
 */
package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Stack;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import picasso.parser.SemanticAnalyzer;
import picasso.parser.language.ExpressionTreeNode;
import picasso.parser.language.expressions.*;
import picasso.parser.tokens.*;
import picasso.parser.tokens.operations.AssignmentToken;
import picasso.parser.tokens.functions.CosToken;
import picasso.parser.tokens.functions.ExpToken;
import picasso.parser.tokens.functions.FloorToken;
import picasso.parser.tokens.functions.LogToken;
import picasso.parser.tokens.functions.PerlinBWToken;
import picasso.parser.tokens.functions.PerlinColorToken;
import picasso.parser.tokens.functions.RandomToken;
import picasso.parser.tokens.functions.RgbToYCrCbToken;
import picasso.parser.tokens.functions.SinToken;
import picasso.parser.tokens.functions.TanToken;
import picasso.parser.tokens.functions.WrapToken;
import picasso.parser.tokens.functions.YCrCbToRGBToken;
import picasso.parser.tokens.functions.AtanToken;
import picasso.parser.tokens.functions.CeilToken;
import picasso.parser.tokens.functions.ClampToken;
import picasso.parser.tokens.functions.ImageClipToken;
import picasso.parser.tokens.functions.ImageWrapToken;
import picasso.parser.tokens.functions.AbsToken;
import picasso.parser.tokens.operations.*;

/**
 * Test the parsing from the Stack (not as easy as using a String as input, but
 * helps to isolate where the problem is)
 * 
 * @author Sara Sprenkle
 *
 */
class SemanticAnalyzerTest {

	private SemanticAnalyzer semAnalyzer;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		semAnalyzer = SemanticAnalyzer.getInstance();
	}

	@Test
	void testParsePlus() {

		Stack<Token> tokens = new Stack<>();
		tokens.push(new IdentifierToken("x"));
		tokens.push(new IdentifierToken("y"));
		tokens.push(new PlusToken());

		ExpressionTreeNode actual = semAnalyzer.generateExpressionTree(tokens);

		assertEquals(new Plus(new X(), new Y()), actual);
	}
	@Test
	void testParseMinus() {

		Stack<Token> tokens = new Stack<>();
		tokens.push(new IdentifierToken("x"));
		tokens.push(new IdentifierToken("y"));
		tokens.push(new MinusToken());

		ExpressionTreeNode actual = semAnalyzer.generateExpressionTree(tokens);

		assertEquals(new Minus(new X(), new Y()), actual);
	}
	@Test
	void testParseMultiply() {

		Stack<Token> tokens = new Stack<>();
		tokens.push(new IdentifierToken("x"));
		tokens.push(new IdentifierToken("y"));
		tokens.push(new MultiplyToken());

		ExpressionTreeNode actual = semAnalyzer.generateExpressionTree(tokens);

		assertEquals(new Multiply(new X(), new Y()), actual);
	}
	
	@Test
	void testParseDivision() {

		Stack<Token> tokens = new Stack<>();
		tokens.push(new IdentifierToken("x"));
		tokens.push(new IdentifierToken("y"));
		tokens.push(new DivideToken());

		ExpressionTreeNode actual = semAnalyzer.generateExpressionTree(tokens);

		assertEquals(new Divide(new X(), new Y()), actual);
	}
	
	@Test
	void testParseModulo() {

		Stack<Token> tokens = new Stack<>();
		tokens.push(new IdentifierToken("x"));
		tokens.push(new IdentifierToken("y"));
		tokens.push(new ModToken());

		ExpressionTreeNode actual = semAnalyzer.generateExpressionTree(tokens);

		assertEquals(new Mod(new X(), new Y()), actual);
	}
	@Test
	void testParseNegate() {

		Stack<Token> tokens = new Stack<>();
		tokens.push(new IdentifierToken("x"));
		tokens.push(new NegToken());

		ExpressionTreeNode actual = semAnalyzer.generateExpressionTree(tokens);

		assertEquals(new Neg(new X()), actual);
	}
	@Test
	void testParseExponentiate() {
		
		Stack<Token> tokens = new Stack<>();
		tokens.push(new IdentifierToken("x"));
		tokens.push(new IdentifierToken("y"));
		tokens.push(new ExponentiateToken());

		ExpressionTreeNode actual = semAnalyzer.generateExpressionTree(tokens);

		assertEquals(new Exponentiate(new X(), new Y()), actual);
	}

	@Test
	void testParseAssignment() {
		
		Stack<Token> tokens = new Stack<>();
		tokens.push(new IdentifierToken("a"));
		tokens.push(new IdentifierToken("x"));
		tokens.push(new IdentifierToken("y"));
		tokens.push(new PlusToken());
		tokens.push(new AssignmentToken());
		
		ExpressionTreeNode actual = semAnalyzer.generateExpressionTree(tokens);
		
		assertEquals(new Assignment(new Variable("a"), new Plus(new X(), new Y())), actual);
	}
	
	@Test
	void testParseCosine() {
		
		Stack<Token> tokens = new Stack<>();
		tokens.push(new IdentifierToken("x"));
		tokens.push(new CosToken());
		
		ExpressionTreeNode actual = semAnalyzer.generateExpressionTree(tokens);
		
		assertEquals(new Cos(new X()), actual);
		
	}
	
	@Test
	void testParseFloor() {
		
		Stack<Token> tokens = new Stack<>();
		tokens.push(new IdentifierToken("x"));
		tokens.push(new FloorToken());
		
		ExpressionTreeNode actual = semAnalyzer.generateExpressionTree(tokens);
		
		assertEquals(new Floor(new X()), actual);
		
	}
	@Test
	void testParseLog() {
		
		Stack<Token> tokens = new Stack<>();
		tokens.push(new IdentifierToken("x"));
		tokens.push(new LogToken());
		
		ExpressionTreeNode actual = semAnalyzer.generateExpressionTree(tokens);
		
		assertEquals(new Log(new X()), actual);
		
	}

	@Test
	void testParseCeil() {
		
		Stack<Token> tokens = new Stack<>();
		tokens.push(new IdentifierToken("x"));
		tokens.push(new CeilToken());
		
		ExpressionTreeNode actual = semAnalyzer.generateExpressionTree(tokens);
		
		assertEquals(new Ceil(new X()), actual);
		
	}
	
	@Test
	void testParseSine() {
		
		Stack<Token> tokens = new Stack<>();
		tokens.push(new IdentifierToken("x"));
		tokens.push(new SinToken());
		
		ExpressionTreeNode actual = semAnalyzer.generateExpressionTree(tokens);
		
		assertEquals(new Sin(new X()), actual);
		
	}
	
	@Test
	void testParseTan() {
		
		Stack<Token> tokens = new Stack<>();
		tokens.push(new IdentifierToken("x"));
		tokens.push(new TanToken());
		
		ExpressionTreeNode actual = semAnalyzer.generateExpressionTree(tokens);
		
		assertEquals(new Tan(new X()), actual);
	}
	
	@Test
	void testParseExp() {
		
		Stack<Token> tokens = new Stack<>();
		tokens.push(new IdentifierToken("x"));
		tokens.push(new ExpToken());
		
		ExpressionTreeNode actual = semAnalyzer.generateExpressionTree(tokens);
		
		assertEquals(new Exp(new X()), actual);
		
	}
	
	@Test
	void testParseAtan() {
		
		Stack<Token> tokens = new Stack<>();
		tokens.push(new IdentifierToken("x"));
		tokens.push(new AtanToken());
		
		ExpressionTreeNode actual = semAnalyzer.generateExpressionTree(tokens);
		
		assertEquals(new Atan(new X()), actual);
		
	}
	@Test
	void testParseClamp() {
		
		Stack<Token> tokens = new Stack<>();
		tokens.push(new IdentifierToken("x"));
		tokens.push(new ClampToken());
		
		ExpressionTreeNode actual = semAnalyzer.generateExpressionTree(tokens);
		
		assertEquals(new Clamp(new X()), actual);
		
	}
	
	@Test
	void testParseWrap() {
		
		Stack<Token> tokens = new Stack<>();
		tokens.push(new IdentifierToken("x"));
		tokens.push(new WrapToken());
		
		ExpressionTreeNode actual = semAnalyzer.generateExpressionTree(tokens);
		
		assertEquals(new Wrap(new X()), actual);
		
	}
	@Test
	void testParsePerlinBW() {
		Stack<Token> tokens = new Stack<>();
		tokens.push(new IdentifierToken("x"));
		tokens.push(new IdentifierToken("y"));
		tokens.push(new PerlinBWToken());
		
		ExpressionTreeNode actual = semAnalyzer.generateExpressionTree(tokens);
		
		assertEquals(new PerlinBW(new X(), new Y()), actual);
	}
	@Test
	void testParsePerlinColor() {
		Stack<Token> tokens = new Stack<>();
		tokens.push(new IdentifierToken("x"));
		tokens.push(new IdentifierToken("y"));
		tokens.push(new PerlinColorToken());
		
		ExpressionTreeNode actual = semAnalyzer.generateExpressionTree(tokens);
		
		assertEquals(new PerlinColor(new X(), new Y()), actual);
	}
	
	@Test
	void testParseRgbToYCrCb() {
		Stack<Token> tokens = new Stack<>();
		tokens.push(new IdentifierToken("y"));
		tokens.push(new RgbToYCrCbToken());
		
		ExpressionTreeNode actual = semAnalyzer.generateExpressionTree(tokens);
		
		assertEquals(new RgbToYCrCb(new Y()), actual);
	}
	
	@Test
	void testParseYCrCbToRGB() {
		Stack<Token> tokens = new Stack<>();
		tokens.push(new IdentifierToken("y"));
		tokens.push(new YCrCbToRGBToken());
		
		ExpressionTreeNode actual = semAnalyzer.generateExpressionTree(tokens);
		
		assertEquals(new YCrCbToRGB(new Y()), actual);
	}
	
	
	@Test
	void testParseImageClip() {
		Stack<Token> tokens = new Stack<>();
		tokens.push(new QuoteToken("vortex.jpg"));
		tokens.push(new IdentifierToken("x"));
		tokens.push(new IdentifierToken("x"));
		tokens.push(new PlusToken());
		tokens.push(new IdentifierToken("y"));
		tokens.push(new ImageClipToken());
		
		ExpressionTreeNode actual = semAnalyzer.generateExpressionTree(tokens);
		
		assertEquals(new ImageClip(new Image(new Quote("vortex.jpg")), new Plus(new X(), new X()), new Y()), actual);
	}
	
	@Test
	void testParseImageWrap() {
		Stack<Token> tokens = new Stack<>();
		tokens.push(new QuoteToken("vortex.jpg"));
		tokens.push(new IdentifierToken("x"));
		tokens.push(new IdentifierToken("x"));
		tokens.push(new PlusToken());
		tokens.push(new IdentifierToken("y"));
		tokens.push(new ImageWrapToken());
		
		ExpressionTreeNode actual = semAnalyzer.generateExpressionTree(tokens);
		
		assertEquals(new ImageWrap(new Image(new Quote("vortex.jpg")), new Plus(new X(), new X()), new Y()), actual);
	}
	
	@Test
	void testParseAbs() {
	    Stack<Token> tokens = new Stack<>();
	    tokens.push(new IdentifierToken("x"));
	    tokens.push(new AbsToken());

	    ExpressionTreeNode actual = semAnalyzer.generateExpressionTree(tokens);

	    assertEquals(new Abs(new X()), actual);
	    
	}
	
	@Test
	void testParseRandom() {
	    Stack<Token> tokens = new Stack<>();
	    tokens.push(new RandomToken());

	    ExpressionTreeNode actual = semAnalyzer.generateExpressionTree(tokens);

	    assertTrue(actual instanceof Random); // Check if the generated expression tree is an instance of Random class.
	}

}
