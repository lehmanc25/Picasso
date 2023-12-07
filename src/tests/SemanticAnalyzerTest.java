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
import picasso.parser.tokens.functions.CeilToken;
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
	void testParseAddition() {

		Stack<Token> tokens = new Stack<>();
		tokens.push(new IdentifierToken("x"));
		tokens.push(new IdentifierToken("y"));
		tokens.push(new PlusToken());

		ExpressionTreeNode actual = semAnalyzer.generateExpressionTree(tokens);

		assertEquals(new Addition(new X(), new Y()), actual);
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
		
		assertEquals(new Assignment(new Variable("a"), new Addition(new X(), new Y())), actual);
	}
	void testParseCosine() {
		
		Stack<Token> tokens = new Stack<>();
		tokens.push(new IdentifierToken("x"));
		tokens.push(new CosToken());
		
		ExpressionTreeNode actual = semAnalyzer.generateExpressionTree(tokens);
		
		assertEquals(new Cosine(new X()), actual);
		
	}

	@Test
	void testParseCeil() {
		
		Stack<Token> tokens = new Stack<>();
		tokens.push(new IdentifierToken("x"));
		tokens.push(new CeilToken());
		
		ExpressionTreeNode actual = semAnalyzer.generateExpressionTree(tokens);
		
		assertEquals(new Ceil(new X()), actual);
		
	}
}
