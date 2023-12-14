package picasso.parser.tokens;

import java.util.Objects;

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
	 * @return the hash value of this quote token
	 */
	@Override
	public int hashCode() {
		return Objects.hash(myValue);
	}


	/**
	 * @param obj
	 * @return true if the 2 quote tokens are equal, and false otherwise
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof QuoteToken)) {
			return false;
		}
		QuoteToken other = (QuoteToken) obj;
		return Objects.equals(myValue, other.myValue);
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
