package tests;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.StringReader;
import java.io.StreamTokenizer;
import picasso.parser.tokens.TokenFactory;
import picasso.parser.tokens.functions.FunctionToken;
import picasso.parser.tokens.functions.ImageClipToken;
import picasso.parser.tokens.Token;

public class ImageClipTokenTest {

    @Test
    public void testTokenizationOfImageClip() {
        String input = "imageClip(\"vortex.jpg\", 0.5, -0.5)";
        StreamTokenizer tokenizer = new StreamTokenizer(new StringReader(input));

        // Configure the tokenizer if needed

        try {
            tokenizer.nextToken(); // Process the first token
            Token token = TokenFactory.parse(tokenizer);

            assertNotNull(token, "Token should not be null");
            assertTrue(token instanceof ImageClipToken, "Token should be an instance of ImageClipToken");

            // Additional checks can be added here if necessary

        } catch (Exception e) {
            fail("Exception during tokenization: " + e.getMessage());
        }
    }

    // Additional test cases for different scenarios, edge cases, etc.
    // ...
}
