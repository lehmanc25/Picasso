package picasso.parser.tokens.functions;

/**
 * Token representing the 'imageClip' function in Picasso language.
 * 
 * @author Ford Scott
 */
public class ImageClipToken extends FunctionToken {

    /**
     * Constructs an ImageClipToken.
     */
    public ImageClipToken() {
        super("Image Clip Token");
    }

    @Override
    public boolean isFunction() {
        return true;
    }
}
