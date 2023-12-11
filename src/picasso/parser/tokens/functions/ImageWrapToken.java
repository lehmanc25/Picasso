package picasso.parser.tokens.functions;

/**
 * Token representing the ImageWrap function in the Picasso language.
 * 
 * @author Ford Scott
 */
public class ImageWrapToken extends FunctionToken {

    /**
     * Constructs an ImageWrapToken.
     */
    public ImageWrapToken() {
        super("ImageWrap Function Token");
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
