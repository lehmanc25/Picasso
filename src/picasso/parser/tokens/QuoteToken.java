package picasso.parser.tokens;

/**
 * Represents a quote token.
 * This class extends Token and represents a string enclosed in quotation marks.
 * 
 * @author Ford Scott
 */
public class QuoteToken extends Token {
	
	private final String myValue;
	
	/**
	 * Constructs a token representing a quoted string.
	 * 
	 * @param value The value of this quote token.
	 */
	public QuoteToken(String value) {
		super("Quote Token");
		myValue = value;
	}
	
	/**
	 * Gets the value of this quote token.
	 * 
	 * @return The value of this token as a string.
	 */
	public String value() {
		return myValue;
	}
	
	/**
	 * Returns a string representation of this quote token.
	 * 
	 * @return A string representation in the format "Token Type: Value".
	 */
	public String toString() {
		return super.toString() + ": " + myValue;
	}
	
	/**
	 * Checks if this token is a constant.
	 * 
	 * @return False, as QuoteToken is not considered a constant.
	 */
	public boolean isConstant() {
		return false;
	}
	
	/**
	 * Checks if this token is a function.
	 * 
	 * @return False, as QuoteToken is not considered a function.
	 */
	public boolean isFunction() {
		return false;
	}
}
