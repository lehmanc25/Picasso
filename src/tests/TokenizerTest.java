package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import picasso.parser.ParseException;
import picasso.parser.Tokenizer;
import picasso.parser.tokens.*;
import picasso.parser.tokens.chars.*;
import picasso.parser.tokens.functions.*;
import picasso.parser.tokens.operations.*;


/**
 * Tests that the tokenizer tokens as expected. 
 * @author Sara Sprenkle
 */
public class TokenizerTest {

	Tokenizer tokenizer;
	List<Token> tokens;

	@BeforeEach
	public void setUp() throws Exception {
		tokenizer = new Tokenizer();
	}
	
	/**
	 * Test that parsing an expression with a comment works
	 */
	@Test
	public void testTokenizeComment() {
		String expression = "x // this is a comment";
		List<Token> tokens = tokenizer.parseTokens(expression);
		assertEquals(new IdentifierToken("x"), tokens.get(0));
		assertEquals(1, tokens.size());
		
		expression = "// everything is a comment";
		tokens = tokenizer.parseTokens(expression);
		assertEquals(0, tokens.size());
	}

	/**
	 * Test that parsing a constant works
	 */
	@Test
	public void testTokenizeConstant() {
		String expression = ".324";
		List<Token> tokens = tokenizer.parseTokens(expression);
		assertEquals(new NumberToken(.324), tokens.get(0));

		expression = "-1";
		tokens = tokenizer.parseTokens(expression);
		assertEquals(new NumberToken(-1), tokens.get(0));

		// No problems here; problem will be in next step (Semantic Analysis)
		expression = "-1.2";
		tokens = tokenizer.parseTokens(expression);
		assertEquals(new NumberToken(-1.2), tokens.get(0));
	}

	@Test
	public void testTokenizeColor() {
		String expression = "[1, 1, 1]";
		tokens = tokenizer.parseTokens(expression);
		assertEquals(new ColorToken(1, 1, 1), tokens.get(0));

		expression = "[-1, 0, .5]";
		tokens = tokenizer.parseTokens(expression);
		assertEquals(new ColorToken(-1, 0, .5), tokens.get(0));
	}

	@Test
	public void testTokenizeInvalidColor() {
		String expression = "[1, 1.0001, 1]";

		assertThrows(ParseException.class, () -> {
			tokens = tokenizer.parseTokens(expression);
		});
	}

	@Test
	public void testTokenizeBasicFunctionExpressions() {
		String expression = "floor(x)";
		tokens = tokenizer.parseTokens(expression);
		assertEquals(new FloorToken(), tokens.get(0));
		assertEquals(new LeftParenToken(), tokens.get(1));
		assertEquals(new IdentifierToken("x"), tokens.get(2));
		assertEquals(new RightParenToken(), tokens.get(3));
		
		String expression2 = "cos(x)";
		tokens = tokenizer.parseTokens(expression2);
		assertEquals(new CosToken(), tokens.get(0));
		assertEquals(new LeftParenToken(), tokens.get(1));
		assertEquals(new IdentifierToken("x"), tokens.get(2));
		assertEquals(new RightParenToken(), tokens.get(3));
		
		String expression3 = "ceil(x)";
		tokens = tokenizer.parseTokens(expression3);
		assertEquals(new CeilToken(), tokens.get(0));
		assertEquals(new LeftParenToken(), tokens.get(1));
		assertEquals(new IdentifierToken("x"), tokens.get(2));
		assertEquals(new RightParenToken(), tokens.get(3));
		
		String expression4 = "log(x)";
		tokens = tokenizer.parseTokens(expression4);
		assertEquals(new LogToken(), tokens.get(0));
		assertEquals(new LeftParenToken(), tokens.get(1));
		assertEquals(new IdentifierToken("x"), tokens.get(2));
		assertEquals(new RightParenToken(), tokens.get(3));	
		
		String expression5 = "abs(x)";
		List<Token> newTokens = tokenizer.parseTokens(expression5);
		assertEquals(new AbsToken(), newTokens.get(0));
		assertEquals(new LeftParenToken(), newTokens.get(1));
		assertEquals(new IdentifierToken("x"), newTokens.get(2));
		assertEquals(new RightParenToken(), newTokens.get(3));	
		
		String expression6 = "sin(y)";
		tokens = tokenizer.parseTokens(expression6);
		assertEquals(new SinToken(), tokens.get(0));
		assertEquals(new LeftParenToken(), tokens.get(1));
		assertEquals(new IdentifierToken("y"), tokens.get(2));
		assertEquals(new RightParenToken(), tokens.get(3));
		
		String expression7 = "tan(y)";
		tokens = tokenizer.parseTokens(expression7);
		assertEquals(new TanToken(), tokens.get(0));
		assertEquals(new LeftParenToken(), tokens.get(1));
		assertEquals(new IdentifierToken("y"), tokens.get(2));
		assertEquals(new RightParenToken(), tokens.get(3));
		
		String expression8 = "exp(y)";
		tokens = tokenizer.parseTokens(expression8);
		assertEquals(new ExpToken(), tokens.get(0));
		assertEquals(new LeftParenToken(), tokens.get(1));
		assertEquals(new IdentifierToken("y"), tokens.get(2));
		assertEquals(new RightParenToken(), tokens.get(3));
		
		String expression9 = "atan(y)";
		tokens = tokenizer.parseTokens(expression9);
		assertEquals(new AtanToken(), tokens.get(0));
		assertEquals(new LeftParenToken(), tokens.get(1));
		assertEquals(new IdentifierToken("y"), tokens.get(2));
		assertEquals(new RightParenToken(), tokens.get(3));
		
		String expression10 = "clamp(y)";
		tokens = tokenizer.parseTokens(expression10);
		assertEquals(new ClampToken(), tokens.get(0));
		assertEquals(new LeftParenToken(), tokens.get(1));
		assertEquals(new IdentifierToken("y"), tokens.get(2));
		assertEquals(new RightParenToken(), tokens.get(3));
		
		String expression11 = "wrap(y)";
		tokens = tokenizer.parseTokens(expression11);
		assertEquals(new WrapToken(), tokens.get(0));
		assertEquals(new LeftParenToken(), tokens.get(1));
		assertEquals(new IdentifierToken("y"), tokens.get(2));
		assertEquals(new RightParenToken(), tokens.get(3));
		
		String expression12 = "rgbToYCrCb(x)";
		tokens = tokenizer.parseTokens(expression12);
		assertEquals(new RgbToYCrCbToken(), tokens.get(0));
		assertEquals(new LeftParenToken(), tokens.get(1));
		assertEquals(new IdentifierToken("x"), tokens.get(2));
		assertEquals(new RightParenToken(), tokens.get(3));
		
		String expression13 = "yCrCbToRGB(y)";
		tokens = tokenizer.parseTokens(expression13);
		assertEquals(new YCrCbToRGBToken(), tokens.get(0));
		assertEquals(new LeftParenToken(), tokens.get(1));
		assertEquals(new IdentifierToken("y"), tokens.get(2));
		assertEquals(new RightParenToken(), tokens.get(3));
		

	}
	@Test
	public void testTokenizeNoArgumentFunctionExpression() {
		String expression = "random()";
		tokens = tokenizer.parseTokens(expression);
		assertEquals(new RandomToken(), tokens.get(0));
		assertEquals(new LeftParenToken(), tokens.get(1));
		assertEquals(new RightParenToken(), tokens.get(2));
	}
	@Test
	public void testTokenizeMultiArgumentFunctionExpressions() {
		String expression = "perlinBW(x, y)";
		tokens = tokenizer.parseTokens(expression);
		assertEquals(new PerlinBWToken(), tokens.get(0));
		assertEquals(new LeftParenToken(), tokens.get(1));
		assertEquals(new IdentifierToken("x"), tokens.get(2));
		assertEquals(new CommaToken(), tokens.get(3));
		assertEquals(new IdentifierToken("y"), tokens.get(4));
		assertEquals(new RightParenToken(), tokens.get(5));
		
		expression = "perlinColor(x, y)";
		tokens = tokenizer.parseTokens(expression);
		assertEquals(new PerlinColorToken(), tokens.get(0));
		assertEquals(new LeftParenToken(), tokens.get(1));
		assertEquals(new IdentifierToken("x"), tokens.get(2));
		assertEquals(new CommaToken(), tokens.get(3));
		assertEquals(new IdentifierToken("y"), tokens.get(4));
		assertEquals(new RightParenToken(), tokens.get(5));
	}
	@Test
	public void testTokenizeNestedLogFunctionExpression() {
		String expression = "log(log(y))";
		List<Token> tokens = tokenizer.parseTokens(expression);
		assertEquals(new LogToken(), tokens.get(0));
		assertEquals(new LeftParenToken(), tokens.get(1));
		assertEquals(new LogToken(), tokens.get(2));
		assertEquals(new LeftParenToken(), tokens.get(3));
		assertEquals(new IdentifierToken("y"), tokens.get(4));
		assertEquals(new RightParenToken(), tokens.get(5));	
		assertEquals(new RightParenToken(), tokens.get(6));	

	}

	@Test
	public void testTokenizeCombinedFunctionExpression() {
		String expression = "perlinColor(floor(x), y)";
		List<Token> tokens = tokenizer.parseTokens(expression);
		
		assertEquals(new PerlinColorToken(), tokens.get(0));
		assertEquals(new LeftParenToken(), tokens.get(1));
		assertEquals(new FloorToken(), tokens.get(2));
		assertEquals(new LeftParenToken(), tokens.get(3));
		assertEquals(new IdentifierToken("x"), tokens.get(4));
		assertEquals(new RightParenToken(), tokens.get(5));
		assertEquals(new CommaToken(), tokens.get(6));
		assertEquals(new IdentifierToken("y"), tokens.get(7));
		assertEquals(new RightParenToken(), tokens.get(8));

		expression = "sin(perlinColor(x, y))";
		tokens = tokenizer.parseTokens(expression);
		assertEquals(new SinToken(), tokens.get(0));
		assertEquals(new LeftParenToken(), tokens.get(1));
		assertEquals(new PerlinColorToken(), tokens.get(2));
		assertEquals(new LeftParenToken(), tokens.get(3));
		assertEquals(new IdentifierToken("x"), tokens.get(4));
		assertEquals(new CommaToken(), tokens.get(5));
		assertEquals(new IdentifierToken("y"), tokens.get(6));
		assertEquals(new RightParenToken(), tokens.get(7));
		assertEquals(new RightParenToken(), tokens.get(8));
	}
	@Test
	public void testTokenizeArithmeticExpressions() {
		String expression1 = "x+y";
		tokens = tokenizer.parseTokens(expression1);
		assertEquals(new IdentifierToken("x"), tokens.get(0));
		assertEquals(new PlusToken(), tokens.get(1));
		assertEquals(new IdentifierToken("y"), tokens.get(2));
		
		String expression2 = "x*y";
		tokens = tokenizer.parseTokens(expression2);
		assertEquals(new IdentifierToken("x"), tokens.get(0));
		assertEquals(new MultiplyToken(), tokens.get(1));
		assertEquals(new IdentifierToken("y"), tokens.get(2));
		
		String expression3 = "x/y";
		tokens = tokenizer.parseTokens(expression3);
		assertEquals(new IdentifierToken("x"), tokens.get(0));
		assertEquals(new DivideToken(), tokens.get(1));
		assertEquals(new IdentifierToken("y"), tokens.get(2));
		
		String expression4 = "x-y";
		tokens = tokenizer.parseTokens(expression4);
		assertEquals(new IdentifierToken("x"), tokens.get(0));
		assertEquals(new MinusToken(), tokens.get(1));
		assertEquals(new IdentifierToken("y"), tokens.get(2));
		
		String expression5 = "x%y";
		tokens = tokenizer.parseTokens(expression5);
		assertEquals(new IdentifierToken("x"), tokens.get(0));
		assertEquals(new ModToken(), tokens.get(1));
		assertEquals(new IdentifierToken("y"), tokens.get(2));
		
		String expression6 = "!x";
		tokens = tokenizer.parseTokens(expression6);
		assertEquals(new NegToken(), tokens.get(0));
		assertEquals(new IdentifierToken("x"), tokens.get(1));
			
		String expression7 = "x^y";
		tokens = tokenizer.parseTokens(expression7);
		assertEquals(new IdentifierToken("x"), tokens.get(0));
		assertEquals(new ExponentiateToken(), tokens.get(1));
		assertEquals(new IdentifierToken("y"), tokens.get(2));

	}
	@Test
	public void testAssignmentExpression() {
		String expression = "a=x+y";
		tokens = tokenizer.parseTokens(expression);
		assertEquals(new IdentifierToken("a"), tokens.get(0));
		assertEquals(new AssignmentToken(), tokens.get(1));
		assertEquals(new IdentifierToken("x"), tokens.get(2));
		assertEquals(new PlusToken(), tokens.get(3));
		assertEquals(new IdentifierToken("y"), tokens.get(4));
	}
	
	@Test
	public void testTokenizeImageManipulationFunctions() {
		String expression1 = "imageClip(\"vortex.jpg\", x + x, y)";
		tokens = tokenizer.parseTokens(expression1);
		assertEquals(new ImageClipToken(), tokens.get(0));
		assertEquals(new LeftParenToken(), tokens.get(1));
		assertEquals(new QuoteToken("vortex.jpg"), tokens.get(2));
		assertEquals(new CommaToken(), tokens.get(3));
		assertEquals(new IdentifierToken("x"), tokens.get(4));
		assertEquals(new PlusToken(), tokens.get(5));
		assertEquals(new IdentifierToken("x"), tokens.get(6));
		assertEquals(new CommaToken(), tokens.get(7));
		assertEquals(new IdentifierToken("y"), tokens.get(8));
		assertEquals(new RightParenToken(), tokens.get(9));
		
		String expression2 = "imageWrap(\"vortex.jpg\", x + x, y)";
		tokens = tokenizer.parseTokens(expression2);
		assertEquals(new ImageWrapToken(), tokens.get(0));
		assertEquals(new LeftParenToken(), tokens.get(1));
		assertEquals(new QuoteToken("vortex.jpg"), tokens.get(2));
		assertEquals(new CommaToken(), tokens.get(3));
		assertEquals(new IdentifierToken("x"), tokens.get(4));
		assertEquals(new PlusToken(), tokens.get(5));
		assertEquals(new IdentifierToken("x"), tokens.get(6));
		assertEquals(new CommaToken(), tokens.get(7));
		assertEquals(new IdentifierToken("y"), tokens.get(8));
		assertEquals(new RightParenToken(), tokens.get(9));
		
	}
	
	
	
}
