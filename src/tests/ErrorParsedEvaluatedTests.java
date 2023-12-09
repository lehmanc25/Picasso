package tests;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;

import picasso.parser.ParseException;
import picasso.parser.ExpressionTreeGenerator;

/**
 * Tests of incorrect parsing of strings by the ExpressionTreeGenerator
 * 
 * @author Sara Sprenkle
 * 
 */
public class ErrorParsedEvaluatedTests {

	private ExpressionTreeGenerator parser;

	@BeforeEach
	public void setUp() throws Exception {
		parser = new ExpressionTreeGenerator();
	}
	// Haven't implemented - yet. 
	//@Test
	//public void errorConstantExpressionTest() {
		//assertThrows(ParseException.class, () -> {
			//parser.makeExpression("- 7");
		//});
	//}

	@Test
	public void errorUnrecognizedInputTest() {
		assertThrows(ParseException.class, () -> {
			parser.makeExpression("a");
		});
	}

	@Test
	public void errorTooFewArgsTest() {
		assertThrows(ParseException.class, () -> {
			parser.makeExpression("( 7 + )");
		});
		assertThrows(ParseException.class, () -> {
			parser.makeExpression("( * 5");
		});
	}

	@Test
	public void errorExtraOperandTest() {
		assertThrows(ParseException.class, () -> {
			parser.makeExpression("( 7 + 3 5)");
		});
	}

	@Test
	public void errorExtraOperandTest2() {
		assertThrows(ParseException.class, () -> {
			parser.makeExpression("( 7 * 3 ) 5");
		});
	}

	@Test
	public void errorMissingRightParenTest() {
		assertThrows(ParseException.class, () -> {
			parser.makeExpression("( 7 * 3");
		});
	}

	@Test
	public void errorMissingLeftParenTest() {
		assertThrows(ParseException.class, () -> {
			parser.makeExpression("7 * 3 )");
		});
	}
	@Test
	public void errorInvalidAssignmentTest() {
		assertThrows(ParseException.class, () -> {
			parser.makeExpression("a + x = y");
			parser.makeExpression("sin(y) = x");
		});
	}
	@Test
	public void errorMissingFunctionParenTest() {
		assertThrows(ParseException.class, () -> {
			parser.makeExpression("floor(x");
		});
		
		assertThrows(ParseException.class, () -> {
			parser.makeExpression("cosx)");
		});
		
		assertThrows(ParseException.class, () -> {
			parser.makeExpression("abs(x");
		});
		
		assertThrows(ParseException.class, () -> {
			parser.makeExpression("ceilx)");
		});
		
		assertThrows(ParseException.class, () -> {
			parser.makeExpression("logx)");
		});
		
		assertThrows(ParseException.class, () -> {
			parser.makeExpression("sin(x");
		});
		
		assertThrows(ParseException.class, () -> {
			parser.makeExpression("tanx)");
		});
		
		assertThrows(ParseException.class, () -> {
			parser.makeExpression("atan(x");
		});
		
		assertThrows(ParseException.class, () -> {
			parser.makeExpression("expx)");
		});
	}

	@Test
	public void errorMissingLogFunctionRightParenTest() {
		assertThrows(ParseException.class, () -> {
			parser.makeExpression("log(x");
		});
	}
	@Test
	public void errorMissingLogFunctionLeftParenTest() {
		assertThrows(ParseException.class, () -> {
			parser.makeExpression("logx)");
		});
	}
}
