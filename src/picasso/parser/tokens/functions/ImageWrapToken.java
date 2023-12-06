package picasso.parser.tokens.functions;

/**
 * Token representing the 'imageWrap' function in the Picasso language.
 * 
 * @author Ford Scott
 */
public class ImageWrapToken extends FunctionToken {

    /**
     * Constructs an ImageWrapToken.
     */
    public ImageWrapToken() {
        super("Image Wrap Token");
    }

    @Override
    public boolean isFunction() {
        return true;
    }
}