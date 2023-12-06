package picasso.parser.tokens;

/**
 * Represents an image token in the Picasso language.
 * 
 * @author Ford Scott
 */
public class ImageToken extends Token {

    private final String filename;

    public ImageToken(String filename) {
        super("Image Token");
        this.filename = filename;
    }

    /**
     * Gets the filename of the image.
     *
     * @return the filename of the image
     */
    public String getFilename() {
        return filename;
    }

    @Override
    public boolean isConstant() {
        return false;
    }

    @Override
    public boolean isFunction() {
        return false;
    }
}
