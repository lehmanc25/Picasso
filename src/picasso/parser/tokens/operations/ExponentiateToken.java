/**
 * 
 */
package picasso.parser.tokens.operations;

import picasso.parser.language.CharConstants;
import picasso.parser.tokens.chars.CharToken;

/**
 * Represents the exponentiate sign token
 * @author Desire Asinya
 */
public class ExponentiateToken extends CharToken implements OperationInterface {

	public ExponentiateToken() {
		super(CharConstants.CARET);
	}

}
