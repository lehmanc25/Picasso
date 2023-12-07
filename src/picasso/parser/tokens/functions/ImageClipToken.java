package picasso.parser.tokens.functions;

/**
 * Represents the ImageClip function token.
 * This class extends FunctionToken and represents the ImageClip function in the Picasso language.
 */
public class ImageClipToken extends FunctionToken {
	
	/**
	 * Constructs an ImageClipToken representing the ImageClip function.
	 */
	public ImageClipToken() {
		super("ImageClip Function Token");
	}
	/**
	 * @see picasso.parser.tokens.Token#isConstant()
	 */
	@Override
	public boolean isConstant() {
		return false;
	}

	/**
	 * @see picasso.parser.tokens.Token#isFunction()
	 */
	@Override
	public boolean isFunction() {
		return true;
	}
}
